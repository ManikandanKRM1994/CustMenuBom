package com.krm.custmenubom

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.krm.custmenubom.menu.boomButtons.ButtonPlaceEnum
import com.krm.custmenubom.menu.BoomMenuButton
import com.krm.custmenubom.menu.ButtonEnum
import com.krm.custmenubom.menu.piece.PiecePlaceEnum

class ActionBarActivity : AppCompatActivity() {
    @SuppressLint("InflateParams", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_bar)
        val mActionBar = supportActionBar!!
        mActionBar.setDisplayShowHomeEnabled(false)
        mActionBar.setDisplayShowTitleEnabled(false)
        val mInflater = LayoutInflater.from(this)
        val actionBar = mInflater.inflate(R.layout.custom_actionbar, null)
        val mTitleTextView =
            actionBar.findViewById<View>(R.id.title_text) as TextView
        mTitleTextView.text = "ActionBar"
        mActionBar.customView = actionBar
        mActionBar.setDisplayShowCustomEnabled(true)
        (actionBar.parent as Toolbar).setContentInsetsAbsolute(0, 0)
        val leftBmb =
            actionBar.findViewById<View>(R.id.action_bar_left_bmb) as BoomMenuButton
        val rightBmb =
            actionBar.findViewById<View>(R.id.action_bar_right_bmb) as BoomMenuButton
        leftBmb.buttonEnum = ButtonEnum.TextOutsideCircle
        leftBmb.piecePlaceEnum = PiecePlaceEnum.DOT_9_1
        leftBmb.buttonPlaceEnum = ButtonPlaceEnum.SC_9_1
        for (i in 0 until leftBmb.piecePlaceEnum.pieceNumber()) leftBmb.addBuilder(
            BuilderManager.textOutsideCircleButtonBuilderWithDifferentPieceColor
        )
        rightBmb.buttonEnum = ButtonEnum.Ham
        rightBmb.piecePlaceEnum = PiecePlaceEnum.HAM_4
        rightBmb.buttonPlaceEnum = ButtonPlaceEnum.HAM_4
        for (i in 0 until rightBmb.piecePlaceEnum.pieceNumber()) rightBmb.addBuilder(
            BuilderManager.hamButtonBuilderWithDifferentPieceColor
        )
    }
}