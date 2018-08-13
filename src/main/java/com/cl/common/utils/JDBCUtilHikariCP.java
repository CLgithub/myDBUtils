package com.cl.common.utils;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by chenlei on 2016/12/21.
 */
public class JDBCUtilHikariCP {

    static HikariDataSource hikariDataSource=null;
    static QueryRunner queryRunner=null;
    static HikariDataSource hikariDataSource2=null;
    static QueryRunner queryRunner2=null;
    static HikariDataSource hikariDataSource3=null;
    static QueryRunner queryRunner3=null;

    static {
        Properties properties = new Properties();
        try{
            properties.load(JDBCUtilDbcp.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            hikariDataSource=new HikariDataSource();
            hikariDataSource.setDriverClassName(properties.getProperty("driverClassName"));
            hikariDataSource.setJdbcUrl(properties.getProperty("url"));
            hikariDataSource.setUsername(properties.getProperty("username"));
            hikariDataSource.setPassword(properties.getProperty("password"));
//            hikariDataSource.setMaximumPoolSize(10);
            hikariDataSource.setConnectionTestQuery(properties.getProperty("conTestQuery"));
            queryRunner = new QueryRunner(hikariDataSource);

            hikariDataSource2=new HikariDataSource();
            hikariDataSource2.setDriverClassName(properties.getProperty("driverClassName2"));
            hikariDataSource2.setJdbcUrl(properties.getProperty("url2"));
            hikariDataSource2.setUsername(properties.getProperty("username2"));
            hikariDataSource2.setPassword(properties.getProperty("password2"));
//            hikariDataSource.setMaximumPoolSize(10);
            hikariDataSource2.setConnectionTestQuery(properties.getProperty("conTestQuery2"));
            queryRunner2=new QueryRunner(hikariDataSource2);

            hikariDataSource3=new HikariDataSource();
            hikariDataSource3.setDriverClassName(properties.getProperty("driverClassName3"));
            hikariDataSource3.setJdbcUrl(properties.getProperty("url3"));
            hikariDataSource3.setUsername(properties.getProperty("username3"));
            hikariDataSource3.setPassword(properties.getProperty("password3"));
//            hikariDataSource.setMaximumPoolSize(10);
            hikariDataSource3.setConnectionTestQuery(properties.getProperty("conTestQuery3"));

            queryRunner3=new QueryRunner(hikariDataSource3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HikariDataSource getHikariDataSource(){
        return hikariDataSource;
    }

    public static HikariDataSource getHikariDataSource2(){
        return hikariDataSource2;
    }

    public static HikariDataSource getHikariDataSource3(){
        return hikariDataSource3;
    }

    public static QueryRunner getQueryRunner(){
        return queryRunner;
    }
    public static QueryRunner getQueryRunner2(){
        return queryRunner2;
    }
    public static QueryRunner getQueryRunner3(){
        return queryRunner3;
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

    public static void closeConnect(Connection connection) throws SQLException {
        if(connection!=null){
           connection.close();
        }
    }

}

