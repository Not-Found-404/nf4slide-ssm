package com.qtu404.user.utils;

import com.qtu404.user.domain.UserVo;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserUtil {
    private UserVo currentUser;

    public UserVo getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserVo currentUser) {
        this.currentUser = currentUser;
    }
}
