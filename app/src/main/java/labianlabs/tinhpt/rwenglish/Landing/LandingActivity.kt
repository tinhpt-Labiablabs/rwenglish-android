package labianlabs.tinhpt.rwenglish.Landing

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import labianlabs.tinhpt.rwenglish.R
import labianlabs.tinhpt.rwenglish.Views.MainActivity

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        openMain()
    }

    private fun openMain() {
        Handler().postDelayed(Runnable {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }, 3000)
    }
}