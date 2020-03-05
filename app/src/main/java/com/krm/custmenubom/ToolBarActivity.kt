package com.krm.custmenubom

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.krm.custmenubom.menu.BoomMenuButton
import com.krm.custmenubom.menu.ButtonEnum

class ToolBarActivity : AppCompatActivity() {
    private var bmb: BoomMenuButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tool_bar)
        val toolbar =
            findViewById<View>(R.id.tool_bar) as Toolbar
        setSupportActionBar(toolbar)
        bmb = findViewById<View>(R.id.bmb) as BoomMenuButton
        assert(bmb != null)
        bmb!!.buttonEnum = ButtonEnum.Ham
        for (i in 0 until bmb!!.piecePlaceEnum
            .pieceNumber()) bmb!!.addBuilder(BuilderManager.hamButtonBuilderWithDifferentPieceColor)
    }
}