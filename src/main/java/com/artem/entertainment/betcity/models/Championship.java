package com.artem.entertainment.betcity.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Championship {
    long id_ch;
    long id_ch_gl;
    String name_ch;
    long order;
    long cn_ch;
    Events evts;

}