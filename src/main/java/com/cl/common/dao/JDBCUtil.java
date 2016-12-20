package com.cl.common.dao;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by chenlei on 2016/12/20.
 */
public class JDBCUtil {
    private static DataSource dataSource=null;

    static {
        Properties properties = new Properties();
        try{
            properties.load(JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            dataSource= BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnect() {
        try {
            Connection connection = dataSource.getConnection();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConn(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static QueryRunner getQueryRunner(){
        QueryRunner queryRunner = new QueryRunner(dataSource);
        return queryRunner;
    }
}
