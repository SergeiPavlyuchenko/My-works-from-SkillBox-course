package com.example.fragments_2

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ArticleAdapter(
    private val articles: List<ArticleModel>,
    fragment: Fragment
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return articles.size
    }

    override fun createFragment(position: Int): Fragment {
        val article = articles[position]
        return ArticleFragment.newInstance(
            article.text,
            article.image,
            article.position
        )
    }
}