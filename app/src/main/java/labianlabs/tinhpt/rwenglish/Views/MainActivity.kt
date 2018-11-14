package labianlabs.tinhpt.rwenglish.Views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.ViewGroup
import android.widget.GridView
import android.widget.RelativeLayout
import android.widget.Toast
import labianlabs.tinhpt.rwenglish.Adapter.VocabularyAdapter
import labianlabs.tinhpt.rwenglish.Components.ScoreHeartComponent
import labianlabs.tinhpt.rwenglish.Components.TextToSpeechComponent
import labianlabs.tinhpt.rwenglish.Model.Vocabulary
import labianlabs.tinhpt.rwenglish.R
import java.util.*

class MainActivity : AppCompatActivity() {

    //endregion
    private var flipView: GridView? = null
    private lateinit var scoreHeartComponent: ScoreHeartComponent
    private lateinit var speakComponent: TextToSpeechComponent
    private lateinit var scoreHeartContainer: RelativeLayout
    private lateinit var speakContainer: RelativeLayout
    val vocabularies = ArrayList<Vocabulary>()
    //endregion


    //region SYSTEM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initWidget()
        addHeartView()
        setupFlip()
        vrData()
        addSpeakComponent()
        setAllEvent()
    }

    //endregion

    //region UTILS
    private fun initWidget() {
        flipView = findViewById(R.id.flipView)
        scoreHeartContainer = findViewById(R.id.score_heart_cmp)
        speakContainer = findViewById(R.id.speak_component)
    }

    private fun addHeartView() {
        scoreHeartComponent = ScoreHeartComponent(this)
        val view = scoreHeartComponent.createView()
        val params: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        view.layoutParams = params
        scoreHeartComponent.updateView(45,2)
        scoreHeartContainer.addView(view)
    }

    private fun addSpeakComponent(){
        speakComponent = TextToSpeechComponent(this)
        val view = speakComponent.createView()
        val params: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        view.layoutParams = params
        speakComponent.updateView(vocabularies.get(0))
        speakContainer.addView(view)
    }

    private fun setupFlip() {
        val width = resources.displayMetrics.densityDpi
        flipView!!.columnWidth = width / 4
        flipView!!.gravity = Gravity.CENTER
        flipView!!.numColumns = 4
        flipView!!.horizontalSpacing = 2
    }

    private fun vrData() {
        for (i in 0..15) {
            val vocabulary = Vocabulary("Flip", "Lật ngược", 1, R.mipmap.ic_launcher)
            if (i % 2 == 0) {
                vocabulary.isDisplayImage = true
            }
            vocabularies.add(vocabulary)
        }
        val vocabularyAdapter = VocabularyAdapter(vocabularies, this)
        flipView!!.adapter = vocabularyAdapter
    }

    //endregion

    //region VIEW EVENT
    private fun setAllEvent(){
        onSpeakAgainClick()
    }

    private fun onSpeakAgainClick(){
        speakComponent.onSpeakAgainClick = {
            Toast.makeText(this,"on Speak Again",Toast.LENGTH_SHORT).show()
        }
    }
    //endregion

}
