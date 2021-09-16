package km.binarcourse.rockpaperscissorsgame.ui.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import km.binarcourse.rockpaperscissorsgame.ui.game.GameActivity.Companion.TAG
import km.binarcourse.rockpaperscissorsgame.utils.Constants.DRAW
import km.binarcourse.rockpaperscissorsgame.utils.Constants.FIRST_PLAYER_WIN
import km.binarcourse.rockpaperscissorsgame.utils.Constants.PAPER
import km.binarcourse.rockpaperscissorsgame.utils.Constants.ROCK
import km.binarcourse.rockpaperscissorsgame.utils.Constants.SCISSORS
import km.binarcourse.rockpaperscissorsgame.utils.Constants.SECOND_PLAYER_WIN
import km.binarcourse.rockpaperscissorsgame.utils.Constants.UNSELECTED

class GameViewModel : ViewModel() {

    //Set all variables as a backing property.
    //A backing property allows you to return something from a getter other than the exact object
    //so that the value of the property only can be changed from this class

    private val _firstPlayerScore = MutableLiveData(0)
    val firstPlayerScore: LiveData<Int>
        get() = _firstPlayerScore

    private val _firstPlayerWeapon = MutableLiveData(UNSELECTED)
    val firstPlayerWeapon: LiveData<Int>
        get() = _firstPlayerWeapon

    private val _secondPlayerScore = MutableLiveData(0)
    val secondPlayerScore: LiveData<Int>
        get() = _secondPlayerScore

    private val _secondPlayerWeapon = MutableLiveData(UNSELECTED)
    val secondPlayerWeapon: LiveData<Int>
        get() = _secondPlayerWeapon

    private val _gameResult = MutableLiveData(UNSELECTED)
    val gameResult: LiveData<Int>
        get() = _gameResult

    private val _isGameFinished = MutableLiveData(false)
    val isGameFinished: LiveData<Boolean>
        get() = _isGameFinished

    private val _gameRound = MutableLiveData(0)
    val gameRound: LiveData<Int>
        get() = _gameRound

    /**
     * this method is used for checking the state of game and setting the first player's weapon.
     * when the state of game is finished then the users won't be able to select the weapon before
     * they click the restart game button.
     */
    fun setFirstPlayerWeapon(weapon: Int) {
        // if game is not finished then show log, set weapon, and call selectRandomComputerWeapon()
        if (!_isGameFinished.value!!) {
            _firstPlayerWeapon.value = weapon
            Log.d(TAG, "first player select ${getWeaponString(_firstPlayerWeapon.value)}")
            selectRandomComputerWeapon()
        }
    }

    /**
     * this method is used for random selecting a weapon for computer
     */
    private fun selectRandomComputerWeapon() {
        when ((0..2).random()) {
            ROCK -> _secondPlayerWeapon.value = ROCK
            PAPER -> _secondPlayerWeapon.value = PAPER
            SCISSORS -> _secondPlayerWeapon.value = SCISSORS
        }

        Log.d(TAG, "second player select ${getWeaponString(_secondPlayerWeapon.value)}")

        increaseTheWinnerScore(_firstPlayerWeapon.value, _secondPlayerWeapon.value)
        findTheWinner()
    }

    /**
     * this method is for finding the final winner of all games round
     * the winner is the first player who can get a 2 score.
     * when the winner has found then the value of variable _isGameFinished will be set to true
     */
    private fun findTheWinner(){
        if(_firstPlayerScore.value == 2 || _secondPlayerScore.value == 2){
            _isGameFinished.value = true
            _gameResult.value =
                if (_firstPlayerScore.value!! > _secondPlayerScore.value!!)
                    FIRST_PLAYER_WIN
                else
                    SECOND_PLAYER_WIN

            Log.d(TAG, "final result : ${getTheWinnerString(_gameResult.value)}")
        }
    }

    /**
     * this method is used for increasing the players scores when one of them win the game,
     * and also for calling increaseCurrentRound() method, so that the current games round
     * will be increased each time players fight until the game is finished
     */
    private fun increaseTheWinnerScore(firstPlayerWeapon: Int?, secondPlayerWeapon: Int?) {
        when {
            firstPlayerWeapon?.plus(1)?.mod(3) == secondPlayerWeapon -> {
                _secondPlayerScore.value = _secondPlayerScore.value?.plus(1)
                _gameResult.value = SECOND_PLAYER_WIN
            }
            firstPlayerWeapon == secondPlayerWeapon -> {
                _gameResult.value = DRAW
            }
            else -> {
                _firstPlayerScore.value = _firstPlayerScore.value?.plus(1)
                _gameResult.value = FIRST_PLAYER_WIN
            }
        }
        increaseCurrentRound()
        Log.d(TAG, "Round ${_gameRound.value} result : ${getTheWinnerString(_gameResult.value)}")
    }

    //increase current games round
    private fun increaseCurrentRound(){
        _gameRound.value = _gameRound.value?.plus(1)
    }

    /**
     * this method is used for resetting the game condition to the initial condition
     * - reset all player's scores to 0
     * - reset all player's weapons to be UNSELECTED
     * - reset game result to be UNSELECTED
     */
    fun restartGame() {
        _isGameFinished.value = false
        _gameRound.value = 0

        _firstPlayerWeapon.value = UNSELECTED
        _firstPlayerScore.value = 0

        _secondPlayerWeapon.value = UNSELECTED
        _secondPlayerScore.value = 0

        _gameResult.value = UNSELECTED
    }

    //method is used for logging
    private fun getWeaponString(weapon: Int?): String{
        return when(weapon){
            ROCK -> "Rock"
            PAPER -> "Paper"
            else -> "Scissors"
        }
    }

    //method is used for logging
    private fun getTheWinnerString(winner: Int?): String{
        return when(winner){
            FIRST_PLAYER_WIN -> "First Player Win"
            SECOND_PLAYER_WIN -> "Second Player Win"
            else -> "DRAW"
        }
    }
}