package com.krm.custmenubom

import android.os.Bundle
import android.util.Pair
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.krm.custmenubom.BuilderManager.getHamButtonData
import com.krm.custmenubom.BuilderManager.hamButtonBuilder
import com.krm.custmenubom.menu.boomButtons.ButtonPlaceEnum
import com.krm.custmenubom.menu.BoomMenuButton
import com.krm.custmenubom.menu.ButtonEnum
import com.krm.custmenubom.menu.piece.PiecePlaceEnum
import java.util.*

class HamButtonActivity : AppCompatActivity() {
    private var bmb: BoomMenuButton? = null
    private val piecesAndButtons =
        ArrayList<Pair<*, *>?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ham_button)
        bmb = findViewById<View>(R.id.bmb) as BoomMenuButton
        assert(bmb != null)
        bmb!!.buttonEnum = ButtonEnum.Ham
        bmb!!.piecePlaceEnum = PiecePlaceEnum.HAM_1
        bmb!!.buttonPlaceEnum = ButtonPlaceEnum.HAM_1
        bmb!!.addBuilder(hamButtonBuilder)
        val listView =
            (findViewById<View>(R.id.list_view) as ListView)
        listView.adapter = ArrayAdapter(
            this, android.R.layout.simple_expandable_list_item_1,
            getHamButtonData(piecesAndButtons)
        )
        listView.onItemClickListener = OnItemClickListener { _, _, position, _ ->
            bmb!!.piecePlaceEnum = piecesAndButtons[position]!!.first as PiecePlaceEnum
            bmb!!.buttonPlaceEnum = piecesAndButtons[position]!!.second as ButtonPlaceEnum
            bmb!!.clearBuilders()
            for (i in 0 until bmb!!.piecePlaceEnum.pieceNumber()) bmb!!.addBuilder(
                hamButtonBuilder
            )
        }
    }
}