package labianlabs.tinhpt.rwenglish.Views

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import labianlabs.tinhpt.rwenglish.R
import labianlabs.tinhpt.rwenglish.localize

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        setupView()
        openMain()

    }

    private fun setupView(){
        titleLanding = findViewById(R.id.title_landing)
        titleLanding.text = "Remember Word English \n The best for you".localize()
    }

    private fun openMain() {
        Handler().postDelayed(Runnable {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }, 3000)
    }

    //region VARS
    private lateinit var titleLanding: TextView
    //endregion
}