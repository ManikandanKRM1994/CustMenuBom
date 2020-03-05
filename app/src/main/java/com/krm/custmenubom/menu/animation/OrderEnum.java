package com.krm.custmenubom.menu.animation;

public enum OrderEnum {

    DEFAULT(),
    REVERSE(),
    RANDOM(),

    Unknown();

    OrderEnum() {
    }

    public static OrderEnum getEnum(int value) {
        if (value < 0 || value >= values().length) return Unknown;
        return values()[value];
    }
}
