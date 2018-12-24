package labianlabs.tinhpt.rwenglish.Views

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.RelativeLayout
import labianlabs.tinhpt.rwenglish.Components.FlipComponent
import labianlabs.tinhpt.rwenglish.Components.InfoPlayComponent
import labianlabs.tinhpt.rwenglish.Components.TextToSpeechComponent
import labianlabs.tinhpt.rwenglish.Model.FakeData
import labianlabs.tinhpt.rwenglish.Model.Vocabulary
import labianlabs.tinhpt.rwenglish.R
import labianlabs.tinhpt.rwenglish.Utils.CommunityKeyUtils
import labianlabs.tinhpt.rwenglish.localize

class MainActivity : AppCompatActivity() {

    //region VARS
    private lateinit var infoPlayComponent: InfoPlayComponent
    private lateinit var speakComponent: TextToSpeechComponent
    private lateinit var scoreHeartContainer: RelativeLayout
    private lateinit var speakContainer: RelativeLayout
    private lateinit var flipContainer: RelativeLayout
    private lateinit var flipComponent: FlipComponent
    private var vocabularies = ArrayList<Vocabulary>()
    private var score: Float = 0.0f
    private var scoreAdd: Float = 0.0f
    private var firstTime = true
    private var currentIndex = 0
    private var currentId = 0
    //endregion

    //region SYSTEM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initWidget()
        dataCommonForFlip()
        addInfoPlayComponent()
        addSpeakComponent()
        addFlipComponent()
        setAllEvent()
    }

    override fun onDestroy() {
        super.onDestroy()
        infoPlayComponent.removeTimeUp()
    }

    override fun onBackPressed() {
        showDialog("Do you want exit?".localize())
    }
    //endregion

    //region PRIVATE UTILS
    private fun initWidget() {
        scoreHeartContainer = findViewById(R.id.score_heart_container)
        speakContainer = findViewById(R.id.speak_container)
        flipContainer = findViewById(R.id.flip_container)
    }

    private fun addInfoPlayComponent() {
        infoPlayComponent = InfoPlayComponent(this)
        val view = infoPlayComponent.createView()
        val params: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        view.layoutParams = params
        scoreHeartContainer.addView(view)
        updateScoreForInfoPlay(0, false)
        infoPlayComponent.onTimeLeftFinish = {
            showDialog("Time up".localize())
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
        currentId = vocabularies.get(currentIndex).idWord
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

    private fun updateAllScreen(id: Int) {
        val temp = removeDataWith(id)
        updateDataFlip(temp)
        currentIndex++
        if (currentIndex < vocabularies.size) {
            updateDataSpeak(temp.get(currentIndex))
            currentId = temp.get(currentIndex).idWord
        } else {
            openRewardPopup()
        }
    }

    private fun removeDataWith(id: Int):List<Vocabulary>{
        val temp: ArrayList<Vocabulary> = vocabularies
        for (i in 0..vocabularies.size - 1) {
            if (temp.get(i).idWord == id && temp.get(i).isDisplayImage) {
                temp.removeAt(i)
                break
            }
        }
        return temp
    }

    private fun scoring() {
        if (score < 100) {
            score += scoreAdd;
            updateScoreForInfoPlay(score.toInt());
        }
    }

    private fun showDialog(message: String) {
        val alDialog = AlertDialog.Builder(this)
        alDialog.setMessage(message)
        alDialog.setCancelable(true)
        alDialog.setPositiveButton("OK".localize(), DialogInterface.OnClickListener { dialog, which ->
            finish()
        })
        alDialog.show()

    }

    private fun openRewardPopup() {
        val intent = Intent(this, RewardFinishPopup::class.java)
        val bundle = Bundle()
        bundle.putInt(CommunityKeyUtils.SEND_EXP_TO_REWARD_FINISH, score.toInt())
        intent.putExtra(CommunityKeyUtils.PUT_EXP_BUNDLE, bundle)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    //endregion

    //region UPDATE DATA
    private fun dataCommonForFlip() {
        val data = FakeData().createDatas()
        this.vocabularies = data as ArrayList<Vocabulary>
        scoreAdd = 100 / (vocabularies.size / 2).toFloat()
    }

    private fun updateScoreForInfoPlay(score: Int, isStart: Boolean) {
        infoPlayComponent.updateView(score, isStart)
    }

    private fun updateScoreForInfoPlay(score: Int) {
        infoPlayComponent.updateView(score)
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
        onSelectCorrect()
        startTimeUpCountWhenFirstSelect()
    }

    /*
    * Call when selected correct vocabulary*/
    private fun onSelectCorrect() {
        flipComponent.onSelectedCorrect = {
            if (currentId == it) {
                scoring()
                updateAllScreen(it)
            }
        }
    }
    /*
    * Event start only one to start time up progress
    * */
    private fun startTimeUpCountWhenFirstSelect() {
        flipComponent.onSelected = {
            if (firstTime) {
                infoPlayComponent.updateView(this.score.toInt(), true)
                firstTime = false
            }

        }
    }
    //endregion

}
