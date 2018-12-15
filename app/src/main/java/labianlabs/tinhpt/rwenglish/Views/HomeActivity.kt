package labianlabs.tinhpt.rwenglish.Views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import labianlabs.tinhpt.rwenglish.R

class HomeActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mapView()
        onStartClick()
        onExitClick()
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
    }
    //endregion

    //region VARS
    private lateinit var _btnStart: Button
    private lateinit var _btnExit: Button
    //endregion

}