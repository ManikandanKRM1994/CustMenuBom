package com.krm.custmenubom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.krm.custmenubom.BuilderManager.hamButtonBuilder
import com.krm.custmenubom.BuilderManager.simpleCircleButtonBuilder
import com.krm.custmenubom.menu.BoomMenuButton

internal class RecyclerViewAdapter :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item, null, false)
        view.layoutParams = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.text.text = "No. $position"
        holder.bmb1.clearBuilders()
        for (i in 0 until holder.bmb1.piecePlaceEnum.pieceNumber()) holder.bmb1.addBuilder(
            simpleCircleButtonBuilder
        )
        holder.bmb2.clearBuilders()
        for (i in 0 until holder.bmb2.piecePlaceEnum.pieceNumber()) holder.bmb2.addBuilder(
            hamButtonBuilder
        )
        holder.bmb3.clearBuilders()
        for (i in 0 until holder.bmb3.buttonPlaceEnum.buttonNumber()) holder.bmb3.addBuilder(
            simpleCircleButtonBuilder
        )
    }

    override fun getItemCount(): Int {
        return 1000
    }

    internal inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        var text: TextView = view.findViewById<View>(R.id.text) as TextView
        var bmb1: BoomMenuButton = view.findViewById<View>(R.id.bmb1) as BoomMenuButton
        var bmb2: BoomMenuButton = view.findViewById<View>(R.id.bmb2) as BoomMenuButton
        var bmb3: BoomMenuButton = view.findViewById<View>(R.id.bmb3) as BoomMenuButton

    }
}