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

    static {
        Properties properties = new Properties();
        try{
            properties.load(JDBCUtilDbcp.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            hikariDataSource=new HikariDataSource();
            hikariDataSource.setDriverClassName((String) properties.get("driverClassName"));
            hikariDataSource.setJdbcUrl((String) properties.get("url"));
            hikariDataSource.setUsername((String) properties.get("username"));
            hikariDataSource.setPassword((String) properties.get("password"));
//            hikariDataSource.setMaximumPoolSize(10);
            hikariDataSource.setConnectionTestQuery((String) properties.get("conTestQuery"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static QueryRunner getQueryRunner(){
        QueryRunner queryRunner = new QueryRunner(hikariDataSource);
        return queryRunner;
    }

    public static Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }

    public static void closeConnect(Connection connection) throws SQLException {
        if(connection!=null){
           connection.close();
        }
    }

}

