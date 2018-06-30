package com.qtu404;

import com.qtu404.user.dao.UserDao;
import com.qtu404.user.dao.impl.UserDaoImpl;
import com.qtu404.user.domain.UserVo;

public class Test {
    public static void main(String[] args) {
        UserDao userDao= new UserDaoImpl();

        UserVo userVo = userDao.fetchUserByPhone("17866625604");
        System.out.println();
    }
}
