package km.binarcourse.rockpaperscissorsgame.ui.form

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import km.binarcourse.rockpaperscissorsgame.data.preference.PlayerPreference
import km.binarcourse.rockpaperscissorsgame.databinding.FragmentFormBinding
import km.binarcourse.rockpaperscissorsgame.ui.menu.MenuActivity

class FormFragment : Fragment() {

    private lateinit var binding: FragmentFormBinding
    private var sharedPref: PlayerPreference? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFormBinding.inflate(inflater, container, false)
        binding.btnPlay.setOnClickListener { onPlayBtnClicked(requireContext()) }
        return binding.root
    }

    private fun onPlayBtnClicked(context: Context) {
        if (binding.etPlayerName.text.isNullOrEmpty()) {
            binding.etPlayerName.error = "You can't let it empty"
        } else {
            sharedPref?.playerName = binding.etPlayerName.text.toString()
            navigateToMenu(context)
        }
    }

    private fun navigateToMenu(context: Context) {
        val intent = Intent(context, MenuActivity::class.java)
        context.startActivity(intent)
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context) =
            FormFragment().apply {
                sharedPref = PlayerPreference(context)
//                onPlayBtnClicked(context)
            }
    }

}