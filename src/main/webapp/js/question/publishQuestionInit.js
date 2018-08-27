$(function () {

    var questionId = getUrlParameter("questionId");
    var publishId = getUrlParameter("publishId");
    var publishDate = getUrlParameter("publishDate")
    var ipAddress = returnCitySN["cip"]; // 获取用户的IP地址
    var timeOfNow = new Date();
    dialogInit();
    $.ajax({
        url: "question/fetchQuestionById",
        type: "POST",
        cache: false,
        data: {questionId: questionId},
        dataType: "JSON",
        async: false,
        success: function (data) {
            if ( timeOfNow > publishDate){
                $('.information').html("本题目已经超时，无法进行答题!!");
            }else{
                publishPageInit(data);
            }

        },
        error: function () {
        }
    });


    $("#btn-submit").click(function () {
        $.ajax({
            url: "answer/saveAnswer",
            type: "POST",
            cache: false,
            data: {
                answerId:$('input[name="option"]:checked ').val(),
                publishId: publishId,
                ipAddress: ipAddress,
                respondentInfo:$('#respondent-information').val()
            },
            dataType: "JSON",
            async: false,
            success: function (data) {
               alert("成功提交");
               $('.reveal').html("");
            },
            error: function () {
                alert("提交失败");
            }
        })
    });
})