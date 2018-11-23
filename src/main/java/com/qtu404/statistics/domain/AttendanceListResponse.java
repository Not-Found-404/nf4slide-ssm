package com.qtu404.statistics.domain;

import java.text.SimpleDateFormat;

public class AttendanceListResponse extends Attendance {
    private String beginTime;
    private String endTime;
    private String slideName;
    private static SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public String getEndTime() {
        if (this.getEndAt() != null) {
            return s.format(this.getEndAt());
        }
        return "2000-01-01";
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSlideName() {
        return slideName;
    }

    public void setSlideName(String slideName) {
        this.slideName = slideName;
    }

    public String getBeginTime() {
        if (this.getBeginAt() != null) {
            return s.format(this.getBeginAt());
        }
        return "2000-01-01";
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }
}
