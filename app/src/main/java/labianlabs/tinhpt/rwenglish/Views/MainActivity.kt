package labianlabs.tinhpt.rwenglish.Views

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
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

class MainActivity : AppCompatActivity() {

    //region VARS
    private lateinit var scoreHeartComponent: ScoreHeartComponent
    private lateinit var speakComponent: TextToSpeechComponent
    private lateinit var scoreHeartContainer: RelativeLayout
    private lateinit var speakContainer: RelativeLayout
    private lateinit var flipContainer: RelativeLayout
    private lateinit var flipComponent: FlipComponent
    var vocabularies = ArrayList<Vocabulary>()
    private var score: Float = 0.0f
    private var scoreAdd: Float = 0.0f
    private var firstTime = true;
    private var currentIndex = 0;
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

    override fun onDestroy() {
        super.onDestroy()
        scoreHeartComponent.removeTimeLeft()
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
        updateScoreHeart(0, false)
        scoreHeartComponent.onTimeLeftFinish = {
           showDialog("Finish Time Left")
        }
    }

    private fun addSpeakComponent() {
        speakComponent = TextToSpeechComponent(this)
        val view = speakComponent.createView()
        val params: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        view.layoutParams = params
        speakContainer.addView(view)
        updateDataSpeak(vocabularies.get(currentIndex))
    }

    private fun addFlipComponent() {
        flipComponent = FlipComponent(this)
        val view = flipComponent.createView()
        val params: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        view.layoutParams = params
        flipContainer.addView(view)
        updateDataFlip(this.vocabularies)

    }

    private fun removeIfCorrect(id: Int) {
        val temp: ArrayList<Vocabulary> = vocabularies
        for (i in 0..vocabularies.size - 1) {
            if (temp.get(i).idWord == id && temp.get(i).isDisplayImage) {
                temp.removeAt(i)
                break
            }
        }
        updateDataFlip(temp)
        currentIndex++
        updateDataSpeak(temp.get(currentIndex))
    }

    private fun scoring() {
        //TODO: update logic
        if (score < 100) {
            score += scoreAdd;
            updateScoreHeart(score.toInt());
        }else if(score.toInt() == 100){
            showDialog("Finish Lesson")
        }
    }

    private fun showDialog(message: String){
        var alDialog = AlertDialog.Builder(this)
        alDialog.setMessage(message)
        alDialog.setCancelable(true)
        alDialog.setPositiveButton("Ok",DialogInterface.OnClickListener { dialog, which ->
            dialog.dismiss()
            finish()
        })
        alDialog.show()

    }


    //endregion

    //region UPDATE DATA
    private fun dataCommonForFlip() {
        val data = FakeData().createDatas()
        this.vocabularies = data as ArrayList<Vocabulary>
        scoreAdd = 100 / (vocabularies.size / 2).toFloat()
    }

    private fun updateScoreHeart(score: Int, isStart: Boolean) {
        scoreHeartComponent.updateView(score, isStart)
    }

    private fun updateScoreHeart(score: Int) {
        scoreHeartComponent.updateView(score)
    }

    private fun updateDataSpeak(vocabulary: Vocabulary) {
        speakComponent.updateView(vocabulary)
    }

    private fun updateDataFlip(vocabularys: List<Vocabulary>) {
        flipComponent.updateView(vocabularys)
    }

    //endregion

    //region VIEW EVENT
    private fun setAllEvent() {
//        onSpeakAgainClick()
        onSelectCorrect()
        onSelect()
    }

    private fun onSpeakAgainClick() {
        speakComponent.onSpeakAgainClick = {
            Toast.makeText(this, "on Speak Again", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onSelectCorrect() {
        flipComponent.onSelectedCorrect = {
            scoring()
            removeIfCorrect(it)
        }
    }

    private fun onSelect() {
        flipComponent.onSelected = {
            if (firstTime) {
                scoreHeartComponent.updateView(this.score.toInt(), true)
                firstTime = false
            }

        }
    }

    //endregion

}
