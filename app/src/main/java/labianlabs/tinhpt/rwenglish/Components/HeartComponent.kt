package labianlabs.tinhpt.rwenglish.Components

import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import labianlabs.tinhpt.rwenglish.R

class HeartComponent(context: Context) : View(context) {

    fun createView(): View {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        _view = inflater.inflate(R.layout.heart_number_component,null) as LinearLayout
        _progressTimeLeft = _view.findViewById(R.id.progress_time_left);
        timeLeft = TimeLeft.makeView(this._progressTimeLeft)
        return _view
    }


    fun updateView(isStart: Boolean) {
        if (isStart){
            Thread(timeLeft).start()
            timeLeft.onFinishProgress ={
                onFinishTime!!.invoke()
            }
        }
    }

    fun removeTimeLeft(){
        timeLeft.destroy()
    }

    //region VARS
    private lateinit var _view: LinearLayout
    private lateinit var _progressTimeLeft: ProgressBar
    private lateinit var timeLeft: TimeLeft
    var onFinishTime:(()->Unit)?= null

    //endregion

    class TimeLeft: Runnable{

        private lateinit var progress: ProgressBar
        private  var handler = Handler()
        var onFinishProgress: (()->Unit)? = null

        companion object {
            fun makeView(probgressBar: ProgressBar): TimeLeft{
                return  TimeLeft().apply {
                    this.progress = probgressBar
                }
            }
        }

        override fun run() {
            for (i in 0..100){
                Thread.sleep(500)
                handler.post(Runnable {
                    progress.progress = i
                    if(i == 100){
                        onFinishProgress?.invoke()
                    }
                })
            }
        }

        fun destroy(){
            handler.removeCallbacks(null)
        }

    }
}