package com.artem.entertainment.betcity.models;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sports {
    @SerializedName("1")
    Sport fooball;

    @SerializedName("2")
    Sport tennis;

}
