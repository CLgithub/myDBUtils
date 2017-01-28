package com.cl.common.servicce;

import com.cl.common.utils.PageBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenlei on 2016/12/20.
 */
public class BaseServiceImpl implements BaseService {

    public PageBean getPageBean(QueryRunner queryRunner, String sql, String mapORarray, Integer currentPage, Integer pageSize, Object... objects) throws SQLException {
        Integer total = this.getTotlaBySql(queryRunner, sql, objects);// 得到总的记录长度
        String pageSql = this.getPageBeanSqlOracle(sql, currentPage, pageSize);
        List<?> list=new ArrayList<Object>();
        if("map".equals(mapORarray.toLowerCase())){
            list = this.selectListMapBySql(queryRunner, pageSql, objects);
        }else if("array".equals(mapORarray.toLowerCase())){
            list=this.selectListArrayBySql(queryRunner, pageSql,objects);
        }
        PageBean pageBean = new PageBean();
        pageBean.setTotal(total);
        pageBean.setList(list);
        pageBean.setTotalPage(PageBean.countTatalPage(pageSize, total));
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        return pageBean;
    }

    public Integer getTotlaBySql(QueryRunner queryRunner, String sql, Object... objects) throws SQLException {
        int index = sql.toLowerCase().indexOf("from");
        String subString = sql.substring(index);
        String newSql = "select count(1) " + subString;
        Object o = queryRunner.query(newSql, new ScalarHandler(), objects);
        return Integer.parseInt(o.toString());
    }

    public List<Map<String, Object>> selectListMapBySql(QueryRunner queryRunner, String sql, Object... objects) throws SQLException {
        Object o = queryRunner.query(sql, new MapListHandler(), objects);
        List<Map<String, Object>> listMap= (List<Map<String, Object>>) o;
        return listMap;

    }

    public List<Object[]> selectListArrayBySql(QueryRunner queryRunner, String sql,Object... objects) throws SQLException {
        List<Object[]> listArray = queryRunner.query(sql, new ArrayListHandler(), objects);
        return listArray;
    }

    public String getPageBeanSqlOracle(String sql, int currentPage, int pageSize) {
        int startI = (currentPage - 1) * pageSize+1;
        int endI=currentPage*pageSize;
        String pageBeanSql="select * from (select A.*,rownum rn from("+sql+")A where rownum<="+endI+") where rn>="+startI;
        return pageBeanSql;
    }

//    public Object selectObject(QueryRunner queryRunner, String sql, ResultSetHandler resultSetHandler, Object... objects) throws SQLException {
//        Object query = queryRunner.query(sql, resultSetHandler, objects);
//        return query;
//    }

    public int executeSql(Connection connect, String sql, Object... objects) throws SQLException {
        PreparedStatement pStatement= connect.prepareStatement(sql);
        for(int i=0;i<objects.length;i++){
            pStatement.setObject(i+1,objects[i]);
        }
        int i = pStatement.executeUpdate();
        pStatement.close();
        return i;
    }

}
