package km.binarcourse.rockpaperscissorsgame.ui.bindingadapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import km.binarcourse.rockpaperscissorsgame.R
import km.binarcourse.rockpaperscissorsgame.utils.Constants.DRAW
import km.binarcourse.rockpaperscissorsgame.utils.Constants.FIRST_PLAYER_WIN
import km.binarcourse.rockpaperscissorsgame.utils.Constants.PAPER
import km.binarcourse.rockpaperscissorsgame.utils.Constants.ROCK
import km.binarcourse.rockpaperscissorsgame.utils.Constants.SECOND_PLAYER_WIN
import km.binarcourse.rockpaperscissorsgame.utils.Constants.UNSELECTED

@BindingAdapter("imgResource")
fun setResultImageResource(imageView: ImageView, gameResult: Int) {
    when (gameResult) {
        FIRST_PLAYER_WIN -> imageView.setImageResource(R.drawable.img_win)
        SECOND_PLAYER_WIN -> imageView.setImageResource(R.drawable.img_lose)
        DRAW -> imageView.setImageResource(R.drawable.img_draw)
        UNSELECTED -> imageView.setImageResource(0)
    }
}

@BindingAdapter("playerTurn", "gameState")
fun setTextTurnInfo(textView: TextView, isFirstPlayerTurn: Boolean, isGameFinished: Boolean) {
    textView.visibility = if (isGameFinished) View.INVISIBLE else View.VISIBLE
    textView.setText(
        if (isFirstPlayerTurn) R.string.text_first_player_turn else R.string.text_second_player_turn
    )
}

@BindingAdapter("selectedWeapon", "gameRoundState")
fun setImgSelectedWeapon(imgView: ImageView, weapon: Int, isGameRoundFinished: Boolean) {
    imgView.visibility = if (isGameRoundFinished) View.VISIBLE else View.INVISIBLE
    imgView.setImageResource(
        when (weapon) {
            ROCK -> R.drawable.ic_rock
            PAPER -> R.drawable.ic_paper
            else -> R.drawable.ic_scissors
        }
    )
}

@BindingAdapter("imageUrl")
fun loadImgFromUrl(imgView: ImageView, imgUrl: String?){
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(RequestOptions()
                .error(R.drawable.ic_broken_image)
                .placeholder(R.drawable.anim_loading))
            .into(imgView)
    }
}

@BindingAdapter("gameMode")
fun setAvaSecondPlayer(imgView: ImageView, isMultiPlayerMode: Boolean){
    imgView.setImageResource(
        if(isMultiPlayerMode) R.drawable.img_ava_player2 else R.drawable.img_ava_com
    )
}