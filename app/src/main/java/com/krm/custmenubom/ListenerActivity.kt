package com.krm.custmenubom

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.krm.custmenubom.BuilderManager.imageResource
import com.krm.custmenubom.menu.boomButtons.BoomButton
import com.krm.custmenubom.menu.boomButtons.ButtonPlaceEnum
import com.krm.custmenubom.menu.boomButtons.SimpleCircleButton
import com.krm.custmenubom.menu.BoomMenuButton
import com.krm.custmenubom.menu.ButtonEnum
import com.krm.custmenubom.menu.OnBoomListener
import com.krm.custmenubom.menu.OnBoomListenerAdapter
import com.krm.custmenubom.menu.piece.PiecePlaceEnum

class ListenerActivity : AppCompatActivity() {
    private var textViewForAnimation: TextView? = null
    private var textViewForButton: TextView? = null
    private var bmb: BoomMenuButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listener)
        textViewForButton = findViewById<View>(R.id.text_for_button) as TextView
        textViewForAnimation = findViewById<View>(R.id.text_for_animation) as TextView
        bmb = findViewById<View>(R.id.bmb) as BoomMenuButton
        assert(bmb != null)
        bmb!!.buttonEnum = ButtonEnum.SimpleCircle
        bmb!!.piecePlaceEnum = PiecePlaceEnum.DOT_6_3
        bmb!!.buttonPlaceEnum = ButtonPlaceEnum.SC_6_3
        for (i in 0 until bmb!!.piecePlaceEnum.pieceNumber()) addBuilder()

        // Use OnBoomListenerAdapter to listen part of methods
        bmb!!.onBoomListener = object : OnBoomListenerAdapter() {
            override fun onBoomWillShow() {
                super.onBoomWillShow()
                // logic here
            }
        }

        // Use OnBoomListener to listen all methods
        bmb!!.onBoomListener = object : OnBoomListener {
            override fun onClicked(index: Int, boomButton: BoomButton) {
                // If you have implement listeners for boom-buttons in builders,
                // then you shouldn't add any listener here for duplicate callbacks.
            }

            override fun onBackgroundClick() {
                textViewForAnimation!!.text = "Click background!!!"
            }

            override fun onBoomWillHide() {
                Log.d(
                    "BMB",
                    "onBoomWillHide: " + bmb!!.isBoomed + " " + bmb!!.isReBoomed
                )
                textViewForAnimation!!.text = "Will RE-BOOM!!!"
            }

            override fun onBoomDidHide() {
                Log.d(
                    "BMB",
                    "onBoomDidHide: " + bmb!!.isBoomed + " " + bmb!!.isReBoomed
                )
                textViewForAnimation!!.text = "Did RE-BOOM!!!"
            }

            override fun onBoomWillShow() {
                Log.d(
                    "BMB",
                    "onBoomWillShow: " + bmb!!.isBoomed + " " + bmb!!.isReBoomed
                )
                textViewForAnimation!!.text = "Will BOOM!!!"
            }

            override fun onBoomDidShow() {
                Log.d(
                    "BMB",
                    "onBoomDidShow: " + bmb!!.isBoomed + " " + bmb!!.isReBoomed
                )
                textViewForAnimation!!.text = "Did BOOM!!!"
            }
        }
    }

    private fun addBuilder() {
        bmb!!.addBuilder(
            SimpleCircleButton.Builder()
                .normalImageRes(imageResource)
                .listener { index ->
                    textViewForButton!!.text = "No.$index boom-button is clicked!"
                }!!
        )
    }
}