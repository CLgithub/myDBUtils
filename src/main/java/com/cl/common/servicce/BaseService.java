package com.cl.common.servicce;

import com.cl.common.utils.PageBean;

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
    PageBean getPageBean(String sql, String mapORarray, Integer currentPage, Integer pageSize, Object... objects) throws SQLException;

    /**
     * 根据sql查询总记录数
     * @param sql sql语句
     * @param objects sql不定参数
     * @return 总记录数
     */
    Integer getTotlaBySql(String sql, Object... objects) throws SQLException;

    /**
     * 根据sql查询listMap
     * @param sql sql语句
     * @param objects sql不定参数
     * @return listMap
     */
    List<Map<String,Object>> selectListMapBySql(String sql, Object... objects) throws SQLException;

    /**
     *
     * @param sql
     * @param currentPage
     * @param pageSize
     * @return
     */
    String getPageBeanSqlOracle(String sql, int currentPage, int pageSize);
}
