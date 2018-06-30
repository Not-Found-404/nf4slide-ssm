$(function () {
    var playSlideId = getUrlParameter("slideId");
    var isControl = getUrlParameter("control");

    $.ajax({
        url: "slide/fetchSlidePlayById",
        type: "POST",
        cache: false,
        data: {slideId: playSlideId},
        dataType: "JSON",
        async: false,
        success: function (data) {
            pageContentInit(playSlideId, isControl, data);
            $('.navigate-right').css("display", "block");
            $('.navigate-left').css("display", "block");
        },
        error: function () {
        }
    })
    dialogInit(isControl);
})