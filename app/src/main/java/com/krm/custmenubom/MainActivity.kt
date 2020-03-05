package com.krm.custmenubom

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listenClickEventOf(R.id.simple_circle_button_example)
        listenClickEventOf(R.id.text_inside_circle_button_example)
        listenClickEventOf(R.id.text_outside_circle_button_example)
        listenClickEventOf(R.id.ham_button_example)
        listenClickEventOf(R.id.square_and_piece_corner_radius_example)
        listenClickEventOf(R.id.boom_example)
        listenClickEventOf(R.id.button_place_alignment_example)
        listenClickEventOf(R.id.order_example)
        listenClickEventOf(R.id.actionbar_example)
        listenClickEventOf(R.id.tool_bar_example)
        listenClickEventOf(R.id.draggable_example)
        listenClickEventOf(R.id.ease_example)
        listenClickEventOf(R.id.listener_example)
        listenClickEventOf(R.id.control_example)
        listenClickEventOf(R.id.share_example)
        listenClickEventOf(R.id.list_example)
        listenClickEventOf(R.id.recycler_view_example)
        listenClickEventOf(R.id.fragment_example)
        listenClickEventOf(R.id.change_boom_button_example)
        listenClickEventOf(R.id.three_d_animation_example)
        listenClickEventOf(R.id.custom_position_example)
        listenClickEventOf(R.id.fade_views_example)
        listenClickEventOf(R.id.orientation_example)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.simple_circle_button_example -> startActivity(
                SimpleCircleButtonActivity::class.java
            )
            R.id.text_inside_circle_button_example -> startActivity(TextInsideCircleButtonActivity::class.java)
            R.id.text_outside_circle_button_example -> startActivity(TextOutsideCircleButtonActivity::class.java)
            R.id.ham_button_example -> startActivity(HamButtonActivity::class.java)
            R.id.square_and_piece_corner_radius_example -> startActivity(
                SquareAndPieceCornerRadiusActivity::class.java
            )
            R.id.boom_example -> startActivity(BoomExampleActivity::class.java)
            R.id.button_place_alignment_example -> startActivity(ButtonPlaceAlignmentActivity::class.java)
            R.id.order_example -> startActivity(OrderExampleActivity::class.java)
            R.id.tool_bar_example -> startActivity(ToolBarActivity::class.java)
            R.id.actionbar_example -> startActivity(ActionBarActivity::class.java)
            R.id.draggable_example -> startActivity(DraggableActivity::class.java)
            R.id.ease_example -> startActivity(EaseActivity::class.java)
            R.id.listener_example -> startActivity(ListenerActivity::class.java)
            R.id.control_example -> startActivity(ControlActivity::class.java)
            R.id.share_example -> startActivity(ShareActivity::class.java)
            R.id.list_example -> startActivity(ListViewActivity::class.java)
            R.id.recycler_view_example -> startActivity(RecyclerViewActivity::class.java)
            R.id.fragment_example -> startActivity(FragmentActivity::class.java)
            R.id.change_boom_button_example -> startActivity(ChangeBoomButtonActivity::class.java)
            R.id.three_d_animation_example -> startActivity(ThreeDAnimationActivity::class.java)
            R.id.custom_position_example -> startActivity(CustomPositionActivity::class.java)
            R.id.fade_views_example -> startActivity(FadeViewsActivity::class.java)
            R.id.orientation_example -> startActivity(OrientationActivity::class.java)
        }
    }

    private fun listenClickEventOf(id: Int) {
        findViewById<View>(id).setOnClickListener(this)
    }

    private fun startActivity(cls: Class<*>) {
        startActivity(Intent(this, cls))
    }
}