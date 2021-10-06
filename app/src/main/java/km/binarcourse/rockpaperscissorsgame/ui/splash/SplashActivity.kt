package km.binarcourse.rockpaperscissorsgame.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import km.binarcourse.rockpaperscissorsgame.R
import km.binarcourse.rockpaperscissorsgame.data.preference.PlayerPreference
import km.binarcourse.rockpaperscissorsgame.ui.intro.IntroActivity
import km.binarcourse.rockpaperscissorsgame.ui.menu.MenuActivity


class SplashActivity : AppCompatActivity() {

    private var timer: CountDownTimer? = null
    private lateinit var sharedPref: PlayerPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setSplashTimer()
        supportActionBar?.hide()

        sharedPref = PlayerPreference(this)
    }

    private fun setSplashTimer() {
        timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                val intentIntro = Intent(this@SplashActivity, IntroActivity::class.java)
                val intentMenu = Intent(this@SplashActivity, MenuActivity::class.java)

                if(sharedPref.playerName.isNullOrEmpty())
                    navigateToNextPage(intentIntro)
                else
                    navigateToNextPage(intentMenu)

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

    private fun navigateToNextPage(intent: Intent){
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        //set fade animation
        val bundle = ActivityOptionsCompat.makeCustomAnimation(
            this@SplashActivity,
            android.R.anim.fade_in,
            android.R.anim.fade_out
        ).toBundle()
        startActivity(intent, bundle)
    }
}