package labianlabs.tinhpt.rwenglish.Components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import labianlabs.tinhpt.rwenglish.R

class HeartComponent(context: Context) : View(context) {

    fun createView(): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        _view = inflater.inflate(R.layout.heart_number_component,null) as LinearLayout
        _mivHeartFirst = _view.findViewById(R.id.iv_heart_first)
        _mivHeartSecond = _view.findViewById(R.id.iv_heart_second)
        _mivHeartThird = _view.findViewById(R.id.iv_heart_third)
        return _view
    }

    fun updateView(heartNumber: Int) {
        when (heartNumber) {
            3 -> {
                _mivHeartFirst.visibility = VISIBLE
                _mivHeartSecond.visibility = VISIBLE
                _mivHeartThird.visibility = VISIBLE
            }
            2 -> {
                _mivHeartFirst.visibility = INVISIBLE
                _mivHeartSecond.visibility = VISIBLE
                _mivHeartThird.visibility = VISIBLE
            }
            1 -> {
                _mivHeartFirst.visibility = INVISIBLE
                _mivHeartSecond.visibility = INVISIBLE
                _mivHeartThird.visibility = VISIBLE
            }
        }
    }

    //region VARS
    private lateinit var _view: LinearLayout
    private lateinit var _mivHeartFirst: ImageView
    private lateinit var _mivHeartSecond: ImageView
    private lateinit var _mivHeartThird: ImageView
    //endregion
}