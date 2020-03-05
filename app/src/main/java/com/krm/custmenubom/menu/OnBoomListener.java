package com.krm.custmenubom.menu;

import com.krm.custmenubom.menu.boomButtons.BoomButton;

public interface OnBoomListener {

    void onClicked(int index, BoomButton boomButton);

    void onBackgroundClick();

    void onBoomWillHide();

    void onBoomDidHide();

    void onBoomWillShow();

    void onBoomDidShow();

}
