package labianlabs.tinhpt.rwenglish.Views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import labianlabs.tinhpt.rwenglish.Components.FlipComponent
import labianlabs.tinhpt.rwenglish.Components.ScoreHeartComponent
import labianlabs.tinhpt.rwenglish.Components.TextToSpeechComponent
import labianlabs.tinhpt.rwenglish.Model.FakeData
import labianlabs.tinhpt.rwenglish.Model.Vocabulary
import labianlabs.tinhpt.rwenglish.R
import java.util.*

class MainActivity : AppCompatActivity() {

    //region VARS
    private lateinit var scoreHeartComponent: ScoreHeartComponent
    private lateinit var speakComponent: TextToSpeechComponent
    private lateinit var scoreHeartContainer: RelativeLayout
    private lateinit var speakContainer: RelativeLayout
    private lateinit var flipContainer: RelativeLayout
    private lateinit var flipComponent: FlipComponent
    var vocabularies = ArrayList<Vocabulary>()
    //endregion

    //region SYSTEM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initWidget()
        dataCommonForFlip()
        addHeartView()
        addSpeakComponent()
        addFlipComponent()
        setAllEvent()
    }
    //endregion

    //region UTILS
    private fun initWidget() {
        scoreHeartContainer = findViewById(R.id.score_heart_container)
        speakContainer = findViewById(R.id.speak_container)
        flipContainer = findViewById(R.id.flip_container)
    }

    private fun addHeartView() {
        scoreHeartComponent = ScoreHeartComponent(this)
        val view = scoreHeartComponent.createView()
        val params: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        view.layoutParams = params
        scoreHeartContainer.addView(view)
        updateScoreHeart(0,3)
    }

    private fun addSpeakComponent(){
        speakComponent = TextToSpeechComponent(this)
        val view = speakComponent.createView()
        val params: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        view.layoutParams = params
        speakContainer.addView(view)
        updateDataSpeak(vocabularies.get(0))
    }

    private fun addFlipComponent(){
        flipComponent = FlipComponent(this)
        val view = flipComponent.createView()
        val params: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        view.layoutParams = params
        flipContainer.addView(view)
        updateDataFlip(this.vocabularies)

    }

    //endregion

    //region UPDATE DATA
    private fun dataCommonForFlip(){
        val data = FakeData().createDatas()
        this.vocabularies = data as ArrayList<Vocabulary>
    }

    private fun updateScoreHeart(score: Int, heart: Int){
        scoreHeartComponent.updateView(score,heart)
    }

    private fun updateDataSpeak(vocabulary: Vocabulary){
        speakComponent.updateView(vocabulary)
    }

    private fun updateDataFlip(vocabularys: List<Vocabulary>){
        flipComponent.updateView(vocabularys)
    }
    //endregion

    //region VIEW EVENT
    private fun setAllEvent(){
        onSpeakAgainClick()
        onSelectCorrect()
    }

    private fun onSpeakAgainClick(){
        speakComponent.onSpeakAgainClick = {
            Toast.makeText(this,"on Speak Again",Toast.LENGTH_SHORT).show()
        }
    }

    private fun onSelectCorrect(){
        flipComponent.onSelectedCorrect = {
            Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
        }
    }
    //endregion

}
