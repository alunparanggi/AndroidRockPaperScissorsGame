package km.binarcourse.rockpaperscissorsgame.ui.menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import km.binarcourse.rockpaperscissorsgame.R
import km.binarcourse.rockpaperscissorsgame.databinding.ActivityMenuBinding
import km.binarcourse.rockpaperscissorsgame.ui.game.GameActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.ivMultiPlayerMode.setOnClickListener { onMultiPlayerModeSelected() }
        binding.ivSinglePlayerMode.setOnClickListener { onSinglePlayerModeSelected() }

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
}