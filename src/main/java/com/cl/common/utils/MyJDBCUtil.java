package com.cl.common.utils;

import com.zaxxer.hikari.HikariConfig;

/**
 * @Author l
 * @Date 2021/3/16 17:38
 */
public class MyJDBCUtil {

    public static void main(String[] args) {
        HikariConfig config = new HikariConfig(".jdbc.properties");
        System.out.println(config);
    }
}
