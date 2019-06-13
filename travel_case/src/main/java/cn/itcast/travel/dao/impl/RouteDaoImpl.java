package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid,String rname) {
        //1.定义sql模板
        String sql = "select count(*) from tab_route where 1=1 ";
        Conditions conditions = new Conditions(cid, rname, sql).invoke();
        sql = conditions.getSql();
        List params = conditions.getParams();
        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public List<Route> findByPage(PageBean pageBean, int cid, String rname) {
        //String sql = "select * from tab_route where cid = ? and rname like ?  limit ? , ?";
        String sql = " select * from tab_route where 1 = 1 ";
        //1.定义sql模板
        Conditions conditions = new Conditions(pageBean, cid, rname, sql).invoke();
        sql = conditions.getSql();
        List params = conditions.getParams();
         return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),params.toArray());
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }

    private class Conditions {
        private PageBean pageBean;
        private int cid;
        private String rname = "";
        private String sql = "";
        private List params;

        public Conditions(int cid, String rname, String sql) {
            this.cid = cid;
            this.rname = rname;
            this.sql = sql;
        }

        public Conditions(PageBean pageBean, int cid, String rname, String sql) {
            this.pageBean = pageBean;
            this.cid = cid;
            this.rname = rname;
            this.sql = sql;
        }

        public String getSql() {
            return sql;
        }

        public List getParams() {
            return params;
        }

        public Conditions invoke() {
            StringBuilder sb = new StringBuilder(sql);

            params = new ArrayList();
            //2.判断参数是否有值
            if(cid != 0){
                sb.append( " and cid = ? ");

                params.add(cid);//添加？对应的值
            }

            if(StringUtils.isNotBlank(rname) && !"null".equals(rname)){
                sb.append(" and rname like ? ");
                params.add("%"+rname+"%");
            }
            if (pageBean != null) {
                sb.append(" limit ? , ? ");//分页条件
                params.add(pageBean.getStart());
                params.add(pageBean.getPageSize());
            }

            sql = sb.toString();


            return this;
        }
    }
}
