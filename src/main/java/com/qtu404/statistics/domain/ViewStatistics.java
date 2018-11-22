package com.qtu404.statistics.domain;

public class ViewStatistics {
    private Integer id;
    private String identify;
    private Long totalTime;
    private Integer exitTimes;
    private Integer attendanceId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public Long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getExitTimes() {
        return exitTimes;
    }

    public void setExitTimes(Integer exitTimes) {
        this.exitTimes = exitTimes;
    }

    public Integer getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Integer attendanceId) {
        this.attendanceId = attendanceId;
    }
}
