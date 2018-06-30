/**
 * 编辑界面初始化
 */


var editHtml = new Array();
var slideId = null;
/**
 * @author wildhunt_unique
 */
$(function () {
    //播放设置弹出框 初始化
    settingDialogInit();

    //保存按钮绑定
    $("#nf4-menu__save").click(function () {
        saveSlide();
    });

    //cdeditor的设置，具体干嘛的我也忘了，查查官方文档
    CKEDITOR.disableAutoInline = true;

    /*组件内容初始化*/

    var flag = "";
    var reg = new RegExp("(^|&)" + "slideId" + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        slideId = unescape(r[2]);
        flag = "true";
    } else {
        slideId = "";
        flag = "";
    }
    /**
     * 播放按钮绑定
     */
    $("#nf4-menu__play_slides").click(function () {
        window.open("toPlayPage?slideId=" + slideId);
    });



    if (flag != "") {
        $.ajax({
            type: "POST",
            url: "slide/fetchSlideContentById",
            async: false,
            dataType: "JSON",
            data: {
                slideId: slideId
            },
            success: function (msg) {
                editHtml = msg.content.split("<--nf4-->");
                for (i = 0; i < editHtml.length - 1; i++) {
                    addSlide(".nf4-workspace", ".nf4-browser-view", "reAdd", editHtml[i]);
                }
                changeSilde("tabs-1");
            }
        });
    }
    $.ajax({
        url: "user/fetchLoginInfo",
        type: "POST",
        dataType: "JSON",
        success: function (data) {
            //var json = eval('(' + data + ')');
            $(".nf4-navbar__user-name").html(data.username);
            if (data.avator != "") {
                $('.user-avator__img').attr("src", data.avator + "?x=" + Math.random());
            }
        },
        error: function (e) {
            alert("请检查网络连接！！");
        }
    })

})

/*** 王海涛 wht-start ***/
$(function responseResizeLayout() {
    /*调整内容自适应窗口高度函数*/
    var h = $(window).outerHeight();
    var w = $(window).outerWidth();
    /* 主面板大小控制 */
    $(".nf4-main").height(h - 48);
    /* 滑动菜单大小控制 */
    $(".nf4-dropdownMenu").height(h - 48);
    $(".nf4-dropdownMenu").width(w);
    $(window).resize(function () {
        //窗口尺寸改变获取窗口大小
        var h = $(window).outerHeight();
        var w = $(window).outerWidth();
        /* 主面板大小控制 */
        $(".nf4-main").height(h - 48);
        /* 滑动菜单大小控制 */
        $(".nf4-dropdownMenu").height(h - 48);
        $(".nf4-dropdownMenu").width(w);
        //重新设置工作区位置
        workspaceLocation();
    });
    //重新设置工作区位置
    workspaceLocation();
    console.info("窗口高度：" + h + "; 窗口宽度：" + w);
});
/*** 王海涛 wht-end ***/

$(function bindButton() {

    /*** 王海涛 wht-start ***/

    //绑定菜单按钮
    //元素栏切换按钮
    $("#nf4-menu-element").bind("click", function () {
        $(".nf4-element").stop(true, true).toggle(100, "swing");
        workspaceLocation();
    });
    //全屏按钮
    $("#nf4-menu__full_screen").bind("click", function () {
        //调用全屏函数
        if ($("#nf4-menu__full_screen i").hasClass("fa-expand")) {
            //进入全屏模式
            fullScreen();
            $("#nf4-menu__full_screen i").removeClass("fa-expand").addClass("fa-compress");
        } else {
            //退出全屏模式
            exitFullScreen();
            $("#nf4-menu__full_screen i").removeClass("fa-compress").addClass("fa-expand");
        }
    });

    /*** 王海涛 wht-end ***/

    //绑定workplace工作区
    //幻灯片编辑区域
    $(".nf4-workspace").tabs();
    //幻灯片浏览区绑定
    $("#nf4-add-slides").click(function () {
        //绑定幻灯片添加按钮
        addSlide(".nf4-workspace", ".nf4-browser-view"); //替换#workplace和#slideSelect
    });

    //绑定幻灯片浏览区拖动功能
    $(".nf4-browser-view").sortable({
        placeholder: "ui-state-highlight"
    });
    $(".nf4-browser-view").disableSelection();

    //组件按钮事件绑定
    //组件元素区域
    //添加文本
    $("#nf4-element__text").click(function () {
        //绑定添加文本按钮
        addTextToSlide(".show-active");
    });
    //添加形状
    $(".index-svg").click(function () {
        addShapeToSlide(".show-active", this);
    });

    //添加代码
    $("#nf4-element__code").click(function () {
        //绑定添加文本按钮
        addCodeToSlide(".show-active");
    });


    /*** 王海涛 wht-start ***/
    //popovers组件事件绑定
    //popovers关闭按钮绑定
    $(".nf4-popovers-item__close").bind("click", function () {
        hidePopover();
    });

    /* 添加书签函数 */
    $("#menu__favorite").click(function () {
        if (window.sidebar) {
            // Mozilla Firefox Bookmark
            window.sidebar.addPanel(location.href, document.title, "");
        } else if (window.external) {
            // IE Favorite
            window.external.AddFavorite(location.href, document.title);
        } else if (window.opera && window.print) {
            // Opera Hotlist
            this.title = document.title;
            return true;
        }
        alert("请按下Ctrl+D键保存书签");
    });
    /*** 王海涛 wht-end ***/
});

/* 工具栏提示区域 */
$(function dropDownMenu() {
    //下拉菜单函数
    $(".nf4-navbar-button__user").bind("click", function () {
        if ($(".nf4-navbar-button__user").hasClass("dropdownMenu__effectIn")) {
            dropDownMenu_hide();
        } else {
            dropDownMenu_show($(this));
        }
    });

    $("#nf4-navbar__settings").bind("click", function () {
        dropDownMenu_hide();
    });

    $("#nf4-navbar__help").bind("click", function () {
        dropDownMenu_hide();
    });

    /* function area */
    $(".nf4-dropdownMenu").bind("click", function () {
        dropDownMenu_hide();
    });

    $(".nf4-dropdownMenu__control").bind("click", function (event) {
        event.stopPropagation();
    });

    $(".dropdown-panel__close").bind("click", function () {
        dropDownMenu_hide();
    });
});

/* 工具提示区 */
$(function activePopovers() {
    /* 菜单栏提示 */
    //元素切换按钮
    $("#nf4-menu-element").popover({
        animate: true,
        trigger: 'hover',
        container: 'body',
        placement: 'auto',
        title: '切换元素栏',
        content: '切换显示或隐藏元素栏'
    });
    //播放按钮
    $("#nf4-menu__play_slides").popover({
        animate: true,
        trigger: 'hover',
        container: 'body',
        placement: 'auto',
        title: '播放',
        content: '播放幻灯片，进入播放界面'
    });
    //全屏按钮
    $("#nf4-menu__full_screen").popover({
        animate: true,
        trigger: 'hover',
        container: 'body',
        placement: 'auto',
        title: '全屏',
        content: '全屏模式，体验更佳的操作效果'
    });
    //保存按钮
    $("#nf4-menu__save").popover({
        animate: true,
        trigger: 'hover',
        container: 'body',
        placement: 'auto',
        title: '保存',
        content: '将制作的幻灯片保存到云端，随时随地访问'
    });
    //保存书签
    $("#menu__favorite").popover({
        animate: true,
        trigger: 'hover',
        container: 'body',
        placement: 'auto',
        title: '收藏',
        content: '将本网站保存至收藏栏，便于下次访问'
    });
    //分享
    $("#menu__share").popover({
        animate: true,
        trigger: 'hover',
        container: 'body',
        placement: 'auto',
        title: '分享',
        content: '将你制作的幻灯片分享给朋友'
    });

    /* 元素栏提示 */
    //文本
    $("#nf4-element__text").popover({
        animate: true,
        trigger: 'hover',
        container: 'body',
        placement: 'auto',
        title: '文本',
        content: '添加文本内容到幻灯片，你可以设置文字的颜色、大小、字体等属性'
    });
    //图片
    $("#nf4-element__image").popover({
        animate: true,
        trigger: 'hover',
        container: 'body',
        placement: 'auto',
        title: '图片',
        content: '添加图片到幻灯片，你可以设置文字的大小、透明度等属性'
    });
    //形状
    $("#nf4-element__shape").popover({
        animate: true,
        trigger: 'hover',
        container: 'body',
        placement: 'auto',
        title: '形状',
        content: '添加形状到幻灯片'
    });
    //代码
    $("#nf4-element__code").popover({
        animate: true,
        trigger: 'hover',
        container: 'body',
        placement: 'auto',
        title: '代码',
        content: '添加代码段到幻灯片，支持代码高亮'
    });

    /* 浏览界面 */
    //新建幻灯片按钮
    $("#nf4-add-slides").popover({
        animate: true,
        trigger: 'hover',
        container: 'body',
        placement: 'auto',
        title: '新建幻灯片',
        content: '创建一张新的幻灯片'
    });

});

/* 页面启动处理 */
$(function onStartup() {
    //从服务器得到幻灯片信息


    //启动出发添加幻灯片事件
    if (editHtml.length != 1) {
        textIdExtend = findMaxID('nf4-text');
        codeIdExtend = findMaxID('nf4-code');
        imageExId = findMaxID('nf4-image');
        shapeExId = findMaxID('nf4-shape');
        drawExId = findMaxID('nf4-draw');
        lineExId = findMaxID('nf4-line');
        addEvent();
    } else {
        addSlide(".nf4-workspace", ".nf4-browser-view");
    }

    //初始化页面Popovers事件
    hidePopover();
    /*图片属性设置界面事件处理*/
    scale = function (btn, bar, title) {
        this.btn = document.getElementById(btn);
        this.bar = document.getElementById(bar);
        this.title = document.getElementById(title);
        this.step = this.bar.getElementsByTagName("DIV")[0];
        this.init();
    };
    scale.prototype = {
        init: function () {
            var f = this,
                g = document,
                b = window,
                m = Math;
            f.btn.onmousedown = function (e) {
                var x = (e || b.event).clientX;
                var l = this.offsetLeft;
                var max = f.bar.offsetWidth - this.offsetWidth;
                g.onmousemove = function (e) {
                    var thisX = (e || b.event).clientX;
                    var to = m.min(max, m.max(-2, l + (thisX - x)));
                    f.btn.style.left = to + 'px';
                    f.ondrag(m.round(m.max(0, to / max) * 100), to);
                    b.getSelection ? b.getSelection().removeAllRanges() : g.selection.empty();
                    changeProperty();
                };
                g.onmouseup = new Function('this.onmousemove = null');
            };
        },
        ondrag: function (pos, x) {
            this.step.style.width = Math.max(0, x) + 'px';
            this.title.innerHTML = pos + '%';

        }
    };
    new scale('btn', 'bar', 'title');
    new scale('btn2', 'bar2', 'title2');
    new scale('btn4', 'bar4', 'title4');

    function changeProperty() {
        $("img[data-selected='true']").css("opacity", 1 - ($("#title").text().replace(/[^0-9]/ig, "") / 100));
        var test2 = $("#title4").text().replace(/[^0-9]/ig, "") / 100 * 360;
        $("img[data-selected='true']").css("filter", "hue-rotate(" + test2 + "deg)");
//		var test1 = $("#title3").text();
//		$("img[data-selected='true']").css("filter","grayscale("+test1+")");
//		$("img[data-selected='true']").css("-webkit-filter","grayscale("+test1+")");
        var test = $("#title2").text();
        $("img[data-selected='true']").css("filter", "saturate(" + test + ")");
        $("img[data-selected='true']").css("-webkit-filter", "saturate(" + test + ")");
    }

    $("#ImagePropertyButton").click(function () {
        $("img[data-selected='true']")[0].setAttribute("data-selected", "false");
    });
    //失去焦点隐藏拖动点
    document.onmousedown = ShowOrNot;

    function ShowOrNot(e) {
        var a = $(e.target).parents().hasClass("ui-draggable") || $(e.target).hasClass("ui-draggable");

        if (a) {
            if ($(e.target).hasClass("ui-resizable-handle") && $(e.target).parents().hasClass("nf4-line")) {
                $(e.target).parents(".ui-tabs-panel").mousemove(function () {
                    var wid = $(e.target).parent(".nf4-line").css("width");
                    var hei = $(e.target).parent(".nf4-line").css("height");
                    $(e.target).prev("svg").attr("viewBox", "0 0 " + wid.replace(/[^0-9]/ig, "") + " " + hei.replace(/[^0-9]/ig, ""));
                    $(e.target).parent(".nf4-line").find("line").attr({
                        x1: "0",
                        y1: hei.replace(/[^0-9]/ig, ""),
                        x2: wid.replace(/[^0-9]/ig, ""),
                        y2: "0"
                    });
                });
            }
            if ($(e.target).parent().hasClass("nf4-text") || $(e.target).hasClass("nf4-text")) {
                $(".nf4-text").css({
                    border: "none"
                });
                $(e.target).parent(".nf4-text").css({
                    border: "1px dashed #000000"
                });
            } else {
                $(".nf4-text").css({
                    border: "none"
                });
            }
            $(".ui-resizable-handle").css("display", "none");
            $(e.target).parents(".ui-draggable").find(".ui-resizable-handle").css("display", "inline");

        } else {
            $(".ui-resizable-handle").css("display", "none");
            $(".nf4-text").css({
                border: "none"
            });
        }
    }
});


/* 侧边菜单栏切换 */
// $(function menuBar(){
//     /* 切换侧边浏览菜单 */

//     按键测试
//     $(".nf4-element__group>.btn:eq(5)").bind("click",function click(){
//         /* 元素得到焦点事件 */
//         if($(".nf4-browser").hasClass("nf4-browser__menu-bar")){

//         }else{
//             $(".nf4-browser-view").stop(true,true).toggle(250,"swing");
//             $(".nf4-menu-bar").stop(true,true).fadeIn(300,"swing");
//             $(".nf4-browser-control").stop(true,true).toggle(300,"swing");
//             $(".nf4-browser").addClass("nf4-browser__menu-bar");
//         }
//     })

//     $(".nf4-element__group>.btn:eq(6)").bind("click",function click(){
//         /* 元素失去焦点事件 */
//         if($(".nf4-browser").hasClass("nf4-browser__menu-bar")){
//             $(".nf4-browser").removeClass("nf4-browser__menu-bar");
//             $(".nf4-browser-view").stop(true,true).toggle(250,"swing");
//             $(".nf4-menu-bar").stop(true,true).fadeOut(300,"swing");
//             $(".nf4-browser-control").stop(true,true).toggle(300,"swing");
//         }
//     })

//     /* 侧边菜单组件绑定 */
//     var nf4_element__Height = $(".nf4-element-panel .nf4-element-panel__spinner").spinner();

// });
