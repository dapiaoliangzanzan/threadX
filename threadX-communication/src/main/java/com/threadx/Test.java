package com.threadx;

import cn.hutool.json.JSONUtil;
import lombok.Data;

/**
 * *************************************************<br/>
 * <br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/4/6 21:42
 */
@Data
public class Test {

    private final String name;

    public Test(String name) {
        this.name = name;
    }




    public static void main(String[] args) {
        Test test = new Test("huangfu");
        System.out.println(JSONUtil.toJsonStr(test));
    }
}
