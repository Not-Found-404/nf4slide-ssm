package com.qtu404.slide.domain;

public class SlideVo {
    public SlideVo() {
    }

    private Integer slideId;
    private Integer userId;   //外键？
    private String name;
    private String content;
    private String play;
    private String config;
    private String theme;
    private String whoPlay;
    private String exit;

    public String getExit() {
        return exit;
    }

    public void setExit(String exit) {
        this.exit = exit;
    }

    public String getWhoPlay() {
        return whoPlay;
    }

    public void setWhoPlay(String whoPlay) {
        this.whoPlay = whoPlay;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Integer getSlideId() {
        return slideId;
    }

    public void setSlideId(Integer slideId) {
        this.slideId = slideId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public static SlideVo createNewSlide(Integer userId) {
        SlideVo slideVo = new SlideVo();
        slideVo.setExit("true");
        slideVo.setName("新建幻灯片");
        slideVo.setContent("");
        slideVo.setConfig("");
        slideVo.setUserId(userId);
        slideVo.setPlay("");
        slideVo.setTheme("sky");
        return slideVo;
    }
}
