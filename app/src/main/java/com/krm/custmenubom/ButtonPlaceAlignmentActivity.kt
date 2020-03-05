package com.krm.custmenubom

import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.krm.custmenubom.BuilderManager.simpleCircleButtonBuilder
import com.krm.custmenubom.menu.boomButtons.ButtonPlaceAlignmentEnum
import com.krm.custmenubom.menu.boomButtons.ButtonPlaceEnum
import com.krm.custmenubom.menu.BoomMenuButton
import com.krm.custmenubom.menu.ButtonEnum
import com.krm.custmenubom.menu.piece.PiecePlaceEnum
import com.krm.custmenubom.menu.Util
import java.util.*

class ButtonPlaceAlignmentActivity : AppCompatActivity() {
    private var bmb: BoomMenuButton? = null
    private var topMarginSeekText: TextView? = null
    private var bottomMarginSeekText: TextView? = null
    private var leftMarginSeekText: TextView? = null
    private var rightMarginSeekText: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button_place_alignment)
        bmb = findViewById<View>(R.id.bmb) as BoomMenuButton
        assert(bmb != null)
        bmb!!.buttonEnum = ButtonEnum.SimpleCircle
        bmb!!.piecePlaceEnum = PiecePlaceEnum.DOT_4_1
        bmb!!.buttonPlaceEnum = ButtonPlaceEnum.SC_4_1
        for (i in 0 until bmb!!.piecePlaceEnum
            .pieceNumber()) bmb!!.addBuilder(simpleCircleButtonBuilder)
        val listView =
            (findViewById<View>(R.id.list_view) as ListView)
        listView.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1,
            data
        )
        listView.onItemClickListener = OnItemClickListener { _, _, position, _ ->
            bmb!!.buttonPlaceAlignmentEnum = ButtonPlaceAlignmentEnum.getEnum(position)
        }
        initTopMarginSeek()
        initBottomMarginSeek()
        initLeftMarginSeek()
        initRightMarginSeek()
    }

    private fun initTopMarginSeek() {
        val topMarginSeekBar =
            (findViewById<View>(R.id.top_margin_seek) as SeekBar)
        topMarginSeekBar.max = Util.dp2px(50f)
        topMarginSeekBar.progress = bmb!!.buttonTopMargin.toInt()
        topMarginSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                topMarginSeekText!!.text = "Top margin = " + seekBar.progress + " pixel(s)"
                bmb!!.buttonTopMargin = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        topMarginSeekText = findViewById<View>(R.id.top_margin_text) as TextView
        topMarginSeekText!!.text = "Top margin = " + topMarginSeekBar.progress + " pixel(s)"
    }

    private fun initBottomMarginSeek() {
        val bottomMarginSeekBar =
            (findViewById<View>(R.id.bottom_margin_seek) as SeekBar)
        bottomMarginSeekBar.max = Util.dp2px(50f)
        bottomMarginSeekBar.progress = bmb!!.buttonBottomMargin.toInt()
        bottomMarginSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                bottomMarginSeekText!!.text = "Bottom margin = " + seekBar.progress + " pixel(s)"
                bmb!!.buttonBottomMargin = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        bottomMarginSeekText = findViewById<View>(R.id.bottom_margin_text) as TextView
        bottomMarginSeekText!!.text =
            "Bottom margin = " + bottomMarginSeekBar.progress + " pixel(s)"
    }

    private fun initLeftMarginSeek() {
        val leftMarginSeekBar =
            (findViewById<View>(R.id.left_margin_seek) as SeekBar)
        leftMarginSeekBar.max = Util.dp2px(50f)
        leftMarginSeekBar.progress = bmb!!.buttonLeftMargin.toInt()
        leftMarginSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                leftMarginSeekText!!.text = "Left margin = " + seekBar.progress + " pixel(s)"
                bmb!!.buttonLeftMargin = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        leftMarginSeekText = findViewById<View>(R.id.left_margin_text) as TextView
        leftMarginSeekText!!.text = "Left margin = " + leftMarginSeekBar.progress + " pixel(s)"
    }

    private fun initRightMarginSeek() {
        val rightMarginSeekBar =
            (findViewById<View>(R.id.right_margin_seek) as SeekBar)
        rightMarginSeekBar.max = Util.dp2px(50f)
        rightMarginSeekBar.progress = bmb!!.buttonRightMargin.toInt()
        rightMarginSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                rightMarginSeekText!!.text = "Right margin = " + seekBar.progress + " pixel(s)"
                bmb!!.buttonRightMargin = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        rightMarginSeekText = findViewById<View>(R.id.right_margin_text) as TextView
        rightMarginSeekText!!.text = "Right margin = " + rightMarginSeekBar.progress + " pixel(s)"
    }

    private val data: List<String>
        get() {
            val data: MutableList<String> =
                ArrayList()
            for (p in 0 until ButtonPlaceAlignmentEnum.values().size - 1) data.add(
                ButtonPlaceAlignmentEnum.getEnum(p).toString() + ""
            )
            return data
        }
}