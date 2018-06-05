package com.cl.common.servicce;

import com.cl.common.utils.JDBCUtilHikariCP;
import com.cl.common.utils.PageBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

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

    public void getFileBySql(String sql, File csvFile, String separator, boolean head, int batchSize) {
        BufferedWriter bufferedWriter=null;
        try {
            bufferedWriter=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile)));
            BaseServiceImpl baseService = new BaseServiceImpl();
            QueryRunner queryRunner = JDBCUtilHikariCP.getQueryRunner1();
            if(head){
                // 过去头信息
                String pageSql = baseService.getPageBeanSqlOracle(sql, 1, 1);
                List<Map<String, Object>> listMap1 = baseService.selectListMapBySql(queryRunner, pageSql);
                Map<String, Object> map = listMap1.get(0);
                Set<Map.Entry<String, Object>> entrySet = map.entrySet();
                StringBuffer sBuffer2 = new StringBuffer();
                for (Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator(); iterator.hasNext();) {
                    Map.Entry<String, Object> entry = iterator.next();
                    sBuffer2.append(entry.getKey());
                    sBuffer2.append(separator);
                }
                String strT = sBuffer2.toString();
                strT = strT.substring(0, strT.length() - separator.length());
                bufferedWriter.write(strT+System.getProperty("line.separator"));
            }
            // 获取各行数据
//            int batchSize=5000;
            int total=baseService.getTotlaBySql(queryRunner,sql);
            for(int i=1;i<=total/batchSize+1;i++){
                String pageSql = baseService.getPageBeanSqlOracle(sql, i, batchSize);
                List<Object[]> list = baseService.selectListArrayBySql(queryRunner, pageSql);
                for (Object[] objects:list) {
                    objects[1]="";	//第二列序号要去掉
                    String strLin = Arrays.toString(objects);
                    strLin=strLin.replace("null", "");
                    strLin = strLin.substring(1, strLin.length() - 1);
                    strLin = strLin.replace(", ", separator);
                    bufferedWriter.write(strLin+System.getProperty("line.separator"));
                }
                bufferedWriter.flush();
            }
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(bufferedWriter!=null)
                    bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
