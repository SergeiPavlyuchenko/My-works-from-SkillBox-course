package com.example.workwith_viewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.workwith_viewpager.databinding.ActivityTabsBinding
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayoutMediator

class TabsActivity : AppCompatActivity(R.layout.activity_tabs) {

    private val binding by viewBinding(ActivityTabsBinding::bind)

    private val screens: List<OnboardingScreen> = listOf(
        OnboardingScreen(
            textRes = R.string.text_1,
            bgColorRes = R.color.color_1,
            drawableRes = R.drawable.drawable_1
        ),
        OnboardingScreen(
            textRes = R.string.text_2,
            bgColorRes = R.color.color_2,
            drawableRes = R.drawable.drawable_2
        ),
        OnboardingScreen(
            textRes = R.string.text_3,
            bgColorRes = R.color.color_3,
            drawableRes = R.drawable.drawable_3
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabs)

        val adapter = OnboardingAdapter(screens + screens, this)
        binding.viewPager.adapter = adapter

        //Используется для связки TabLayout и ViewPager
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            //Устанавливаем текст
            tab.text = "Tab ${position + 1}"
            //Устанавливаем иконку
            if (position % 2 == 0) tab.setIcon(R.drawable.ic_beach_access)
        }.attach()

        //Используем бейджики
        binding.tabLayout.getTabAt(1)?.orCreateBadge?.apply {
            number = 2
            badgeGravity = BadgeDrawable.TOP_END
        }

        //Этот метод сначала определяет какая страница открыта, потом проверяет есть ли там бейдж
        //И если есть - удаляет его
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.getTabAt(position)?.removeBadge()
            }
        })
    }
}