package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

/**
 * @author ziyin
 @create 2019-06-2019/6/8-13:22
 */
public interface UserDao {

     User getUserByUsername(String name);

     void saveUser(User user);


    User getUserByCode(String code);

    void updateStatus(User user);

    User getUserByUsernameAndPwd(User user);
}
