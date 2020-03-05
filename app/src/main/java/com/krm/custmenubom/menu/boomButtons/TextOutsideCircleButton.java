package com.krm.custmenubom.menu.boomButtons;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.view.LayoutInflater;
import android.view.View;

import com.krm.custmenubom.R;
import com.krm.custmenubom.menu.ButtonEnum;

import java.util.ArrayList;

@SuppressLint("ViewConstructor")
@SuppressWarnings("unused")
public class TextOutsideCircleButton extends BoomButton {

    private TextOutsideCircleButton(Builder builder, Context context) {
        super(context);
        this.context = context;
        this.buttonEnum = ButtonEnum.TextOutsideCircle;
        init(builder);
    }

    private void init(Builder builder) {
        LayoutInflater.from(context).inflate(R.layout.bmb_text_outside_circle_button, this, true);
        initAttrs(builder);
        initTextOutsideCircleButtonLayout();
        if (isRound) initShadow(buttonRadius + shadowRadius);
        else initShadow(shadowCornerRadius);
        initCircleButton();
        initText(layout);
        initImage();
        centerPoint = new PointF(trueRadius, trueRadius);
    }

    private void initAttrs(Builder builder) {
        super.initAttrs(builder);
    }

    @Override
    public ButtonEnum type() {
        return ButtonEnum.TextOutsideCircle;
    }

    @Override
    public ArrayList<View> goneViews() {
        ArrayList<View> goneViews = new ArrayList<>();
        goneViews.add(image);
        goneViews.add(text);
        return goneViews;
    }

    @Override
    public ArrayList<View> rotateViews() {
        ArrayList<View> rotateViews = new ArrayList<>();
        if (rotateImage) rotateViews.add(image);
        if (rotateText) rotateViews.add(text);
        return rotateViews;
    }

    @Override
    public int trueWidth() {
        return trueRadius * 2;
    }

    @Override
    public int trueHeight() {
        return trueRadius * 2;
    }

    @Override
    public int contentWidth() {
        return buttonRadius * 2;
    }

    @Override
    public int contentHeight() {
        return buttonRadius * 2;
    }

    @Override
    public void toHighlighted() {
        if (lastStateIsNormal && ableToHighlight) {
            toHighlightedImage();
            toHighlightedText();
            lastStateIsNormal = false;
        }
    }

    @Override
    public void toNormal() {
        if (!lastStateIsNormal) {
            toNormalImage();
            toNormalText();
            lastStateIsNormal = true;
        }
    }

    @Override
    public void setRotateAnchorPoints() {
        image.setPivotX(buttonRadius - imageRect.left);
        image.setPivotY(buttonRadius - imageRect.top);
        text.setPivotX(trueRadius - textRect.left);
        text.setPivotY(trueRadius - textRect.top);
    }

    @Override
    public void setSelfScaleAnchorPoints() {

    }

    public static class Builder extends BoomButtonWithTextBuilder<Builder> {

        public Builder rotateText(boolean rotateText) {
            this.rotateText = rotateText;
            return this;
        }

        public Builder textTopMargin(int textTopMargin) {
            if (textTopMargin < 0) textTopMargin = 0;
            this.textTopMargin = textTopMargin;
            return this;
        }

        public Builder textWidth(int textWidth) {
            this.textWidth = textWidth;
            return this;
        }

        public Builder textHeight(int textHeight) {
            this.textHeight = textHeight;
            return this;
        }

        public Builder buttonRadius(int buttonRadius) {
            this.buttonRadius = buttonRadius;
            return this;
        }

        public Builder buttonCornerRadius(int buttonCornerRadius) {
            this.buttonCornerRadius = buttonCornerRadius;
            return this;
        }

        public Builder isRound(boolean isRound) {
            this.isRound = isRound;
            return this;
        }

        public int getButtonContentWidth() {
            int width = buttonRadius * 2;
            if (textRect != null) width = Math.max(width, textWidth);
            return width;
        }

        public int getButtonContentHeight() {
            int height = buttonRadius * 2;
            if (textRect != null)
                height = Math.max(height, textRect.bottom - shadowOffsetY - shadowRadius);
            return height;
        }

        public int getButtonRadius() {
            return buttonRadius;
        }

        @Override
        public TextOutsideCircleButton build(Context context) {
            TextOutsideCircleButton button = new TextOutsideCircleButton(this, context);
            weakReferenceButton(button);
            return button;
        }
    }
}
