package com.cl.common.utils;

//import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by chenlei on 2016/12/21.
 */
public class JDBCUtilHikariCP {

    static HikariDataSource hikariDataSource = null;
    static HikariDataSource hikariDataSource2 = null;
    static HikariDataSource hikariDataSource3 = null;

//    static DruidDataSource druidDataSource = null;
//    static DruidDataSource druidDataSource2 = null;
//    static DruidDataSource druidDataSource3 = null;


    static {
        Properties properties = new Properties();
        try {
            properties.load(JDBCUtilHikariCP.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            hikariDataSource = new HikariDataSource();
            hikariDataSource.setDriverClassName(properties.getProperty("driverClassName"));
            hikariDataSource.setJdbcUrl(properties.getProperty("url"));
            hikariDataSource.setUsername(properties.getProperty("username"));
            hikariDataSource.setPassword(properties.getProperty("password"));
            hikariDataSource.setConnectionTestQuery(properties.getProperty("conTestQuery"));

            hikariDataSource.setMaximumPoolSize(Integer.parseInt(properties.getProperty("maxMumPoolSize") == null ? "10" : properties.getProperty("maxMumPoolSize")));
            hikariDataSource.setConnectionTimeout(Long.parseLong(properties.getProperty("countTimeOut") == null ? "60000" : properties.getProperty("countTimeOut")));

            hikariDataSource2 = new HikariDataSource();
            hikariDataSource2.setDriverClassName(properties.getProperty("driverClassName2") == null ? properties.getProperty("driverClassName") : properties.getProperty("driverClassName2"));
            hikariDataSource2.setJdbcUrl(properties.getProperty("url2") == null ? properties.getProperty("url") : properties.getProperty("url2"));
            hikariDataSource2.setUsername(properties.getProperty("username2") == null ? properties.getProperty("username") : properties.getProperty("username2"));
            hikariDataSource2.setPassword(properties.getProperty("password2") == null ? properties.getProperty("password") : properties.getProperty("password2"));
            hikariDataSource2.setConnectionTestQuery(properties.getProperty("conTestQuery2") == null ? properties.getProperty("conTestQuery") : properties.getProperty("conTestQuery2"));
            hikariDataSource2.setMaximumPoolSize(Integer.parseInt(properties.getProperty("maxMumPoolSize2") == null ? "10" : properties.getProperty("maxMumPoolSize2")));
            hikariDataSource2.setConnectionTimeout(Long.parseLong(properties.getProperty("countTimeOut2") == null ? "60000" : properties.getProperty("countTimeOut2")));

            hikariDataSource3 = new HikariDataSource();
            hikariDataSource3.setDriverClassName(properties.getProperty("driverClassName3") == null ? properties.getProperty("driverClassName") : properties.getProperty("driverClassName3"));
            hikariDataSource3.setJdbcUrl(properties.getProperty("url3") == null ? properties.getProperty("url") : properties.getProperty("url3"));
            hikariDataSource3.setUsername(properties.getProperty("username3") == null ? properties.getProperty("username") : properties.getProperty("username3"));
            hikariDataSource3.setPassword(properties.getProperty("password3") == null ? properties.getProperty("password") : properties.getProperty("password3"));
            hikariDataSource3.setConnectionTestQuery(properties.getProperty("conTestQuery3") == null ? properties.getProperty("conTestQuery") : properties.getProperty("conTestQuery3"));
            hikariDataSource3.setMaximumPoolSize(Integer.parseInt(properties.getProperty("maxMumPoolSize3") == null ? "10" : properties.getProperty("maxMumPoolSize3")));
            hikariDataSource3.setConnectionTimeout(Long.parseLong(properties.getProperty("countTimeOut3") == null ? "60000" : properties.getProperty("countTimeOut3")));

//            druidDataSource=new DruidDataSource();
//            druidDataSource.setDriverClassName(properties.getProperty("driverClassName"));
//            druidDataSource.setUrl(properties.getProperty("url"));
//            druidDataSource.setUsername(properties.getProperty("username"));
//            druidDataSource.setPassword(properties.getProperty("password"));
//            druidDataSource.setQueryTimeout(Integer.parseInt(properties.getProperty("countTimeOut") == null ? "60000" : properties.getProperty("countTimeOut"))/1000);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HikariDataSource getHikariDataSource() {
        return hikariDataSource;
    }

    public static HikariDataSource getHikariDataSource2() {
        return hikariDataSource2;
    }

    public static HikariDataSource getHikariDataSource3() {
        return hikariDataSource3;
    }

    public static QueryRunner getQueryRunner() {
        return new QueryRunner(hikariDataSource);
    }

    public static QueryRunner getQueryRunner2() {
        return new QueryRunner(hikariDataSource2);
    }

    public static QueryRunner getQueryRunner3() {
        return new QueryRunner(hikariDataSource3);
    }


    public static Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }

    public static Connection getConnection2() throws SQLException {
        return hikariDataSource2.getConnection();
    }

    public static Connection getConnection3() throws SQLException {
        return hikariDataSource3.getConnection();
    }



}

