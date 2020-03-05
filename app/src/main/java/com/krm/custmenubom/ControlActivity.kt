package com.krm.custmenubom

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.krm.custmenubom.BuilderManager.simpleCircleButtonBuilder
import com.krm.custmenubom.menu.BoomMenuButton

class ControlActivity : AppCompatActivity(), View.OnClickListener {
    private var bmb: BoomMenuButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)
        bmb = findViewById<View>(R.id.bmb) as BoomMenuButton
        assert(bmb != null)
        for (i in 0 until bmb!!.piecePlaceEnum
            .pieceNumber()) bmb!!.addBuilder(simpleCircleButtonBuilder)
        findViewById<View>(R.id.boom).setOnClickListener(this)
        findViewById<View>(R.id.reboom).setOnClickListener(this)
        findViewById<View>(R.id.boom_immediately).setOnClickListener(this)
        findViewById<View>(R.id.reboom_immediately).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.boom -> bmb!!.boom()
            R.id.reboom -> bmb!!.reboom()
            R.id.boom_immediately -> bmb!!.boomImmediately()
            R.id.reboom_immediately -> bmb!!.reboomImmediately()
        }
    }
}