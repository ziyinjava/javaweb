package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

/**
 * 线路Service
 */
public interface RouteService {

    /**
     * 根据类别进行分页查询
     * @param cid
     * @param pageBean
     * @return
     */
    PageBean<Route> pageQuery(PageBean pageBean,int cid, String rname);

    /**
     * 根据id查询
     * @param rid
     * @return
     */
    Route findOne(String rid);
}
