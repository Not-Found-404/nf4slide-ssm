//页面内容初始化
function pageContentInit(playSlideId, isControl, data) {
    //var json = eval('(' + data + ')');
    $(".slides").html(data.play);//填充幻灯片内容
    $("#theme").attr("href", "js/plugin/reveal.js/css/theme/" + data.theme + ".css");//读取主题
    //config顺序
    config_ = eval('(' + data.config + ')');
    // alert("config.transition:" + config_.transition);
    Reveal.initialize({
        center: false,
        transitionSpeed: config_.transitionSpeed,//换页速度
        transition: config_.transition,//换页方式
        progress: config_.progress,//是否进度条
        slideNumber: config_.slideNumber//是否显示页数
    });

    if (config_.autoSlide != "0") {
        Reveal.configure({autoSlide: config_.autoSlide});//自动播放
    }

    if (isControl == "true") {
        danmuInit(playSlideId, data.whoPlay);//弹幕初始化
        syncControlInit(playSlideId, data.whoPlay);//同步翻页初始化
    }
}

//得到参数
function getUrlParameter(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    } else {
        return null;

    }
}

// 弹幕初始化
function danmuInit(playSlideId, whoPlay) {
    // 用于控制当前是否能发送弹幕，5s内只能发一次
    canSendDamoo = true;

    /********************************************关于弹幕控制的部分*************************************************************/
    $("#send").css("display", "block");
    $("#send").click(function () {
        $("#danmu-send-place").css("display", "block");
        $("#send").css("display", "none");
    });
    $("#danmu-send-place-cancel").click(function () {
        $("#danmu-send-place").css("display", "none");
        $("#send").css("display", "block");
    });

    var damoo = new Damoo('danmuPlace', 'dm-canvas', 20);//初始化弹幕

    damoo.play();//激活弹幕
    /***************************************   websocket部分 **************************************************************/
    var bullet_websocket = null;

    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        bullet_websocket = new WebSocket("ws://zoom.qtu404.com:8080/BulletWebSocket/websocket");
    }
    else {
        alert('Not support websocket')
    }

    // 点击按钮发送弹幕
    $("#danmu-send-place-send").click(function () {

        // 判断内容是否为空
        if ($("#danmu-send-place-text").val() === "") {
            alert("请输入弹幕内容");
        }

        if (canSendDamoo === true) {
            //发送给服务器
            bullet_websocket.send(playSlideId + ":" + $("#danmu-send-place-text").val());

            // 清空弹幕输入框，免得有些逼人一直点发送
            $("#danmu-send-place-text").val("");

            // 先置为false不能发送，过5s后置为true，可以发送
            canSendDamoo = false;
            setTimeout(function () {
                canSendDamoo = true;
            }, 5000);
        } else {
            alert("发送过于频繁，请5秒之后再试");
        }
    });

    //连接发生错误的回调方法
    bullet_websocket.onerror = function () {
        alert("弹幕功能发生错误，请检查网络连接");
    };

    //连接成功建立的回调方法
    bullet_websocket.onopen = function (event) {
        console.log("弹幕链接成功");
    }

    function randomHexColor() { //随机生成十六进制颜色
        var hex = Math.floor(Math.random() * 16777216).toString(16); //生成ffffff以内16进制数
        while (hex.length < 6) { //while循环判断hex位数，少于6位前面加0凑够6位
            hex = '0' + hex;
        }
        return '#' + hex; //返回‘#'开头16进制颜色
    }

    //接收到消息的回调方法
    bullet_websocket.onmessage = function (event) {
        var msg = new Array();
        msg = event.data.split(":");
        if (playSlideId == msg[0]) {
            damoo.emit({text: msg[1], color: randomHexColor()});
        }
    }
    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        bullet_websocket.close();
    }

    //关闭连接
    function closeWebSocket() {
        websocket.close();
    }
}

// 同步控制初始化
function syncControlInit(playSlideId, whoPlay) {
    /********************************************关于换页控制的部分*************************************************************/
    var SlideNumNext = 0;
    var websocket = null;

    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
        websocket = new WebSocket("ws://alpha.qtu404.com:8080/MyWebSocket/websocket");
    } else {
        alert('Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
        console.log("同步控制连接失败");
    };

    //连接成功建立的回调方法
    websocket.onopen = function (event) {
        console.log("同步控制连接成功");
    }

    //连接关闭的回调方法
    websocket.onclose = function () {
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        websocket.close();
    }

    //关闭连接
    function closeWebSocket() {
        websocket.close();
    }

    //发送消息
    function send() {
        var message = SlideNumNext;
        websocket.send(message);
        console.log("发送换页消息:" + message);
    }

    $(".navigate-up").css("display", "none");
    $(".navigate-down").css("display", "none");

    $(".navigate-left").css("display", "none");
    $(".navigate-right").css("display", "none");


    if (whoPlay == "owner") {// 只有拥有者有权发出换页消息
        //发送消息
        Reveal.addEventListener('slidechanged', function (event) {
            // event.previousSlide, event.currentSlide, event.indexh, event.indexv
            SlideNumNext = playSlideId + ":" + event.indexh;
            send();
        });
    }

    websocket.onmessage = function (event) {
        var msg = new Array();
        msg = event.data.split(":");
        if (playSlideId == msg[0]) {
            console.log("执行换页");
            Reveal.slide(msg[1]);
        }
    }
}

//二维码初始化
function dialogInit(isControl) {
    $("#dialog-form").dialog({
        autoOpen: false,
        height: 550,
        width: 350,
        modal: true,
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
    var dataUriPngImage = document.createElement("img"),
        u = window.location.href,
        s = QRCode.generatePNG(u, {
            ecclevel: "M",
            format: "html",
            fillcolor: "#FFFFFF",
            textcolor: "#373737",
            margin: 4,
            modulesize: 8
        });
    dataUriPngImage.src = s;
    dataUriPngImage.height = 300;
    dataUriPngImage.widht = 300;
    $("#dialog-form").append("<div style=\"font-size:130%\"id=\"msg\"></div>");
    document.getElementById('dialog-form').appendChild(dataUriPngImage);
    $("#dialog-form").append("<div style=\"font-size:130%\"align=\"center\">扫描上面的二维码，或者复制以下链接，将幻灯片分享给他人！</div><div style=\"font-size:130%\"align=\"center\">" + window.location.href + "</div>");
    if (isControl == "true") {
        $("#msg").html("分享幻灯片，并控制观看者的播放");
    }
    $("#dialog-form").dialog("open");
}

/**
 * 考勤初始化
 */
function attendanceIdInit(attendanceId) {
    var identify = prompt("请输入您的身份信息", "如：学号_姓名");
    var totalTime = 0; // 总观看时间
    var exitTimes = 0; // 退出次数
    var looptervalTime = 1000 * 9; // 定时向服务器推送数据的间隔时间
    var loopFlag = true; // 是否想后端发送消息的标志，如果某次传回来超时，那就不搞了
    var sendDateThreshold = 1000 * 6;// 向后端推送消息的总时间阙值，若totalTime小于这个值，就留着下次推
    // 最近一次停留开始计算的时间
    var beginTime = new Date();
    var viewFlag = true; // 当前是否在看
    //
    if (identify != null && identify !== "") {
        // 当页面失去焦点
        window.onblur = function () {
            // 总的停留时间 += 当前的时间 - 上次停留开始的时间
            totalTime += new Date().getTime() - beginTime.getTime();
            console.log("第" + exitTimes + "次退出");
            // 退出次数增加
            exitTimes++;
            console.log("一共观看了" + totalTime / 1000 + "s");
            viewFlag = false;
        };

        // 当页面重新获得焦点
        window.onfocus = function () {
            // 更新停留开始时间
            beginTime = new Date();
            viewFlag = true;
        };

        // 定时任务，推送数据到后台
        var interval = setInterval(function () {
            if (!loopFlag) {
                clearInterval(interval);
            }
            if (viewFlag) {
                totalTime += new Date().getTime() - beginTime.getTime();
                beginTime = new Date();
            }
            console.log("数据\n本次 " + identify + "共：" + exitTimes + "次退出" + "，观看了" + totalTime / 1000 + "s");
            if (totalTime > sendDateThreshold) {
                $.ajax({// 向后台发送异步请求
                    url: "api/statistics/view/saveOrUpdate",  // 请求地址
                    type: "GET",  // 请求方式
                    data: {
                        exitTime: exitTimes,
                        totalTime: totalTime,
                        attendanceId: attendanceId,
                        identify: identify
                    },// 请求的一些参数
                    success: function (data) {
                        totalTime = 0;// 重置
                        exitTimes = 0;// 重置
                        console.log(data);
                        if (data == "timeout") {
                            loopFlag = false;
                            clearInterval(interval);
                        }
                    }
                });
            }
        }, looptervalTime);
    }
}