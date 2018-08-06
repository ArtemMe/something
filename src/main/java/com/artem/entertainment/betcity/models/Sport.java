package com.artem.entertainment.betcity.models;

import java.util.List;

public class Sport {
    String name_sp;
    long id_sp;
    long order;
    Championships chmps;

    public String getName_sp() {
        return name_sp;
    }

    public void setName_sp(String name_sp) {
        this.name_sp = name_sp;
    }

    public long getId_sp() {
        return id_sp;
    }

    public void setId_sp(long id_sp) {
        this.id_sp = id_sp;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public Championships getChmps() {
        return chmps;
    }

    public void setChmps(Championships chmps) {
        this.chmps = chmps;
    }
}
