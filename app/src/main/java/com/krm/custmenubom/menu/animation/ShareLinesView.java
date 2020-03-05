package com.krm.custmenubom.menu.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.View;
import android.widget.FrameLayout;

import com.krm.custmenubom.menu.BoomMenuButton;
import com.krm.custmenubom.menu.Util;

import java.util.ArrayList;

public class ShareLinesView extends View {

    private ArrayList<PointF> piecePositions;

    private int line2Color = Color.WHITE;
    private float lineWidth = 3;

    private Paint linePaint;

    public ShareLinesView(Context context) {
        super(context);
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
    }

    public void setData(ArrayList<RectF> piecePositions, BoomMenuButton bmb) {
        float xOffset = bmb.getDotRadius() - lineWidth / 4f;
        float yOffset = (float) (bmb.getDotRadius() - lineWidth * Math.sqrt(3) / 4f) + Util.dp2px(0.25f);

        this.piecePositions = new ArrayList<>();
        for (RectF piecePosition : piecePositions) {
            boolean existed = false;
            for (PointF p : this.piecePositions) {
                if (p.equals(piecePosition.left, piecePosition.top)) {
                    existed = true;
                    break;
                }
            }
            if (!existed)
                this.piecePositions.add(new PointF(piecePosition.left, piecePosition.top));
        }
        for (PointF piecePosition : this.piecePositions) piecePosition.offset(xOffset, yOffset);

        int[] mPieceNumbers = new int[3];
        int pieceNumber = piecePositions.size();
        for (int i = 0; i < pieceNumber; i++) mPieceNumbers[i % 3]++;

    }

    public void setLine1Color(int line1Color) {
        linePaint.setColor(line1Color);
    }

    public void setLine2Color(int line2Color) {
        this.line2Color = line2Color;
    }

    public void setLineWidth(float lineWidth) {
        this.lineWidth = lineWidth;
        linePaint.setStrokeWidth(lineWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float processForLine1 = 1;
        canvas.drawLine(
                piecePositions.get(1).x,
                piecePositions.get(1).y,
                (piecePositions.get(0).x - piecePositions.get(1).x) * processForLine1 + piecePositions.get(1).x,
                (piecePositions.get(0).y - piecePositions.get(1).y) * processForLine1 + piecePositions.get(1).y, linePaint);
        linePaint.setColor(line2Color);
        float processForLine2 = 1;
        canvas.drawLine(
                piecePositions.get(2).x,
                piecePositions.get(2).y,
                (piecePositions.get(1).x - piecePositions.get(2).x) * processForLine2 + piecePositions.get(2).x,
                (piecePositions.get(1).y - piecePositions.get(2).y) * processForLine2 + piecePositions.get(2).y, linePaint);
        super.onDraw(canvas);
    }

    public void place(int left, int top, int width, int height) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        if (layoutParams != null) {
            layoutParams.leftMargin = left;
            layoutParams.topMargin = top;
            layoutParams.width = width;
            layoutParams.height = height;
            setLayoutParams(layoutParams);
        }
    }
}
