package com.krm.custmenubom

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.krm.custmenubom.BuilderManager.hamButtonBuilderWithDifferentPieceColor
import com.krm.custmenubom.BuilderManager.simpleCircleButtonBuilder
import com.krm.custmenubom.menu.BoomMenuButton

class FadeViewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fade_views)
        val bmb1 = findViewById<View>(R.id.bmb1) as BoomMenuButton
        for (i in 0 until bmb1.piecePlaceEnum
            .pieceNumber()) bmb1.addBuilder(simpleCircleButtonBuilder)
        val bmb2 = findViewById<View>(R.id.bmb2) as BoomMenuButton
        for (i in 0 until bmb2.piecePlaceEnum
            .pieceNumber()) bmb2.addBuilder(hamButtonBuilderWithDifferentPieceColor)
    }
}