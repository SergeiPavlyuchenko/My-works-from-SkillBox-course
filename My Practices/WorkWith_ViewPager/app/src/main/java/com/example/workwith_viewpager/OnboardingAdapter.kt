package com.example.workwith_viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardingAdapter(
    private val screens: List<OnboardingScreen>,
    activity: FragmentActivity
) : FragmentStateAdapter(activity) {


    //Сколько будет экранов у viewPager
    override fun getItemCount(): Int {
        return screens.size
    }

    //Создаёт фрагмент по определённой позиции
    override fun createFragment(position: Int): Fragment {
        val screen: OnboardingScreen = screens[position]
        return OnboardingFragment.newInstance(
            textRes = screen.textRes,
            bgColorRes = screen.bgColorRes,
            drawableRes = screen.drawableRes
        )
    }
}