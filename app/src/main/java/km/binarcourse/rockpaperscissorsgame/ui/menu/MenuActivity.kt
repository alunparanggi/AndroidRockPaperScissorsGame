package km.binarcourse.rockpaperscissorsgame.ui.menu

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import km.binarcourse.rockpaperscissorsgame.R
import km.binarcourse.rockpaperscissorsgame.data.preference.PlayerPreference
import km.binarcourse.rockpaperscissorsgame.databinding.ActivityMenuBinding
import km.binarcourse.rockpaperscissorsgame.ui.game.GameActivity
import km.binarcourse.rockpaperscissorsgame.ui.intro.IntroActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private lateinit var sharedPref: PlayerPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.ivMultiPlayerMode.setOnClickListener { onMultiPlayerModeSelected() }
        binding.ivSinglePlayerMode.setOnClickListener { onSinglePlayerModeSelected() }

        sharedPref = PlayerPreference(this)

        binding.btnChangeName.setOnClickListener { navigateToIntro() }

        initTextMenu()
        showSnackBar()
    }

    private fun initTextMenu(){
        binding.tvMenuMultiplayer.text =
            getString(R.string.text_menu_multiplayer, sharedPref.playerName)
        binding.tvMenuSinglePlayer.text =
            getString(R.string.text_menu_single_player, sharedPref.playerName)
    }

    private fun showSnackBar() {
        Snackbar
            .make(binding.root,
                getString(R.string.text_welcome_player, sharedPref.playerName),
                Snackbar.LENGTH_LONG)
            .setAction("Close") { }
            .show()
    }

    private fun navigateToGame(isMultiPlayerSelected: Boolean){
        GameActivity.startActivity(this, isMultiPlayerSelected)
    }

    private fun onMultiPlayerModeSelected(){
        navigateToGame(isMultiPlayerSelected = true)
    }

    private fun onSinglePlayerModeSelected() {
        navigateToGame(isMultiPlayerSelected = false)
    }

    private fun navigateToIntro(){
        startActivity(Intent(this, IntroActivity::class.java))
        finish()
    }

}