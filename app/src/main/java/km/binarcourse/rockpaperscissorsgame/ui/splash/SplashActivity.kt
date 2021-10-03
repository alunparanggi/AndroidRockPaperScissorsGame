package km.binarcourse.rockpaperscissorsgame.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import km.binarcourse.rockpaperscissorsgame.R
import km.binarcourse.rockpaperscissorsgame.ui.intro.IntroActivity
import androidx.core.app.ActivityOptionsCompat
import java.security.AccessController.getContext


class SplashActivity : AppCompatActivity() {

    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setSplashTimer()
        supportActionBar?.hide()
    }

    private fun setSplashTimer() {
        timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                val intent = Intent(this@SplashActivity, IntroActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                //set fade animation
                val bundle = ActivityOptionsCompat.makeCustomAnimation(
                    this@SplashActivity,
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
                ).toBundle()
                startActivity(intent, bundle)

//                if (UserPreference(this@SplashScreenActivity).isUserLoggedIn) {
//                    val intent = Intent(this@SplashScreenActivity, HomeActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                    startActivity(intent)
//                } else {
//                    val intent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                    startActivity(intent)
//                }
            }

        }

        timer?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.let {
            it.cancel()
            timer = null
        }
    }
}