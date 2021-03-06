package com.krm.custmenubom

import android.os.Bundle
import android.util.Pair
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.krm.custmenubom.BuilderManager.getCircleButtonData
import com.krm.custmenubom.BuilderManager.simpleCircleButtonBuilder
import com.krm.custmenubom.menu.boomButtons.ButtonPlaceEnum
import com.krm.custmenubom.menu.BoomMenuButton
import com.krm.custmenubom.menu.ButtonEnum
import com.krm.custmenubom.menu.piece.PiecePlaceEnum
import java.util.*

class SimpleCircleButtonActivity : AppCompatActivity() {
    private var bmb: BoomMenuButton? = null
    private val piecesAndButtons =
        ArrayList<Pair<*, *>?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_circle_button)
        bmb = findViewById<View>(R.id.bmb) as BoomMenuButton
        assert(bmb != null)
        bmb!!.buttonEnum = ButtonEnum.SimpleCircle
        bmb!!.piecePlaceEnum = PiecePlaceEnum.DOT_1
        bmb!!.buttonPlaceEnum = ButtonPlaceEnum.SC_1
        bmb!!.addBuilder(simpleCircleButtonBuilder)
        val listView =
            (findViewById<View>(R.id.list_view) as ListView)
        listView.adapter = ArrayAdapter(
            this, android.R.layout.simple_expandable_list_item_1,
            getCircleButtonData(piecesAndButtons)
        )
        listView.onItemClickListener = OnItemClickListener { _, _, position, _ ->
            bmb!!.piecePlaceEnum = piecesAndButtons[position]!!.first as PiecePlaceEnum
            bmb!!.buttonPlaceEnum = piecesAndButtons[position]!!.second as ButtonPlaceEnum
            bmb!!.clearBuilders()
            for (i in 0 until bmb!!.piecePlaceEnum.pieceNumber()) bmb!!.addBuilder(
                simpleCircleButtonBuilder
            )
        }
    }
}