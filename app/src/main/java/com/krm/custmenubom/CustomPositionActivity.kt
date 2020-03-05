package com.krm.custmenubom

import android.graphics.PointF
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.krm.custmenubom.BuilderManager.hamButtonBuilder
import com.krm.custmenubom.BuilderManager.simpleCircleButtonBuilder
import com.krm.custmenubom.BuilderManager.textInsideCircleButtonBuilder
import com.krm.custmenubom.BuilderManager.textOutsideCircleButtonBuilderWithDifferentPieceColor
import com.krm.custmenubom.menu.BoomMenuButton
import com.krm.custmenubom.menu.Util

class CustomPositionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_position)
        initializeBmb1()
        initializeBmb2()
        initializeBmb3()
        initializeBmb4()
    }

    private fun initializeBmb1() {
        val bmb = findViewById<View>(R.id.bmb1) as BoomMenuButton
        for (i in 0 until bmb.buttonPlaceEnum
            .buttonNumber()) bmb.addBuilder(hamButtonBuilder)
        val w_0_5 = bmb.hamWidth / 2
        val h_0_5 = bmb.hamHeight / 2
        val hm_0_5 = bmb.pieceHorizontalMargin / 2
        val vm_0_5 = bmb.pieceVerticalMargin / 2
        bmb.customPiecePlacePositions.add(PointF(-w_0_5 - hm_0_5, -h_0_5 - vm_0_5))
        bmb.customPiecePlacePositions.add(PointF(+w_0_5 + hm_0_5, -h_0_5 - vm_0_5))
        bmb.customPiecePlacePositions.add(PointF(-w_0_5 - hm_0_5, +h_0_5 + vm_0_5))
        bmb.customPiecePlacePositions.add(PointF(+w_0_5 + hm_0_5, +h_0_5 + vm_0_5))
    }

    private fun initializeBmb2() {
        val bmb = findViewById<View>(R.id.bmb2) as BoomMenuButton
        for (i in 0 until bmb.piecePlaceEnum
            .pieceNumber()) bmb.addBuilder(simpleCircleButtonBuilder)
        bmb.customButtonPlacePositions.add(
            PointF(
                Util.dp2px(-80f).toFloat(),
                Util.dp2px(-80f).toFloat()
            )
        )
        bmb.customButtonPlacePositions.add(PointF(0F, 0F))
        bmb.customButtonPlacePositions.add(
            PointF(
                Util.dp2px(+80f).toFloat(),
                Util.dp2px(+80f).toFloat()
            )
        )
    }

    private fun initializeBmb3() {
        val bmb = findViewById<View>(R.id.bmb3) as BoomMenuButton
        for (i in 0..11) bmb.addBuilder(textOutsideCircleButtonBuilderWithDifferentPieceColor)
        val w = Util.dp2px(80f).toFloat()
        val h = Util.dp2px(96f).toFloat()
        val h_0_5 = h / 2
        val h_1_5 = h * 1.5f
        val hm = bmb.buttonHorizontalMargin
        val vm = bmb.buttonVerticalMargin
        val vm_0_5 = vm / 2
        val vm_1_5 = vm * 1.5f
        bmb.customButtonPlacePositions.add(PointF(-w - hm, -h_1_5 - vm_1_5))
        bmb.customButtonPlacePositions.add(PointF(0F, -h_1_5 - vm_1_5))
        bmb.customButtonPlacePositions.add(PointF(+w + hm, -h_1_5 - vm_1_5))
        bmb.customButtonPlacePositions.add(PointF(-w - hm, -h_0_5 - vm_0_5))
        bmb.customButtonPlacePositions.add(PointF(0F, -h_0_5 - vm_0_5))
        bmb.customButtonPlacePositions.add(PointF(+w + hm, -h_0_5 - vm_0_5))
        bmb.customButtonPlacePositions.add(PointF(-w - hm, +h_0_5 + vm_0_5))
        bmb.customButtonPlacePositions.add(PointF(0F, +h_0_5 + vm_0_5))
        bmb.customButtonPlacePositions.add(PointF(+w + hm, +h_0_5 + vm_0_5))
        bmb.customButtonPlacePositions.add(PointF(-w - hm, +h_1_5 + vm_1_5))
        bmb.customButtonPlacePositions.add(PointF(0F, +h_1_5 + vm_1_5))
        bmb.customButtonPlacePositions.add(PointF(+w + hm, +h_1_5 + vm_1_5))
    }

    private fun initializeBmb4() {
        val bmb = findViewById<View>(R.id.bmb4) as BoomMenuButton
        for (i in 0..2) bmb.addBuilder(textInsideCircleButtonBuilder)
        bmb.customPiecePlacePositions.add(
            PointF(
                Util.dp2px(+6f).toFloat(),
                Util.dp2px(-6f).toFloat()
            )
        )
        bmb.customPiecePlacePositions.add(PointF(0F, 0F))
        bmb.customPiecePlacePositions.add(
            PointF(
                Util.dp2px(-6f).toFloat(),
                Util.dp2px(+6f).toFloat()
            )
        )
        bmb.customButtonPlacePositions.add(
            PointF(
                Util.dp2px(-80f).toFloat(),
                Util.dp2px(-80f).toFloat()
            )
        )
        bmb.customButtonPlacePositions.add(PointF(0F, 0F))
        bmb.customButtonPlacePositions.add(
            PointF(
                Util.dp2px(+80f).toFloat(),
                Util.dp2px(+80f).toFloat()
            )
        )
    }
}