$(function () {
    // 从url中得到参数
    var playSlideId = getUrlParameter("slideId"); // 播放幻灯片的id
    var isControl = getUrlParameter("control");   // 是否进行同步控制

    $.ajax({// 向后台发送异步请求
        url: "slide/fetchSlidePlayById",  // 请求地址
        type: "POST",  // 请求方式
        cache: false, // 是否保留缓存
        data: {slideId: playSlideId},// 请求的一些参数
        dataType: "JSON", // 从后台返回的数据按什么格式进行解析
        async: false,   // 是否异步发送，false就代表用同步的方式
        success: function (data) { // 当请求成功返回数据的行数， data为从后台得到的幻灯片的数据
            // 进行真正的页面初始化，传入三个参数：打开的幻灯片的id，是否同步控制， 幻灯片的数据
            pageContentInit(playSlideId, isControl, data);
            $('.navigate-right').css("display", "block");
            $('.navigate-left').css("display", "block");
        },
        error: function () {
        }
    })
    dialogInit(isControl);// 弹出二维码
})
