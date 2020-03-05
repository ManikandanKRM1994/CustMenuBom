package com.krm.custmenubom

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.krm.custmenubom.BuilderManager.hamButtonBuilderWithDifferentPieceColor
import com.krm.custmenubom.BuilderManager.squareSimpleCircleButtonBuilder
import com.krm.custmenubom.BuilderManager.textOutsideCircleButtonBuilder
import com.krm.custmenubom.menu.BoomMenuButton

class ThreeDAnimationActivity : AppCompatActivity() {
    private var durationTextView: TextView? = null
    private var bmb1: BoomMenuButton? = null
    private var bmb2: BoomMenuButton? = null
    private var bmb3: BoomMenuButton? = null
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three_d_animation)
        bmb1 = findViewById<View>(R.id.bmb1) as BoomMenuButton
        for (i in 0 until bmb1!!.piecePlaceEnum
            .pieceNumber()) bmb1!!.addBuilder(squareSimpleCircleButtonBuilder)
        bmb2 = findViewById<View>(R.id.bmb2) as BoomMenuButton
        for (i in 0 until bmb2!!.piecePlaceEnum
            .pieceNumber()) bmb2!!.addBuilder(hamButtonBuilderWithDifferentPieceColor)
        bmb3 = findViewById<View>(R.id.bmb3) as BoomMenuButton
        for (i in 0 until bmb3!!.piecePlaceEnum
            .pieceNumber()) bmb3!!.addBuilder(textOutsideCircleButtonBuilder)
        val threeDAnimationSwitch =
            findViewById<View>(R.id.three_d_animation_switch) as Switch
        threeDAnimationSwitch.setOnCheckedChangeListener { _, isChecked ->
            bmb1!!.isUse3DTransformAnimation = isChecked
            bmb2!!.isUse3DTransformAnimation = isChecked
            bmb3!!.isUse3DTransformAnimation = isChecked
        }
        threeDAnimationSwitch.isChecked = true
        val durationSeekBar =
            findViewById<View>(R.id.duration_seek) as SeekBar
        durationSeekBar.max = 3000
        durationSeekBar.progress = 300
        durationSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
                durationTextView!!.text = "Show/Hide duration = " + seekBar.progress + " ms"
                bmb1!!.setDuration(progress.toLong())
                bmb2!!.setDuration(progress.toLong())
                bmb3!!.setDuration(progress.toLong())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
        durationTextView = findViewById<View>(R.id.duration_text) as TextView
        durationTextView!!.text = "Show/Hide duration = " + durationSeekBar.progress + " ms"
    }
}