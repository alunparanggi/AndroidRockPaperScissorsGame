package km.binarcourse.rockpaperscissorsgame.ui.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import km.binarcourse.rockpaperscissorsgame.databinding.FragmentIntroBinding

class IntroFragment : Fragment() {

    private lateinit var binding: FragmentIntroBinding

    private var descIntro: String? = null
    private var imgResIntro: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            descIntro = it.getString(ARG_DESCRIPTION)
            imgResIntro = it.getInt(ARG_IMG_RESOURCE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntroBinding.inflate(inflater, container, false)
        binding.ivIntro.setImageResource(imgResIntro?:0)
        binding.tvIntro.text = descIntro

        return binding.root
    }

    companion object {

        const val ARG_DESCRIPTION = "DESCRIPTION"
        const val ARG_IMG_RESOURCE = "IMG_RESOURCE"

        @JvmStatic
        fun newInstance(desc: String, imgRes: Int) =
            IntroFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_DESCRIPTION, desc)
                    putInt(ARG_IMG_RESOURCE, imgRes)
                }
            }
    }
}