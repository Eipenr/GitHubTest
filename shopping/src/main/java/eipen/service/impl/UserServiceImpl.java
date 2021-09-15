package eipen.service.impl;

import com.alibaba.druid.util.StringUtils;
import eipen.Exceptions.MybaseException;
import eipen.Utils.EmailUtil;
import eipen.Utils.ExecutorsUtils;
import eipen.Utils.MD5Utils;
import eipen.dao.UserDao;
import eipen.dao.impl.UserDaoImpl;
import eipen.pojo.TbUser;
import eipen.service.UserService;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();

    /**
     * 注册方法
     * @param user
     * @throws Exception
     */
    @Override
    public void addUser(TbUser user) throws Exception {
        //进行对数据的判断emailregex
        String emailregex="^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        String passwordregex="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z\\W]{6,18}$";
        if (!user.getPassword().matches(passwordregex)) {
            System.err.println("密码格式不对");
            throw new MybaseException("密码格式不对");
//            return;
        }
        if (!user.getEmail().matches(emailregex)) {
            System.err.println("邮箱格式不对");
            throw new MybaseException("邮箱格式不对");
//            return;
        }
        //注册的时候判断是否存在此用户名  就需要一个查询方法
        TbUser userByName = userDao.findUserByName(user.getUsername());
        if(userByName!=null){
            System.err.println("用户名已存在");
            throw new MybaseException("用户名已存在");
//            return;
        }
        //将密码转换成 MD5 格式  创建一个MD5工具类
        String md5 = MD5Utils.md5(user.getPassword());
        user.setPassword(md5);

        //生成一个激活码
        String code = UUID.randomUUID().toString().replace("-", "");
        user.setCode(code);

        userDao.addUser(user);
        //添加成功之后，我们需要进行发邮件的激活验证，由于时间问题，我们用线程池的方式进行发送邮件，以避免下面其他代码的运行
        //创建线程池工具类
        ExecutorsUtils.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    EmailUtil.sendEmail(user);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public int active(String username, String code) throws SQLException {
      return userDao.active(username,code);
    }

    @Override
    public TbUser login(String username,String password) throws SQLException {
        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            return null;
        }
        TbUser user = userDao.findUserByName(username);
        if(user==null){
            return null;
        }
        //如果不为null，就是查到信息了，再核对密码
        String md5 = MD5Utils.md5(password);
        if(!md5.equals(user.getPassword())){
            return null;
        }
        //都符合条件了，就将user 返回
        return user;
    }
}
