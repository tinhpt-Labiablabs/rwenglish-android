package labianlabs.tinhpt.rwenglish.Components

import android.app.ActionBar
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import labianlabs.tinhpt.rwenglish.R

class ScoreHeartComponent(context: Context): View(context){
    fun createView():View{
        val inflater = LayoutInflater.from(context)
        view = inflater.inflate(R.layout.score_heart_component,null) as RelativeLayout
        _mtvScore = view.findViewById(R.id.text_score)
        _heartComponent = HeartComponent(context)
        val viewHeart = _heartComponent.createView()
        val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT)
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        viewHeart.layoutParams = params
        view.addView(viewHeart)
        return view
    }

    fun updateView(score: Int, heart: Int){
        _mtvScore.text = score.toString() +" XP"
        _heartComponent.updateView(heart)
    }

    //region VARS
    private lateinit var view:RelativeLayout
    private lateinit var _mtvScore: TextView
    private lateinit var _heartComponent: HeartComponent
    //endregion
}