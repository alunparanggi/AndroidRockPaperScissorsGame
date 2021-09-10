package km.binarcourse.rockpaperscissorsgame

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import km.binarcourse.rockpaperscissorsgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}