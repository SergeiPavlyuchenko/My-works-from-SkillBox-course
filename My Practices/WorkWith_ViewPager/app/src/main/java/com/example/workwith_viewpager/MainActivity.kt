package com.example.workwith_viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.workwith_viewpager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding(ActivityMainBinding::bind)

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
        setContentView(R.layout.activity_main)

        val adapter = OnboardingAdapter(screens, this)
        binding.viewPager.adapter = adapter
        //сколько страниц предзагружать от текущей
        binding.viewPager.offscreenPageLimit = 1

//        Перелистнуть программно на позицию 1, плавно
//        Но раз запускаем эту функцию в onCreate, то получим данную страницу по умолчанию
//        при запуске приложения
        binding.viewPager.setCurrentItem(1, true)
        //Получить текущую позицию
        binding.viewPager.currentItem


        //Направление скрола, по умолчанию - горизонтально
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        //Отследить, что пользователь перелистнул страницу
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            //Вызывается когда пользователь перелистнул страницу на следующую
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Toast.makeText(
                    this@MainActivity,
                    "Select page = ${position + 1}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        //
        binding.viewPager.setPageTransformer { page, position ->
            when {
                position < -1 || position > 1 -> page.alpha = 0.4f
                position <= 0 -> page.alpha = 1.4f + position
                position <= 1 -> page.alpha = 1.4f - position
            }
        }
    }
}