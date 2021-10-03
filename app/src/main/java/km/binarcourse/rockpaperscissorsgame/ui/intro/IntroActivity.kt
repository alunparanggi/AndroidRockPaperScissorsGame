package km.binarcourse.rockpaperscissorsgame.ui.intro

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import km.binarcourse.rockpaperscissorsgame.R
import km.binarcourse.rockpaperscissorsgame.databinding.ActivityIntroBinding
import km.binarcourse.rockpaperscissorsgame.ui.form.FormFragment

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        initViewPager()
    }

    private fun initViewPager() {
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPagerAdapter.addFragment(IntroFragment.newInstance(
            getString(R.string.text_desc_intro_play_with_friend),
            R.drawable.img_win)
        )
        viewPagerAdapter.addFragment(IntroFragment.newInstance(
            getString(R.string.text_desc_intro_play_with_com),
            R.drawable.img_lose)
        )
        viewPagerAdapter.addFragment(FormFragment.newInstance(this))

        binding.vpIntro.adapter = viewPagerAdapter

        binding.vpIntro.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                when {
                    position == 0 -> {
                        binding.ivNext.visibility = View.VISIBLE
                        binding.ivNext.isEnabled = true
                        binding.ivPrevious.visibility = View.INVISIBLE
                        binding.ivPrevious.isEnabled = false
                    }
                    position < viewPagerAdapter.itemCount - 1 -> {
                        binding.ivNext.visibility = View.VISIBLE
                        binding.ivNext.isEnabled = true
                        binding.ivPrevious.visibility = View.VISIBLE
                        binding.ivPrevious.isEnabled = true
                    }
                    position == viewPagerAdapter.itemCount - 1 -> {
                        binding.ivNext.visibility = View.INVISIBLE
                        binding.ivNext.isEnabled = true
                        binding.ivPrevious.visibility = View.VISIBLE
                        binding.ivPrevious.isEnabled = true
                    }
                }
                super.onPageSelected(position)
            }
        })

        binding.dotsIndicator.setViewPager2(binding.vpIntro)

        binding.ivNext.setOnClickListener { onIntroNextClicked() }

        binding.ivPrevious.setOnClickListener { onIntroPrevClicked() }
    }

    private fun onIntroPrevClicked() {
        if (getPreviousIndex() != -1) {
            binding.vpIntro.setCurrentItem(getPreviousIndex(), true)
        }
    }

    private fun onIntroNextClicked() {
        if (getNextIndex() != -1) {
            binding.vpIntro.setCurrentItem(getNextIndex(), true)
        }
    }

    private fun getPreviousIndex(): Int {
        val currentPageIdx = binding.vpIntro.currentItem
        return if (currentPageIdx - 1 >= 0) {
            currentPageIdx - 1
        } else {
            -1
        }
    }

    private fun getNextIndex(): Int {
        val maxPages = binding.vpIntro.adapter?.itemCount
        val currentPageIdx = binding.vpIntro.currentItem
        var selectedIdx = -1
        maxPages?.let {
            if (currentPageIdx + 1 < maxPages) {
                selectedIdx = currentPageIdx + 1
            }
        }
        return selectedIdx
    }

}