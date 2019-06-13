package cn.itcast.travel.dao;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {

    /**
     * 根据cid查询总记录数
     */
    int findTotalCount(int cid, String rname);

    /**
     * 根据cid，start,pageSize查询当前页的数据集合
     */
    List<Route> findByPage(PageBean pageBean, int cid, String rname);

    /**
     * 根据id查询
     * @param rid
     * @return
     */
    Route findOne(int rid);
}
