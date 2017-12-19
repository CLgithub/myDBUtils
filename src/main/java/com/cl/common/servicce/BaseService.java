package com.cl.common.servicce;

import com.cl.common.utils.PageBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by chenlei on 2016/12/20.
 */
public interface BaseService {

    /**
     * 得到pagebean
     * @param sql sql语句
     * @param mapORarray 封装为map或array类型
     * @param currentPage 当前页
     * @param pageSize 每页大小
     * @param objects sql参数
     * @return pageBean
     */
    PageBean getPageBean(QueryRunner queryRunner, String sql, String mapORarray, Integer currentPage, Integer pageSize, Object... objects) throws SQLException;

    /**
     * 根据sql查询总记录数
     * @param sql sql语句
     * @param objects sql不定参数
     * @return 总记录数
     */
    Integer getTotlaBySql(QueryRunner queryRunner, String sql, Object... objects) throws SQLException;

    /**
     * 根据sql查询listMap
     * @param sql sql语句
     * @param objects sql不定参数
     * @return listMap
     */
    List<Map<String,Object>> selectListMapBySql(QueryRunner queryRunner, String sql, Object... objects) throws SQLException;

    /**
     *
     * @param sql
     * @param currentPage
     * @param pageSize
     * @return
     */
    String getPageBeanSqlOracle(String sql, int currentPage, int pageSize);



//    /**
//     * 根据sql查询，放回对应的结果类型
//     * resultSetHandler实现类
//     * ArrayHandler, 将结果集中第一条记录封装到Object[],数组中的每一个元素就是记录中的字段值。
//     * ArrayListHandler, 将结果集中每一条记录封装到Object[],数组中的每一个元素就是记录中的字段值。在将这些数组装入到List集合。
//     * BeanHandler, 将结果集中第一条记录封装到一个javaBean中。
//     * BeanListHandler, 将结果集中每一条记录封装到javaBean中，在将javaBean封装到List集合.
//     * ColumnListHandler, 将结果集中指定列的值封装到List集合.
//     * MapHandler, 将结果集中第一条记录封装到Map集合中，集合的 key就是字段名称，value就是字段值
//     * MapListHandler, 将结果集中每一条记录封装到Map集合中，集合的 key就是字段名称，value就是字段值，在将这些Map封装到List集合
//     * KeyedHandler,在使用指定的列的值做为一个Map集合的key,值为每一条记录的Map集合封装。
//     * ScalarHandler 进行单值查询 select count(*) from account;
//     * @param sql
//     * @param resultSetHandler
//     * @param objects
//     * @return
//     */
//    Object selectObject(QueryRunner queryRunner, String sql, ResultSetHandler resultSetHandler, Object... objects) throws SQLException;

    /**
     * 执行sql
     * @param connection
     * @param sql
     * @param objects
     * @return
     */
    int executeSql(Connection connection, String sql, Object... objects) throws SQLException;


    /**
     * 根据sql查询数据，将数据存储到csvFile文件中
     * @param sql 查询数据sql
     * @param csvFile csv文件
     * @param separator 分隔符
     * @param head 是否带头信息
     * @param batchSize 批次存入文件的数据量条数
     */
    void getFileBySql(String sql, File csvFile, String separator, boolean head, int batchSize);
}
