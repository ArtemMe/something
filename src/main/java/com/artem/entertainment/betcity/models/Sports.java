package com.artem.entertainment.betcity.models;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

public class Sports {
    @SerializedName("1")
    Sport fooball;

    @SerializedName("2")
    Sport tennis;

    public Sport getFooball() {
        return fooball;
    }

    public void setFooball(Sport fooball) {
        this.fooball = fooball;
    }

    public Sport getTennis() {
        return tennis;
    }

    public void setTennis(Sport tennis) {
        this.tennis = tennis;
    }
}
