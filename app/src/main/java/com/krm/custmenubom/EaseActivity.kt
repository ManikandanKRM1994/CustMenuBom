package com.krm.custmenubom

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.krm.custmenubom.BuilderManager.simpleCircleButtonBuilder
import com.krm.custmenubom.menu.BoomMenuButton

class EaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ease)
        initBmb(R.id.bmb1)
        initBmb(R.id.bmb2)
        initBmb(R.id.bmb3)
        initBmb(R.id.bmb4)
        initBmb(R.id.bmb5)
        initBmb(R.id.bmb6)
        initBmb(R.id.bmb7)
        initBmb(R.id.bmb8)
        initBmb(R.id.bmb9)
    }

    private fun initBmb(res: Int): BoomMenuButton? {
        val bmb = (findViewById<View>(res) as BoomMenuButton)
        for (i in 0 until bmb.piecePlaceEnum
            .pieceNumber()) bmb.addBuilder(simpleCircleButtonBuilder)
        return bmb
    }
}