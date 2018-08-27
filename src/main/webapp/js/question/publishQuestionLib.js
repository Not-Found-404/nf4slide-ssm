//页面内容初始化
function publishPageInit(data) {
    Reveal.initialize({
        center: false
    });

    $("#question-description").html("<h3><span>" + data.description + "</span></h3>");
    for (var i in data.optionList){
        $("#question-optionList").append("<div class=\"optionList\"><input type=\"radio\" name=\"option\" id=\"" + data.optionList[i].optionId + "\" value=" + data.optionList[i].optionId + ">" + "<label for=\"" + data.optionList[i].optionId + "\">" +  data.optionList[i].content + "</div>")
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
//填写答题者信息
function dialogInit() {
    var respondentInfo = null;
    $("#dialog-form").append("<div class=\"input-user-information\">\n" +
        "                姓名:<input type=\"text\" id=\"respondent-information\" placeholder=\"请输入您的信息\">\n" +
        "            </div>");

    $("#dialog-form").dialog({
        autoOpen: true,
        height: 200,
        width: 350,
        modal: true,
        show: {
            effect: "blind", // 百叶窗效果，从上到下显示
            duration: 500
        },
        hide: {
            effect: "explode", // 爆发，碎片效果
            duration: 500
        },
        buttons: {
            "确定": function() {
                 respondentInfo = $('#respondent-information').val();
                if(respondentInfo != null && respondentInfo != ''){
                    // $("#dialog-form").append("填入信息" + respondentInfo);
                    $( this ).dialog( "close" );
                }
            }
        }
    });


}