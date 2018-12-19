package labianlabs.tinhpt.rwenglish.Views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import labianlabs.tinhpt.rwenglish.R
import labianlabs.tinhpt.rwenglish.localize

class HomeActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mapView()
        onStartClick()
        onExitClick()
        setupView()
    }

    //region VIEW EVENTS
    private fun onStartClick(){
        _btnStart.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
    private fun onExitClick(){
        _btnExit.setOnClickListener{
            finish()
        }
    }
    //endregion


    //region UTILS
    private fun mapView(){
        _btnStart = findViewById(R.id.btn_start)
        _btnExit = findViewById(R.id.btn_exit)
        _tvTitle = findViewById(R.id.text_title)
    }
    private fun setupView(){
        _btnStart.text = "Start".localize()
        _btnExit.text = "Exit".localize()
        _tvTitle.text = "Remember Word English \n The best for you".localize()
    }
    //endregion

    //region VARS
    private lateinit var _tvTitle: TextView
    private lateinit var _btnStart: Button
    private lateinit var _btnExit: Button
    //endregion

}