package km.binarcourse.rockpaperscissorsgame.ui.game

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import km.binarcourse.rockpaperscissorsgame.R
import km.binarcourse.rockpaperscissorsgame.dao.PlayerDao
import km.binarcourse.rockpaperscissorsgame.databinding.ActivityGameBinding
import km.binarcourse.rockpaperscissorsgame.model.Player
import km.binarcourse.rockpaperscissorsgame.utils.Constants.PAPER
import km.binarcourse.rockpaperscissorsgame.utils.Constants.ROCK
import km.binarcourse.rockpaperscissorsgame.utils.Constants.SCISSORS
import km.binarcourse.rockpaperscissorsgame.utils.Constants.UNSELECTED

enum class GameState {
    PLAY,
    FINISH
}

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private lateinit var firstPlayer: PlayerDao
    private lateinit var secondPlayer: PlayerDao
    private lateinit var state: GameState

    private val selectedWeaponBackgroundColor = Color.argb(150, 237, 213,216)

    companion object{
        val TAG: String? = GameActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()        //hide action bar
        setState(GameState.PLAY)        //set game state to play
        playerInit()                    //initialize player object
        onFirstPlayerWeaponSelected()   //event handler when first player's weapon image clicked

        //restart game when Restart Game Button clicked
        binding.btnRestartGame.setOnClickListener { onRestartGame() }
    }

    private fun playerInit(){
        firstPlayer = PlayerDao()
        secondPlayer = PlayerDao()
    }

    private fun setState(state: GameState){
        this.state = state
    }


    private fun onFirstPlayerWeaponSelected() {
        binding.ivFirstPlayerRock.setOnClickListener {
            when(state){
                GameState.PLAY -> {
                    Log.d(TAG, "First player select Rock as a weapon")

                    //set first player weapon to ROCK
                    firstPlayer.setWeapon(Player(weapon = ROCK))

                    //change chosen weapon's background color
                    setSelectedFirstWeaponsBackground(firstPlayer.getWeapon())
                    selectRandomComputerWeapon()
                }
                GameState.FINISH -> {
                    showToast()
                }
            }

        }

        binding.ivFirstPlayerPaper.setOnClickListener {
            when(state){
                GameState.PLAY -> {
                    Log.d(TAG, "First player select Paper as a weapon")
                    //set first player weapon to PAPER
                    firstPlayer.setWeapon(Player(weapon = PAPER))
                    setSelectedFirstWeaponsBackground(firstPlayer.getWeapon())
                    selectRandomComputerWeapon()
                }
                GameState.FINISH -> {
                    showToast()
                }
            }

        }

        binding.ivFirstPlayerScissors.setOnClickListener {
            when(state){
                GameState.PLAY -> {
                    Log.d(TAG, "First player select Scissors as a weapon")
                    //set first player weapon to SCISSORS
                    firstPlayer.setWeapon(Player(weapon = SCISSORS))
                    setSelectedFirstWeaponsBackground(firstPlayer.getWeapon())
                    selectRandomComputerWeapon()
                }
                GameState.FINISH -> {
                    showToast()
                }
            }

        }
    }

    private fun setSelectedFirstWeaponsBackground(selectedWeapon: Int){
        when(selectedWeapon){
            ROCK -> {
                binding.ivFirstPlayerRock.setBackgroundColor(selectedWeaponBackgroundColor)
                binding.ivFirstPlayerPaper.background = null
                binding.ivFirstPlayerScissors.background = null
            }
            PAPER -> {
                binding.ivFirstPlayerPaper.setBackgroundColor(selectedWeaponBackgroundColor)
                binding.ivFirstPlayerRock.background = null
                binding.ivFirstPlayerScissors.background = null
            }
            SCISSORS -> {
                binding.ivFirstPlayerScissors.setBackgroundColor(selectedWeaponBackgroundColor)
                binding.ivFirstPlayerRock.background = null
                binding.ivFirstPlayerPaper.background = null
            }
        }
    }

    private fun showResult(firstPlayerWeapon: Int, secondPlayerWeapon: Int){
        when {
            (firstPlayerWeapon+1) % 3 == secondPlayerWeapon -> {
                binding.ivResult.setImageResource(R.drawable.img_lose)
            }
            firstPlayerWeapon == secondPlayerWeapon -> {
                binding.ivResult.setImageResource(R.drawable.img_draw)
            }
            else -> {
                binding.ivResult.setImageResource(R.drawable.img_win)
            }
        }
        setState(GameState.FINISH)
    }

    private fun selectRandomComputerWeapon(){
        val randomWeapon = (0..2).random()
        secondPlayer.setWeapon(Player(weapon = randomWeapon))

        when(randomWeapon){
            ROCK -> {
                binding.ivSecondPlayerRock.setBackgroundColor(selectedWeaponBackgroundColor)
                showResult(firstPlayer.getWeapon(), secondPlayer.getWeapon())
            }
            PAPER -> {
                binding.ivSecondPlayerPaper.setBackgroundColor(selectedWeaponBackgroundColor)
                showResult(firstPlayer.getWeapon(), secondPlayer.getWeapon())
            }
            SCISSORS -> {
                binding.ivSecondPlayerScissors.setBackgroundColor(selectedWeaponBackgroundColor)
                showResult(firstPlayer.getWeapon(), secondPlayer.getWeapon())
            }
        }
    }

    private fun onRestartGame(){
        firstPlayer.setWeapon(Player(weapon = UNSELECTED))
        secondPlayer.setWeapon(Player(weapon = UNSELECTED))

        binding.ivFirstPlayerRock.background = null
        binding.ivFirstPlayerPaper.background = null
        binding.ivFirstPlayerScissors.background = null

        binding.ivSecondPlayerRock.background = null
        binding.ivSecondPlayerPaper.background = null
        binding.ivSecondPlayerScissors.background = null

        binding.ivResult.setImageResource(0)

        setState(GameState.PLAY)
    }

    private fun showToast(){
        Toast.makeText(this, "You need to restart the game!", Toast.LENGTH_SHORT).show()
    }

}