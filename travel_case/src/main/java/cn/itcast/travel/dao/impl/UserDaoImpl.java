package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author ziyin
 @create 2019-06-2019/6/8-13:23
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jt = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User getUserByUsername(String username) {
        User user = null;
        try {
            String sql = "select * from tab_user where username = ?";
            user = jt.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return user;
    }


    @Override
    public void saveUser(User user) {
        //1.定义sql
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        //2.执行sql

        jt.update(sql,user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode()
        );
    }

    @Override
    public User getUserByCode(String code) {
        User user = null;
        try {
            String sql = "select * from tab_user where code = ?";
            user = jt.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),code);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateStatus(User user) {
        String sql = "update tab_user set status = ? where uid = ?";
        jt.update(sql,user.getStatus(),user.getUid());
    }

    @Override
    public User getUserByUsernameAndPwd(User user) {
        User u = null;
        try {
            String sql = "select * from tab_user where username = ? and password = ?";
             u = jt.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),user.getUsername(),user.getPassword());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return u;
    }
}
