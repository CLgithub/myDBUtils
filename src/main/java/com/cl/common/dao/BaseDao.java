package com.cl.common.dao;

import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.SQLException;

/**
 * Created by chenlei on 2016/12/20.
 */
public interface BaseDao {

    /**
     * 执行sql
     * @param sql
     * @param objects
     * @return
     */
    int executeSql(String sql, Object... objects) throws SQLException;

    /**
     * 根据sql查询，放回对应的结果类型
     * resultSetHandler实现类
     * ArrayHandler, 将结果集中第一条记录封装到Object[],数组中的每一个元素就是记录中的字段值。
     * ArrayListHandler, 将结果集中每一条记录封装到Object[],数组中的每一个元素就是记录中的字段值。在将这些数组装入到List集合。
     * BeanHandler, 将结果集中第一条记录封装到一个javaBean中。
     * BeanListHandler, 将结果集中每一条记录封装到javaBean中，在将javaBean封装到List集合.
     * ColumnListHandler, 将结果集中指定列的值封装到List集合.
     * MapHandler, 将结果集中第一条记录封装到Map集合中，集合的 key就是字段名称，value就是字段值
     * MapListHandler, 将结果集中每一条记录封装到Map集合中，集合的 key就是字段名称，value就是字段值，在将这些Map封装到List集合
     * KeyedHandler,在使用指定的列的值做为一个Map集合的key,值为每一条记录的Map集合封装。
     * ScalarHandler 进行单值查询 select count(*) from account;
     * @param sql
     * @param resultSetHandler
     * @param objects
     * @return
     */
    Object selectObject(String sql, ResultSetHandler resultSetHandler, Object... objects) throws SQLException;
}
