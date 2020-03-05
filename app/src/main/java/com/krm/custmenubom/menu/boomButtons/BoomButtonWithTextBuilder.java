package com.krm.custmenubom.menu.boomButtons;

import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;

@SuppressWarnings({"unchecked", "unused"})
public abstract class BoomButtonWithTextBuilder<T> extends BoomButtonBuilder<T> {

    public T normalText(String normalText) {
        if (this.normalText == null || !this.normalText.equals(normalText)) {
            this.normalText = normalText;
            BoomButton button = button();
            if (button != null) {
                button.normalText = normalText;
                button.updateText();
            }
        }
        return (T) this;
    }

    public T normalTextRes(int normalTextRes) {
        if (this.normalTextRes != normalTextRes) {
            this.normalTextRes = normalTextRes;
            BoomButton button = button();
            if (button != null) {
                button.normalTextRes = normalTextRes;
                button.updateText();
            }
        }
        return (T) this;
    }

    public T highlightedText(String highlightedText) {
        if (this.highlightedText == null || !this.highlightedText.equals(highlightedText)) {
            this.highlightedText = highlightedText;
            BoomButton button = button();
            if (button != null) {
                button.highlightedText = highlightedText;
                button.updateText();
            }
        }
        return (T) this;
    }

    public T highlightedTextRes(int highlightedTextRes) {
        if (this.highlightedTextRes != highlightedTextRes) {
            this.highlightedTextRes = highlightedTextRes;
            BoomButton button = button();
            if (button != null) {
                button.highlightedTextRes = highlightedTextRes;
                button.updateText();
            }
        }
        return (T) this;
    }

    public T unableText(String unableText) {
        if (this.unableText == null || !this.unableText.equals(unableText)) {
            this.unableText = unableText;
            BoomButton button = button();
            if (button != null) {
                button.unableText = unableText;
                button.updateText();
            }
        }
        return (T) this;
    }

    public T unableTextRes(int unableTextRes) {
        if (this.unableTextRes != unableTextRes) {
            this.unableTextRes = unableTextRes;
            BoomButton button = button();
            if (button != null) {
                button.unableTextRes = unableTextRes;
                button.updateText();
            }
        }
        return (T) this;
    }

    public T normalTextColor(int normalTextColor) {
        if (this.normalTextColor != normalTextColor) {
            this.normalTextColor = normalTextColor;
            BoomButton button = button();
            if (button != null) {
                button.normalTextColor = normalTextColor;
                button.updateText();
            }
        }
        return (T) this;
    }

    public T normalTextColorRes(int normalTextColorRes) {
        if (this.normalTextColorRes != normalTextColorRes) {
            this.normalTextColorRes = normalTextColorRes;
            BoomButton button = button();
            if (button != null) {
                button.normalTextColorRes = normalTextColorRes;
                button.updateText();
            }
        }
        return (T) this;
    }

    public T highlightedTextColor(int highlightedTextColor) {
        if (this.highlightedTextColor != highlightedTextColor) {
            this.highlightedTextColor = highlightedTextColor;
            BoomButton button = button();
            if (button != null) {
                button.highlightedTextColor = highlightedTextColor;
                button.updateText();
            }
        }
        return (T) this;
    }

    public T highlightedTextColorRes(int highlightedTextColorRes) {
        if (this.highlightedTextColorRes != highlightedTextColorRes) {
            this.highlightedTextColorRes = highlightedTextColorRes;
            BoomButton button = button();
            if (button != null) {
                button.highlightedTextColorRes = highlightedTextColorRes;
                button.updateText();
            }
        }
        return (T) this;
    }

    public T unableTextColor(int unableTextColor) {
        if (this.unableTextColor != unableTextColor) {
            this.unableTextColor = unableTextColor;
            BoomButton button = button();
            if (button != null) {
                button.unableTextColor = unableTextColor;
                button.updateText();
            }
        }
        return (T) this;
    }

    public T unableTextColorRes(int unableTextColorRes) {
        if (this.unableTextColorRes != unableTextColorRes) {
            this.unableTextColorRes = unableTextColorRes;
            BoomButton button = button();
            if (button != null) {
                button.unableTextColorRes = unableTextColorRes;
                button.updateText();
            }
        }
        return (T) this;
    }

    public T textRect(Rect textRect) {
        if (this.textRect != textRect) {
            this.textRect = textRect;
            BoomButton button = button();
            if (button != null) {
                button.textRect = textRect;
                button.updateTextRect();
            }
        }
        return (T) this;
    }

    public T textPadding(Rect textPadding) {
        if (this.textPadding != textPadding) {
            this.textPadding = textPadding;
            BoomButton button = button();
            if (button != null) {
                button.textPadding = textPadding;
                button.updateTextPadding();
            }
        }
        return (T) this;
    }

    public T typeface(Typeface typeface) {
        this.typeface = typeface;
        return (T) this;
    }

    public T maxLines(int maxLines) {
        this.maxLines = maxLines;
        return (T) this;
    }

    public T textGravity(int gravity) {
        this.textGravity = gravity;
        return (T) this;
    }

    public T ellipsize(TextUtils.TruncateAt ellipsize) {
        this.ellipsize = ellipsize;
        return (T) this;
    }

    public T textSize(int textSize) {
        this.textSize = textSize;
        return (T) this;
    }
}
