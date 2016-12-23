package com.cl.common.dao;

import com.cl.common.utils.JDBCUtilDbcp;
import com.cl.common.utils.JDBCUtilHikariCP;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by chenlei on 2016/12/20.
 */
public class BaseDaoImpl implements BaseDao {

    /**
     * 执行原生sql
     * @param sql sql语句
     * @param objects 不定参数
     * @return
     */
    public int executeSql(String sql, Object... objects) throws SQLException {
        Connection connect= JDBCUtilHikariCP.getConnection();
//        Connection connect = JDBCUtilDbcp.getConnection();
        PreparedStatement pStatement= connect.prepareStatement(sql);
        for(int i=0;i<objects.length;i++){
            pStatement.setObject(i+1,objects[i]);
        }
        int i = pStatement.executeUpdate();
        JDBCUtilDbcp.closeConnect(connect);
        return i;
    }

    /**
     * 根据sql查询，返回特定的结果集
     * @param sql sql语句
     * @param resultSetHandler 结果集返回类型
     * @param objects 不定参数
     * @return 查询结果
     * @throws SQLException
     */
    public Object selectObject(String sql, ResultSetHandler resultSetHandler, Object... objects) throws SQLException {
        QueryRunner queryRunner = JDBCUtilHikariCP.getQueryRunner();
//        QueryRunner queryRunner = JDBCUtilDbcp.getQueryRunner();
        Object query = queryRunner.query(sql, resultSetHandler, objects);
        return query;
    }
}
