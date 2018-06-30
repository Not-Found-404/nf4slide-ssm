package com.qtu404.user.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<UserVo> {
    @Override
    public UserVo mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserVo userVo = new UserVo();

        userVo.setPassword(rs.getString("password"));
        userVo.setPhoneNum(rs.getString("phone"));
        userVo.setUserId(rs.getInt("userId"));
        userVo.setUsername(rs.getString("username"));
        userVo.setAvator(rs.getString("avator"));

        return userVo;
    }
}
