package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao routeDao = new RouteDaoImpl();

    private RouteImgDao routeImgDao = new RouteImgDaoImpl();

    private SellerDao sellerDao = new SellerDaoImpl();

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();


    @Override
    public PageBean<Route> pageQuery(PageBean pageBean, int cid, String rname) {
        int totalCount = routeDao.findTotalCount(cid,rname);
        List<Route> list = routeDao.findByPage(pageBean,cid,rname);
        pageBean.setTotalCount(totalCount);
        pageBean.setList(list);
        int pageSize = pageBean.getPageSize();
        pageBean.setTotalPage(totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1); ;
        return pageBean;
    }

    @Override
    public Route findOne(String rid) {
        //1.根据id去route表中查询route对象
        Route route = routeDao.findOne(Integer.parseInt(rid));
        //2.根据route的id 查询图片集合信息
        List<RouteImg> routeImgList = routeImgDao.findByRid(route.getRid());
        //2.2将集合设置到route对象
        route.setRouteImgList(routeImgList);
        //3.根据route的sid（商家id）查询商家对象
        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);
        //4. 查询收藏次数
        int count = favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);
        return route;
    }
}
