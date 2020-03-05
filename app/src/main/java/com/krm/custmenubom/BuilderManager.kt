package com.krm.custmenubom

import android.graphics.Color
import android.util.Pair
import com.krm.custmenubom.menu.boomButtons.*
import com.krm.custmenubom.menu.piece.PiecePlaceEnum
import com.krm.custmenubom.menu.Util
import java.util.*

object BuilderManager {
    private val imageResources = intArrayOf(
        R.drawable.bat,
        R.drawable.bear,
        R.drawable.bee,
        R.drawable.butterfly,
        R.drawable.cat,
        R.drawable.deer,
        R.drawable.dolphin,
        R.drawable.eagle,
        R.drawable.horse,
        R.drawable.elephant,
        R.drawable.owl,
        R.drawable.peacock,
        R.drawable.pig,
        R.drawable.rat,
        R.drawable.snake,
        R.drawable.squirrel
    )
    private var imageResourceIndex = 0

    @JvmStatic
    val imageResource: Int
        get() {
            if (imageResourceIndex >= imageResources.size) imageResourceIndex =
                0
            return imageResources[imageResourceIndex++]
        }

    @JvmStatic
    val simpleCircleButtonBuilder: SimpleCircleButton.Builder
        get() = SimpleCircleButton.Builder()
            .normalImageRes(imageResource)!!

    @JvmStatic
    val squareSimpleCircleButtonBuilder: SimpleCircleButton.Builder
        get() = SimpleCircleButton.Builder()
            .isRound(false)
            .shadowCornerRadius(Util.dp2px(20f))
            .buttonCornerRadius(Util.dp2px(20f))
            .normalImageRes(imageResource)!!

    @JvmStatic
    val textInsideCircleButtonBuilder: TextInsideCircleButton.Builder
        get() = TextInsideCircleButton.Builder()
            .normalImageRes(imageResource)
            .normalTextRes(R.string.text_inside_circle_button_text_normal)!!

    @JvmStatic
    val squareTextInsideCircleButtonBuilder: TextInsideCircleButton.Builder
        get() = TextInsideCircleButton.Builder()
            .isRound(false)
            .shadowCornerRadius(Util.dp2px(10f))
            .buttonCornerRadius(Util.dp2px(10f))
            .normalImageRes(imageResource)
            .normalTextRes(R.string.text_inside_circle_button_text_normal)!!

    @JvmStatic
    val textInsideCircleButtonBuilderWithDifferentPieceColor: TextInsideCircleButton.Builder
        get() = TextInsideCircleButton.Builder()
            .normalImageRes(imageResource)
            .normalTextRes(R.string.text_inside_circle_button_text_normal)
            .pieceColor(Color.WHITE)!!

    @JvmStatic
    val textOutsideCircleButtonBuilder: TextOutsideCircleButton.Builder
        get() = TextOutsideCircleButton.Builder()
            .normalImageRes(imageResource)
            .normalTextRes(R.string.text_outside_circle_button_text_normal)!!

    @JvmStatic
    val textOutsideCircleButtonBuilderWithDifferentPieceColor: TextOutsideCircleButton.Builder
        get() = TextOutsideCircleButton.Builder()
            .normalImageRes(imageResource)
            .normalTextRes(R.string.text_outside_circle_button_text_normal)
            .pieceColor(Color.WHITE)!!

    @JvmStatic
    val hamButtonBuilder: HamButton.Builder
        get() = HamButton.Builder()
            .normalImageRes(imageResource)
            .normalTextRes(R.string.text_ham_button_text_normal)
            .subNormalTextRes(R.string.text_ham_button_sub_text_normal)

    fun getHamButtonBuilder(text: String?, subText: String?): HamButton.Builder {
        return HamButton.Builder()
            .normalImageRes(imageResource)
            .normalText(text)
            .subNormalText(subText)
    }

    @JvmStatic
    val pieceCornerRadiusHamButtonBuilder: HamButton.Builder
        get() = HamButton.Builder()
            .normalImageRes(imageResource)
            .normalTextRes(R.string.text_ham_button_text_normal)
            .subNormalTextRes(R.string.text_ham_button_sub_text_normal)

    @JvmStatic
    val hamButtonBuilderWithDifferentPieceColor: HamButton.Builder
        get() = HamButton.Builder()
            .normalImageRes(imageResource)
            .normalTextRes(R.string.text_ham_button_text_normal)
            .subNormalTextRes(R.string.text_ham_button_sub_text_normal)
            .pieceColor(Color.WHITE)!!

    @JvmStatic
    fun getCircleButtonData(piecesAndButtons: ArrayList<Pair<*, *>?>): List<String> {
        val data: MutableList<String> =
            ArrayList()
        for (p in 0 until PiecePlaceEnum.values().size - 1) {
            for (b in 0 until ButtonPlaceEnum.values().size - 1) {
                val piecePlaceEnum = PiecePlaceEnum.getEnum(p)
                val buttonPlaceEnum = ButtonPlaceEnum.getEnum(b)
                if (piecePlaceEnum.pieceNumber() == buttonPlaceEnum.buttonNumber() || buttonPlaceEnum == ButtonPlaceEnum.Horizontal || buttonPlaceEnum == ButtonPlaceEnum.Vertical
                ) {
                    piecesAndButtons.add(
                        Pair(
                            piecePlaceEnum,
                            buttonPlaceEnum
                        )
                    )
                    data.add("$piecePlaceEnum $buttonPlaceEnum")
                    if (piecePlaceEnum == PiecePlaceEnum.HAM_1 || piecePlaceEnum == PiecePlaceEnum.HAM_2 || piecePlaceEnum == PiecePlaceEnum.HAM_3 || piecePlaceEnum == PiecePlaceEnum.HAM_4 || piecePlaceEnum == PiecePlaceEnum.HAM_5 || piecePlaceEnum == PiecePlaceEnum.HAM_6 || piecePlaceEnum == PiecePlaceEnum.Share || piecePlaceEnum == PiecePlaceEnum.Custom || buttonPlaceEnum == ButtonPlaceEnum.HAM_1 || buttonPlaceEnum == ButtonPlaceEnum.HAM_2 || buttonPlaceEnum == ButtonPlaceEnum.HAM_3 || buttonPlaceEnum == ButtonPlaceEnum.HAM_4 || buttonPlaceEnum == ButtonPlaceEnum.HAM_5 || buttonPlaceEnum == ButtonPlaceEnum.HAM_6 || buttonPlaceEnum == ButtonPlaceEnum.Custom
                    ) {
                        piecesAndButtons.removeAt(piecesAndButtons.size - 1)
                        data.removeAt(data.size - 1)
                    }
                }
            }
        }
        return data
    }

    @JvmStatic
    fun getHamButtonData(piecesAndButtons: ArrayList<Pair<*, *>?>): List<String> {
        val data: MutableList<String> =
            ArrayList()
        for (p in 0 until PiecePlaceEnum.values().size - 1) {
            for (b in 0 until ButtonPlaceEnum.values().size - 1) {
                val piecePlaceEnum = PiecePlaceEnum.getEnum(p)
                val buttonPlaceEnum = ButtonPlaceEnum.getEnum(b)
                if (piecePlaceEnum.pieceNumber() == buttonPlaceEnum.buttonNumber() || buttonPlaceEnum == ButtonPlaceEnum.Horizontal || buttonPlaceEnum == ButtonPlaceEnum.Vertical
                ) {
                    piecesAndButtons.add(
                        Pair(
                            piecePlaceEnum,
                            buttonPlaceEnum
                        )
                    )
                    data.add("$piecePlaceEnum $buttonPlaceEnum")
                    if (piecePlaceEnum.value < PiecePlaceEnum.HAM_1.value || piecePlaceEnum == PiecePlaceEnum.Share || piecePlaceEnum == PiecePlaceEnum.Custom || buttonPlaceEnum.value < ButtonPlaceEnum.HAM_1.value
                    ) {
                        piecesAndButtons.removeAt(piecesAndButtons.size - 1)
                        data.removeAt(data.size - 1)
                    }
                }
            }
        }
        return data
    }

}