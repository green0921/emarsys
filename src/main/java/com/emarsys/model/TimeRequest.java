package com.emarsys.model;

public class TimeRequest {

    private long submitTime;
    private long turnaroundTime;

    public long getSubmitTime() {
        return submitTime;
    }

    public long getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setSubmitTime(long submitTime) {
        this.submitTime = submitTime;
    }

    public void setTurnaroundTime(long turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }
}
