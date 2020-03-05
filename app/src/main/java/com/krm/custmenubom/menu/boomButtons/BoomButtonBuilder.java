package com.krm.custmenubom.menu.boomButtons;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.Gravity;

import com.krm.custmenubom.menu.piece.BoomPiece;
import com.krm.custmenubom.menu.Util;

import java.lang.ref.WeakReference;

@SuppressWarnings({"unchecked", "unused"})
public abstract class BoomButtonBuilder<T> {

    private BoomPiece piece = null;
    private WeakReference<BoomButton> boomButtonWeakReference;

    // Basic
    int index = -1;
    InnerOnBoomButtonClickListener listener;
    OnBMClickListener onBMClickListener;
    boolean rotateImage = true;
    boolean rotateText = true;
    boolean containsSubText = true;

    // piece
    Integer pieceColor = null;
    int pieceColorRes = 0;

    // Shadow
    boolean shadowEffect = true;
    int shadowOffsetX = Util.dp2px(0);
    int shadowOffsetY = Util.dp2px(3);
    int shadowRadius = Util.dp2px(8);
    int shadowColor = Color.parseColor("#88757575");
    int shadowCornerRadius = Util.dp2px(5);

    // Images
    int normalImageRes = 0;
    int highlightedImageRes = 0;
    int unableImageRes = 0;
    Drawable normalImageDrawable = null;
    Drawable highlightedImageDrawable = null;
    Drawable unableImageDrawable = null;
    Rect imageRect = new Rect(Util.dp2px(10), Util.dp2px(10), Util.dp2px(70), Util.dp2px(70));
    Rect imagePadding = new Rect(0, 0, 0, 0);

    // Text
    int normalTextRes = 0;
    int highlightedTextRes = 0;
    int unableTextRes = 0;
    String normalText;
    String highlightedText;
    String unableText;
    int normalTextColor = Color.WHITE;
    int normalTextColorRes = 0;
    int highlightedTextColor = Color.WHITE;
    int highlightedTextColorRes = 0;
    int unableTextColor = Color.WHITE;
    int unableTextColorRes = 0;
    Rect textRect = new Rect(Util.dp2px(15), Util.dp2px(52), Util.dp2px(65), Util.dp2px(72));
    Rect textPadding = new Rect(0, 0, 0, 0);
    Typeface typeface = null;
    int maxLines = 1;
    int textGravity = Gravity.CENTER;
    TextUtils.TruncateAt ellipsize = TextUtils.TruncateAt.MARQUEE;
    int textSize = 10;

    // Sub text
    int subNormalTextRes = 0;
    int subHighlightedTextRes = 0;
    int subUnableTextRes = 0;
    String subNormalText;
    String subHighlightedText;
    String subUnableText;
    int subNormalTextColor = Color.WHITE;
    int subNormalTextColorRes = 0;
    int subHighlightedTextColor = Color.WHITE;
    int subHighlightedTextColorRes = 0;
    int subUnableTextColor = Color.WHITE;
    int subUnableTextColorRes = 0;
    Rect subTextRect = new Rect(Util.dp2px(70), Util.dp2px(35), Util.dp2px(280), Util.dp2px(55));
    Rect subTextPadding = new Rect(0, 0, 0, 0);
    Typeface subTypeface = null;
    int subMaxLines = 1;
    int subTextGravity = Gravity.START | Gravity.CENTER_VERTICAL;
    TextUtils.TruncateAt subEllipsize = TextUtils.TruncateAt.MARQUEE;
    int subTextSize = 10;

    // Text for text-outside-circle-button
    int textTopMargin = Util.dp2px(5);
    int textWidth = Util.dp2px(80);
    int textHeight = Util.dp2px(20);

    // Button
    boolean rippleEffect = true;
    int normalColor = Util.getColor();
    int normalColorRes = 0;
    int highlightedColor = Util.getColor();
    int highlightedColorRes = 0;
    int unableColor = Util.getColor();
    int unableColorRes = 0;
    boolean unable = false;
    int buttonRadius = Util.dp2px(40);
    int buttonWidth = Util.dp2px(300);
    int buttonHeight = Util.dp2px(60);
    int buttonCornerRadius = Util.dp2px(5);
    boolean isRound = true;  // only for simple circle/text inside/text outside circle button

    public abstract BoomButton build(Context context);

    public int pieceColor(Context context) {
        if (pieceColor == null && pieceColorRes == 0)
            if (unable) return Util.getColor(context, unableColorRes, unableColor);
            else return Util.getColor(context, normalColorRes, normalColor);
        else if (pieceColor == null) return Util.getColor(context, pieceColorRes);
        else return Util.getColor(context, pieceColorRes, pieceColor);
    }

    public void setUnable(boolean unable) {
        this.unable = unable;
    }

    public BoomButton button() {
        if (boomButtonWeakReference != null) return boomButtonWeakReference.get();
        return null;
    }

    void weakReferenceButton(BoomButton button) {
        boomButtonWeakReference = new WeakReference<>(button);
    }

    public void piece(BoomPiece piece) {
        this.piece = piece;
    }

    public BoomButtonBuilder index(int index) {
        this.index = index;
        return this;
    }

    public BoomButtonBuilder innerListener(InnerOnBoomButtonClickListener listener) {
        this.listener = listener;
        return this;
    }

    public T listener(OnBMClickListener onBMClickListener) {
        this.onBMClickListener = onBMClickListener;
        return (T) this;
    }

    public T rotateImage(boolean rotateImage) {
        this.rotateImage = rotateImage;
        return (T) this;
    }

    public T shadowEffect(boolean shadowEffect) {
        this.shadowEffect = shadowEffect;
        return (T) this;
    }

    public T shadowOffsetX(int shadowOffsetX) {
        this.shadowOffsetX = shadowOffsetX;
        return (T) this;
    }

    public T shadowOffsetY(int shadowOffsetY) {
        this.shadowOffsetY = shadowOffsetY;
        return (T) this;
    }

    public T shadowRadius(int shadowRadius) {
        this.shadowRadius = shadowRadius;
        return (T) this;
    }

    public T shadowCornerRadius(int shadowCornerRadius) {
        this.shadowCornerRadius = shadowCornerRadius;
        return (T) this;
    }

    public T shadowColor(int shadowColor) {
        this.shadowColor = shadowColor;
        return (T) this;
    }

    public T normalImageDrawable(Drawable normalImageDrawable) {
        if (this.normalImageDrawable != normalImageDrawable) {
            this.normalImageDrawable = normalImageDrawable;
            BoomButton button = button();
            if (button != null) {
                button.normalImageDrawable = normalImageDrawable;
                button.updateImage();
            }
        }
        return (T) this;
    }

    public T normalImageRes(int normalImageRes) {
        if (this.normalImageRes != normalImageRes) {
            this.normalImageRes = normalImageRes;
            BoomButton button = button();
            if (button != null) {
                button.normalImageRes = normalImageRes;
                button.updateImage();
            }
        }
        return (T) this;
    }

    public T highlightedImageDrawable(Drawable highlightedImageDrawable) {
        if (this.highlightedImageDrawable != highlightedImageDrawable) {
            this.highlightedImageDrawable = highlightedImageDrawable;
            BoomButton button = button();
            if (button != null) {
                button.highlightedImageDrawable = highlightedImageDrawable;
                button.updateImage();
            }
        }
        return (T) this;
    }

    public T highlightedImageRes(int highlightedImageRes) {
        if (this.highlightedImageRes != highlightedImageRes) {
            this.highlightedImageRes = highlightedImageRes;
            BoomButton button = button();
            if (button != null) {
                button.highlightedImageRes = highlightedImageRes;
                button.updateImage();
            }
        }
        return (T) this;
    }

    public T unableImageDrawable(Drawable unableImageDrawable) {
        if (this.unableImageDrawable != unableImageDrawable) {
            this.unableImageDrawable = unableImageDrawable;
            BoomButton button = button();
            if (button != null) {
                button.unableImageDrawable = unableImageDrawable;
                button.updateImage();
            }
        }
        return (T) this;
    }

    public T unableImageRes(int unableImageRes) {
        if (this.unableImageRes != unableImageRes) {
            this.unableImageRes = unableImageRes;
            BoomButton button = button();
            if (button != null) {
                button.unableImageRes = unableImageRes;
                button.updateImage();
            }
        }
        return (T) this;
    }

    public T imageRect(Rect imageRect) {
        if (this.imageRect != imageRect) {
            this.imageRect = imageRect;
            BoomButton button = button();
            if (button != null) {
                button.imageRect = imageRect;
                button.updateImageRect();
            }
        }
        return (T) this;
    }

    public T imagePadding(Rect imagePadding) {
        if (this.imagePadding != imagePadding) {
            this.imagePadding = imagePadding;
            BoomButton button = button();
            if (button != null) {
                button.imagePadding = imagePadding;
                button.updateImagePadding();
            }
        }
        return (T) this;
    }

    public T rippleEffect(boolean rippleEffect) {
        this.rippleEffect = rippleEffect;
        return (T) this;
    }

    public T normalColor(int normalColor) {
        if (this.normalColor != normalColor) {
            this.normalColor = normalColor;
            BoomButton button = button();
            if (button != null) {
                button.normalColor = normalColor;
                button.updateButtonDrawable();
            }
        }
        return (T) this;
    }

    public T normalColorRes(int normalColorRes) {
        if (this.normalColorRes != normalColorRes) {
            this.normalColorRes = normalColorRes;
            BoomButton button = button();
            if (button != null) {
                button.normalColorRes = normalColorRes;
                button.updateButtonDrawable();
            }
        }
        return (T) this;
    }

    public T highlightedColor(int highlightedColor) {
        if (this.highlightedColor != highlightedColor) {
            this.highlightedColor = highlightedColor;
            BoomButton button = button();
            if (button != null) {
                button.highlightedColor = highlightedColor;
                button.updateButtonDrawable();
            }
        }
        return (T) this;
    }

    public T highlightedColorRes(int highlightedColorRes) {
        if (this.highlightedColorRes != highlightedColorRes) {
            this.highlightedColorRes = highlightedColorRes;
            BoomButton button = button();
            if (button != null) {
                button.highlightedColorRes = highlightedColorRes;
                button.updateButtonDrawable();
            }
        }
        return (T) this;
    }

    public T unableColor(int unableColor) {
        if (this.unableColor != unableColor) {
            this.unableColor = unableColor;
            BoomButton button = button();
            if (button != null) {
                button.unableColor = unableColor;
                button.updateButtonDrawable();
            }
        }
        return (T) this;
    }

    public T unableColorRes(int unableColorRes) {
        if (this.unableColorRes != unableColorRes) {
            this.unableColorRes = unableColorRes;
            BoomButton button = button();
            if (button != null) {
                button.unableColorRes = unableColorRes;
                button.updateButtonDrawable();
            }
        }
        return (T) this;
    }

    public T pieceColor(int pieceColor) {
        if (this.pieceColor == null || this.pieceColor != pieceColor) {
            this.pieceColor = pieceColor;
            BoomButton button = button();
            if (button != null) button.pieceColor = pieceColor;
            if (piece != null) piece.setColor(pieceColor);
        }
        return (T) this;
    }

    public T pieceColorRes(int pieceColorRes) {
        if (this.pieceColorRes != pieceColorRes) {
            this.pieceColorRes = pieceColorRes;
            BoomButton button = button();
            if (button != null) button.pieceColorRes = pieceColorRes;
            if (piece != null) piece.setColorRes(pieceColorRes);
        }
        return (T) this;
    }

    public T unable(boolean unable) {
        if (this.unable != unable) {
            this.unable = unable;
            BoomButton button = button();
            if (button != null) {
                button.unable = unable;
                button.updateUnable();
                if (piece != null) piece.setColor(button.pieceColor());
            }
        }
        return (T) this;
    }
}
