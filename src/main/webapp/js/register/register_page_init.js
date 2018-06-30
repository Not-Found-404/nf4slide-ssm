var checkPhone = "failed";
var checkPwd = "failed";
var checkRePwd = "failed";
var checkQuestion = "failed";
var checkAnwser = "failed";
$("#inputPhone").blur(function () {
    $("#warning_Phone").html("验证手机号中...");
    if ($(this).val() == "") {
        $("#warning_Phone").html("X 账号不能为空");
        $(this).css("border", "2px solid red");
        checkPhone = "failed";
        return;
    } else {
        if (!(/^1(3|4|5|7|8)\d{9}$/.test($(this).val()))) {
            $("#warning_Phone").html("X 手机号码有误");
            $(this).css("border", "2px solid red");
            checkPhone = "failed";
            return;
        }
        $.ajax({
            type: "GET",
            url: "user/verifyPhone",
            async: false,
            dataType: "JSON",
            data: {
                phoneNum: $("#inputPhone").val()
            },
            success: function (msg) {
                if (msg.result == "false") {
                    $("#warning_Phone").html("X 该号码已被注册");
                    $("#inputPhone").css("border", "2px solid red");
                    checkPhone = "failed";
                }
                else {
                    $("#warning_Phone").html("<span style='color:green'>√ 通过</span>");
                    $("#inputPhone").css("border", "2px solid green");
                    checkPhone = "success";
                }
            }
        });
    }
})
$("#inputPassword").blur(function () {
    if ($(this).val() == "") {
        $("#warning_password").html("X 密码不能为空");
        $(this).css("border", "2px solid red");
        checkPwd = "failed";
    } else {
        $("#warning_password").html("<span style='color:green'>√ 通过</span>");
        $(this).css("border", "2px solid green");
        checkPwd = "success";
    }
})
$("#repassword").blur(function () {
    if ($(this).val() != $("#inputPassword").val()) {
        $("#warning_repassword").html("X 输入的密码不同");
        $(this).css("border", "2px solid red");
        checkRePwd = "failed";
    } else {
        $("#warning_repassword").html("<span style='color:green'>√ 通过</span>");
        $(this).css("border", "2px solid green");
        checkRePwd = "success";
    }
})

$("#question").blur(function () {
    if ($(this).val() == "") {
        $("#warning_question").html("X 用户名不能为空");
        $(this).css("border", "2px solid red");
        checkQuestion = "failed";
    } else {
        $("#warning_question").html("<span style='color:green'>√ 通过</span>");
        $(this).css("border", "2px solid green");
        checkQuestion = "success";
    }
})

$("#register").click(function () {
    $('#inputPhone').trigger("blur");
    $('#anwser').trigger("blur");
    $('#inputPassword').trigger("blur");
    $('#repassword').trigger("blur");
    $('#question').trigger("blur");
    if (checkPhone != "success" || checkPwd != "success" || checkRePwd != "success" || checkQuestion != "success") {
        alert("还有尚未完成的项！");
    } else {
        alert("注册成功!");
        $("form").submit();
    }
})