package com.example.fragments_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fragments_2.databinding.FragmentMainBinding

class MainFragment: Fragment(R.layout.fragment_main) {

    private val binding by viewBinding (FragmentMainBinding::bind)

    private val articles = listOf(
            ArticleModel(R.string.text_lokomotiv, R.drawable.loko),
            ArticleModel(R.string.text_sychev, R.drawable.sychev),
            ArticleModel(R.string.text_loskov, R.drawable.loskov),
            ArticleModel(R.string.text_miranchuk_al, R.drawable.miranchuk_al),
            ArticleModel(R.string.text_miranchuk_an, R.drawable.miranchuk_an),
            ArticleModel(R.string.text_barinov, R.drawable.barinov)
    )



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPagerMainFragment.adapter = ArticleAdapter(articles, this)

    }
}