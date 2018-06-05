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

    static HikariDataSource hikariDataSource1=null;
    static QueryRunner queryRunner1=null;
    static HikariDataSource hikariDataSource2=null;
    static QueryRunner queryRunner2=null;
    static HikariDataSource hikariDataSource3=null;
    static QueryRunner queryRunner3=null;

    static {
        Properties properties = new Properties();
        try{
            properties.load(JDBCUtilDbcp.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            hikariDataSource1=new HikariDataSource();
            hikariDataSource1.setDriverClassName((String) properties.get("driverClassName1"));
            hikariDataSource1.setJdbcUrl((String) properties.get("url1"));
            hikariDataSource1.setUsername((String) properties.get("username1"));
            hikariDataSource1.setPassword((String) properties.get("password1"));
//            hikariDataSource.setMaximumPoolSize(10);
            hikariDataSource1.setConnectionTestQuery((String) properties.get("conTestQuery1"));
            queryRunner1 = new QueryRunner(hikariDataSource1);

            hikariDataSource2=new HikariDataSource();
            hikariDataSource2.setDriverClassName((String) properties.get("driverClassName2"));
            hikariDataSource2.setJdbcUrl((String) properties.get("url2"));
            hikariDataSource2.setUsername((String) properties.get("username2"));
            hikariDataSource2.setPassword((String) properties.get("password2"));
//            hikariDataSource.setMaximumPoolSize(10);
            hikariDataSource2.setConnectionTestQuery((String) properties.get("conTestQuery2"));
            queryRunner2=new QueryRunner(hikariDataSource2);

            hikariDataSource3=new HikariDataSource();
            hikariDataSource3.setDriverClassName((String) properties.get("driverClassName3"));
            hikariDataSource3.setJdbcUrl((String) properties.get("url3"));
            hikariDataSource3.setUsername((String) properties.get("username3"));
            hikariDataSource3.setPassword((String) properties.get("password3"));
//            hikariDataSource.setMaximumPoolSize(10);
            hikariDataSource3.setConnectionTestQuery((String) properties.get("conTestQuery3"));
            queryRunner3=new QueryRunner(hikariDataSource3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static QueryRunner getQueryRunner1(){
        return queryRunner1;
    }
    public static Connection getConnection1() throws SQLException {
        return hikariDataSource1.getConnection();
    }
    public static QueryRunner getQueryRunner2(){
        return queryRunner2;
    }
    public static Connection getConnection2() throws SQLException {
        return hikariDataSource2.getConnection();
    }
    public static QueryRunner getQueryRunner3(){
        return queryRunner3;
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

