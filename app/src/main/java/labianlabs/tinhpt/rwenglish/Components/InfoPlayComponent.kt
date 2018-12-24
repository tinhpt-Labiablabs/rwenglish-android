package labianlabs.tinhpt.rwenglish.Components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import labianlabs.tinhpt.rwenglish.R
import labianlabs.tinhpt.rwenglish.localize

class InfoPlayComponent(context: Context) : View(context) {

    //region UTILS
    fun createView(): View {
        val inflater = LayoutInflater.from(context)
        view = inflater.inflate(R.layout.score_heart_component, null) as RelativeLayout
        _mtvScore = view.findViewById(R.id.text_score)
        _timeUpComponent = TimeUpComponent(context)
        _timeUpComponent.onFinishTime = {
            onTimeLeftFinish!!.invoke()
        }
        val viewHeart = _timeUpComponent.createView()
        val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT)
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        params.addRule(RelativeLayout.RIGHT_OF, _mtvScore.id)
        params.addRule(RelativeLayout.CENTER_VERTICAL)
        viewHeart.layoutParams = params
        view.addView(viewHeart)
        return view
    }

    fun updateView(score: Int, isStart: Boolean) {
        _mtvScore.text = score.toString() + " " + "XP".localize()
        _timeUpComponent.updateView(isStart)
    }

    fun updateView(score: Int) {
        _mtvScore.text = score.toString() + " " + "XP".localize()
    }

    fun removeTimeUp() {
        _timeUpComponent.removeTimeUp()
    }
    //endregion

    //region VARS
    private lateinit var view: RelativeLayout
    private lateinit var _mtvScore: TextView
    private lateinit var _timeUpComponent: TimeUpComponent

    var onTimeLeftFinish: (() -> Unit)? = null
    //endregion
}