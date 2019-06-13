package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.ExecutorsUtil;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.StatusEnum;
import cn.itcast.travel.util.UuidUtil;

/**
 * @author ziyin
 @create 2019-06-2019/6/8-13:22
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean regist(User user) {
        User u = userDao.getUserByUsername(user.getUsername());
        if (u != null) {
            return false;
        }
        user.setStatus(StatusEnum.N.getStatus());
        user.setCode(UuidUtil.getUuid());
        userDao.saveUser(user);
        ExecutorsUtil.execute(() -> {
            String content="<a href='http://localhost/travel/user/active?code="+user.getCode()+"'>点击激活【黑马旅游网】</a>";
            MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        });
        return true;

    }

    @Override
    public boolean activeUser(String code) {
        User user = userDao.getUserByCode(code);
        if (user != null) {
            user.setStatus(StatusEnum.Y.getStatus());
            userDao.updateStatus(user);
            return true;
        }
        return false;
    }

    @Override
    public User login(User user) {

        return userDao.getUserByUsernameAndPwd(user);
    }
}
