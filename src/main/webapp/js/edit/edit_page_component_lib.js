/**
 * 此页js为幻灯片组件的库函数
 */

/**
 *各个组件的id的额外编号
 * @type {number}
 */
var slideIdEx = 1;
var videoExId = 1;
var textIdExtend = 1;
var codeIdExtend = 1;
var imageExId = 1;
var shapeExId = 1;
var drawExId = 1;
var lineExId = 1;

/**
 * 什么玩意 我也不太清楚
 */
var time_clock;

/**
 * 这...谁写的... 是什么玩意
 * 应该和创建菜单有关，右键点击菜单的？
 */
jQuery.fn.contextPopup = function (menuData) {
    // Define default settings
    var settings = {
        contextMenuClass: 'contextMenuPlugin',
        gutterLineClass: 'gutterLine',
        headerClass: 'header',
        seperatorClass: 'divider',
        title: '',
        items: []
    };

    // merge them
    $.extend(settings, menuData);

    // Build popup menu HTML
    function createMenu(e) {
        var menu = $('<ul class="' + settings.contextMenuClass + '"><div class="' + settings.gutterLineClass + '"></div></ul>')
            .appendTo(document.body);
        if (settings.title) {
            $('<li class="' + settings.headerClass + '"></li>').text(settings.title).appendTo(menu);
        }
        settings.items.forEach(function (item) {
            if (item) {
                var rowCode = '<li><a href="#"><span></span></a></li>';
                // if(item.icon)
                //   rowCode += '<img>';
                // rowCode +=  '<span></span></a></li>';
                var row = $(rowCode).appendTo(menu);
                if (item.icon) {
                    var icon = $('<img>');
                    icon.attr('src', item.icon);
                    icon.insertBefore(row.find('span'));
                }
                row.find('span').text(item.label);
                if (item.action) {
                    row.find('a').click(function () {
                        item.action(e);
                    });
                }
            } else {
                $('<li class="' + settings.seperatorClass + '"></li>').appendTo(menu);
            }
        });
        menu.find('.' + settings.headerClass).text(settings.title);
        return menu;
    }

    // On contextmenu event (right click)
    this.bind('contextmenu', function (e) {
        var menu = createMenu(e)
            .show();

        var left = e.pageX + 5, /* nudge to the right, so the pointer is covering the title */
            top = e.pageY;
        if (top + menu.height() >= $(window).height()) {
            top -= menu.height();
        }
        if (left + menu.width() >= $(window).width()) {
            left -= menu.width();
        }

        // Create and show menu
        menu.css({
            zIndex: 1000001,
            left: left,
            top: top
        })
            .bind('contextmenu', function () {
                return false;
            });

        // Cover rest of page with invisible div that when clicked will cancel the popup.
        var bg = $('<div></div>')
            .css({
                left: 0,
                top: 0,
                width: '100%',
                height: '100%',
                position: 'absolute',
                zIndex: 1000000
            })
            .appendTo(document.body)
            .bind('contextmenu click', function () {
                // If click or right click anywhere else on page: remove clean up.
                bg.remove();
                menu.remove();
                return false;
            });

        // When clicking on a link in menu: clean up (in addition to handlers on link already)
        menu.find('a').click(function () {
            bg.remove();
            menu.remove();
        });

        // Cancel event, so real browser popup doesn't appear.
        return false;
    });

    return this;
};

/**
 * 添加一页幻灯片
 * @param workplace 在哪个div添加
 * @param view 存放缩略图的div
 * @param method 添加方法 up向上添加  down向下添加  reAdd从数据库重新添加
 * @param htmlSlide 从数据库重新添加的html代码
 */
function addSlide(workplace, view, method, htmlSlide) {
    var slide_id = "slide-" + slideIdEx;
    var tabs_id = "tabs-" + slideIdEx;
    var ExId = slideIdEx;
    //先加tabs的div loginBackground
    $(workplace).append("<div id=\"" + tabs_id + "\" style=\"background:url(img/tableLine.png) no-repeat 1px 2px\"></div>");
    $("#" + tabs_id).addClass(" ui-tabs-panel ui-corner-bottom ui-widget-content ");
    $("#" + tabs_id).css("height", "100%");
    $("#" + tabs_id).css("width", "100%");
    $("#" + tabs_id).css("position", "relative");
    $("#" + tabs_id).css("dispaly", "none");
    $("#" + tabs_id).attr("aria-labelledby", "ui-id-" + ExId);
    $("#" + tabs_id).attr("role", "tabpanel");
    $("#" + tabs_id).attr("aria-hidden", "true");

    //再加slide
    if (method == null) {
        $(view).append("<div id=\"slide-" + ExId + "\"></div>");
    } else if (method == "up") {
        $(view).before("<div id=\"slide-" + ExId + "\"></div>");
    } else if (method == "down") {
        $(view).after("<div id=\"slide-" + ExId + "\"></div>");
    } else if (method == "reAdd") {
        $(view).append("<div id=\"slide-" + ExId + "\"></div>");
        $("#" + tabs_id).html(htmlSlide);
    }
    $("#slide-" + ExId).addClass("nf4-slide");

    //计算宽度、高度和比例
    var viewWidth = $("#slide-" + ExId).width();
    var workspaceHeight = $("#" + tabs_id).height();
    var workspaceWidth = $("#" + tabs_id).width();
    var viewHeight = viewWidth * workspaceHeight / workspaceWidth;
    var proportion = viewWidth / workspaceWidth;

    $("#slide-" + ExId).css("height", viewHeight);

    $("#slide-" + ExId).append("<div id=\"view-" + ExId + "\"></div>");
    $("#view-" + ExId).addClass("nf4-view");
    $("#view-" + ExId).css("transform", "scale(" + proportion + "," + proportion + ")");

    $("#slide-" + ExId).click(function () {
        changeSilde(tabs_id);
    });

    $("#" + tabs_id).mouseup(function () {
        $(".show-active .nf4-image").each(function () {
            $(this).css("height", $(this).height());
            $(this).css("width", $(this).width());
        });
        var val = $("#" + tabs_id).html();
        $("#view-" + ExId).html(val);
    });

    $("#slide-" + ExId).contextPopup({
        //title: '选项',
        items: [
            {
                label: '删除此页幻灯片',
                icon: 'img/icons/nf4-view-delete.png',
                action: function () {
                    $("#slide-" + ExId).remove();
                    $("#tabs-" + ExId).remove()
                }
            },
            {
                label: '向上添加幻灯片',
                icon: 'img/icons/nf4-view-up.png',
                action: function () {
                    addSlide(workplace, "#slide-" + ExId, "up")
                }
            },
            //null, /* null can be used to add a separator to the menu items */
            {
                label: '向下添加幻灯片',
                icon: 'img/icons/nf4-view-down.png',
                action: function () {
                    addSlide(workplace, "#slide-" + ExId, "down")
                }
            },
        ]
    });

    changeSilde(tabs_id);
    slideIdEx++;

    $(".nf4-slide").popover({
        animate: true,
        trigger: 'hover',
        container: 'body',
        placement: 'auto',
        content: '按住鼠标拖动调整顺序，右键删除/添加幻灯片'
    });

}

/**
 * 刷新缩略图的幻灯片的内容
 * @param id 被刷新的幻灯片的额外id
 */
function changeSilde(id) {
    var ExId = $(".show-active").attr("id");
    $(".show-active .nf4-image").each(function () {
        $(this).css("height", $(this).height());
        $(this).css("width", $(this).width());
    });
    if (ExId == null) {
    } else {
        ExId = ExId.substr(5);
        var val = $(".show-active").html();
        $("#view-" + ExId).html(val);
    }

    $(".show-active").attr("aria-hidden", "true");
    $(".show-active").css("display", "none");
    $(".show-active").removeClass("show-active");
    $("#" + id).addClass("show-active");
    $("#" + id).attr("aria-hidden", "false");
    $("#" + id).css("display", "block");
}

/**
 * 往id中添加一个文本组件  id可以是类名 亦可以是id,用jquery的选择器的规则
 * @param id div的选择器
 */
function addTextToSlide(id) {
    var idExtend = textIdExtend;
    textIdExtend++;
    var workWidth = $(id).width() * 1.0 / 2;

    /*-------------------------------------先添加外层外面缩放和移动的div-----------------------------------*/
    $(id).append("<div id=\"nf4-text-" + idExtend + "\"></div>");
    $("#nf4-text-" + idExtend).addClass("nf4-text nf4-module"); //增加指定类名
    $("#nf4-text-" + idExtend).css("width", workWidth);
    $("#nf4-text-" + idExtend).css("position", "absolute");
    //再往外层div里面添加具体负责移动、缩放的点, 以及编辑功能的div

    /*----------------------被取消的拖动方式---------------------*/
    //$("#nf4-text-" + idExtend).append("<div class=\"draggable-handle\"></div>");//负责移动的点
    //$("#nf4-text-" + idExtend).draggable({handle: ".draggable-handle",containment:id, scroll: false});//指定拖动的div
    /*----------------------被取消的拖动方式---------------------*/


    //负责ckeditor编辑的div
    $("#nf4-text-" + idExtend).append("<div id=\"nf4-text-editor-" + idExtend + "\" ></div>");
    $("#nf4-text-editor-" + idExtend).addClass("nf4-text-editor");


    //负责缩放的8个div
    $("#nf4-text-" + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-nw\"></div>");
    $("#nf4-text-" + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-ne\"></div>");
    $("#nf4-text-" + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-sw\"></div>");
    $("#nf4-text-" + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-se\"></div>");
    $("#nf4-text-" + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-n\"></div>");
    $("#nf4-text-" + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-s\"></div>");
    $("#nf4-text-" + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-e\"></div>");
    $("#nf4-text-" + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-w\"></div>");

    /*得到焦点和失去焦点时候光标改变*/
    $("#nf4-text-editor-" + idExtend).dblclick(function () {
        $("#nf4-text-editor-" + idExtend).css("cursor", "text");
        event.stopPropagation();
    });
    $("#nf4-text-editor-" + idExtend).blur(function () {
        $("#nf4-text-editor-" + idExtend).css("cursor", "move");
        $("#nf4-text-" + idExtend).draggable('option', 'disabled', false); //失去焦点后,变为可以拖动
    });

    //激活ckeditor
    CKEDITOR.inline("nf4-text-editor-" + idExtend, {
        // Allow some non-standard markup that we used in the introduction.
        //extraAllowedContent: 'a(documentation);abbr[title];code',
        removePlugins: 'stylescombo',
        //extraPlugins: 'sourcedialog',
        // Show toolbar on startup (optional).
        title: "文本框",
        fontSize_sizes: "16/16px;24/24px;32/32px;48/48px;72/72px;96/96px;120/120px",
        font_names: "宋体;黑体;微软雅黑;仿宋;方正兰亭黑简体;方正行楷_GBK;方正硬笔行书简体;华文隶书;仿宋_GB2312;Times New Roman",
        removePlugins: 'codesnippet,codemirror',
        startupFocus: true
    });
    $("#nf4-text-editor-" + idExtend).attr("contenteditable", "true");

    //最后外层div指定内层哪些div负责缩放和移动
    $("#nf4-text-" + idExtend).resizable({
        handles: {
            'nw': '.ui-resizable-nw',
            'ne': '.ui-resizable-ne',
            'sw': '.ui-resizable-sw',
            'se': '.ui-resizable-se',
            'n': '.ui-resizable-n',
            'e': '.ui-resizable-e',
            's': '.ui-resizable-s',
            'w': '.ui-resizable-w'
        },
        containment: id
    });

    //新的拖动
    $("#nf4-text-" + idExtend)
        .draggable({
            containment: id,
            scroll: false
        })
        .on('click', function () {
            if ($(this).is('.ui-draggable-dragging')) {
                return;
            } else {
                $(this).draggable('option', 'disabled', true);
            }

        });
    //右键菜单
    $("#nf4-text-" + idExtend).contextPopup({
        //title: '选项',
        items: [
            {
                label: '删除此文本框',
                icon: 'img/icons/nf4-view-delete.png',
                action: function () {
                    $("#nf4-text-" + idExtend).remove();
                }
            },
            {
                label: '层次:置顶',
                icon: 'img/icons/nf4-view-up.png',
                action: function () {
                    $("#nf4-text-" + idExtend).css('z-index', searchMaxZindex() + 1);
                }
            },
            //null, /* null can be used to add a separator to the menu items */
            {
                label: '层次:置底',
                icon: 'img/icons/nf4-view-down.png',
                action: function () {
                    $("#nf4-text-" + idExtend).css('z-index', searchMinZindex() - 1);
                }
            },
        ]
    });
    $("#nf4-text-" + idExtend).css('z-index', searchMaxZindex() + 1);

}

/**
 * 添加图片
 * @param id 哪个div里加 div的选择器
 * @param imgSrc img标记对内，src的内容
 */
function addImageToSlideByUpLoad(id, imgSrc) {
    var idExtend = imageExId;
    var idBase = "nf4-image-";
    imageExId++;


    /*-------------------------------------先添加外层外面缩放和移动的div-----------------------------------*/
    $(id).append("<div id=\"nf4-image-" + idExtend + "\"></div>");
    $("#" + idBase + idExtend).addClass("nf4-image nf4-module"); //增加指定类名
    $("#" + idBase + idExtend).css("position", "absolute");


    //再往外层div里面添加具体负责移动、缩放的点, 以及编辑功能的div

    //负责图片的image
    $("#" + idBase + idExtend).append("<img data-selected=\"false\" id=\"imgPre-" + idExtend + "\">");
    $("#imgPre-" + idExtend).attr("src", imgSrc);

    //负责缩放的8个div
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-nw\"></div>");
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-ne\"></div>");
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-sw\"></div>");
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-se\"></div>");
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-n\"></div>");
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-s\"></div>");
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-e\"></div>");
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-w\"></div>");

    //适应大小和位置

    $("#imgPre-" + idExtend).css("height", "100%");
    $("#imgPre-" + idExtend).css("width", "100%");
    $("#imgPre-" + idExtend).css("cursor", "move");
    $("#imgPre-" + idExtend).addClass("nf4-image-editor");
    //最后外层div指定内层哪些div负责缩放和移动

    $("#" + idBase + idExtend).resizable({
        handles: {
            'nw': '.ui-resizable-nw',
            'ne': '.ui-resizable-ne',
            'sw': '.ui-resizable-sw',
            'se': '.ui-resizable-se',
            'n': '.ui-resizable-n',
            'e': '.ui-resizable-e',
            's': '.ui-resizable-s',
            'w': '.ui-resizable-w'
        },
        containment: id
    });

    /*新的拖动方式*/
    $("#" + idBase + idExtend)
        .draggable({
            containment: id,
            scroll: false
        });
    //右键菜单
    $("#" + idBase + idExtend).contextPopup({
        //title: '选项',
        items: [

            {
                label: '删除此图片',
                icon: 'img/icons/nf4-view-delete.png',
                action: function () {
                    $("#" + idBase + idExtend).remove();
                }
            },
            {
                label: '层次:置顶',
                icon: 'img/icons/nf4-view-up.png',
                action: function () {
                    $("#" + idBase + idExtend).css("z-index", searchMaxZindex() + 1)
                }
            },
            //null, /* null can be used to add a separator to the menu items */
            {
                label: '层次:置底',
                icon: 'img/icons/nf4-view-down.png',
                action: function () {
                    $("#" + idBase + idExtend).css("z-index", searchMinZindex() - 1)
                }
            },
            {
                label: '属性',
                icon: 'img/icons/nf4-attr.png',
                action: function () {
                    $("#ImageProperty").trigger("click");
                    $("#" + idBase + idExtend).find("img")[0].setAttribute("data-selected", "true")
                }
            }
        ]
    });
    $("#" + idBase + idExtend).css('z-index', searchMaxZindex() + 1);
    /*8月23日夜改,添加鼠标右击事件 end edit*/
}

/**
 * 寻找当前幻灯片中 组件最大的z-index
 */
function searchMaxZindex() {
    var MaxZ = 50;
    $(".show-active .ui-resizable").each(function () {
        if (MaxZ <= $(this).css("z-index") && $(this).css("z-index") != "auto") {
            MaxZ = $(this).css("z-index");
        }
    });
    return parseInt(MaxZ);
}

/**
 * 寻找当前幻灯片中 组件最小的z-index
 */
function searchMinZindex() {
    var MinZ = 1000;
    $(".show-active .ui-resizable").each(function () {
        if (MinZ >= $(this).css("z-index") && $(this).css("z-index") != "auto") {
            MinZ = $(this).css("z-index");
        }
    });
    return parseInt(MinZ);
}

/**
 * 添加线条
 * @param id 往哪个div里加 div的选择器
 */
function addLineToSlide(id) {
    var idExtend = lineExId;
    var idBase = "nf4-line-";
    lineExId++;


    /*-------------------------------------先添加外层外面缩放和移动的div-----------------------------------*/
    $(id).append("<div id=\"nf4-line-" + idExtend + "\"></div>");
    $("#" + idBase + idExtend).addClass("nf4-line"); //增加指定类名

    $("#" + idBase + idExtend).html("<svg id=\"nf4-linePre-" + idExtend + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 200 200\" width=\"200\" height=\"200\" style=\"overflow: visible;\"><line x1=\"0\" y1=\"200\" x2=\"200\" y2=\"0\" stroke=\"currentColor\" stroke-width=\"2\"></line> <!----> <!----> <!----> <!----><!----> <!----> <!----> <!----></svg>");
    $(".nf4-line").find("#nf4-linePre-" + idExtend).attr("viewBox", "0 0 200 200");
    $(".nf4-line").find("#nf4-linePre-" + idExtend).children("line").attr({
        x1: "0",
        y1: "200",
        x2: "200",
        y2: "0"
    });
    //再往外层div里面添加具体负责移动、缩放的点, 以及编辑功能的div

    /*被取消的拖动方式*/
    //$("#"+idBase + idExtend).append("<div class=\"draggable-handle draggable-handle-image\"></div>");//负责移动的点,
    //idBase + idExtend).draggable({handle: ".draggable-handle",containment:id, scroll: false});//指定拖动的div
    /*被取消的拖动方式*/

    //负责缩放的8个div
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-ne\"></div>");
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-sw\"></div>");

    //适应大小和位置

    $("#nf4-linePre-" + idExtend).attr("height", "100%");
    $("#nf4-linePre-" + idExtend).attr("width", "100%");

    //最后外层div指定内层哪些div负责缩放和移动
    $("#" + idBase + idExtend).resizable({
        handles: {
            'ne': '.ui-resizable-ne',
            'sw': '.ui-resizable-sw',
        },
        containment: id
    });

    /*新的拖动方式*/
    $("#" + idBase + idExtend)
        .draggable({
            containment: id,
            scroll: false
        });
    //右键菜单
    $("#" + idBase + idExtend).contextPopup({
        //title: '选项',
        items: [
            {
                label: '删除此线条',
                icon: 'img/icons/nf4-view-delete.png',
                action: function () {
                    $("#" + idBase + idExtend).remove();
                }
            },
            {
                label: '层次:置顶',
                icon: 'img/icons/nf4-view-up.png',
                action: function () {
                    $("#" + idBase + idExtend).css("z-index", searchMaxZindex() + 1)
                }
            },
            //null, /* null can be used to add a separator to the menu items */
            {
                label: '层次:置底',
                icon: 'img/icons/nf4-view-down.png',
                action: function () {
                    $("#" + idBase + idExtend).css("z-index", searchMinZindex() - 1)
                }
            },
        ]
    });
    $("#" + idBase + idExtend).css('z-index', searchMaxZindex() + 1);
}

/**
 * 添加形状
 * @param id 往哪个div里加
 * @param this_id 不是我写的  不清楚这什么玩意 估计和形状有关
 */
function addShapeToSlide(id, this_id) {
    var idExtend = shapeExId;
    var idBase = "nf4-shape-";
    shapeExId++;

    /*-------------------------------------先添加外层外面缩放和移动的div-----------------------------------*/
    $(id).append("<div id=\"nf4-shape-" + idExtend + "\"></div>");
    $("#" + idBase + idExtend).addClass("nf4-shape"); //增加指定类名


    //再往外层div里面添加具体负责移动、缩放的点, 以及编辑功能的div
    var clone = $.clone(this_id);
    clone.id = "nf4-shapePre-" + idExtend;
    $(clone).attr("class", "show-shape");
    $("#" + idBase + idExtend).append(clone);


    //负责缩放的8个div
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-nw\"></div>");
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-ne\"></div>");
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-sw\"></div>");
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-se\"></div>");
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-n\"></div>");
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-s\"></div>");
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-e\"></div>");
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-w\"></div>");

    //适应大小和位置

    $("#nf4-shapePre-" + idExtend).css("height", "100%");
    $("#nf4-shapePre-" + idExtend).css("width", "100%");

    //最后外层div指定内层哪些div负责缩放和移动

    $("#" + idBase + idExtend).resizable({
        handles: {
            'nw': '.ui-resizable-nw',
            'ne': '.ui-resizable-ne',
            'sw': '.ui-resizable-sw',
            'se': '.ui-resizable-se',
            'n': '.ui-resizable-n',
            'e': '.ui-resizable-e',
            's': '.ui-resizable-s',
            'w': '.ui-resizable-w'
        },
        containment: id
    });

    /*新的拖动方式*/
    $("#" + idBase + idExtend)
        .draggable({
            containment: id,
            scroll: false
        });
    //右键菜单
    $("#" + idBase + idExtend).contextPopup({
        //title: '选项',
        items: [
            {
                label: '删除此形状',
                icon: 'img/icons/nf4-view-delete.png',
                action: function () {
                    $("#" + idBase + idExtend).remove();
                }
            },
            {
                label: '层次:置顶',
                icon: 'img/icons/nf4-view-up.png',
                action: function () {
                    $("#" + idBase + idExtend).css("z-index", searchMaxZindex() + 1)
                }
            },
            //null, /* null can be used to add a separator to the menu items */
            {
                label: '层次:置底',
                icon: 'img/icons/nf4-view-down.png',
                action: function () {
                    $("#" + idBase + idExtend).css("z-index", searchMinZindex() - 1)
                }
            },
        ]
    });
    $("#" + idBase + idExtend).css('z-index', searchMaxZindex() + 1);
}

/**
 * 添加画图
 * 因为播放的时候 无法跨域，暂时被取消了
 */
function addDrawToSlide(id, this_id) {
    var idExtend = drawExId;
    var idBase = "nf4-draw-";
    drawExId++;


    /*-------------------------------------先添加外层外面缩放和移动的div-----------------------------------*/
    $(id).append("<div id=\"nf4-draw-" + idExtend + "\"></div>");
    $("#" + idBase + idExtend).addClass("nf4-draw"); //增加指定类名


    //再往外层div里面添加具体负责移动、缩放的点, 以及编辑功能的div

    /*被取消的拖动方式*/
    //$("#"+idBase + idExtend).append("<div class=\"draggable-handle draggable-handle-image\"></div>");//负责移动的点,
    //idBase + idExtend).draggable({handle: ".draggable-handle",containment:id, scroll: false});//指定拖动的div
    /*被取消的拖动方式*/

    var clone = $.clone(this_id);
    clone.id = "nf4-drawPre-" + idExtend;
    $(clone).attr("class", "show-draw");
    $("#" + idBase + idExtend).append(clone);
    clone.getContext("2d").drawImage($("#drawCanvas")[0], 0, 0);


    //负责缩放的8个div
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-nw\"></div>");
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-ne\"></div>");
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-sw\"></div>");
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-se\"></div>");
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-n\"></div>");
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-s\"></div>");
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-e\"></div>");
    $("#" + idBase + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-w\"></div>");

    //适应大小和位置

    $("#nf4-shapePre-" + idExtend).css("height", "100%");
    $("#nf4-shapePre-" + idExtend).css("width", "100%");
    $("#nf4-shapePre-" + idExtend).css("cursor", "move");

    //最后外层div指定内层哪些div负责缩放和移动

    $("#" + idBase + idExtend).resizable({
        handles: {
            'nw': '.ui-resizable-nw',
            'ne': '.ui-resizable-ne',
            'sw': '.ui-resizable-sw',
            'se': '.ui-resizable-se',
            'n': '.ui-resizable-n',
            'e': '.ui-resizable-e',
            's': '.ui-resizable-s',
            'w': '.ui-resizable-w'
        },
        containment: id
    });

    /*新的拖动方式*/
    $("#" + idBase + idExtend)
        .draggable({
            containment: id,
            scroll: false
        });
    //右键菜单
    $("#" + idBase + idExtend).contextPopup({
        //title: '选项',
        items: [
            {
                label: '删除此绘图',
                icon: 'img/icons/nf4-view-delete.png',
                action: function () {
                    $("#" + idBase + idExtend).remove();
                }
            },
            {
                label: '层次:置顶',
                icon: 'img/icons/nf4-view-up.png',
                action: function () {
                    $("#" + idBase + idExtend).css("z-index", searchMaxZindex() + 1)
                }
            },
            //null, /* null can be used to add a separator to the menu items */
            {
                label: '层次:置底',
                icon: 'img/icons/nf4-view-down.png',
                action: function () {
                    $("#" + idBase + idExtend).css("z-index", searchMinZindex() - 1)
                }
            },
        ]
    });
    $("#" + idBase + idExtend).css('z-index', searchMaxZindex() + 1);
}

/**
 * 添加代码组件
 * @param id 往哪个div里加
 */
function addCodeToSlide(id) {
    var idExtend = codeIdExtend;
    codeIdExtend++;
    var workWidth = $(id).width() * 1.0 / 2;

    /*-------------------------------------先添加外层外面缩放和移动的div-----------------------------------*/
    $(id).append("<div id=\"nf4-code-" + idExtend + "\"></div>");
    $("#nf4-code-" + idExtend).addClass("nf4-code nf4-module");//增加指定类名
    $("#nf4-code-" + idExtend).css("width", workWidth);
    $("#nf4-code-" + idExtend).css("position", "absolute");
    //再往外层div里面添加具体负责移动、缩放的点, 以及编辑功能的div

    /*----------------------被取消的拖动方式---------------------*/
    //$("#nf4-code-" + idExtend).append("<div class=\"draggable-handle\"></div>");//负责移动的点
    //$("#nf4-code-" + idExtend).draggable({handle: ".draggable-handle",containment:id, scroll: false});//指定拖动的div
    /*----------------------被取消的拖动方式---------------------*/


    //负责ckeditor编辑的div
    $("#nf4-code-" + idExtend).append("<div id=\"nf4-code-editor-" + idExtend + "\" ></div>");
    $("#nf4-code-editor-" + idExtend).addClass("nf4-code-editor nf4-module-plug");


    //负责缩放的8个div
    $("#nf4-code-" + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-nw\"></div>");
    $("#nf4-code-" + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-ne\"></div>");
    $("#nf4-code-" + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-sw\"></div>");
    $("#nf4-code-" + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-se\"></div>");
    $("#nf4-code-" + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-n\"></div>");
    $("#nf4-code-" + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-s\"></div>");
    $("#nf4-code-" + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-e\"></div>");
    $("#nf4-code-" + idExtend).append("<div class=\"ui-resizable-handle ui-resizable-w\"></div>");

    /*得到焦点和失去焦点时候光标改变*/
    $("#nf4-code-editor-" + idExtend).dblclick(function () {
        $("#nf4-code-editor-" + idExtend).css("cursor", "text");
        event.stopPropagation();
    });
    $("#nf4-code-editor-" + idExtend).blur(function () {
        $("#nf4-code-editor-" + idExtend).css("cursor", "move");
        $("#nf4-code-" + idExtend).draggable('option', 'disabled', false);//失去焦点后,变为可以拖动
    });

    //激活ckeditor
    CKEDITOR.inline("nf4-code-editor-" + idExtend, {
        // Allow some non-standard markup that we used in the introduction.
        //extraAllowedContent: 'a(documentation);abbr[title];code',
        removePlugins: 'stylescombo',
        //extraPlugins: 'sourcedialog',
        // Show toolbar on startup (optional).
        title: "文本框",
        fontSize_sizes: "16/16px;24/24px;48/48px;72/72px;96/96px;120/120px",
        font_names: "宋体;黑体;微软雅黑;仿宋;方正兰亭黑简体;方正行楷_GBK;方正硬笔行书简体;华文隶书;仿宋_GB2312;Times New Roman",
        removePlugins: 'list,indentlist,font,undo,uicolor,colorbutton,colordialog,specialchar,codemirror,eqneditor',
        startupFocus: true
    });
    $("#nf4-code-editor-" + idExtend).attr("contenteditable", "true");

    //最后外层div指定内层哪些div负责缩放和移动
    $("#nf4-code-" + idExtend).resizable({
        handles: {
            'nw': '.ui-resizable-nw',
            'ne': '.ui-resizable-ne',
            'sw': '.ui-resizable-sw',
            'se': '.ui-resizable-se',
            'n': '.ui-resizable-n',
            'e': '.ui-resizable-e',
            's': '.ui-resizable-s',
            'w': '.ui-resizable-w'
        }, containment: id
    });

    //新的拖动
    $("#nf4-code-" + idExtend)
        .draggable({
            containment: id, scroll: false
        })
        .on('click', function () {
            if ($(this).is('.ui-draggable-dragging')) {
                return;
            } else {
                $(this).draggable('option', 'disabled', true);
            }

        });
    //右键菜单
    $("#nf4-code-" + idExtend).contextPopup({
        //title: '选项',
        items: [
            {
                label: '删除此代码框', icon: 'img/icons/nf4-view-delete.png', action: function () {
                $("#nf4-code-" + idExtend).remove();
            }
            },
            {
                label: '层次:置顶', icon: 'img/icons/nf4-view-up.png', action: function () {
                $("#nf4-code-" + idExtend).css('z-index', searchMaxZindex() + 1);
            }
            },
            //null, /* null can be used to add a separator to the menu items */
            {
                label: '层次:置底', icon: 'img/icons/nf4-view-down.png', action: function () {
                $("#nf4-code-" + idExtend).css('z-index', searchMinZindex() - 1);
            }
            },
        ]
    });
    $("#nf4-code-" + idExtend).css('z-index', searchMaxZindex() + 1);
}

/**
 * 保存幻灯片
 */
function saveSlide() {
    /*弹出框提示 保存*/
    shwoPopovers("nf4-Popovers__on-save");

    /*得到播放设置参数*/
    var plugValues_ = $("#autoSlide option:selected").val() + $("#slideMethod option:selected").val() + $("#slideSpeed option:selected").val() + isDisplayPro + isDisplayNum;
    var plugValues = {
        "transition": $("#slideMethod option:selected").val(),
        "transitionSpeed": $("#slideSpeed option:selected").val(),
        "autoSlide": $("#autoSlide option:selected").val(),
        "progress": isDisplayPro,
        "slideNumber": isDisplayNum
    }

    /*得到内容组件的html*/
    var upLoadData = "";
    $(".nf4-slide").each(function () { //循环每一个幻灯片
        var ExId = $(this).attr("id").substr(6);
        upLoadData = upLoadData + $("#tabs-" + ExId).html() + "<--nf4-->";
    });

    /*得到播放的html*/
    var playHtmlCode = playSlide();

    /*上传到服务*/
    $.ajax({
        type: "POST",
        url: "slide/modifySlide",
        dataType: "JSON",
        data: {
            slideId: slideId,
            content: upLoadData,
            play: playHtmlCode,
            theme: $("#nf4-slide-themes option:selected").val(),
            config: JSON.stringify(plugValues)
        },
        success: function (msg) {
            if (msg.result == "modifySuccess") {
                shwoPopovers("nf4-Popovers__auto-save", 5000);
            } else {
                alert("保存失败");
            }
        }
    });
}

/**
 * 自动保存函数
 */
$(function timeActve() {
    time_clock = window.setInterval(function () {
        saveSlide();
    }, 3 * 60 * 1000);
});

/**
 * 找到某一个类 其所有标签最大的id值
 * @param className
 * @returns {number} 这个类 最大的id值
 */
function findMaxID(className) {
    var maxId = 1;
    $("." + className).each(function () {
        var id = $(this).attr("id");
        id = id.substr(className.length + 1);
        id = parseInt(id);
        if (maxId < id) {
            maxId = id;
        }
    });
    return parseInt(maxId) + 1;
}

/**
 * 为从数据读取的html代码，添加事件
 */
function addEvent() {
    //文本
    $(".ui-tabs-panel .nf4-text").each(function () {
        var thisId = "#" + $(this).attr("id");
        var id = $(this).attr("id");
        var id = $("#" + id + " .nf4-text-editor").attr("id");
        CKEDITOR.inline(id, {
            // Allow some non-standard markup that we used in the introduction.
            //extraAllowedContent: 'a(documentation);abbr[title];code',
            removePlugins: 'stylescombo',
            //extraPlugins: 'sourcedialog',
            // Show toolbar on startup (optional).
            title: "文本框",
            fontSize_sizes: "16/16px;24/24px;32/32px;48/48px;72/72px;96/96px;120/120px",
            font_names: "宋体;黑体;微软雅黑;仿宋;方正兰亭黑简体;方正行楷_GBK;方正硬笔行书简体;华文隶书;仿宋_GB2312;Times New Roman",
            removePlugins: 'codesnippet,codemirror',
            startupFocus: true
        });
    });
    $(".ui-tabs-panel .nf4-code").each(function () {
        var thisId = "#" + $(this).attr("id");
        var id = $(this).attr("id");
        var id = $("#" + id + " .nf4-code-editor").attr("id");
        CKEDITOR.inline(id, {
            // Allow some non-standard markup that we used in the introduction.
            //extraAllowedContent: 'a(documentation);abbr[title];code',
            removePlugins: 'stylescombo',
            //extraPlugins: 'sourcedialog',
            // Show toolbar on startup (optional).
            title: "文本框",
            fontSize_sizes: "16/16px;24/24px;48/48px;72/72px;96/96px;120/120px",
            font_names: "宋体;黑体;微软雅黑;仿宋;方正兰亭黑简体;方正行楷_GBK;方正硬笔行书简体;华文隶书;仿宋_GB2312;Times New Roman",
            removePlugins: 'listc,indentlist,font,undo,uicolor,colorbutton,colordialog,specialchar,codemirror,eqneditor',
            startupFocus: true
        });
    });
    $(".ui-tabs-panel .nf4-text,.ui-tabs-panel .nf4-code").each(function () {
        var thisId = "#" + $(this).attr("id");
        $(this).resizable({
            handles: {
                'nw': '.ui-resizable-nw',
                'ne': '.ui-resizable-ne',
                'sw': '.ui-resizable-sw',
                'se': '.ui-resizable-se',
                'n': '.ui-resizable-n',
                'e': '.ui-resizable-e',
                's': '.ui-resizable-s',
                'w': '.ui-resizable-w'
            },
            containment: "#" + $(this).parent().attr("id")
        });

        //新的拖动
        $(this).draggable({
            containment: "#" + $(this).parent().attr("id"),
            scroll: false
        }).on('click', function () {
            if ($(this).is('.ui-draggable-dragging')) {
                return;
            } else {
                $(this).draggable('option', 'disabled', true);
            }
        });

        //右键菜单
        $(thisId).contextPopup({
            //title: '选项',
            items: [
                {
                    label: '删除此文本框',
                    icon: 'img/icons/nf4-view-delete.png',
                    action: function () {
                        $(thisId).remove();
                    }
                },
                {
                    label: '层次:置顶',
                    icon: 'img/icons/nf4-view-up.png',
                    action: function () {
                        $(thisId).css('z-index', searchMaxZindex() + 1);
                    }
                },
                //null, /* null can be used to add a separator to the menu items */
                {
                    label: '层次:置底',
                    icon: 'img/icons/nf4-view-down.png',
                    action: function () {
                        $(thisId).css('z-index', searchMinZindex() - 1);
                    }
                },
            ]
        }); //end 右键菜单
        var id = $(this).attr("id");
        var id = $("#" + id + " .nf4-text-editor").attr("id");

        $("#" + id).dblclick(function () {
            $("#" + id).css("cursor", "text");
            event.stopPropagation();
        });
        $("#" + id).blur(function () {
            $("#" + id).css("cursor", "move");
            $(thisId).draggable('option', 'disabled', false); //失去焦点后,变为可以拖动
        });
    }); //end 文本

    //图片
    $(".ui-tabs-panel .nf4-image").each(function () {
        var thisId = "#" + $(this).attr("id");
        $(this).resizable({
            handles: {
                'nw': '.ui-resizable-nw',
                'ne': '.ui-resizable-ne',
                'sw': '.ui-resizable-sw',
                'se': '.ui-resizable-se',
                'n': '.ui-resizable-n',
                'e': '.ui-resizable-e',
                's': '.ui-resizable-s',
                'w': '.ui-resizable-w'
            },
            containment: "#" + $(this).parent().attr("id")
        });
        $(this)
            .draggable({
                containment: "#" + $(this).parent().attr("id"),
                scroll: false
            });
        $(thisId).contextPopup({
            //title: '选项',
            items: [
                {
                    label: '删除',
                    icon: 'img/icons/nf4-view-delete.png',
                    action: function () {
                        $(thisId).remove();
                    }
                },
                {
                    label: '层次:置顶',
                    icon: 'img/icons/nf4-view-up.png',
                    action: function () {
                        $(thisId).css('z-index', searchMaxZindex() + 1);
                    }
                },
                //null, /* null can be used to add a separator to the menu items */
                {
                    label: '层次:置底',
                    icon: 'img/icons/nf4-view-down.png',
                    action: function () {
                        $(thisId).css('z-index', searchMinZindex() - 1);
                    }
                },
                {
                    label: '属性',
                    icon: 'http://120.24.186.116/Myicons/nf4-attr.png',
                    action: function () {
                        $("#ImageProperty").trigger("click");
                        $(thisId).find("img")[0].setAttribute("data-selected", "true")
                    }
                }
            ]
        });
    }); //end图片
    //其他图片
    $(".ui-tabs-panel .nf4-shape,.ui-tabs-panel .nf4-line,.ui-tabs-panel .nf4-draw").each(function () {
        var thisId = "#" + $(this).attr("id");
        $(this).resizable({
            handles: {
                'nw': '.ui-resizable-nw',
                'ne': '.ui-resizable-ne',
                'sw': '.ui-resizable-sw',
                'se': '.ui-resizable-se',
                'n': '.ui-resizable-n',
                'e': '.ui-resizable-e',
                's': '.ui-resizable-s',
                'w': '.ui-resizable-w'
            },
            containment: "#" + $(this).parent().attr("id")
        });
        $(this)
            .draggable({
                containment: "#" + $(this).parent().attr("id"),
                scroll: false
            });
        $(thisId).contextPopup({
            //title: '选项',
            items: [
                {
                    label: '删除',
                    icon: 'img/icons/nf4-view-delete.png',
                    action: function () {
                        $(thisId).remove();
                    }
                },
                {
                    label: '层次:置顶',
                    icon: 'img/icons/nf4-view-up.png',
                    action: function () {
                        $(thisId).css('z-index', searchMaxZindex() + 1);
                    }
                },
                //null, /* null can be used to add a separator to the menu items */
                {
                    label: '层次:置底',
                    icon: 'img/icons/nf4-view-down.png',
                    action: function () {
                        $(thisId).css('z-index', searchMinZindex() - 1);
                    }
                },
            ]
        });
    }); //end图片
}

