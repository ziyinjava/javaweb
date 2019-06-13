package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import cn.itcast.travel.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.CollectionUtils;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ziyin
 @create 2019-06-2019/6/9-10:49
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public synchronized List<Category> listCategory() {
        Jedis jedis = JedisUtil.getJedis();
        List<String> categories = jedis.lrange("categories", 0, -1);
        List<Category> categoryList;
        if (CollectionUtils.isEmpty(categories)) {
            System.out.println("从数据库取");
            categoryList = categoryDao.listCategory();
            //放入redis中
            String[] strs = new String[categoryList.size()];
            for (int i = 0; i < categoryList.size(); i++) {
                try {
                    strs[i] = JsonUtils.writeValueAsString(categoryList.get(i));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
            jedis.rpush("categories",strs);
        } else {
            System.out.println("从redis取");
            categoryList = categories.stream().map(c -> {
               ObjectMapper om = new ObjectMapper();
               try {
                   return om.readValue(c,Category.class);
               } catch (IOException e) {
                   e.printStackTrace();
               }
               return null;
           }).collect(Collectors.toList());
        }
        return categoryList;
    }
}
