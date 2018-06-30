/* 王海涛 2017.9.26 主界面控制js */
$(function startOn() {
    /* 开始运行加载函数 */
    /* 重写layout padding为0，保证移动端自适应 */
    $(".layout").css({"paddingLeft": 0, "paddingRight": 0});
});

$(function inputTip() {
    /* 输入框文字提示效果_绑定 */
    inputInit();
    inputTipIn();
    inputTipOut();
});

function inputInit() {
    /* 输入框初始化检测 */
    var $input_1 = $("#user_name");
    var $label_1 = $input_1.parent().children("label");
    var $input_2 = $("#user_password");
    var $label_2 = $input_2.parent().children("label");
    if ($input_1.val() != "") {
        //用户名执行TipIn动画
        /* 输入框提示字获得焦点动画 */
        $label_1.stop().animate({
                /* properties */
                fontSize: "15px",
                top: 0
            },
            {
                /* options */
                duration: 150
            });
    }
    if ($input_2.val() != "") {
        //用户密码框执行TipIn动画
        /* 输入框提示字获得焦点动画 */
        $label_2.stop().animate({
                /* properties */
                fontSize: "15px",
                top: 0
            },
            {
                /* options */
                duration: 150
            });
    }
}

function inputTipIn() {
    /* 输入提示进入控制函数 */
    $(".login_input>input").bind("focusin", function () {
        /* 登录输入框绑定焦点进入事件 */
        var $parent_area = $(this).parent();
        var $label = $parent_area.children("label");
        var $symble = $parent_area.children(".login__symble_1");
        var $input = $parent_area.children("input");
        /* 输入框提示字获得焦点动画 */
        $label.stop().animate({
                /* properties */
                fontSize: "15px",
                top: 0
            },
            {
                /* options */
                duration: 150
            });
        /* 输入框边框颜色改变 */
        $parent_area.parent().css("borderColor", "#346d89");
        /* 图标颜色改变 */
        $symble.css("color", "#346d89");
    });
}

function inputTipOut() {
    /* 输入提示退出函数 */
    $(".login_input>input").bind("focusout", function () {
        /* 输入框提示字失去焦点动画 */
        var $parent_area = $(this).parent();
        var $label = $parent_area.children("label");
        var $symble = $parent_area.children(".login__symble_1");
        var $input = $parent_area.children("input");
        var screen_height = window.innerHeight;
        var screen_width = window.innerWidth;
        /* 失去焦点动画 */
        if ($input.val() === "") {
            /* 输入框为空则恢复提示文字位置 */
            if (screen_height > 620 && screen_width > 430) {
                $label.stop().animate({
                        /* properties */
                        fontSize: "20px",
                        top: "24px"
                    },
                    {
                        /* options */
                        duration: 150
                    });
            } else {
                $label.stop().animate({
                        /* properties */
                        fontSize: "16px",
                        top: "24px"
                    },
                    {
                        /* options */
                        duration: 150
                    });
            }
        }
        ;
        /* 输入框边框颜色改变 */
        $parent_area.parent().css("borderColor", "#8aadbe");
        /* 图标颜色改变 */
        $symble.css("color", "rgba(10, 92, 159, 0.50)");
    });
}

$(function inputPopovers() {
    /* 输入框_Popovers提示 */
    $("#input_popovers__1").popover({
        animate: true,
        trigger: 'hover',
        container: 'body',
        placement: 'bottom',
        content: '用户名是您的电话号码'
    });
    $("#input_popovers__2").popover({
        animate: true,
        trigger: 'hover',
        container: 'body',
        placement: 'bottom',
        content: '忘记密码？请联系管理员'
    });
})

/*随机更换壁纸函数*/
$(function changeTheme() {
    var reg = /\d/;
    var themeClass = "theme";
    var index = Math.random() * 10;
    index = index.toString();
    themeClass += reg.exec(index);
    $("body").removeClass("theme0")
        .removeClass("theme1")
        .removeClass("theme2")
        .removeClass("theme3")
        .removeClass("theme4")
        .removeClass("theme5")
        .removeClass("theme6")
        .removeClass("theme7")
        .removeClass("theme8")
        .removeClass("theme9")
        .removeClass("mobiletheme0")
        .removeClass("mobiletheme1")
        .removeClass("mobiletheme2")
        .removeClass("mobiletheme3")
        .removeClass("mobiletheme4")
        .removeClass("mobiletheme5")
        .removeClass("mobiletheme6")
        .removeClass("mobiletheme7")
        .removeClass("mobiletheme8")
        .removeClass("mobiletheme9");
    if (window.innerHeight >= 620 && window.innerWidth >= 430) {
        $("body").addClass(themeClass);
    } else {
        $("body").addClass("mobile" + themeClass);
    }
})

$(function addBindEvent() {
    //绑定注册按钮事件
    $("#signup_button").bind("click", function () {
        window.open("/toRegisterPage", "_self");
    });

    //绑定版权信息按钮事件
    $("#copyright__link").bind("click", function () {
        $('.bd-example-modal-sm').modal();
    });

    //绑定登录按钮事件
    $("#login_button").bind("click", function (event) {
        event.preventDefault();
        //调用登录验证函数
        if (loginValidate()) {
            $.ajax({
                url: "user/login",
                type: "POST",
                dataType: "JSON",
                data: {
                    phoneNum: $("#user_name").val(),
                    password: $("#user_password").val()
                },
                success: function (data, status) {
                    console.log(data);
                    if (data.result == "loginSuccess") {
                        window.open("toUserPage", "_self");
                    } else {
                        loginFailTip();
                    }
                },
                error: function (e) {
                    alert("请检查网络连接！！");
                    window.clearInterval(timer);
                }
            });
        }

    });
})

function loginFailTip() {
    //登录失败提示，调用函数弹出登录失败提示
    $("#login-error").stop(true).fadeIn({
        duration: 0,
        complete: function autoClose() {
            $("#login-error").delay(3200).fadeOut();
        }
    });
}

function loginValidate() {
    //登录信息合法性验证
    var $userName = $("#user_name");
    var $userPassword = $("#user_password");
    if ($userName.val() == "") {
        alert("请输入用户名");
        return false;
    }
    if ($userPassword.val() == "") {
        alert("请输入密码");
        return false;
    }
    return true;
}

/*
    提交表单函数：
    $("#user_login").submit();
*/