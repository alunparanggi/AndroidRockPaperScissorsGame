package km.binarcourse.rockpaperscissorsgame.ui.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import km.binarcourse.rockpaperscissorsgame.R
import km.binarcourse.rockpaperscissorsgame.data.preference.PlayerPreference
import km.binarcourse.rockpaperscissorsgame.databinding.ActivityGameBinding
import km.binarcourse.rockpaperscissorsgame.databinding.CustomDialogBinding
import km.binarcourse.rockpaperscissorsgame.ui.menu.MenuActivity
import km.binarcourse.rockpaperscissorsgame.utils.Constants.FIRST_PLAYER_WIN
import km.binarcourse.rockpaperscissorsgame.utils.Constants.UNSELECTED

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private lateinit var sharedPref: PlayerPreference
    private val viewModel: GameViewModel by viewModels()

    companion object {
        val TAG: String? = GameActivity::class.java.simpleName
        const val EXTRAS_GAME_MODE = "GAME_MODE"

        @JvmStatic
        fun startActivity(context: Context, isMultiPlayerMode: Boolean) {
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

        sharedPref = PlayerPreference(this)

        //set game mode
        viewModel.setGameMode(gameMode())

        showToastSelectedWeapon()
        showCustomDialog()
    }

    //get game mode (single or multiplayer)
    private fun gameMode(): Boolean =
        intent.getBooleanExtra(EXTRAS_GAME_MODE, false)

    //trigger for showing a toast which weapon player selected
    private fun showToastSelectedWeapon() {
        if(viewModel.isMultiPlayerMode.value == true){
            viewModel.firstPlayerWeapon.observe(this, {
                if (it != UNSELECTED) {
                    Toast.makeText(
                        this,
                        "${sharedPref.playerName} chose ${viewModel.getWeaponString(it)}",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            })

            viewModel.secondPlayerWeapon.observe(this, {
                if (it != UNSELECTED) {
                    Toast.makeText(
                        this,
                        "2nd Player chose ${viewModel.getWeaponString(it)}",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            })
        }
    }

    private fun showCustomDialog() {
        viewModel.isGameFinished.observe(this, { isGameFinished ->
            if (isGameFinished) {
                val dialogBinding = CustomDialogBinding.inflate(layoutInflater, null, false)
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setView(dialogBinding.root)

                val dialog = dialogBuilder.create()

                dialogBinding.btnBackToMenu.setOnClickListener { navigateToMenu() }

                dialogBinding.btnPlayAgain.setOnClickListener {
                    viewModel.restartGame()
                    dialog.dismiss()
                }

                dialogBinding.tvWinner.text = getString(
                    R.string.text_the_winner,
                    if (viewModel.gameResult.value == FIRST_PLAYER_WIN)
                        sharedPref.playerName
                    else
                        if (viewModel.isMultiPlayerMode.value == true) "2nd Player" else "Computer"
                )

                dialog.show()
            }
        })
    }

    //navigate to menu activity
    private fun navigateToMenu() {
        val intent = Intent(this, MenuActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

}