package km.binarcourse.rockpaperscissorsgame.data.preference

import android.content.Context
import android.content.SharedPreferences

class PlayerPreference(context: Context) {
    private var preference = context.getSharedPreferences(PREF_NAME, PREF_MODE)

    companion object{
        private const val PREF_NAME = "RPS_GAME"
        private const val PREF_MODE = Context.MODE_PRIVATE
        private const val PREF_PLAYER_NAME = "PLAYER_NAME"
    }

    var playerName: String?
        get() = preference.getString(PREF_PLAYER_NAME, "")
        set(value) = preference.edit {
            it.putString(PREF_PLAYER_NAME, value)
        }

}

private inline fun SharedPreferences.edit( operation: (SharedPreferences.Editor) -> Unit){
    val editor = edit()
    operation(editor)
    editor.apply()
}