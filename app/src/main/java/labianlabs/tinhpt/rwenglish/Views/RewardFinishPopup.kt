package labianlabs.tinhpt.rwenglish.Views

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.Spanned
import android.util.Log
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import labianlabs.tinhpt.rwenglish.R
import labianlabs.tinhpt.rwenglish.Utils.KeyUtils
import labianlabs.tinhpt.rwenglish.localize
import java.security.Key

class RewardFinishPopup:AppCompatActivity(){

    //region SYSTEM EVENTS
    override fun onCreate(savedInstanceState: Bundle?) {
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reward_finish_popup)
        this.setFinishOnTouchOutside(false)
        mapView()
        receiveData()
        displayUI()
        onContinueClick()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        Log.e("on Back Pressed","Cancel")
    }
    //endregion

    //region VIEW EVENT
    private fun onContinueClick(){
        _btnContinue.setOnClickListener {
//            showDialog("Do you exit?".localize())
            openHome()
            finish()
        }
    }

    private fun openHome(){
        val intent = Intent(this,HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun showDialog(message: String){
        var alDialog = AlertDialog.Builder(this)
        alDialog.setMessage(message)
        alDialog.setCancelable(true)
        alDialog.setPositiveButton("OK".localize(), DialogInterface.OnClickListener { dialog, which ->
            openHome()
        })
        alDialog.setNegativeButton("No".localize(),DialogInterface.OnClickListener{dialog, which ->
            dialog.dismiss()
        })
        alDialog.show()
    }

    //endregion

    //region UTILS
    private fun receiveData(){
        val bundle  = intent.getBundleExtra(KeyUtils.PUT_EXP_BUNDLE)
        _exp = if(bundle!=null){
            bundle.getInt(KeyUtils.SEND_EXP_TO_REWARD_FINISH)
        }else{
            0
        }
    }

    private fun displayUI(){
        _tvEXP.text = "$_exp"
        _tvMessage.text = String.format("You have been %d exp".localize(),_exp)
    }

    private fun mapView(){
        _tvEXP = findViewById(R.id.text_xp)
        _tvMessage = findViewById(R.id.text_title)
        _btnContinue = findViewById(R.id.btn_continue)
        _btnContinue.text = "Continue".localize()
    }
    //endregion

    //region VARS
    private var _exp: Int = 0
    private lateinit var _tvEXP: TextView
    private lateinit var _tvMessage: TextView
    private lateinit var _btnContinue: Button
    //endregion


}