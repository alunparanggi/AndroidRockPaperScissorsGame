package km.binarcourse.rockpaperscissorsgame.ui.game

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import km.binarcourse.rockpaperscissorsgame.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private val viewModel: GameViewModel by viewModels()

    companion object{
        val TAG: String? = GameActivity::class.java.simpleName
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
    }

}