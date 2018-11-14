package labianlabs.tinhpt.rwenglish.Components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import labianlabs.tinhpt.rwenglish.Model.Vocabulary
import labianlabs.tinhpt.rwenglish.R

class TextToSpeechComponent(context: Context) : View(context) {

    fun createView(): View {
        val inflater = LayoutInflater.from(context)
        _container = inflater.inflate(R.layout.text_to_speech_component, null) as RelativeLayout
        _mtvLocalText = _container.findViewById(R.id.text_local)
        _mbtnSpeakAgain = _container.findViewById(R.id.imbtn_speak_again)
        setOnSpeakAgainClick()
        return _container
    }

    fun updateView(vocabulary: Vocabulary){
        _mtvLocalText.text = vocabulary.localizedWord
    }

    private fun setOnSpeakAgainClick(){
        _mbtnSpeakAgain.setOnClickListener {
            onSpeakAgainClick!!.invoke()
        }
    }

    //region VARS
    private lateinit var _container: RelativeLayout
    private lateinit var _mtvLocalText: TextView
    private lateinit var _mbtnSpeakAgain: ImageView

    var onSpeakAgainClick:(()->Unit)? = null
}