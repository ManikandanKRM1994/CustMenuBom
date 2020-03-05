package com.krm.custmenubom

import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.krm.custmenubom.menu.animation.BoomEnum
import com.krm.custmenubom.menu.boomButtons.ButtonPlaceEnum
import com.krm.custmenubom.menu.BoomMenuButton
import com.krm.custmenubom.menu.ButtonEnum
import com.krm.custmenubom.menu.piece.PiecePlaceEnum
import java.util.*

class BoomExampleActivity : AppCompatActivity() {
    private var bmb: BoomMenuButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_boom_example)
        bmb = findViewById<View>(R.id.bmb) as BoomMenuButton
        assert(bmb != null)
        bmb!!.buttonEnum = ButtonEnum.SimpleCircle
        bmb!!.piecePlaceEnum = PiecePlaceEnum.DOT_9_1
        bmb!!.buttonPlaceEnum = ButtonPlaceEnum.SC_9_1
        for (i in 0 until bmb!!.piecePlaceEnum
            .pieceNumber()) bmb!!.addBuilder(BuilderManager.simpleCircleButtonBuilder)
        val listView =
            (findViewById<View>(R.id.list_view) as ListView)
        listView.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1,
            data
        )
        listView.onItemClickListener = OnItemClickListener { _, _, position, _ ->
            bmb!!.boomEnum = BoomEnum.values()[position]
        }
    }

    private val data: List<String>
        get() {
            val data: MutableList<String> =
                ArrayList()
            for (i in 0 until BoomEnum.values().size - 1) data.add(
                BoomEnum.values()[i].toString() + ""
            )
            return data
        }
}