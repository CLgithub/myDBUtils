package com.cl.common.utils;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by chenlei on 2016/12/20.
 */
public class JDBCUtilDbcp {
    private static DataSource dataSource=null;

    static {
        Properties properties = new Properties();
        try{
            properties.load(JDBCUtilDbcp.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            dataSource= BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static QueryRunner getQueryRunner(){
        QueryRunner queryRunner = new QueryRunner(dataSource);
        return queryRunner;
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }

    public static void closeConnect(Connection connection) throws SQLException {
        if(connection!=null){
            connection.close();
        }
    }
}
