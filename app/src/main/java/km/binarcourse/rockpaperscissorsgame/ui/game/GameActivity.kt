package km.binarcourse.rockpaperscissorsgame.ui.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import km.binarcourse.rockpaperscissorsgame.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private val viewModel: GameViewModel by viewModels()

    companion object{
        val TAG: String? = GameActivity::class.java.simpleName
        const val EXTRAS_GAME_MODE = "GAME_MODE"

        @JvmStatic
        fun startActivity(context: Context, isMultiPlayerMode: Boolean){
            val intent = Intent(context, GameActivity::class.java)
            intent.putExtra(EXTRAS_GAME_MODE, isMultiPlayerMode)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this

        //bind gameViewModel object to view
        binding.gameViewModel = viewModel

        //hide action bar
        supportActionBar?.hide()

        //set game mode
        viewModel.setGameMode(gameMode())
    }

    private fun gameMode(): Boolean =
        intent.getBooleanExtra(EXTRAS_GAME_MODE, false)

}