package com.threadx.metrics.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 基类
 *
 * @author huangfukexing
 * @date 2023/4/21 15:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 7271668085047470531L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 数据创建时间
     */
    private Long createTime;


    public void init(){
        this.createTime = System.currentTimeMillis();
    }

}
