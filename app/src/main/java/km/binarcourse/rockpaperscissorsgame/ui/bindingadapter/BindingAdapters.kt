package km.binarcourse.rockpaperscissorsgame.ui.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import km.binarcourse.rockpaperscissorsgame.R
import km.binarcourse.rockpaperscissorsgame.utils.Constants.DRAW
import km.binarcourse.rockpaperscissorsgame.utils.Constants.FIRST_PLAYER_WIN
import km.binarcourse.rockpaperscissorsgame.utils.Constants.SECOND_PLAYER_WIN
import km.binarcourse.rockpaperscissorsgame.utils.Constants.UNSELECTED

@BindingAdapter("imgResource")
fun setResultImageResource(imageView: ImageView, gameResult: Int){
    when(gameResult){
        FIRST_PLAYER_WIN -> imageView.setImageResource(R.drawable.img_win)
        SECOND_PLAYER_WIN -> imageView.setImageResource(R.drawable.img_lose)
        DRAW -> imageView.setImageResource(R.drawable.img_draw)
        UNSELECTED -> imageView.setImageResource(0)
    }
}