package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

/**
 * @author ziyin
 @create 2019-06-2019/6/8-13:22
 */
public interface UserService {

    boolean regist(User user);

    boolean activeUser(String code);

    User login(User user);
}
