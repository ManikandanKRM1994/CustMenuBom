package com.krm.custmenubom

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.krm.custmenubom.BuilderManager.getHamButtonBuilder
import com.krm.custmenubom.menu.boomButtons.BoomButton
import com.krm.custmenubom.menu.boomButtons.HamButton
import com.krm.custmenubom.menu.BoomMenuButton
import com.krm.custmenubom.menu.OnBoomListenerAdapter

class ChangeBoomButtonActivity : AppCompatActivity() {
    private var bmb: BoomMenuButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_boom_button)
        bmb = findViewById<View>(R.id.bmb) as BoomMenuButton
        assert(bmb != null)
        bmb!!.addBuilder(getHamButtonBuilder("Change Text", "..."))
        bmb!!.addBuilder(
            getHamButtonBuilder("Change Image", "...")
                .normalImageRes(R.drawable.elephant)
        )
        bmb!!.addBuilder(
            getHamButtonBuilder("Change Color", "...")
                .normalColorRes(R.color.colorPrimary)
        )
        bmb!!.addBuilder(getHamButtonBuilder("Change Piece Color", "..."))
        bmb!!.addBuilder(
            getHamButtonBuilder("Change Unable", "...")
                .unableColor(Color.BLUE)
                .unableImageRes(R.drawable.butterfly)
                .unableText("Unable!")
        )
        bmb!!.onBoomListener = object : OnBoomListenerAdapter() {
            override fun onClicked(index: Int, boomButton: BoomButton) {
                super.onClicked(index, boomButton)
                changeBoomButton(index)
            }
        }
    }

    private fun changeBoomButton(index: Int) {
        val builder = bmb!!.getBuilder(index) as HamButton.Builder
        when (index) {
            0 -> {
                builder.normalText("Changed!")
                builder.highlightedText("Highlighted, changed!")
                builder.subNormalText("Sub-text, changed!")
                builder.normalTextColor(Color.YELLOW)
                builder.highlightedTextColorRes(R.color.colorPrimary)
                builder.subNormalTextColor(Color.BLACK)
            }
            1 -> {
                builder.normalImageRes(R.drawable.bat)
                builder.highlightedImageRes(R.drawable.bear)
            }
            2 -> {
                builder.normalColorRes(R.color.colorAccent)
            }
            3 -> {
                builder.pieceColor(Color.WHITE)
            }
            4 -> {
                builder.unable(true)
            }
        }
    }
}