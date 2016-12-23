package com.cl.common.servicce;

import com.cl.common.dao.BaseDao;
import com.cl.common.dao.BaseDaoImpl;
import com.cl.common.utils.PageBean;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenlei on 2016/12/20.
 */
public class BaseServiceImpl implements BaseService {

    BaseDao baseDao=new BaseDaoImpl();

    public PageBean getPageBean(String sql, String mapORarray, Integer currentPage, Integer pageSize, Object... objects) throws SQLException {
        Integer total = this.getTotlaBySql(sql, objects);// 得到总的记录长度
        String pageSql = this.getPageBeanSqlOracle(sql, currentPage, pageSize);
        List<?> list=new ArrayList<Object>();
        if("map".equals(mapORarray.toLowerCase())){
            list = this.selectListMapBySql(pageSql, objects);
        }else if("array".equals(mapORarray.toLowerCase())){
            list=this.selectListArrayBySql(pageSql,objects);
        }
        PageBean pageBean = new PageBean();
        pageBean.setTotal(total);
        pageBean.setList(list);
        pageBean.setTotalPage(PageBean.countTatalPage(pageSize, total));
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        return pageBean;
    }

    public Integer getTotlaBySql(String sql, Object... objects) throws SQLException {
        int index = sql.toLowerCase().indexOf("from");
        String subString = sql.substring(index);
        String newSql = "select count(1) " + subString;
        Object o = baseDao.selectObject(newSql, new ScalarHandler(), objects);
        return Integer.parseInt(o.toString());
    }

    public List<Map<String, Object>> selectListMapBySql(String sql, Object... objects) throws SQLException {
        Object o = baseDao.selectObject(sql, new MapListHandler(), objects);
        List<Map<String, Object>> listMap= (List<Map<String, Object>>) o;
        return listMap;

    }

    public List<String[]> selectListArrayBySql(String sql,Object... objects) throws SQLException {
        Object o = baseDao.selectObject(sql, new ArrayListHandler(),objects);
        List<String[]> listArray= (List<String[]>) o;
        return listArray;
    }

    public String getPageBeanSqlOracle(String sql, int currentPage, int pageSize) {
        int startI = (currentPage - 1) * pageSize+1;
        int endI=currentPage*pageSize;
        String pageBeanSql="select * from (select A.*,rownum rn from("+sql+")A where rownum<="+endI+") where rn>="+startI;
        return pageBeanSql;
    }

}
