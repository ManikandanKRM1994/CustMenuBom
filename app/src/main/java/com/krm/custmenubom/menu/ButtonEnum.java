package com.krm.custmenubom.menu;

public enum ButtonEnum {

    SimpleCircle(),

    TextInsideCircle(),

    TextOutsideCircle(),

    Ham(),

    Unknown();

    ButtonEnum() {
    }

    public static ButtonEnum getEnum(int value) {
        if (value < 0 || value > values().length) return Unknown;
        else return values()[value];
    }
}
