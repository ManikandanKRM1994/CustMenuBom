package com.krm.custmenubom.menu.boomButtons;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import com.krm.custmenubom.R;
import com.krm.custmenubom.menu.ButtonEnum;
import com.krm.custmenubom.menu.Util;

import java.util.ArrayList;

@SuppressLint("ViewConstructor")
@SuppressWarnings("unused")
public class HamButton extends BoomButton {

    private HamButton(Builder builder, Context context) {
        super(context);
        this.context = context;
        this.buttonEnum = ButtonEnum.Ham;
        init(builder);
    }

    private void init(Builder builder) {
        LayoutInflater.from(context).inflate(R.layout.bmb_ham_button, this, true);
        initAttrs(builder);
        initShadow(builder.shadowCornerRadius);
        initHamButton();
        initText(button);
        initSubText(button);
        initImage();
        centerPoint = new PointF(
                buttonWidth / 2.0f + shadowRadius + shadowOffsetX,
                buttonHeight / 2.0f + shadowRadius + shadowOffsetY);
    }

    private void initAttrs(Builder builder) {
        super.initAttrs(builder);
    }

    @Override
    public ButtonEnum type() {
        return ButtonEnum.Ham;
    }

    @Override
    public ArrayList<View> goneViews() {
        ArrayList<View> goneViews = new ArrayList<>();
        goneViews.add(image);
        goneViews.add(text);
        if (subText != null) goneViews.add(subText);
        return goneViews;
    }

    @Override
    public ArrayList<View> rotateViews() {
        ArrayList<View> rotateViews = new ArrayList<>();
        if (rotateImage) rotateViews.add(image);
        return rotateViews;
    }

    @Override
    public int trueWidth() {
        return buttonWidth + shadowRadius * 2 + shadowOffsetX * 2;
    }

    @Override
    public int trueHeight() {
        return buttonHeight + shadowRadius * 2 + shadowOffsetY * 2;
    }

    @Override
    public int contentWidth() {
        return buttonWidth;
    }

    @Override
    public int contentHeight() {
        return buttonHeight;
    }

    @Override
    public void toHighlighted() {
        if (lastStateIsNormal && ableToHighlight) {
            toHighlightedImage();
            toHighlightedText();
            toHighlightedSubText();
            lastStateIsNormal = false;
        }
    }

    @Override
    public void toNormal() {
        if (!lastStateIsNormal) {
            toNormalImage();
            toNormalText();
            toNormalSubText();
            lastStateIsNormal = true;
        }
    }

    @Override
    public void setRotateAnchorPoints() {

    }

    @Override
    public void setSelfScaleAnchorPoints() {

    }

    public static class Builder extends BoomButtonWithTextBuilder<Builder> {

        public Builder() {
            imageRect = new Rect(0, 0, Util.dp2px(60), Util.dp2px(60));
            textRect = new Rect(Util.dp2px(70), Util.dp2px(10), Util.dp2px(280), Util.dp2px(40));
            textGravity = Gravity.START | Gravity.CENTER_VERTICAL;
            textSize = 15;
        }

        public Builder containsSubText(boolean containsSubText) {
            this.containsSubText = containsSubText;
            return this;
        }

        public Builder subNormalText(String subNormalText) {
            if (this.subNormalText == null || !this.subNormalText.equals(subNormalText)) {
                this.subNormalText = subNormalText;
                BoomButton button = button();
                if (button != null) {
                    button.subNormalText = subNormalText;
                    button.updateSubText();
                }
            }
            return this;
        }

        public Builder subNormalTextRes(int subNormalTextRes) {
            if (this.subNormalTextRes != subNormalTextRes) {
                this.subNormalTextRes = subNormalTextRes;
                BoomButton button = button();
                if (button != null) {
                    button.subNormalTextRes = subNormalTextRes;
                    button.updateSubText();
                }
            }
            return this;
        }

        public Builder subHighlightedText(String subHighlightedText) {
            if (this.subHighlightedText == null || !this.subHighlightedText.equals(subHighlightedText)) {
                this.subHighlightedText = subHighlightedText;
                BoomButton button = button();
                if (button != null) {
                    button.subHighlightedText = subHighlightedText;
                    button.updateSubText();
                }
            }
            return this;
        }

        public Builder subHighlightedTextRes(int subHighlightedTextRes) {
            if (this.subHighlightedTextRes != subHighlightedTextRes) {
                this.subHighlightedTextRes = subHighlightedTextRes;
                BoomButton button = button();
                if (button != null) {
                    button.subHighlightedTextRes = subHighlightedTextRes;
                    button.updateSubText();
                }
            }
            return this;
        }

        public Builder subUnableText(String subUnableText) {
            if (this.subUnableText == null || !this.subUnableText.equals(subUnableText)) {
                this.subUnableText = subUnableText;
                BoomButton button = button();
                if (button != null) {
                    button.subUnableText = subUnableText;
                    button.updateSubText();
                }
            }
            return this;
        }

        public Builder subUnableTextRes(int subUnableTextRes) {
            if (this.subUnableTextRes != subUnableTextRes) {
                this.subUnableTextRes = subUnableTextRes;
                BoomButton button = button();
                if (button != null) {
                    button.subUnableTextRes = subUnableTextRes;
                    button.updateSubText();
                }
            }
            return this;
        }

        public Builder subNormalTextColor(int subNormalTextColor) {
            if (this.subNormalTextColor != subNormalTextColor) {
                this.subNormalTextColor = subNormalTextColor;
                BoomButton button = button();
                if (button != null) {
                    button.subNormalTextColor = subNormalTextColor;
                    button.updateSubText();
                }
            }
            return this;
        }

        public Builder subNormalTextColorRes(int subNormalTextColorRes) {
            if (this.subNormalTextColorRes != subNormalTextColorRes) {
                this.subNormalTextColorRes = subNormalTextColorRes;
                BoomButton button = button();
                if (button != null) {
                    button.subNormalTextColorRes = subNormalTextColorRes;
                    button.updateSubText();
                }
            }
            return this;
        }

        public Builder subHighlightedTextColor(int subHighlightedTextColor) {
            if (this.subHighlightedTextColor != subHighlightedTextColor) {
                this.subHighlightedTextColor = subHighlightedTextColor;
                BoomButton button = button();
                if (button != null) {
                    button.subHighlightedTextColor = subHighlightedTextColor;
                    button.updateSubText();
                }
            }
            return this;
        }

        public Builder subHighlightedTextColorRes(int subHighlightedTextColorRes) {
            if (this.subHighlightedTextColorRes != subHighlightedTextColorRes) {
                this.subHighlightedTextColorRes = subHighlightedTextColorRes;
                BoomButton button = button();
                if (button != null) {
                    button.subHighlightedTextColorRes = subHighlightedTextColorRes;
                    button.updateSubText();
                }
            }
            return this;
        }

        public Builder subUnableTextColor(int subUnableTextColor) {
            if (this.subUnableTextColor != subUnableTextColor) {
                this.subUnableTextColor = subUnableTextColor;
                BoomButton button = button();
                if (button != null) {
                    button.subUnableTextColor = subUnableTextColor;
                    button.updateSubText();
                }
            }
            return this;
        }

        public Builder subUnableTextColorRes(int subUnableTextColorRes) {
            if (this.subUnableTextColorRes != subUnableTextColorRes) {
                this.subUnableTextColorRes = subUnableTextColorRes;
                BoomButton button = button();
                if (button != null) {
                    button.subUnableTextColorRes = subUnableTextColorRes;
                    button.updateSubText();
                }
            }
            return this;
        }

        public Builder subTextRect(Rect subTextRect) {
            if (this.subTextRect != subTextRect) {
                this.subTextRect = subTextRect;
                BoomButton button = button();
                if (button != null) {
                    button.subTextRect = subTextRect;
                    button.updateSubTextRect();
                }
            }
            return this;
        }

        public Builder subTextPadding(Rect subTextPadding) {
            if (this.subTextPadding != subTextPadding) {
                this.subTextPadding = subTextPadding;
                BoomButton button = button();
                if (button != null) {
                    button.subTextPadding = subTextPadding;
                    button.updateSubTextPadding();
                }
            }
            return this;
        }

        public Builder subTypeface(Typeface subTypeface) {
            this.subTypeface = subTypeface;
            return this;
        }

        public Builder subMaxLines(int subMaxLines) {
            this.subMaxLines = subMaxLines;
            return this;
        }

        public Builder subTextGravity(int subTextGravity) {
            this.subTextGravity = subTextGravity;
            return this;
        }

        public Builder subEllipsize(TextUtils.TruncateAt subEllipsize) {
            this.subEllipsize = subEllipsize;
            return this;
        }

        public Builder subTextSize(int subTextSize) {
            this.subTextSize = subTextSize;
            return this;
        }

        public Builder buttonWidth(int buttonWidth) {
            this.buttonWidth = buttonWidth;
            return this;
        }

        public Builder buttonHeight(int buttonHeight) {
            this.buttonHeight = buttonHeight;
            return this;
        }

        public Builder buttonCornerRadius(int buttonCornerRadius) {
            this.buttonCornerRadius = buttonCornerRadius;
            return this;
        }

        public int getButtonWidth() {
            return buttonWidth;
        }

        public int getButtonHeight() {
            return buttonHeight;
        }

        @Override
        public HamButton build(Context context) {
            HamButton button = new HamButton(this, context);
            weakReferenceButton(button);
            return button;
        }
    }
}
