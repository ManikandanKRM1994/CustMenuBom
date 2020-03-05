package com.krm.custmenubom

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.krm.custmenubom.BuilderManager.pieceCornerRadiusHamButtonBuilder
import com.krm.custmenubom.BuilderManager.squareSimpleCircleButtonBuilder
import com.krm.custmenubom.BuilderManager.squareTextInsideCircleButtonBuilder
import com.krm.custmenubom.BuilderManager.textOutsideCircleButtonBuilderWithDifferentPieceColor
import com.krm.custmenubom.menu.BoomMenuButton

class SquareAndPieceCornerRadiusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_square_and_piece_corner_radius)
        val bmb1 = findViewById<View>(R.id.bmb1) as BoomMenuButton
        for (i in 0 until bmb1.piecePlaceEnum
            .pieceNumber()) bmb1.addBuilder(squareSimpleCircleButtonBuilder)
        val bmb2 = findViewById<View>(R.id.bmb2) as BoomMenuButton
        for (i in 0 until bmb2.piecePlaceEnum
            .pieceNumber()) bmb2.addBuilder(squareTextInsideCircleButtonBuilder)
        val bmb3 = findViewById<View>(R.id.bmb3) as BoomMenuButton
        for (i in 0 until bmb3.piecePlaceEnum
            .pieceNumber()) bmb3.addBuilder(textOutsideCircleButtonBuilderWithDifferentPieceColor)
        val bmb4 = findViewById<View>(R.id.bmb4) as BoomMenuButton
        for (i in 0 until bmb4.piecePlaceEnum
            .pieceNumber()) bmb4.addBuilder(pieceCornerRadiusHamButtonBuilder)
    }
}