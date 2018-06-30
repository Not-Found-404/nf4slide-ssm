//检测移动端测试
function IsPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod");
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}

//addAPPT("#add-PPT",10001,'');
function addAPPT(workplace, id, name) {
    var pptId = "ppt-" + id;
    var ExId = id;
    //在新建和导入后面添加一个新的PPT<li>
    $(workplace).after("<li id=\"" + pptId + "\"></li>");
    $("#" + pptId).addClass(" SlideList-item ");
    $("#" + pptId).addClass(" col-3");
    $("#" + pptId).append("<div id=\"infocard-" + ExId + "\"></div>");
    $("#infocard-" + ExId).addClass(" InfoCard ");
    $("#infocard-" + ExId).append("<img alt=\" \" id=\"pptImg-" + ExId + "\"></img>");
    $("#pptImg-" + ExId).addClass("InfoCard-pic");
    $("#pptImg-" + ExId).attr("src", "img/PPT.png");
    $("#infocard-" + ExId).append("<input type=\"text\" id=\"pptTitle-" + ExId + "\" value=\"" + name + "\" />");
    $("#pptTitle-" + ExId).addClass("InfoCard-title");
    $("#pptTitle-" + ExId).focus();
    $("#pptTitle-" + ExId).attr("readonly", "true");
    $("#infocard-" + ExId).append("<div id=\"pptControl-" + ExId + "\"></div>");
    $("#pptControl-" + ExId).addClass("SlideControl");
    $("#pptControl-" + ExId).append("<a id=\"pptEdit-" + ExId + "\"></a>");
    //添加编辑、播放、修改、删除功能

    if (IsPC()) {
        $("#pptEdit-" + ExId).addClass("SlideControl-item");
        $("#pptEdit-" + ExId).attr("target", "_blank");
        $("#pptEdit-" + ExId).append("<i id=\"pptEdit-i-" + ExId + "\"></i>");
        $("#pptEdit-i-" + ExId).addClass("icon-pencil");
        $("#pptEdit-" + ExId).append("<span id=\"pptEdit-span-" + ExId + "\">编辑</span>");
        $("#pptEdit-span-" + ExId).addClass("controlBtn");
        $("#pptEdit-" + ExId).click(function () {
            window.open("toSlideEditPage?slideId=" + id, "_blank");
        });
    }

    $("#pptControl-" + ExId).append("<a id=\"pptPlay-" + ExId + "\"></a>");
    $("#pptPlay-" + ExId).addClass("SlideControl-item");
    $("#pptPlay-" + ExId).attr("target", "_blank");
    $("#pptPlay-" + ExId).append("<i id=\"pptPlay-i-" + ExId + "\"></i>");
    $("#pptPlay-i-" + ExId).addClass("icon-play");
    $("#pptPlay-" + ExId).append("<span id=\"pptPlay-span-" + ExId + "\">播放</span>");
    $("#pptPlay-span-" + ExId).addClass("controlBtn");
    $("#pptPlay-" + ExId).click(function () {
        window.open("toPlayPage?slideId=" + id, "_blank");
    });


    $("#pptControl-" + ExId).append("<a id=\"pptSpeak-" + ExId + "\"></a>");
    $("#pptSpeak-" + ExId).addClass("SlideControl-item");
    $("#pptSpeak-" + ExId).attr("target", "_blank");
    $("#pptSpeak-" + ExId).append("<i id=\"pptSpeak-i-" + ExId + "\"></i>");
    $("#pptSpeak-i-" + ExId).addClass("fa fa-microphone fa-lg");
    $("#pptSpeak-" + ExId).append("<span id=\"pptSpeak-span-" + ExId + "\">演讲</span>");
    $("#pptSpeak-span-" + ExId).addClass("controlBtnSpeak");
    $("#pptSpeak-" + ExId).click(function () {
        window.open("toPlayPage?slideId=" + id + "&control=true", "_blank");
    });

    $("#pptControl-" + ExId).append("<a id=\"pptAlter-" + ExId + "\"></a>");
    $("#pptAlter-" + ExId).addClass("SlideControl-item");
    $("#pptAlter-" + ExId).append("<i id=\"pptAlter-i-" + ExId + "\"></i>");
    $("#pptAlter-i-" + ExId).addClass("icon-cog");
    $("#pptAlter-" + ExId).append("<span id=\"pptAlter-span-" + ExId + "\">重命名</span>");
    $("#pptAlter-span-" + ExId).addClass("controlBtn");

    $("#pptControl-" + ExId).append("<a id=\"pptDelete-" + ExId + "\"></a>");
    $("#pptDelete-" + ExId).addClass("SlideControl-item");
    $("#pptDelete-" + ExId).append("<i id=\"pptDelete-i-" + ExId + "\"></i>");
    $("#pptDelete-i-" + ExId).addClass("icon-trash");
    $("#pptDelete-" + ExId).append("<span id=\"pptDelete-span-" + ExId + "\">删除</span>");
    $("#pptDelete-span-" + ExId).addClass("controlBtn");

    //删除此PPT
    $("#pptDelete-" + ExId).click(function () {
        $.confirm({
            title: '警告!',
            content: '是否确认删除此幻灯片?',
            type: 'blue',
            buttons: {
                ok: {
                    text: "确定!",
                    keys: ['enter'],
                    btnClass: 'btn-primary',
                    action: function () {
                        $.ajax({
                            url: "slide/delSlide",
                            type: "GET",
                            data: {
                                slideId: ExId
                            },
                            dataType: "JSON",
                            success: function (msg) {
                                if (msg.result == "delSuccess") {
                                    $("#ppt-" + ExId).remove();
                                }
                            },
                            error: function (e) {
                                alert("请检查网络连接！！");
                            }
                        });
                    }
                },
                cancel: {
                    text: "取消",
                    action: function () {
                    }
                }
            }
        });
    });

    //重命名PPT标题
    $("#pptAlter-" + ExId).click(function () {
        $("#pptTitle-" + ExId).attr("contenteditable", "true");
        $("#pptTitle-" + ExId).focus();
        $("#pptTitle-" + ExId).select();

    });

    //重命名PPT标题
    $("#pptAlter-" + ExId).click(function () {
        $("#pptTitle-" + ExId).removeAttr("readonly");
        $("#pptTitle-" + ExId).focus();
        $("#pptTitle-" + ExId).select();
        $("#pptTitle-" + ExId).blur(function () {
            if ($("#pptTitle-" + ExId).val() == "") {
                $.confirm({
                    title: '错误!',
                    content: '幻灯片名不可为空!',
                    type: 'blue',
                    buttons: {
                        ok: {
                            text: "确定!",
                            keys: ['enter'],
                            btnClass: 'btn btn-primary',
                        }
                    }
                });
                $("#pptTitle-" + ExId).val("新建幻灯片");
            }
            $.post("slide/modifySlideName", {
                slideId: ExId,
                name: $("#pptTitle-" + ExId).val()
            }, function () {
            });
            $("#pptTitle-" + ExId).attr("readonly", "true");
        });
    });
}