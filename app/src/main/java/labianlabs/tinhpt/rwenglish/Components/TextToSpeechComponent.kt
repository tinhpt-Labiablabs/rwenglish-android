package labianlabs.tinhpt.rwenglish.Components

import android.content.Context
import android.graphics.Color
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import labianlabs.tinhpt.rwenglish.Model.Vocabulary
import labianlabs.tinhpt.rwenglish.R
import java.util.*

class TextToSpeechComponent(context: Context) : View(context) {

    fun createView(): View {
        val inflater = LayoutInflater.from(context)
        _container = inflater.inflate(R.layout.text_to_speech_component, null) as RelativeLayout
        _mtvLocalText = _container.findViewById(R.id.text_local)
        _mibSpeakAgain = _container.findViewById(R.id.imbtn_speak_again)
        _mibSpeakAgain.setBackgroundColor(Color.TRANSPARENT)
        setOnSpeakAgainClick()
        setupTextToSpeech()
        return _container
    }

    fun updateView(vocabulary: Vocabulary){
        _currentVocabulary = vocabulary
        _mtvLocalText.text = vocabulary.localizedWord
        speakText(vocabulary.originWord)
    }

    private fun setOnSpeakAgainClick(){
        _mibSpeakAgain.setOnClickListener {
            speakText(_currentVocabulary.originWord)
        }
    }

    private fun setupTextToSpeech(){
        _textToSpeech = TextToSpeech(context){
            if (it != TextToSpeech.ERROR){
                _textToSpeech.language = Locale.US
            }
        }
    }

     fun speakText(text: String){
        _textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null)
    }

    //region VARS
    private lateinit var _container: RelativeLayout
    private lateinit var _mtvLocalText: TextView
    private lateinit var _mibSpeakAgain: ImageButton
    private lateinit var _textToSpeech: TextToSpeech
    private lateinit var _currentVocabulary: Vocabulary

    var onSpeakAgainClick:(()->Unit)? = null
}