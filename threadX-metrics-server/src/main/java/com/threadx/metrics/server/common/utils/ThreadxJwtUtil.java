package com.threadx.metrics.server.common.utils;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.threadx.metrics.server.common.code.TokenCheckExceptionCode;
import com.threadx.metrics.server.common.exceptions.TokenCheckException;
import com.threadx.metrics.server.vo.UserVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * token工具类
 *
 * @author huangfukexing
 * @date 2023/6/1 08:30
 */
@Slf4j
public class ThreadxJwtUtil {
    private static final String PRIVATE_KEY;
    /**
     * token过期时间
     */
    private static final Long EXPIRATION_TIME = TimeUnit.DAYS.toMillis(1);
    /**
     * token续约阈值  10分钟
     */
    private static final Long RENEWAL_THRESHOLD = TimeUnit.MINUTES.toMillis(10);

    static {
        PRIVATE_KEY = IdUtil.fastSimpleUUID() + IdUtil.fastSimpleUUID();
    }


    /**
     * token生成工具类
     *
     * @param userVo 用户信息
     * @return 生成的token
     */
    public static String generateToken(UserVo userVo) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userVo.getUserName());
        claims.put("email", userVo.getEmail());
        claims.put("id", userVo.getId());
        claims.put("nickName", userVo.getNickName());
        claims.put("createTime", userVo.getCreateTime());


        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(generateKey())
                .compact();
    }

    private static SecretKey generateKey() {
        byte[] encodedKey = Base64.decodeBase64(ThreadxJwtUtil.PRIVATE_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "HmacSHA256");
    }


    /**
     * 验证 解析token
     *
     * @param token token信息
     * @return 返回
     */
    public static Map<String, Object> validateParseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(generateKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("token验证失败！");
            return null;
        }
    }


    /**
     * token续约
     *
     * @param token      旧的token信息
     * @param renewToken 续约的自定义操作
     */
    public static void renewToken(String token, RenewToken renewToken) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(generateKey())
                    .build()
                    .parseClaimsJws(token);

            // 更新Token的过期时间
            Claims updatedClaims = claims.getBody();
            //获取过期时间
            Date expiration = updatedClaims.getExpiration();
            //判断是否需要续约
            if (System.currentTimeMillis() - expiration.getTime() <= RENEWAL_THRESHOLD) {
                //重设过期时间
                updatedClaims.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME));
                //生成新的token
                String newToken = Jwts.builder()
                        .setClaims(updatedClaims)
                        .signWith(generateKey())
                        .compact();
                if (renewToken != null) {
                    //回调自定义的续约方法
                    renewToken.renewToken(newToken);
                }
            }


        } catch (Exception e) {
            throw new TokenCheckException(TokenCheckExceptionCode.TOKEN_CHECK_ERROR);
        }
    }

    @FunctionalInterface
    interface RenewToken {
        /**
         * token续约
         *
         * @param newToken 新的token
         */
        void renewToken(String newToken);
    }


    public static void main(String[] args) {
        String hashedPassword = BCrypt.hashpw("123456");
        System.out.println(hashedPassword);
        System.out.println(BCrypt.checkpw("123456", hashedPassword));
    }
}
