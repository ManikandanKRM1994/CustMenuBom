package com.krm.custmenubom

import android.os.Bundle
import android.view.View
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.krm.custmenubom.BuilderManager.simpleCircleButtonBuilder
import com.krm.custmenubom.menu.boomButtons.ButtonPlaceEnum
import com.krm.custmenubom.menu.BoomMenuButton
import com.krm.custmenubom.menu.ButtonEnum
import com.krm.custmenubom.menu.piece.PiecePlaceEnum

class DraggableActivity : AppCompatActivity() {
    private var bmb: BoomMenuButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draggable)
        bmb = findViewById<View>(R.id.bmb) as BoomMenuButton
        assert(bmb != null)
        bmb!!.buttonEnum = ButtonEnum.SimpleCircle
        bmb!!.piecePlaceEnum = PiecePlaceEnum.DOT_9_1
        bmb!!.buttonPlaceEnum = ButtonPlaceEnum.SC_9_1
        for (i in 0 until bmb!!.piecePlaceEnum
            .pieceNumber()) bmb!!.addBuilder(simpleCircleButtonBuilder)
        val draggableSwitch =
            findViewById<View>(R.id.draggable_switch) as Switch
        draggableSwitch.setOnCheckedChangeListener { _, isChecked ->
            bmb!!.isDraggable = isChecked
        }
        draggableSwitch.isChecked = true
    }
}