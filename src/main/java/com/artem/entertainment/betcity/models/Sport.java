package com.artem.entertainment.betcity.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sport {
    String name_sp;
    long id_sp;
    long order;
    Championships chmps;

}
