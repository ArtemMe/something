package com.artem.entertainment.betcity.models;
import lombok.Getter;
import lombok.Setter;

public class Reply {
    String curr_time;
    long ntime_templates;
    long ntime_vg;
    long ntime;
    Sports sports;

    public String getCurr_time() {
        return curr_time;
    }

    public void setCurr_time(String curr_time) {
        this.curr_time = curr_time;
    }

    public long getNtime_templates() {
        return ntime_templates;
    }

    public void setNtime_templates(long ntime_templates) {
        this.ntime_templates = ntime_templates;
    }

    public long getNtime_vg() {
        return ntime_vg;
    }

    public void setNtime_vg(long ntime_vg) {
        this.ntime_vg = ntime_vg;
    }

    public long getNtime() {
        return ntime;
    }

    public void setNtime(long ntime) {
        this.ntime = ntime;
    }

    public Sports getSports() {
        return sports;
    }

    public void setSports(Sports sports) {
        this.sports = sports;
    }
}
