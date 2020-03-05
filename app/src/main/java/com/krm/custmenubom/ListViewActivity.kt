package com.krm.custmenubom

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.krm.custmenubom.BuilderManager.hamButtonBuilder
import com.krm.custmenubom.BuilderManager.simpleCircleButtonBuilder
import com.krm.custmenubom.menu.BoomMenuButton

class ListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        val listView =
            (findViewById<View>(R.id.list_view) as ListView)
        listView.adapter = MyAdapter()
        listView.onItemClickListener = OnItemClickListener { _, view, _, _ ->
            val bmb =
                view.findViewById<View>(R.id.bmb1) as BoomMenuButton
            bmb.boom()
        }
    }

    internal class MyAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return 1000
        }

        override fun getItem(position: Int): Any? {
            return null
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(
            position: Int,
            convertView: View,
            parent: ViewGroup
        ): View {
            val viewHolder: ViewHolder = convertView.tag as ViewHolder
            viewHolder.text!!.text = "No. $position"
            viewHolder.bmb1!!.clearBuilders()
            for (i in 0 until viewHolder.bmb1!!.piecePlaceEnum
                .pieceNumber()) viewHolder.bmb1!!.addBuilder(simpleCircleButtonBuilder)
            viewHolder.bmb2!!.clearBuilders()
            for (i in 0 until viewHolder.bmb2!!.piecePlaceEnum
                .pieceNumber()) viewHolder.bmb2!!.addBuilder(hamButtonBuilder)
            viewHolder.bmb3!!.clearBuilders()
            for (i in 0 until viewHolder.bmb3!!.buttonPlaceEnum
                .buttonNumber()) viewHolder.bmb3!!.addBuilder(simpleCircleButtonBuilder)
            return convertView
        }

        internal inner class ViewHolder {
            var text: TextView? = null
            var bmb1: BoomMenuButton? = null
            var bmb2: BoomMenuButton? = null
            var bmb3: BoomMenuButton? = null
        }
    }
}