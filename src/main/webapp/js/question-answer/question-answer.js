/* NF4 园丁助手 - 答题界面
 * author: BillowsTao
 * date: 2018.8.27
 */

/* 全局变量 */
var questionId // 试题 id
var publishId // 试题发布 id
var publishDate // 试题发布日期
var ipAddress // 通过搜狗API,获取当前访问 ip 地址
var timeOfNow // 当前的时间
var testeeName = '匿名' // 答题者姓名

$(function initialize () {
    /* 初始化信息 */
    questionId = getUrlParameter('questionId') // 试题 id
    publishId = getUrlParameter('publishId') // 试题发布 id
    publishDate = getUrlParameter('publishDate') // 试题发布日期
    ipAddress = returnCitySN['cip'] // 通过搜狗API,获取当前访问 ip 地址
    timeOfNow = new Date() // 当前的时间
    /* 请求试题数据 */
    $.ajax({
        url: 'question/fetchQuestionById',
        type: 'POST',
        data: {
            questionId: questionId
        },
        dataType: 'JSON',
        success: function (data) {
            if (timeOfNow <= publishDate) {
                // 装载试题
                loadData(data)
                // 绑定事件
                bindEvent()
            } else {
                // 试题超时，弹出无法答题提示
                window.alert('当前试题已过期!')
                // 关闭窗口
                window.close()
            }
        },
        error: function (e) {
        }
    })

})

function bindEvent () {
    /* 绑定事件初始化函数 */
    /* 注册选项点击事件 */
    $('.option > .option-item').bind('click', function questionSingleCheck (event) {
        /* 添加选中属性 */
        let $selectedItem = $(this); // 选中的选项
        let $siblingItems = $selectedItem.siblings() // 其他选项
        let $currentQuestion = $selectedItem.closest('.question-block') // 当前的问题
        $currentQuestion.attr('answered', 'true') // 置当前问题为已作答
        $(this).attr('itemChecked', 'true') // 置选项为选中
        $siblingItems.filter('[itemChecked="true"]').removeAttr('itemChecked')
        // 修改选项的样式
        singleApperanceChange($currentQuestion)
    })

    $('.control__submit-button').bind('click', function submit (event) {
        /* 提交试题信息到服务器 */
        // console.log($('.question-block:first .option-item[itemChecked="true"]').attr('optionId'))
        if (validate()) {
            /* 弹出对话框，提示输入姓名 */
            testeeName = window.prompt('请输入您的姓名', '')
            /* 向服务器提交数据 */
            submitData()
        } else {
            window.alert('您有题目未回答!')
        }
    })
}

function singleApperanceChange (question) {
    /* 修改选中选项的外观 */
    let $selectedItem = question.find('.option-item[itemChecked="true"]')
    let $otherItem = question.find('.option-item').not('[itemChecked="true"]')
    // 修改选中选项的图标为选中状态
    $selectedItem.find('.check-box > i').attr('class', 'fas fa-dot-circle fa-lg check-box__icon-checked')
    // 清除未选中图标的状态
    $otherItem.find('.check-box > i').attr('class', 'far fa-circle fa-lg check-box__icon')
};

function getUrlParameter (name) {
    // 获取 URL 中的参数
    let reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)')
    let r = window.location.search.substr(1).match(reg)
    if (r != null) {
        return unescape(r[2])
    } else {
        return null
    }
};

function loadData (data) {
    // 装载试题数据到页面
    let $questionBlock = $('<div class="question-block"><div class="question-header"><div class="question-title"></div></div><hr class="question-split" /><div clss="question-content"><div class="option"></div></div>')
    $questionBlock.attr('questionId', data.questionId) // 写入问题 id
    $questionBlock.find('.question-title').html(data.description) // 填充问题描述（标题）
    // console.log(data)
    /* 遍历填充问题选项 */
    data.optionList.forEach(optionItem => {
        // console.log(optionItem)
        let $questionOption = $('<div class="option-item"><div class="option-item__check-box"><span class="check-box"><i class="far fa-circle fa-lg check-box__icon"></i></span></div><div class="option-item__text"></div></div>')
        $questionOption.attr('optionId', optionItem.optionId) // 写入选项 id
        $questionOption.find('.option-item__text').html(optionItem.content) // 填充选项内容
        $questionBlock.find('.option').append($questionOption) // 添加选项
    })
    /* 添加问题块到问题流 */
    $questionBlock.appendTo($('.question-flow'))
}

function submitData () {
    /* 提交试题信息到服务器 */
    $('.control__submit-button').html('已提交')
    $('.control__submit-button').attr('disabled', 'true')
    $('.option > .option-item').unbind('click')
    /* Ajax 提交数据 */
    $.ajax({
        url: 'answer/saveAnswer',
        type: 'POST',
        data: {
            answerId: $('.question-block:first .option-item[itemChecked="true"]').attr('optionId'),
            publishId: publishId,
            ipAddress: ipAddress,
            respondentInfo: testeeName
        },
        dataType: 'JSON',
        success: function (data) {
            window.alert('试题提交成功！')
        },
        error: function () {
            window.alert('请检查网络连接')
        }
    })
}

function validate () {
    /* 试题有效性检测 */
    // 未回答的题目个数
    let blankQuestion = $('.question-flow > .question-block').not('[answered="true"]')
    if (blankQuestion.length > 0) {
        return false // 有题目未回答
    } else {
        return true // 所有题目都已回答
    }
}
