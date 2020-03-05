package com.krm.custmenubom.menu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.PowerManager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import com.krm.custmenubom.R;
import com.krm.custmenubom.menu.animation.AnimationManager;
import com.krm.custmenubom.menu.animation.BoomEnum;
import com.krm.custmenubom.menu.animation.Ease;
import com.krm.custmenubom.menu.animation.EaseEnum;
import com.krm.custmenubom.menu.animation.HideRgbEvaluator;
import com.krm.custmenubom.menu.animation.OrderEnum;
import com.krm.custmenubom.menu.animation.Rotate3DAnimation;
import com.krm.custmenubom.menu.animation.ShareLinesView;
import com.krm.custmenubom.menu.animation.ShowRgbEvaluator;
import com.krm.custmenubom.menu.boomButtons.BoomButton;
import com.krm.custmenubom.menu.boomButtons.BoomButtonBuilder;
import com.krm.custmenubom.menu.boomButtons.ButtonPlaceAlignmentEnum;
import com.krm.custmenubom.menu.boomButtons.ButtonPlaceEnum;
import com.krm.custmenubom.menu.boomButtons.ButtonPlaceManager;
import com.krm.custmenubom.menu.boomButtons.HamButton;
import com.krm.custmenubom.menu.boomButtons.InnerOnBoomButtonClickListener;
import com.krm.custmenubom.menu.boomButtons.SimpleCircleButton;
import com.krm.custmenubom.menu.boomButtons.TextInsideCircleButton;
import com.krm.custmenubom.menu.boomButtons.TextOutsideCircleButton;
import com.krm.custmenubom.menu.piece.BoomPiece;
import com.krm.custmenubom.menu.piece.PiecePlaceEnum;
import com.krm.custmenubom.menu.piece.PiecePlaceManager;

import java.util.ArrayList;

import static com.krm.custmenubom.menu.piece.PiecePlaceEnum.Share;

@SuppressWarnings("unused")
public class BoomMenuButton extends FrameLayout implements InnerOnBoomButtonClickListener {

    protected static final String TAG = "BoomMenuButton";

    // Basic
    private Context context;
    private boolean needToLayout = true;
    private boolean cacheOptimization;
    private boolean boomInWholeScreen;
    private boolean inList;
    private boolean inFragment;
    private boolean isBackPressListened = true;
    private Runnable layoutJobsRunnable;

    // Shadow
    private boolean shadowEffect;
    private int shadowOffsetX;
    private int shadowOffsetY;
    private int shadowRadius;
    private int shadowColor;
    private BMBShadow shadow;

    // Button
    private int buttonRadius;
    private ButtonEnum buttonEnum = ButtonEnum.Unknown;
    private boolean backgroundEffect;
    private boolean rippleEffect;
    private int normalColor;
    private int highlightedColor;
    private int unableColor;
    private boolean draggable;
    private float startPositionX;
    private float startPositionY;
    private boolean ableToStartDragging = false;
    private boolean isDragging = false;
    private float lastMotionX = -1;
    private float lastMotionY = -1;
    private Rect edgeInsetsInParentView;
    private FrameLayout button;

    // Piece
    private ArrayList<BoomPiece> pieces;
    private ArrayList<RectF> piecePositions;
    private float dotRadius;
    private float hamWidth;
    private float hamHeight;
    private float pieceCornerRadius = -1;
    private float pieceHorizontalMargin;
    private float pieceVerticalMargin;
    private float pieceInclinedMargin;
    private float shareLineLength;
    private int shareLine1Color;
    private int shareLine2Color;
    private float shareLineWidth;
    private ShareLinesView shareLinesView;
    private PiecePlaceEnum piecePlaceEnum = PiecePlaceEnum.Unknown;
    private ArrayList<PointF> customPiecePlacePositions = new ArrayList<>();

    // Animation
    private int animatingViewNumber = 0;
    private OnBoomListener onBoomListener;
    private int dimColor;
    private long showDuration;
    private long showDelay;
    private long hideDuration;
    private long hideDelay;
    private boolean cancelable;
    private boolean autoHide;
    private OrderEnum orderEnum;
    private int frames;
    private BoomEnum boomEnum;
    private EaseEnum showMoveEaseEnum;
    private EaseEnum showScaleEaseEnum;
    private EaseEnum showRotateEaseEnum;
    private EaseEnum hideMoveEaseEnum;
    private EaseEnum hideScaleEaseEnum;
    private EaseEnum hideRotateEaseEnum;
    private int rotateDegree;
    private boolean use3DTransformAnimation;
    private boolean autoBoom;
    private boolean autoBoomImmediately;
    private BoomStateEnum boomStateEnum = BoomStateEnum.DidReboom;

    // Background
    private BackgroundView background;

    // Boom Buttons
    private ArrayList<BoomButton> boomButtons = new ArrayList<>();
    private ArrayList<BoomButtonBuilder> boomButtonBuilders = new ArrayList<>();
    private float simpleCircleButtonRadius;
    private float textInsideCircleButtonRadius;
    private float textOutsideCircleButtonWidth;
    private float textOutsideCircleButtonHeight;
    private float hamButtonWidth;
    private float hamButtonHeight;
    private ButtonPlaceEnum buttonPlaceEnum = ButtonPlaceEnum.Unknown;
    private ArrayList<PointF> customButtonPlacePositions = new ArrayList<>();
    private ButtonPlaceAlignmentEnum buttonPlaceAlignmentEnum;
    private float buttonHorizontalMargin;
    private float buttonVerticalMargin;
    private float buttonInclinedMargin;
    private float buttonTopMargin;
    private float buttonBottomMargin;
    private float buttonLeftMargin;
    private float buttonRightMargin;
    private ArrayList<PointF> startPositions;
    private ArrayList<PointF> endPositions;
    private float bottomHamButtonTopMargin;
    private boolean needToCreateButtons = true;
    private boolean needToCalculateStartPositions = true;
    private int lastReboomIndex = -1;

    // Orientation
    private boolean isOrientationAdaptable;
    private int lastOrientation = OrientationEventListener.ORIENTATION_UNKNOWN;
    private boolean isOrientationChanged = false;
    private OrientationEventListener orientationEventListener;

    private void ____________________________Initialization() {
    }
    //region Constructor and Initializer

    public BoomMenuButton(Context context) {
        super(context);
        init(context, null);
    }

    public BoomMenuButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BoomMenuButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;

        LayoutInflater.from(context).inflate(R.layout.bmb, this, true);
        initAttrs(context, attrs);
        initShadow();
        initButton();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(
                attrs, R.styleable.BoomMenuButton, 0, 0);
        try {
            // Basic
            cacheOptimization = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_cacheOptimization, R.bool.default_bmb_cacheOptimization);
            boomInWholeScreen = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_boomInWholeScreen, R.bool.default_bmb_boomInWholeScreen);
            inList = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_inList, R.bool.default_bmb_inList);
            inFragment = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_inFragment, R.bool.default_bmb_inFragment);
            isBackPressListened = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_backPressListened, R.bool.default_bmb_backPressListened);
            isOrientationAdaptable = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_orientationAdaptable, R.bool.default_bmb_orientationAdaptable);

            // Shadow
            shadowEffect = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_shadowEffect, R.bool.default_bmb_shadow_effect);
            shadowRadius = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_shadowRadius, R.dimen.default_bmb_shadow_radius);
            shadowOffsetX = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_shadowOffsetX, R.dimen.default_bmb_shadow_offset_x);
            shadowOffsetY = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_shadowOffsetY, R.dimen.default_bmb_shadow_offset_y);
            shadowColor = Util.getColor(typedArray, R.styleable.BoomMenuButton_bmb_shadowColor, R.color.default_bmb_shadow_color);

            // Button
            buttonRadius = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_buttonRadius, R.dimen.default_bmb_button_radius);
            buttonEnum = ButtonEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_buttonEnum, R.integer.default_bmb_button_enum));
            backgroundEffect = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_backgroundEffect, R.bool.default_bmb_background_effect);
            rippleEffect = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_rippleEffect, R.bool.default_bmb_ripple_effect);
            normalColor = Util.getColor(typedArray, R.styleable.BoomMenuButton_bmb_normalColor, R.color.default_bmb_normal_color);
            highlightedColor = Util.getColor(typedArray, R.styleable.BoomMenuButton_bmb_highlightedColor, R.color.default_bmb_highlighted_color);
            if (highlightedColor == Color.TRANSPARENT)
                highlightedColor = Util.getDarkerColor(normalColor);
            unableColor = Util.getColor(typedArray, R.styleable.BoomMenuButton_bmb_unableColor, R.color.default_bmb_unable_color);
            if (unableColor == Color.TRANSPARENT) unableColor = Util.getLighterColor(normalColor);
            draggable = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_draggable, R.bool.default_bmb_draggable);
            edgeInsetsInParentView = new Rect(0, 0, 0, 0);
            edgeInsetsInParentView.left = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_edgeInsetsLeft, R.dimen.default_bmb_edgeInsetsLeft);
            edgeInsetsInParentView.top = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_edgeInsetsTop, R.dimen.default_bmb_edgeInsetsTop);
            edgeInsetsInParentView.right = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_edgeInsetsRight, R.dimen.default_bmb_edgeInsetsRight);
            edgeInsetsInParentView.bottom = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_edgeInsetsBottom, R.dimen.default_bmb_edgeInsetsBottom);

            // Piece
            dotRadius = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_dotRadius, R.dimen.default_bmb_dotRadius);
            hamWidth = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_hamWidth, R.dimen.default_bmb_hamWidth);
            hamHeight = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_hamHeight, R.dimen.default_bmb_hamHeight);
            pieceCornerRadius = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_pieceCornerRadius, R.dimen.default_bmb_pieceCornerRadius);
            pieceHorizontalMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_pieceHorizontalMargin, R.dimen.default_bmb_pieceHorizontalMargin);
            pieceVerticalMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_pieceVerticalMargin, R.dimen.default_bmb_pieceVerticalMargin);
            pieceInclinedMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_pieceInclinedMargin, R.dimen.default_bmb_pieceInclinedMargin);
            shareLineLength = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_sharedLineLength, R.dimen.default_bmb_sharedLineLength);
            shareLine1Color = Util.getColor(typedArray, R.styleable.BoomMenuButton_bmb_shareLine1Color, R.color.default_bmb_shareLine1Color);
            shareLine2Color = Util.getColor(typedArray, R.styleable.BoomMenuButton_bmb_shareLine2Color, R.color.default_bmb_shareLine2Color);
            shareLineWidth = Util.getDimenSize(typedArray, R.styleable.BoomMenuButton_bmb_shareLineWidth, R.dimen.default_bmb_shareLineWidth);
            piecePlaceEnum = PiecePlaceEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_piecePlaceEnum, R.integer.default_bmb_pieceEnum));

            // Animation
            dimColor = Util.getColor(typedArray, R.styleable.BoomMenuButton_bmb_dimColor, R.color.default_bmb_dimColor);
            showDuration = Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_showDuration, R.integer.default_bmb_showDuration);
            showDelay = Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_showDelay, R.integer.default_bmb_showDelay);
            hideDuration = Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_hideDuration, R.integer.default_bmb_hideDuration);
            hideDelay = Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_hideDelay, R.integer.default_bmb_hideDelay);
            cancelable = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_cancelable, R.bool.default_bmb_cancelable);
            autoHide = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_autoHide, R.bool.default_bmb_autoHide);
            orderEnum = OrderEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_orderEnum, R.integer.default_bmb_orderEnum));
            frames = Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_frames, R.integer.default_bmb_frames);
            boomEnum = BoomEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_boomEnum, R.integer.default_bmb_boomEnum));
            showMoveEaseEnum = EaseEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_showMoveEaseEnum, R.integer.default_bmb_showMoveEaseEnum));
            showScaleEaseEnum = EaseEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_showScaleEaseEnum, R.integer.default_bmb_showScaleEaseEnum));
            showRotateEaseEnum = EaseEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_showRotateEaseEnum, R.integer.default_bmb_showRotateEaseEnum));
            hideMoveEaseEnum = EaseEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_hideMoveEaseEnum, R.integer.default_bmb_hideMoveEaseEnum));
            hideScaleEaseEnum = EaseEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_hideScaleEaseEnum, R.integer.default_bmb_hideScaleEaseEnum));
            hideRotateEaseEnum = EaseEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_hideRotateEaseEnum, R.integer.default_bmb_hideRotateEaseEnum));
            rotateDegree = Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_rotateDegree, R.integer.default_bmb_rotateDegree);
            use3DTransformAnimation = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_use3DTransformAnimation, R.bool.default_bmb_use3DTransformAnimation);
            autoBoom = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_autoBoom, R.bool.default_bmb_autoBoom);
            autoBoomImmediately = Util.getBoolean(typedArray, R.styleable.BoomMenuButton_bmb_autoBoomImmediately, R.bool.default_bmb_autoBoomImmediately);

            // Boom buttons
            buttonPlaceEnum = ButtonPlaceEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_buttonPlaceEnum, R.integer.default_bmb_buttonPlaceEnum));
            buttonPlaceAlignmentEnum = ButtonPlaceAlignmentEnum.getEnum(Util.getInt(typedArray, R.styleable.BoomMenuButton_bmb_buttonPlaceAlignmentEnum, R.integer.default_bmb_buttonPlaceAlignmentEnum));
            buttonHorizontalMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_buttonHorizontalMargin, R.dimen.default_bmb_buttonHorizontalMargin);
            buttonVerticalMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_buttonVerticalMargin, R.dimen.default_bmb_buttonVerticalMargin);
            buttonInclinedMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_buttonInclinedMargin, R.dimen.default_bmb_buttonInclinedMargin);
            buttonTopMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_buttonTopMargin, R.dimen.default_bmb_buttonTopMargin);
            buttonBottomMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_buttonBottomMargin, R.dimen.default_bmb_buttonBottomMargin);
            buttonLeftMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_buttonLeftMargin, R.dimen.default_bmb_buttonLeftMargin);
            buttonRightMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_buttonRightMargin, R.dimen.default_bmb_buttonRightMargin);
            bottomHamButtonTopMargin = Util.getDimenOffset(typedArray, R.styleable.BoomMenuButton_bmb_bottomHamButtonTopMargin, R.dimen.default_bmb_bottomHamButtonTopMargin);
        } finally {
            typedArray.recycle();
        }
    }

    private void initShadow() {
        if (shadow == null) shadow = findViewById(R.id.shadow);
        boolean hasShadow = shadowEffect && backgroundEffect && !inList;
        shadow.setShadowEffect(hasShadow);
        if (hasShadow) {
            shadow.setShadowOffsetX(shadowOffsetX);
            shadow.setShadowOffsetY(shadowOffsetY);
            shadow.setShadowColor(shadowColor);
            shadow.setShadowRadius(shadowRadius);
            shadow.setShadowCornerRadius(shadowRadius + buttonRadius);
        } else {
            shadow.clearShadow();
        }
    }

    private void initButton() {
        if (button == null) button = findViewById(R.id.button);
        button.setOnClickListener(v -> boom());
        initDraggableTouchListener();

        setButtonSize();
        setButtonBackground();
    }

    private void setButtonSize() {
        LayoutParams params = (LayoutParams) button.getLayoutParams();
        params.width = buttonRadius * 2;
        params.height = buttonRadius * 2;
        button.setLayoutParams(params);
    }

    private void setButtonBackground() {
        if (backgroundEffect && !inList) {
            if (rippleEffect && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                RippleDrawable rippleDrawable = new RippleDrawable(
                        ColorStateList.valueOf(highlightedColor),
                        Util.getOvalDrawable(button, normalColor),
                        null);
                Util.setDrawable(button, rippleDrawable);
            } else {
                StateListDrawable stateListDrawable = Util.getOvalStateListBitmapDrawable(
                        button,
                        buttonRadius,
                        normalColor,
                        highlightedColor,
                        unableColor);
                Util.setDrawable(button, stateListDrawable);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Util.setDrawable(button, Util.getSystemDrawable(context, android.R.attr.selectableItemBackgroundBorderless));
            } else {
                Util.setDrawable(button, Util.getSystemDrawable(context, android.R.attr.selectableItemBackground));
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode
                && isBackPressListened
                && (boomStateEnum == BoomStateEnum.WillBoom || boomStateEnum == BoomStateEnum.DidBoom)) {
            reboom();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //endregion

    private void ____________________________Place_Pieces() {
    }

    //region Place Pieces

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (isOrientationChanged) {
            reLayoutAfterOrientationChanged();
        }
        if (needToLayout) {
            if (inList) delayToDoLayoutJobs();
            else doLayoutJobs();
        }
        needToLayout = false;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        checkAutoBoom();
    }

    private void doLayoutJobs() {
        if (uninitializedBoomButtons()) return;
        clearPieces();
        createPieces();
        placeShareLinesView();
        placePieces();
        placePiecesAtPositions();
        calculateStartPositions(false);
        setShareLinesViewData();
    }

    private void clearPieces() {
        if (pieces != null) for (BoomPiece piece : pieces) button.removeView(piece);
        if (pieces != null) pieces.clear();
    }

    private void createPieces() {
        calculatePiecePositions();
        int pieceNumber = pieceNumber();
        pieces = new ArrayList<>(pieceNumber);
        for (int i = 0; i < pieceNumber; i++)
            pieces.add(PiecePlaceManager.createPiece(this, boomButtonBuilders.get(i)));
    }

    private void placePieces() {
        ArrayList<Integer> indexes;
        if (piecePlaceEnum == Share)
            indexes = AnimationManager.getOrderIndex(OrderEnum.DEFAULT, pieces.size());
        else
            indexes = AnimationManager.getOrderIndex(orderEnum, pieces.size());
        // Reverse to keep the former pieces are above than the latter(z-axis)
        // So the early-animating pieces are above than the later ones
        for (int i = indexes.size() - 1; i >= 0; i--) button.addView(pieces.get(indexes.get(i)));
    }

    private void placePiecesAtPositions() {
        int pieceNumber = pieceNumber();
        for (int i = 0; i < pieceNumber; i++) pieces.get(i).place(piecePositions.get(i));
    }

    private void calculatePiecePositions() {
        switch (buttonEnum) {
            case SimpleCircle:
            case TextInsideCircle:
            case TextOutsideCircle:
                if (piecePlaceEnum == Share)
                    piecePositions = PiecePlaceManager.getShareDotPositions(this,
                            new Point(button.getWidth(), button.getHeight()),
                            boomButtonBuilders.size());
                else
                    piecePositions = PiecePlaceManager.getDotPositions(this,
                            new Point(button.getWidth(), button.getHeight()));
                break;
            case Ham:
                piecePositions = PiecePlaceManager.getHamPositions(this,
                        new Point(button.getWidth(), button.getHeight()));
                break;
            case Unknown:
                throw new RuntimeException("The button-enum is unknown!");
        }
    }

    //endregion

    private void ____________________________Touch() {
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initDraggableTouchListener() {
        if (button == null) return;
        if (!draggable) button.setOnTouchListener(null);
        else {
            button.setOnTouchListener((v, event) -> {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN:
                        if (draggable) {
                            startPositionX = getX() - event.getRawX();
                            startPositionY = getY() - event.getRawY();
                            lastMotionX = event.getRawX();
                            lastMotionY = event.getRawY();
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (Math.abs(lastMotionX - event.getRawX()) > 10
                                || Math.abs(lastMotionY - event.getRawY()) > 10)
                            ableToStartDragging = true;
                        if (draggable && ableToStartDragging) {
                            isDragging = true;
                            if (shadow != null) {
                                setX(event.getRawX() + startPositionX);
                                setY(event.getRawY() + startPositionY);
                            }
                        } else {
                            ableToStartDragging = false;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (isDragging) {
                            ableToStartDragging = false;
                            isDragging = false;
                            needToCalculateStartPositions = true;
                            preventDragOutside();
                            button.setPressed(false);
                            return true;
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        if (isDragging) {
                            ableToStartDragging = false;
                            isDragging = false;
                            needToCalculateStartPositions = true;
                            preventDragOutside();
                            return true;
                        }
                        break;
                }
                return false;
            });
        }
    }

    //endregion

    private void ____________________________Animation() {
    }
    //region Animation

    public boolean isAnimating() {
        return animatingViewNumber != 0;
    }

    public boolean isBoomed() {
        return boomStateEnum == BoomStateEnum.DidBoom;
    }

    public boolean isReBoomed() {
        return boomStateEnum == BoomStateEnum.DidReboom;
    }

    public void boom() {
        innerBoom(false);
    }

    public void boomImmediately() {
        innerBoom(true);
    }

    private void innerBoom(boolean immediately) {
        if (isAnimating() || boomStateEnum != BoomStateEnum.DidReboom) return;
        ExceptionManager.judge(this, boomButtonBuilders);
        boomStateEnum = BoomStateEnum.WillBoom;
        if (onBoomListener != null) onBoomListener.onBoomWillShow();
        calculateStartPositions(false);
        createButtons();
        dimBackground(immediately);
        startBoomAnimations(immediately);
        startBoomAnimationForFadeViews(immediately);
        if (isBackPressListened) {
            setFocusable(true);
            setFocusableInTouchMode(true);
            requestFocus();
        }
    }

    public void reboom() {
        innerReboom(false);
    }

    public void reboomImmediately() {
        innerReboom(true);
    }

    private void innerReboom(boolean immediately) {
        if (isAnimating() || boomStateEnum != BoomStateEnum.DidBoom) return;
        boomStateEnum = BoomStateEnum.WillReboom;
        if (onBoomListener != null) onBoomListener.onBoomWillHide();
        lightBackground(immediately);
        startReboomAnimations(immediately);
        startReboomAnimationForFadeViews(immediately);
        if (isBackPressListened) {
            setFocusable(false);
            setFocusableInTouchMode(false);
        }
    }

    private void dimBackground(boolean immediately) {
        createBackground();
        Util.setVisibility(VISIBLE, background);
        long duration = immediately ? 1 : showDuration + showDelay * (pieces.size() - 1);
        background.dim(duration, new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                boomStateEnum = BoomStateEnum.DidBoom;
                if (onBoomListener != null) onBoomListener.onBoomDidShow();
            }
        });
        if (piecePlaceEnum == Share) {
            AnimationManager.animate(shareLinesView, "showProcess", 0, duration,
                    Ease.getInstance(EaseEnum.Linear), 0f, 1f);
        }
    }

    private void lightBackground(boolean immediately) {
        createBackground();
        long duration = immediately ? 1 : hideDuration + hideDelay * (pieces.size() - 1);
        background.light(duration);
        if (piecePlaceEnum == Share) {
            AnimationManager.animate(shareLinesView, "hideProcess", 0, duration,
                    Ease.getInstance(EaseEnum.Linear), 0f, 1f);
        }
    }

    private void finishReboomAnimations() {
        if (isAnimating()) {
            return;
        }
        boomStateEnum = BoomStateEnum.DidReboom;
        if (onBoomListener != null) onBoomListener.onBoomDidHide();
        background.setVisibility(GONE);
        clearViews();
    }

    private void startBoomAnimations(boolean immediately) {
        if (background != null) background.removeAllViews();
        calculateEndPositions();
        ArrayList<Integer> indexes;
        if (piecePlaceEnum == Share)
            indexes = AnimationManager.getOrderIndex(OrderEnum.DEFAULT, pieces.size());
        else indexes = AnimationManager.getOrderIndex(orderEnum, pieces.size());
        // There is a strange bug when use3DTransformAnimation is true.
        // The last boom-button that rebooms has a strange behavior with a duplicate shadow.
        // So we need to recreate it.
        if (lastReboomIndex != -1 && use3DTransformAnimation)
            boomButtons.set(lastReboomIndex, boomButtonBuilders.get(lastReboomIndex)
                    .innerListener(this).index(lastReboomIndex).build(context));
        // Reverse to keep the former boom-buttons are above than the latter(z-axis)
        // So the early-animating boom-buttons are above than the later ones
        for (int i = indexes.size() - 1; i >= 0; i--) {
            int index = indexes.get(i);
            BoomButton boomButton = boomButtons.get(index);
            PointF startPosition = new PointF(
                    startPositions.get(index).x - boomButton.centerPoint.x,
                    startPositions.get(index).y - boomButton.centerPoint.y);
            putBoomButtonInBackground(boomButton, startPosition);
            startEachBoomAnimation(pieces.get(index), boomButton, startPosition,
                    endPositions.get(index), i, immediately);
        }
    }

    private void startReboomAnimations(boolean immediately) {
        ArrayList<Integer> indexes;
        if (piecePlaceEnum == Share)
            indexes = AnimationManager.getOrderIndex(OrderEnum.REVERSE, pieces.size());
        else indexes = AnimationManager.getOrderIndex(orderEnum, pieces.size());
        lastReboomIndex = indexes.get(indexes.size() - 1);
        for (Integer index : indexes) boomButtons.get(index).bringToFront();
        for (int i = 0; i < indexes.size(); i++) {
            int index = indexes.get(i);
            BoomButton boomButton = boomButtons.get(index);
            PointF startPosition = new PointF(
                    startPositions.get(index).x - boomButton.centerPoint.x,
                    startPositions.get(index).y - boomButton.centerPoint.y);
            startEachReboomAnimation(pieces.get(index), boomButton,
                    endPositions.get(index), startPosition, i, immediately);
        }
    }

    private void startEachBoomAnimation(final BoomPiece piece,
                                        final BoomButton boomButton,
                                        final PointF startPosition,
                                        final PointF endPosition,
                                        final int delayFactor,
                                        final boolean immediately) {
        if (isBatterySaveModeTurnOn()) {
            post(() -> innerStartEachBoomAnimation(piece, boomButton, startPosition, endPosition, delayFactor, immediately));
        } else {
            innerStartEachBoomAnimation(piece, boomButton, startPosition, endPosition, delayFactor, immediately);
        }
    }

    private void innerStartEachBoomAnimation(final BoomPiece piece,
                                             final BoomButton boomButton,
                                             PointF startPosition,
                                             PointF endPosition,
                                             int delayFactor,
                                             boolean immediately) {
        animatingViewNumber++;
        float[] xs = new float[frames + 1];
        float[] ys = new float[frames + 1];
        float scaleX = piece.getWidth() * 1.0f / boomButton.contentWidth();
        float scaleY = piece.getHeight() * 1.0f / boomButton.contentHeight();
        long delay = immediately ? 1 : showDelay * delayFactor;
        long duration = immediately ? 1 : showDuration;
        boomButton.setSelfScaleAnchorPoints();
        boomButton.setScaleX(scaleX);
        boomButton.setScaleY(scaleY);
        boomButton.hideAllGoneViews();
        AnimationManager.calculateShowXY(boomEnum,
                new PointF(background.getLayoutParams().width, background.getLayoutParams().height),
                Ease.getInstance(showMoveEaseEnum), frames, startPosition, endPosition, xs, ys);
        if (boomButton.isNeededColorAnimation()) {
            if (boomButton.prepareColorTransformAnimation())
                AnimationManager.animate(boomButton, "rippleButtonColor", delay, duration, ShowRgbEvaluator.getInstance(), boomButton.pieceColor(), boomButton.buttonColor());
            else
                AnimationManager.animate(boomButton, "nonRippleButtonColor", delay, duration, ShowRgbEvaluator.getInstance(), boomButton.pieceColor(), boomButton.buttonColor());
        }
        AnimationManager.animate(boomButton, "x", delay, duration, new LinearInterpolator(), xs);
        AnimationManager.animate(boomButton, "y", delay, duration, new LinearInterpolator(), ys);
        AnimationManager.rotate(boomButton, delay, duration, Ease.getInstance(showRotateEaseEnum), 0, rotateDegree);
        AnimationManager.animate("alpha", delay, duration, new float[]{0, 1}, Ease.getInstance(EaseEnum.Linear), boomButton.goneViews());
        AnimationManager.animate(boomButton, "scaleX", delay, duration, Ease.getInstance(showScaleEaseEnum), scaleX, 1);
        AnimationManager.animate(boomButton, "scaleY", delay, duration, Ease.getInstance(showScaleEaseEnum),
                new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        Util.setVisibility(INVISIBLE, piece);
                        Util.setVisibility(VISIBLE, boomButton);
                        boomButton.willShow();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        boomButton.didShow();
                        animatingViewNumber--;
                    }
                }, scaleY, 1);

        if (use3DTransformAnimation) {
            Rotate3DAnimation rotate3DAnimation = AnimationManager.getRotate3DAnimation(xs, ys,
                    delay, duration, boomButton);
            rotate3DAnimation.set(boomButton, startPosition.x, startPosition.y);
            boomButton.setCameraDistance(0);
            boomButton.startAnimation(rotate3DAnimation);
        }
    }

    private void startEachReboomAnimation(final BoomPiece piece,
                                          final BoomButton boomButton,
                                          PointF startPosition,
                                          PointF endPosition,
                                          final int delayFactor,
                                          boolean immediately) {
        animatingViewNumber++;
        float[] xs = new float[frames + 1];
        float[] ys = new float[frames + 1];
        float scaleX = piece.getWidth() * 1.0f / boomButton.contentWidth();
        float scaleY = piece.getHeight() * 1.0f / boomButton.contentHeight();
        long delay = immediately ? 1 : hideDelay * delayFactor;
        long duration = immediately ? 1 : hideDuration;
        AnimationManager.calculateHideXY(
                boomEnum,
                new PointF(background.getLayoutParams().width, background.getLayoutParams().height),
                Ease.getInstance(hideMoveEaseEnum), frames, startPosition, endPosition, xs, ys);
        if (boomButton.isNeededColorAnimation()) {
            if (boomButton.prepareColorTransformAnimation())
                AnimationManager.animate(boomButton, "rippleButtonColor", delay, duration, HideRgbEvaluator.getInstance(), boomButton.buttonColor(), boomButton.pieceColor());
            else
                AnimationManager.animate(boomButton, "nonRippleButtonColor", delay, duration, HideRgbEvaluator.getInstance(), boomButton.buttonColor(), boomButton.pieceColor());
        }
        AnimationManager.animate(boomButton, "x", delay, duration, new LinearInterpolator(), xs);
        AnimationManager.animate(boomButton, "y", delay, duration, new LinearInterpolator(), ys);
        AnimationManager.rotate(boomButton, delay, duration, Ease.getInstance(hideRotateEaseEnum), 0, -rotateDegree);
        AnimationManager.animate("alpha", delay, duration, new float[]{1, 0}, Ease.getInstance(EaseEnum.Linear), boomButton.goneViews());
        AnimationManager.animate(boomButton, "scaleX", delay, duration, Ease.getInstance(hideScaleEaseEnum), 1, scaleX);
        AnimationManager.animate(boomButton, "scaleY", delay, duration, Ease.getInstance(hideScaleEaseEnum),
                new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        boomButton.willHide();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Util.setVisibility(VISIBLE, piece);
                        Util.setVisibility(INVISIBLE, boomButton);
                        boomButton.didHide();
                        animatingViewNumber--;
                        finishReboomAnimations();
                    }
                }, 1, scaleY);
        if (use3DTransformAnimation) {
            Rotate3DAnimation rotate3DAnimation = AnimationManager.getRotate3DAnimation(xs, ys,
                    delay, duration, boomButton);
            rotate3DAnimation.set(boomButton, endPosition.x, endPosition.y);
            boomButton.setCameraDistance(0);
            boomButton.startAnimation(rotate3DAnimation);
        }
    }

    //endregion

    private void ____________________________Support_Methods() {
    }
    //region Support Methods

    private void createBackground() {
        if (background == null) {
            background = new BackgroundView(context, this);
        }
    }

    protected ViewGroup getParentView() {
        if (boomInWholeScreen) {
            Activity activity = Util.scanForActivity(context);
            if (activity == null) {
                return (ViewGroup) getParent();
            } else {
                return (ViewGroup) activity.getWindow().getDecorView();
            }
        } else {
            return (ViewGroup) getParent();
        }
    }

    private void clearBackground() {
        Util.setVisibility(GONE, background);
        if (!cacheOptimization || inList || inFragment) {
            background.removeAllViews();
            ((ViewGroup) background.getParent()).removeView(background);
            background = null;
        }
    }

    private void createButtons() {
        if (!needToCreateButtons) return;
        needToCreateButtons = false;
        boomButtons = new ArrayList<>(pieces.size());
        int buttonNumber = pieces.size();
        for (int i = 0; i < boomButtonBuilders.size(); i++)
            boomButtons.add(boomButtonBuilders.get(i).innerListener(this).index(i).build(context));
        switch (buttonEnum) {
            case SimpleCircle:
                simpleCircleButtonRadius = ((SimpleCircleButton.Builder)
                        boomButtonBuilders.get(0)).getButtonRadius();
                break;
            case TextInsideCircle:
                textInsideCircleButtonRadius = ((TextInsideCircleButton.Builder)
                        boomButtonBuilders.get(0)).getButtonRadius();
                break;
            case TextOutsideCircle:
                textOutsideCircleButtonWidth = ((TextOutsideCircleButton.Builder)
                        boomButtonBuilders.get(0)).getButtonContentWidth();
                textOutsideCircleButtonHeight = ((TextOutsideCircleButton.Builder)
                        boomButtonBuilders.get(0)).getButtonContentHeight();
                break;
            case Ham:
                hamButtonWidth = ((HamButton.Builder)
                        boomButtonBuilders.get(0)).getButtonWidth();
                hamButtonHeight = ((HamButton.Builder)
                        boomButtonBuilders.get(0)).getButtonHeight();
                break;
        }
    }

    protected void onBackgroundClicked() {
        if (isAnimating()) return;
        if (onBoomListener != null) onBoomListener.onBackgroundClick();
        if (cancelable) reboom();
    }

    private void calculateStartPositions(boolean force) {
        if (!(force || needToCalculateStartPositions || inList || inFragment)) return;
        if (!force) needToCalculateStartPositions = false;
        startPositions = new ArrayList<>(pieceNumber());
        ViewGroup rootView = getParentView();
        int[] rootViewLocation = new int[2];
        rootView.getLocationOnScreen(rootViewLocation);
        for (int i = 0; i < pieces.size(); i++) {
            PointF pieceCenterInRootView = new PointF();
            int[] buttonLocation = new int[2];
            button.getLocationOnScreen(buttonLocation);
            pieceCenterInRootView.x = buttonLocation[0] + piecePositions.get(i).left
                    - rootViewLocation[0] + (pieces.get(i).getLayoutParams().width >> 1);
            pieceCenterInRootView.y = buttonLocation[1] + piecePositions.get(i).top
                    - rootViewLocation[1] + (pieces.get(i).getLayoutParams().height >> 1);
            startPositions.add(pieceCenterInRootView);
        }
    }

    private void calculateEndPositions() {
        Point parentSize = new Point(background.getLayoutParams().width,
                background.getLayoutParams().height);
        switch (buttonEnum) {
            case SimpleCircle:
                endPositions = ButtonPlaceManager.getPositions(parentSize,
                        simpleCircleButtonRadius, boomButtonBuilders.size(), this);
                break;
            case TextInsideCircle:
                endPositions = ButtonPlaceManager.getPositions(parentSize,
                        textInsideCircleButtonRadius, boomButtonBuilders.size(), this);
                break;
            case TextOutsideCircle:
                endPositions = ButtonPlaceManager.getPositions(parentSize,
                        textOutsideCircleButtonWidth, textOutsideCircleButtonHeight,
                        boomButtonBuilders.size(), this);
                break;
            case Ham:
                endPositions = ButtonPlaceManager.getPositions(parentSize,
                        hamButtonWidth, hamButtonHeight,
                        boomButtonBuilders.size(), this);
                break;
        }
        for (int i = 0; i < boomButtons.size(); i++)
            endPositions.get(i).offset(-boomButtons.get(i).centerPoint.x,
                    -boomButtons.get(i).centerPoint.y);
    }

    private void putBoomButtonInBackground(BoomButton boomButton, PointF position) {
        createBackground();
        boomButton.place(
                (int) position.x,
                (int) position.y,
                boomButton.trueWidth(),
                boomButton.trueHeight());
        boomButton.setVisibility(INVISIBLE);
        background.addView(boomButton);
    }

    private void clearViews() {
        if (!cacheOptimization || inList || inFragment) {
            clearButtons();
            clearBackground();
        }
    }

    private void clearButtons() {
        needToCreateButtons = true;
        if (background != null)
            for (BoomButton boomButton : boomButtons) background.removeView(boomButton);
        boomButtons.clear();
    }

    private float buttonMaxHeight() {
        switch (buttonEnum) {
            case SimpleCircle:
                return simpleCircleButtonRadius * 2;
            case TextInsideCircle:
                return textInsideCircleButtonRadius * 2;
            case TextOutsideCircle:
                return textOutsideCircleButtonHeight;
            case Ham:
                return hamButtonHeight;
        }
        return 0;
    }

    private void preventDragOutside() {
        boolean needToAdjustXY = false;
        float newX = getX();
        float newY = getY();
        ViewGroup parentView = (ViewGroup) getParent();

        if (newX < edgeInsetsInParentView.left) {
            newX = edgeInsetsInParentView.left;
            needToAdjustXY = true;
        }

        if (newY < edgeInsetsInParentView.top) {
            newY = edgeInsetsInParentView.top;
            needToAdjustXY = true;
        }

        if (newX > parentView.getWidth() - edgeInsetsInParentView.right - getWidth()) {
            newX = parentView.getWidth() - edgeInsetsInParentView.right - getWidth();
            needToAdjustXY = true;
        }

        if (newY > parentView.getHeight() - edgeInsetsInParentView.bottom - getHeight()) {
            newY = parentView.getHeight() - edgeInsetsInParentView.bottom - getHeight();
            needToAdjustXY = true;
        }

        if (needToAdjustXY) {
            AnimationManager.animate(this, "x", 0, 300, Ease.getInstance(EaseEnum.EaseOutBack), getX(), newX);
            AnimationManager.animate(this, "y", 0, 300, Ease.getInstance(EaseEnum.EaseOutBack), getY(), newY);
        }
    }

    private void toLayout() {
        if (needToLayout) return;
        needToLayout = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (!isInLayout()) requestLayout();
        } else {
            requestLayout();
        }
    }

    private void delayToDoLayoutJobs() {
        if (layoutJobsRunnable == null) {
            layoutJobsRunnable = this::doLayoutJobs;
        }
        post(layoutJobsRunnable);
    }

    private int pieceNumber() {
        if (piecePlaceEnum.equals(PiecePlaceEnum.Unknown)) return 0;
        else if (piecePlaceEnum.equals(Share)) return boomButtonBuilders.size();
        else if (piecePlaceEnum.equals(PiecePlaceEnum.Custom))
            return customPiecePlacePositions.size();
        else return piecePlaceEnum.pieceNumber();
    }

    @Override
    public void onButtonClick(int index, BoomButton boomButton) {
        if (isAnimating()) return;
        if (onBoomListener != null) onBoomListener.onClicked(index, boomButton);
        if (autoHide) reboom();
    }

    private void placeShareLinesView() {
        if (piecePlaceEnum == Share) {
            if (shareLinesView != null) button.removeView(shareLinesView);
            shareLinesView = new ShareLinesView(context);
            shareLinesView.setLine1Color(shareLine1Color);
            shareLinesView.setLine2Color(shareLine2Color);
            shareLinesView.setLineWidth(shareLineWidth);
            button.addView(shareLinesView);
            shareLinesView.place(0, 0, button.getWidth(), button.getHeight());
        } else {
            if (shareLinesView != null) button.removeView(shareLinesView);
        }
    }

    private void setShareLinesViewData() {
        if (piecePlaceEnum == Share) shareLinesView.setData(piecePositions, this);
    }

    private boolean uninitializedBoomButtons() {
        return buttonEnum.equals(ButtonEnum.Unknown)
                || piecePlaceEnum.equals(PiecePlaceEnum.Unknown)
                || buttonPlaceEnum.equals(ButtonPlaceEnum.Unknown);
    }

    private boolean isBatterySaveModeTurnOn() {
        PowerManager powerManager = (PowerManager) getContext().getSystemService(Context.POWER_SERVICE);
        assert powerManager != null;
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && powerManager.isPowerSaveMode();
    }

    private void checkAutoBoom() {
        if (autoBoomImmediately) boomImmediately();
        else if (autoBoom) boom();
        autoBoomImmediately = autoBoom = false;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isOrientationAdaptable) initOrientationListener();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (orientationEventListener != null) orientationEventListener.disable();
    }

    private void initOrientationListener() {
        if (orientationEventListener == null) {
            orientationEventListener = new OrientationEventListener(context) {
                @Override
                public void onOrientationChanged(int orientation) {
                    if (orientation != lastOrientation && lastOrientation != OrientationEventListener.ORIENTATION_UNKNOWN) {
                        isOrientationChanged = true;
                    }
                    lastOrientation = orientation;
                }
            };
        }
        if (orientationEventListener.canDetectOrientation()) {
            orientationEventListener.enable();
        }
    }

    private void reLayoutAfterOrientationChanged() {
        post(() -> {
            if (background != null) background.reLayout(BoomMenuButton.this);
            calculateStartPositions(true);
            calculateEndPositions();
            switch (boomStateEnum) {
                case DidReboom:
                    break;
                case DidBoom:
                    placeButtons();
                    break;
                case WillBoom:
                case WillReboom:
                    stopAllAnimations(boomStateEnum == BoomStateEnum.WillBoom);
                    placeButtons();
                    break;
            }
        });
    }

    private void placeButtons() {
        for (int i = 0; i < boomButtons.size(); i++) {
            BoomButton boomButton = boomButtons.get(i);
            PointF pointF = endPositions.get(i);
            boomButton.setX(pointF.x);
            boomButton.setY(pointF.y);
        }
    }

    private void stopAllAnimations(boolean isBoomAnimation) {
        if (background != null) background.clearAnimation();
        for (BoomButton boomButton : boomButtons) {
            boomButton.clearAnimation();
        }
    }

    //endregion

    private void ____________________________Builders_and_Buttons() {
    }

    //region Builders
    public void addBuilder(BoomButtonBuilder builder) {
        boomButtonBuilders.add(builder);
        toLayout();
    }

    public void setBuilder(int index, BoomButtonBuilder builder) {
        boomButtonBuilders.set(index, builder);
        toLayout();
    }

    public void setBuilders(ArrayList<BoomButtonBuilder> builders) {
        boomButtonBuilders = builders;
        toLayout();
    }

    public BoomButtonBuilder getBuilder(int index) {
        if (boomButtonBuilders == null || index < 0 || index >= boomButtonBuilders.size())
            return null;
        else return boomButtonBuilders.get(index);
    }

    public void removeBuilder(BoomButtonBuilder builder) {
        boomButtonBuilders.remove(builder);
        toLayout();
    }

    public void removeBuilder(int index) {
        boomButtonBuilders.remove(index);
        toLayout();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        button.setEnabled(enabled);
        setButtonBackground();
    }

    public void setEnable(int index, boolean enable) {
        if (index < 0) return;
        if (boomButtonBuilders != null && index < boomButtonBuilders.size()) {
            boomButtonBuilders.get(index).setUnable(!enable);
        }
        if (boomButtons != null && index < boomButtons.size()) {
            boomButtons.get(index).setEnabled(enable);
        }
    }

    public void clearBuilders() {
        boomButtonBuilders.clear();
    }

    public ArrayList<BoomButtonBuilder> getBuilders() {
        return boomButtonBuilders;
    }

    public BoomButton getBoomButton(int index) {
        if (boomButtons != null && 0 <= index && index < boomButtons.size())
            return boomButtons.get(index);
        return null;
    }

    public ArrayList<BoomButton> getBoomButtons() {
        return boomButtons;
    }

    //endregion

    private void ____________________________Fade_Views() {
    }

    //region Fade Views

    private void startBoomAnimationForFadeViews(boolean immediately) {
        long duration = immediately ? 1 : showDuration + showDelay * (pieces.size() - 1);
        AnimationManager.animate("alpha", 0, duration, new float[]{1, 0},
                input -> Math.min(input * 2, 1), getFadeViews());
    }

    private void startReboomAnimationForFadeViews(boolean immediately) {
        long duration = immediately ? 1 : hideDuration + hideDelay * (pieces.size() - 1);
        AnimationManager.animate("alpha", 0, duration, new float[]{0, 1},
                input -> {
                    if (input <= 0.5) return 0;
                    else return Math.min((input - 0.5f) * 2, 1);
                }, getFadeViews());
    }

    private ArrayList<View> getFadeViews() {
        ArrayList<View> fadeViews = new ArrayList<>();
        for (int i = 0; i < getChildCount(); i++) {
            View subView = getChildAt(i);
            if (!(subView == shadow
                    || subView == button
                    || subView == shareLinesView)) fadeViews.add(subView);
        }
        return fadeViews;
    }

    //endregion

    private void ____________________________Getters_and_Setters() {
    }

    //region Getter and Setter


    public boolean isCacheOptimization() {
        return cacheOptimization;
    }

    public void setCacheOptimization(boolean cacheOptimization) {
        this.cacheOptimization = cacheOptimization;
    }

    public boolean isBoomInWholeScreen() {
        return boomInWholeScreen;
    }

    public void setBoomInWholeScreen(boolean boomInWholeScreen) {
        this.boomInWholeScreen = boomInWholeScreen;
    }

    public boolean isInList() {
        return inList;
    }

    public void setInList(boolean inList) {
        this.inList = inList;
    }

    public boolean isInFragment() {
        return inFragment;
    }

    public void setInFragment(boolean inFragment) {
        this.inFragment = inFragment;
    }

    public boolean isBackPressListened() {
        return isBackPressListened;
    }

    public void setBackPressListened(boolean backPressListened) {
        isBackPressListened = backPressListened;
    }

    public boolean isShadowEffect() {
        return shadowEffect;
    }

    public void setShadowEffect(boolean shadowEffect) {
        if (this.shadowEffect == shadowEffect) return;
        this.shadowEffect = shadowEffect;
        initShadow();
    }

    public int getShadowOffsetX() {
        return shadowOffsetX;
    }

    public void setShadowOffsetX(int shadowOffsetX) {
        if (this.shadowOffsetX == shadowOffsetX) return;
        this.shadowOffsetX = shadowOffsetX;
        initShadow();
    }

    public int getShadowOffsetY() {
        return shadowOffsetY;
    }

    public void setShadowOffsetY(int shadowOffsetY) {
        if (this.shadowOffsetY == shadowOffsetY) return;
        this.shadowOffsetY = shadowOffsetY;
        initShadow();
    }

    public int getShadowRadius() {
        return shadowRadius;
    }

    public void setShadowRadius(int shadowRadius) {
        if (this.shadowRadius == shadowRadius) return;
        this.shadowRadius = shadowRadius;
        initShadow();
    }

    public int getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(int shadowColor) {
        if (this.shadowColor == shadowColor) return;
        this.shadowColor = shadowColor;
        initShadow();
    }

    public int getButtonRadius() {
        return buttonRadius;
    }

    public void setButtonRadius(int buttonRadius) {
        if (this.buttonRadius == buttonRadius) return;
        this.buttonRadius = buttonRadius;
        initButton();
        toLayout();
    }

    public ButtonEnum getButtonEnum() {
        return buttonEnum;
    }

    public void setButtonEnum(ButtonEnum buttonEnum) {
        if (this.buttonEnum.equals(buttonEnum)) return;
        this.buttonEnum = buttonEnum;
        clearPieces();
        clearBuilders();
        clearButtons();
        toLayout();
    }

    public boolean isBackgroundEffect() {
        return backgroundEffect;
    }

    public void setBackgroundEffect(boolean backgroundEffect) {
        if (this.backgroundEffect == backgroundEffect) return;
        this.backgroundEffect = backgroundEffect;
        setButtonBackground();
        toLayout();
    }

    public boolean isRippleEffect() {
        return rippleEffect;
    }

    public void setRippleEffect(boolean rippleEffect) {
        if (this.rippleEffect == rippleEffect) return;
        this.rippleEffect = rippleEffect;
        setButtonBackground();
        toLayout();
    }

    public int getNormalColor() {
        return normalColor;
    }

    public void setNormalColor(int normalColor) {
        if (this.normalColor == normalColor) return;
        this.normalColor = normalColor;
        setButtonBackground();
        toLayout();
    }

    public int getHighlightedColor() {
        return highlightedColor;
    }

    public void setHighlightedColor(int highlightedColor) {
        if (this.highlightedColor == highlightedColor) return;
        this.highlightedColor = highlightedColor;
        setButtonBackground();
        toLayout();
    }

    public int getUnableColor() {
        return unableColor;
    }

    public void setUnableColor(int unableColor) {
        if (this.unableColor == unableColor) return;
        this.unableColor = unableColor;
        setButtonBackground();
        toLayout();
    }

    public boolean isDraggable() {
        return draggable;
    }

    public void setDraggable(boolean draggable) {
        if (this.draggable == draggable) return;
        this.draggable = draggable;
        initDraggableTouchListener();
    }

    public Rect getEdgeInsetsInParentView() {
        return edgeInsetsInParentView;
    }

    public void setEdgeInsetsInParentView(Rect edgeInsetsInParentView) {
        if (this.edgeInsetsInParentView.equals(edgeInsetsInParentView)) return;
        this.edgeInsetsInParentView = edgeInsetsInParentView;
        preventDragOutside();
    }

    public float getDotRadius() {
        return dotRadius;
    }

    public void setDotRadius(float dotRadius) {
        if (this.dotRadius == dotRadius) return;
        this.dotRadius = dotRadius;
        toLayout();
    }

    public float getHamWidth() {
        return hamWidth;
    }

    public void setHamWidth(float hamWidth) {
        if (this.hamWidth == hamWidth) return;
        this.hamWidth = hamWidth;
        toLayout();
    }

    public float getHamHeight() {
        return hamHeight;
    }

    public void setHamHeight(int hamHeight) {
        if (this.hamHeight == hamHeight) return;
        this.hamHeight = hamHeight;
        toLayout();
    }

    public float getPieceCornerRadius() {
        return pieceCornerRadius;
    }

    public void setPieceCornerRadius(float pieceCornerRadius) {
        if (this.pieceCornerRadius == pieceCornerRadius) return;
        this.pieceCornerRadius = pieceCornerRadius;
        toLayout();
    }

    public float getPieceHorizontalMargin() {
        return pieceHorizontalMargin;
    }

    public void setPieceHorizontalMargin(float pieceHorizontalMargin) {
        if (this.pieceHorizontalMargin == pieceHorizontalMargin) return;
        this.pieceHorizontalMargin = pieceHorizontalMargin;
        toLayout();
    }

    public float getPieceVerticalMargin() {
        return pieceVerticalMargin;
    }

    public void setPieceVerticalMargin(float pieceVerticalMargin) {
        if (this.pieceVerticalMargin == pieceVerticalMargin) return;
        this.pieceVerticalMargin = pieceVerticalMargin;
        toLayout();
    }

    public float getPieceInclinedMargin() {
        return pieceInclinedMargin;
    }

    public void setPieceInclinedMargin(float pieceInclinedMargin) {
        if (this.pieceInclinedMargin == pieceInclinedMargin) return;
        this.pieceInclinedMargin = pieceInclinedMargin;
        toLayout();
    }

    public float getShareLineLength() {
        return shareLineLength;
    }

    public void setShareLineLength(float shareLineLength) {
        if (this.shareLineLength == shareLineLength) return;
        this.shareLineLength = shareLineLength;
        toLayout();
    }

    public int getShareLine1Color() {
        return shareLine1Color;
    }

    public void setShareLine1Color(int shareLine1Color) {
        if (this.shareLine1Color == shareLine1Color) return;
        this.shareLine1Color = shareLine1Color;
        if (shareLinesView != null) {
            shareLinesView.setLine1Color(shareLine1Color);
            shareLinesView.invalidate();
        }
    }

    public int getShareLine2Color() {
        return shareLine2Color;
    }

    public void setShareLine2Color(int shareLine2Color) {
        if (this.shareLine2Color == shareLine2Color) return;
        this.shareLine2Color = shareLine2Color;
        if (shareLinesView != null) {
            shareLinesView.setLine2Color(shareLine2Color);
            shareLinesView.invalidate();
        }
    }

    public float getShareLineWidth() {
        return shareLineWidth;
    }

    public void setShareLineWidth(float shareLineWidth) {
        if (this.shareLineWidth == shareLineWidth) return;
        this.shareLineWidth = shareLineWidth;
        if (shareLinesView != null) {
            shareLinesView.setLineWidth(shareLineWidth);
            shareLinesView.invalidate();
        }
    }

    public PiecePlaceEnum getPiecePlaceEnum() {
        return piecePlaceEnum;
    }

    public void setPiecePlaceEnum(PiecePlaceEnum piecePlaceEnum) {
        this.piecePlaceEnum = piecePlaceEnum;
        clearPieces();
        toLayout();
    }

    public ArrayList<PointF> getCustomPiecePlacePositions() {
        return customPiecePlacePositions;
    }

    public void setCustomPiecePlacePositions(ArrayList<PointF> customPiecePlacePositions) {
        if (this.customPiecePlacePositions.equals(customPiecePlacePositions)) return;
        this.customPiecePlacePositions = customPiecePlacePositions;
        clearPieces();
        toLayout();
    }

    public OnBoomListener getOnBoomListener() {
        return onBoomListener;
    }

    public void setOnBoomListener(OnBoomListener onBoomListener) {
        this.onBoomListener = onBoomListener;
    }

    public int getDimColor() {
        return dimColor;
    }

    public void setDimColor(int dimColor) {
        if (this.dimColor == dimColor) return;
        this.dimColor = dimColor;
        if (boomStateEnum == BoomStateEnum.DidBoom && background != null)
            background.setBackgroundColor(dimColor);
    }

    public void setDelay(long delay) {
        setShowDelay(delay);
        setHideDelay(delay);
    }

    public void setDuration(long duration) {
        setShowDuration(duration);
        setHideDuration(duration);
    }

    public long getShowDelay() {
        return showDelay;
    }

    public void setShowDelay(long showDelay) {
        this.showDelay = showDelay;
        setShareLinesViewData();
    }

    public long getShowDuration() {
        return showDuration;
    }

    public void setShowDuration(long showDuration) {
        if (this.showDuration == showDuration) return;
        this.showDuration = Math.max(1, showDuration);
        setShareLinesViewData();
    }

    public long getHideDelay() {
        return hideDelay;
    }

    public void setHideDelay(long hideDelay) {
        this.hideDelay = hideDelay;
        setShareLinesViewData();
    }

    public long getHideDuration() {
        return hideDuration;
    }

    public void setHideDuration(long hideDuration) {
        if (this.hideDuration == hideDuration) return;
        this.hideDuration = Math.max(1, hideDuration);
        setShareLinesViewData();
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    public boolean isAutoHide() {
        return autoHide;
    }

    public void setAutoHide(boolean autoHide) {
        this.autoHide = autoHide;
    }

    public OrderEnum getOrderEnum() {
        return orderEnum;
    }

    public void setOrderEnum(OrderEnum orderEnum) {
        this.orderEnum = orderEnum;
    }

    public int getFrames() {
        return frames;
    }

    public void setFrames(int frames) {
        this.frames = frames;
    }

    public BoomEnum getBoomEnum() {
        return boomEnum;
    }

    public void setBoomEnum(BoomEnum boomEnum) {
        this.boomEnum = boomEnum;
    }

    public void setShowEaseEnum(EaseEnum showEaseEnum) {
        setShowMoveEaseEnum(showEaseEnum);
        setShowScaleEaseEnum(showEaseEnum);
        setShowRotateEaseEnum(showEaseEnum);
    }

    public EaseEnum getShowMoveEaseEnum() {
        return showMoveEaseEnum;
    }

    public void setShowMoveEaseEnum(EaseEnum showMoveEaseEnum) {
        this.showMoveEaseEnum = showMoveEaseEnum;
    }

    public EaseEnum getShowScaleEaseEnum() {
        return showScaleEaseEnum;
    }

    public void setShowScaleEaseEnum(EaseEnum showScaleEaseEnum) {
        this.showScaleEaseEnum = showScaleEaseEnum;
    }

    public EaseEnum getShowRotateEaseEnum() {
        return showRotateEaseEnum;
    }

    public void setShowRotateEaseEnum(EaseEnum showRotateEaseEnum) {
        this.showRotateEaseEnum = showRotateEaseEnum;
    }

    public void setHideEaseEnum(EaseEnum hideEaseEnum) {
        setHideMoveEaseEnum(hideEaseEnum);
        setHideScaleEaseEnum(hideEaseEnum);
        setHideRotateEaseEnum(hideEaseEnum);
    }

    public EaseEnum getHideMoveEaseEnum() {
        return hideMoveEaseEnum;
    }

    public void setHideMoveEaseEnum(EaseEnum hideMoveEaseEnum) {
        this.hideMoveEaseEnum = hideMoveEaseEnum;
    }

    public EaseEnum getHideScaleEaseEnum() {
        return hideScaleEaseEnum;
    }

    public void setHideScaleEaseEnum(EaseEnum hideScaleEaseEnum) {
        this.hideScaleEaseEnum = hideScaleEaseEnum;
    }

    public EaseEnum getHideRotateEaseEnum() {
        return hideRotateEaseEnum;
    }

    public void setHideRotateEaseEnum(EaseEnum hideRotateEaseEnum) {
        this.hideRotateEaseEnum = hideRotateEaseEnum;
    }

    public int getRotateDegree() {
        return rotateDegree;
    }

    public void setUse3DTransformAnimation(boolean use3DTransformAnimation) {
        this.use3DTransformAnimation = use3DTransformAnimation;
    }

    public boolean isUse3DTransformAnimation() {
        return use3DTransformAnimation;
    }

    public void setAutoBoom(boolean autoBoom) {
        this.autoBoom = autoBoom;
    }

    public boolean isAutoBoom() {
        return autoBoom;
    }

    public void setAutoBoomImmediately(boolean autoBoomImmediately) {
        this.autoBoomImmediately = autoBoomImmediately;
    }

    public boolean isAutoBoomImmediately() {
        return autoBoomImmediately;
    }

    public void setRotateDegree(int rotateDegree) {
        this.rotateDegree = rotateDegree;
    }

    public ButtonPlaceEnum getButtonPlaceEnum() {
        return buttonPlaceEnum;
    }

    public void setButtonPlaceEnum(ButtonPlaceEnum buttonPlaceEnum) {
        this.buttonPlaceEnum = buttonPlaceEnum;
        clearButtons();
        needToCalculateStartPositions = true;
    }

    public ArrayList<PointF> getCustomButtonPlacePositions() {
        return customButtonPlacePositions;
    }

    public void setCustomButtonPlacePositions(ArrayList<PointF> customButtonPlacePositions) {
        this.customButtonPlacePositions = customButtonPlacePositions;
        clearButtons();
        needToCalculateStartPositions = true;
    }

    public ButtonPlaceAlignmentEnum getButtonPlaceAlignmentEnum() {
        return buttonPlaceAlignmentEnum;
    }

    public void setButtonPlaceAlignmentEnum(ButtonPlaceAlignmentEnum buttonPlaceAlignmentEnum) {
        this.buttonPlaceAlignmentEnum = buttonPlaceAlignmentEnum;
    }

    public float getButtonHorizontalMargin() {
        return buttonHorizontalMargin;
    }

    public void setButtonHorizontalMargin(float buttonHorizontalMargin) {
        this.buttonHorizontalMargin = buttonHorizontalMargin;
    }

    public float getButtonVerticalMargin() {
        return buttonVerticalMargin;
    }

    public void setButtonVerticalMargin(float buttonVerticalMargin) {
        this.buttonVerticalMargin = buttonVerticalMargin;
    }

    public float getButtonInclinedMargin() {
        return buttonInclinedMargin;
    }

    public void setButtonInclinedMargin(float buttonInclinedMargin) {
        this.buttonInclinedMargin = buttonInclinedMargin;
    }

    public float getButtonTopMargin() {
        return buttonTopMargin;
    }

    public void setButtonTopMargin(float buttonTopMargin) {
        this.buttonTopMargin = buttonTopMargin;
    }

    public float getButtonBottomMargin() {
        return buttonBottomMargin;
    }

    public void setButtonBottomMargin(float buttonBottomMargin) {
        this.buttonBottomMargin = buttonBottomMargin;
    }

    public float getButtonLeftMargin() {
        return buttonLeftMargin;
    }

    public void setButtonLeftMargin(float buttonLeftMargin) {
        this.buttonLeftMargin = buttonLeftMargin;
    }

    public float getButtonRightMargin() {
        return buttonRightMargin;
    }

    public void setButtonRightMargin(float buttonRightMargin) {
        this.buttonRightMargin = buttonRightMargin;
    }

    public float getBottomHamButtonTopMargin() {
        return bottomHamButtonTopMargin;
    }

    public void setBottomHamButtonTopMargin(float bottomHamButtonTopMargin) {
        this.bottomHamButtonTopMargin = bottomHamButtonTopMargin;
    }

    public boolean isOrientationAdaptable() {
        return isOrientationAdaptable;
    }

    public void setOrientationAdaptable(boolean orientationAdaptable) {
        isOrientationAdaptable = orientationAdaptable;
        if (isOrientationAdaptable) {
            initOrientationListener();
        }
    }
}
