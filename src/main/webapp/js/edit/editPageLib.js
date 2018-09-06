/**
 * 播放設置初始化 isDisplayPro 是否播放進度
 * isDisplayNum 是否播放页数
 * @type {string}
 */
// var isDisplayPro = "true,";
// var isDisplayNum = "true,";
function settingDialogInit() {
    isDisplayPro = "true,";
    isDisplayNum = "true,";
    // $(function () {
    //     $(".nf4-radio").buttonset();
    // });
    $(".nf4-radio").buttonset();
    $("#dialog-form").dialog({
        autoOpen: false,
        height: 500,
        width: 500,
        modal: true,
        dialogClass: "no-close",
        show: {
            effect: "blind",
            duration: 500
        },
        hide: {
            effect: "explode",
            duration: 500
        },
        close: function () {
            $(this).dialog("close");
        }
    });

    $("#nf4-navbar__settings").button().click(function () {
        $("#dialog-form").dialog("open");
    });

    $("#closeDialog").button().click(function () {
        $("#dialog-form").dialog("close");
    });

    $("#proTrue").click(function () {
        isDisplayPro = "true,";
    });
    $("#proFalse").click(function () {
        isDisplayPro = "false,";
    });

    $("#numTrue").click(function () {
        isDisplayNum = "true,";
    });

    $("#numFalse").click(function () {
        isDisplayNum = "false,";
    });

    /* 试题模块 */
    // $("#dialog-form-question").dialog({
    //     autoOpen: false,
    //     height: 600,
    //     width: 800,
    //     modal: true,
    //     dialogClass: "no-close",
    //     show: {
    //         effect: "blind",
    //         duration: 500
    //     },
    //     hide: {
    //         effect: "explode",
    //         duration: 500
    //     },
    //     close: function () {
    //         $(this).dialog("close");
    //     }
    // });
    // $("#closeQuestionDialog").click(function () {
    //     $("#dialog-form-question").dialog("close");
    // })

}



/**
 * 得到播放的html代码，代码可以直接放在 class为slide的div里面
 * @returns {string}
 */
function playSlide() {
    var playHtmlCode = "";
    $(".nf4-slide").each(function () { //循环每一个幻灯片
        var ExId = $(this).attr("id").substr(6);
        playHtmlCode = playHtmlCode + "<section> <div style=\"height:700px;width:960px\">";
        //先文
        $("#tabs-" + ExId + " .nf4-text").each(function () { //循环幻灯片内每一个文本组件x`
            var styleCode = "position:absolute;";
            styleCode = styleCode + "top:" + $(this).css("top") + ";"; //第一层
            styleCode = styleCode + "left:" + $(this).css("left") + ";";
            styleCode = styleCode + "z-index:" + $(this).css("z-index") + ";";
            styleCode = "style=\"" + styleCode + "\"";
            playHtmlCode = playHtmlCode + "<div " + styleCode + "align=\"left\">";
            styleCode = "";

            var container = $("#" + $(this).attr("id") + " .nf4-text-editor").html(); //第二层
            styleCode = styleCode + "width:" + $(this).css("width") + ";";
            styleCode = styleCode + "height:" + $(this).css("height") + ";";
            styleCode = "style=\"" + styleCode + "\"";
            playHtmlCode = playHtmlCode + "<div " + styleCode + ">" + container;
            playHtmlCode = playHtmlCode + "</div></div>";
        });
        //图片
        $("#tabs-" + ExId + " .nf4-image").each(function () { //循环幻灯片内每一个文本组件
            var styleCode = "position:absolute;";
            styleCode = styleCode + "top:" + $(this).css("top") + ";"; //第一层
            styleCode = styleCode + "left:" + $(this).css("left") + ";";
            if ($(this).width() != 0) {
                styleCode = styleCode + "width:" + $(this).width() + "px" + ";";
            }
            if ($(this).height() != 0) {
                styleCode = styleCode + "height:" + $(this).height() + "px" + ";";
            }
            styleCode = styleCode + "z-index:" + $(this).css("z-index") + ";";
            styleCode = styleCode + " transform:" + $(this).css("transform") + ";";
            styleCode = "style=\"" + styleCode + "\"";
            playHtmlCode = playHtmlCode + "<div " + styleCode + ">";
            styleCode = "";

            //第二层
            var opacity = $("#" + $(this).attr("id") + " .nf4-image-editor").css("opacity");
            styleCode = styleCode + "width:100%;";
            styleCode = styleCode + "height:100%;";
            styleCode = styleCode + "opacity:" + opacity + ";";
            styleCode = "style=\"" + styleCode + "\"";
            var src = $("#" + $(this).attr("id") + " .nf4-image-editor").attr("src");
            src = "src=\"" + src + "\"";
            playHtmlCode = playHtmlCode + "<img  " + styleCode + src + ">";
            playHtmlCode = playHtmlCode + "</div>";
        });


        //代码
        $("#tabs-" + ExId + " .nf4-code").each(function () {//循环幻灯片内每一个代码组件
            $("span").remove(".cke_reset");
            $("span").css("float", "left");
            var CODE = $(".nf4-code .cke_widget_wrapper ").html();
            var styleCode = "position:absolute;";
            styleCode = styleCode + "top:" + $(this).css("top") + ";";//第一层
            styleCode = styleCode + "left:" + $(this).css("left") + ";";
            styleCode = styleCode + "z-index:" + $(this).css("z-index") + ";";
            styleCode = "style=\"" + styleCode + "\"";
            playHtmlCode = playHtmlCode + "<div " + styleCode + ">";
            styleCode = "";

            // var container = $("#" + $(this).attr("id")).html();//第二层
            styleCode = styleCode + "width:" + $(this).css("width") + ";";
            styleCode = styleCode + "height:" + $(this).css("height") + ";";
            styleCode = "style=\"" + styleCode + "\"";
            playHtmlCode = playHtmlCode + "<div " + styleCode + ">" + CODE;
            playHtmlCode = playHtmlCode + "</div></div>";

        });


        //形状nf4-shape
        $("#tabs-" + ExId + " .nf4-shape").each(function () { //循环幻灯片内每一个文本组件
            var styleCode = "position:absolute;";
            styleCode = styleCode + "top:" + $(this).css("top") + ";"; //第一层
            styleCode = styleCode + "left:" + $(this).css("left") + ";";
            styleCode = styleCode + "width:" + $(this).css("width") + ";";
            styleCode = styleCode + "height:" + $(this).css("height") + ";";
            styleCode = styleCode + "z-index:" + $(this).css("z-index") + ";";
            styleCode = "style=\"" + styleCode + "\"";
            playHtmlCode = playHtmlCode + "<div " + styleCode + ">";
            styleCode = "";

            //第二层
            var container = $("#" + $(this).attr("id")).html(); //第二层

            playHtmlCode = playHtmlCode + container;
            playHtmlCode = playHtmlCode + "</div>";
        });

        //线条nf4-shape
        $("#tabs-" + ExId + " .nf4-line").each(function () { //循环幻灯片内每一个文本组件
            var styleCode = "position:absolute;";
            styleCode = styleCode + "top:" + $(this).css("top") + ";"; //第一层
            styleCode = styleCode + "left:" + $(this).css("left") + ";";
            styleCode = styleCode + "width:" + $(this).css("width") + ";";
            styleCode = styleCode + "height:" + $(this).css("height") + ";";
            styleCode = styleCode + "z-index:" + $(this).css("z-index") + ";";
            styleCode = "style=\"" + styleCode + "\"";
            playHtmlCode = playHtmlCode + "<div " + styleCode + ">";
            styleCode = "";

            //第二层
            var container = $("#" + $(this).attr("id")).html(); //第二层

            playHtmlCode = playHtmlCode + container;
            playHtmlCode = playHtmlCode + "</div>";
        });

        //绘画nf4-draw
        $("#tabs-" + ExId + " .nf4-draw").each(function () { //循环幻灯片内每一个文本组件
            var styleCode = "position:absolute;";
            styleCode = styleCode + "top:" + $(this).css("top") + ";"; //第一层
            styleCode = styleCode + "left:" + $(this).css("left") + ";";
            styleCode = styleCode + "width:" + $(this).css("width") + ";";
            styleCode = styleCode + "height:" + $(this).css("height") + ";";
            styleCode = styleCode + "z-index:" + $(this).css("z-index") + ";";
            styleCode = "style=\"" + styleCode + "\"";
            playHtmlCode = playHtmlCode + "<div " + styleCode + ">";
            styleCode = "";

            //第二层
            var container = $("#" + $(this).attr("id")).html(); //第二层

            playHtmlCode = playHtmlCode + container;
            playHtmlCode = playHtmlCode + "</div>";
        });
        playHtmlCode = playHtmlCode + "</div></section>";
    });
    return playHtmlCode;
}


/** 界面控制代码 **/

/**
 * 进入全屏函数
 */
function fullScreen() {
    //进入全屏函数
    var el = document.documentElement,
        rfs = el.requestFullScreen || el.webkitRequestFullScreen || el.mozRequestFullScreen || el.msRequestFullScreen,
        wscript;

    if (typeof rfs != "undefined" && rfs) {
        rfs.call(el);
        return;
    }

    if (typeof window.ActiveXObject != "undefined") {
        wscript = new ActiveXObject("WScript.Shell");
        if (wscript) {
            wscript.SendKeys("{F11}");
        }
    }
}

/**
 * 退出全屏函数
 */
function exitFullScreen() {
    // 退出全屏函数
    var el = document,
        cfs = el.cancelFullScreen || el.webkitCancelFullScreen || el.mozCancelFullScreen || el.exitFullScreen,
        wscript;
    if (typeof cfs != "undefined" && cfs) {
        cfs.call(el);
        return;
    }
    if (typeof window.ActiveXObject != "undefined") {
        wscript = new ActiveXObject("WScript.Shell");
        if (wscript != null) {
            wscript.SendKeys("{F11}");
        }
    }
}

/**
 * Workspace工作区大小调整函数
 */
function workspaceLocation() {
    //Workspace工作区大小调整函数
    var display_width = $(".nf4-display").width();
    var display_height = $(".nf4-display").height();
    var workplace_width = $(".nf4-workspace").width();
    var workspace_height = $(".nf4-workspace").height();
    var margin_vertical,
        margin_horizontal;
    margin_horizontal = (display_width - 962) / 2;
    margin_vertical = (display_height - 722) / 2;
    if (margin_horizontal > 0) {
        $(".nf4-workspace__container").css({
            "margin-left": margin_horizontal,
            "margin-right": margin_horizontal
        });
    } else {
        $(".nf4-workspace__container").css({
            "margin-left": 0,
            "margin-right": 0
        });
    }
    if (margin_vertical > 0) {
        $(".nf4-workspace__container").css({
            "margin-top": margin_vertical,
            "margin-bottom": margin_vertical
        });
    } else {
        $(".nf4-workspace__container").css({
            "margin-top": 0,
            "margin-bottom": 0
        });
    }
}

/**
 * 显示Popover提示框
 * @param shwoItem
 * @param delayClose
 */
function shwoPopovers(shwoItem, delayClose) {
    //显示Popover提示框
    var popovers_item = $("#" + shwoItem);
    //如果存在则隐藏现有显示popovers
    hidePopover();
    //显示需要的popovers
    $(".nf4-utilities__popovers").css("display", "block");
    if (delayClose != undefined) {
        popovers_item.stop(true).fadeIn(170, function autoClose() {
            popovers_item.delay(delayClose).fadeOut(170, function close() {
                $(".nf4-utilities__popovers").css("display", "none");
            });
        });
    } else {
        popovers_item.stop(true, true).fadeIn(170);
    }
}

/**
 * 隐藏Popover提示框
 * @param durationTime
 */
function hidePopover(durationTime) {
    //隐藏Popover提示框
    if (durationTime != undefined) {
        /* 延迟动画 */
        $(".nf4-utilities__popovers>.nf4-popovers-item").stop(true, true).fadeOut(durationTime,
            function () {
                $(".nf4-utilities__popovers").css("display", "none");
            });
    } else {
        /* 无延迟动画 */
        $(".nf4-utilities__popovers>.nf4-popovers-item").stop(true, true).fadeOut();
        $(".nf4-utilities__popovers").css("display", "none");
    }
}

function dropDownMenu_hide() {
    /* 下拉菜单隐藏动画 */
    $(".nf4-dropdownMenu__control").animate({
        /* properties */
        marginRight: "-300px"
    }, {
        /* option */
        queue: false,
        duration: 250,
        complete: function () {
            $(this).css("display", "none");
            $(".nf4-dropdownMenu").hide(50);
        }
    });
    $(".nf4-navbar-control>div").removeClass("dropdownMenu__effectIn");
}


/**
 * 下拉菜单显示函数
 * @param trigger
 */
function dropDownMenu_show(trigger) {
    /* 下拉菜单显示函数 */
    $(".nf4-dropdownMenu")
        .fadeIn({
            /* option */
            duration: 50,
            queue: false
        });
    $(".nf4-dropdownMenu__control").css("display", "block");
    $(".nf4-dropdownMenu__control").animate({
        /* properties */
        marginRight: "0px"
    }, {
        /* option */
        duration: 250,
        complete: function () {

        }
    });
    trigger.addClass("dropdownMenu__effectIn");
}

function Popover__slides_onSave() {
    //Popover__正在保存提示
    /* 正在保存Popover不会自动关闭，需要调用保存成功才会自动关闭 */
    shwoPopovers("nf4-Popovers__on-save");
}

function Popover__slides_saveSucceed() {
    //Popover__保存成功提示
    /* 保存成功Popover 5秒后自动关闭 */
    shwoPopovers("nf4-Popovers__save-succeed", 5000);
}

function Popover__slides_saveFailure() {
    //Popover__保存失败提示
    /* 保存失败Popover 5秒后自动关闭 */
    shwoPopovers("nf4-Popovers__save-failure", 5000);
}

function Popover__slides_autoSave() {
    //Popover__自动保存提示
    /* 自动保存Popover 5秒后自动关闭 */
    shwoPopovers("nf4-Popovers__auto-save", 5000);
}