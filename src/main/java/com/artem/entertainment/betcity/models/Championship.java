package com.artem.entertainment.betcity.models;

public class Championship {
    long id_ch;
    long id_ch_gl;
    String name_ch;
    long order;
    long cn_ch;
    Events evts;

    public Events getEvts() {
        return evts;
    }

    public void setEvts(Events evts) {
        this.evts = evts;
    }

    public long getId_ch() {
        return id_ch;
    }

    public void setId_ch(long id_ch) {
        this.id_ch = id_ch;
    }

    public long getId_ch_gl() {
        return id_ch_gl;
    }

    public void setId_ch_gl(long id_ch_gl) {
        this.id_ch_gl = id_ch_gl;
    }

    public String getName_ch() {
        return name_ch;
    }

    public void setName_ch(String name_ch) {
        this.name_ch = name_ch;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public long getCn_ch() {
        return cn_ch;
    }

    public void setCn_ch(long cn_ch) {
        this.cn_ch = cn_ch;
    }
}
