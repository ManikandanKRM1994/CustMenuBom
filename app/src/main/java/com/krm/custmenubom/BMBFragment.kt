package com.krm.custmenubom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.krm.custmenubom.menu.BoomMenuButton

class BMBFragment : Fragment() {
    private var position = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment = inflater.inflate(R.layout.fragment_bmb, container, false)
        val text = fragment.findViewById<View>(R.id.text) as TextView
        text.text = position.toString()
        val bmb1 =
            fragment.findViewById<View>(R.id.bmb1) as BoomMenuButton
        for (i in 0 until bmb1.piecePlaceEnum
            .pieceNumber()) bmb1.addBuilder(BuilderManager.simpleCircleButtonBuilder)
        val bmb2 =
            fragment.findViewById<View>(R.id.bmb2) as BoomMenuButton
        for (i in 0 until bmb2.piecePlaceEnum
            .pieceNumber()) bmb2.addBuilder(BuilderManager.simpleCircleButtonBuilder)
        return fragment
    }

    fun position(position: Int): Fragment {
        this.position = position
        return this
    }
}