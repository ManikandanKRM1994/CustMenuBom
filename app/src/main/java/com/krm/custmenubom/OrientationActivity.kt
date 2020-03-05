package com.krm.custmenubom

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.krm.custmenubom.BuilderManager.simpleCircleButtonBuilder
import com.krm.custmenubom.menu.boomButtons.ButtonPlaceEnum
import com.krm.custmenubom.menu.BoomMenuButton
import com.krm.custmenubom.menu.ButtonEnum
import com.krm.custmenubom.menu.piece.PiecePlaceEnum

class OrientationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orientation)
        val bmb = (findViewById<View>(R.id.bmb) as BoomMenuButton)
        bmb.buttonEnum = ButtonEnum.SimpleCircle
        bmb.piecePlaceEnum = PiecePlaceEnum.DOT_9_1
        bmb.buttonPlaceEnum = ButtonPlaceEnum.SC_9_1
        for (i in 0 until bmb.piecePlaceEnum
            .pieceNumber()) bmb.addBuilder(simpleCircleButtonBuilder)

        // android:configChanges="keyboardHidden|orientation|screenSize" in AndroidManifest.xml
        bmb.isOrientationAdaptable = true
    }
}