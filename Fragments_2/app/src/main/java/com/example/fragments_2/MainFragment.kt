package com.example.fragments_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fragments_2.databinding.FragmentMainBinding
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.random.Random

class MainFragment : Fragment(R.layout.fragment_main), ItemSelectListener, DialogInterfaceListener {

    private val binding by viewBinding(FragmentMainBinding::bind)

    private val articles = listOf(
        ArticleModel(R.string.text_lokomotiv, R.drawable.loko, ),
        ArticleModel(R.string.text_sychev, R.drawable.sychev),
        ArticleModel(R.string.text_loskov, R.drawable.loskov),
        ArticleModel(R.string.text_miranchuk_al, R.drawable.miranchuk_al),
        ArticleModel(R.string.text_miranchuk_an, R.drawable.miranchuk_an),
        ArticleModel(R.string.text_barinov, R.drawable.barinov)
    )


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewPager.adapter = ArticleAdapter(articles, this)
        binding.dotsIndicator.setViewPager2(binding.viewPager)
        binding.viewPager.setPageTransformer { page, position ->
            DepthTransformation().transformPage(page, position)
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Инфо"
                1 -> tab.text = "Дмитрий\nСычев"
                2 -> tab.text = "Дмитрий\nЛоськов"
                3 -> tab.text = "Алексей\nМиранчук"
                4 -> tab.text = "Антон\nМиранчук"
                5 -> tab.text = "Дмитрий\nБаринов"
            }
        }.attach()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.getTabAt(position)?.removeBadge()
            }
        })

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_filter_by_teg -> {
                    tagsDialog()
                    true
                }
                else -> false
            }
        }
    }

    private fun tagsDialog() {
        TagsDialog(ArticleFragment.tags)
            .show(childFragmentManager, "TagsDialogTag")
    }

    override fun onItemSelected() {
        binding.tabLayout.getTabAt(Random.nextInt(6))?.orCreateBadge?.apply {
            number++
            badgeGravity = BadgeDrawable.BOTTOM_END
            backgroundColor = resources.getColor(R.color.dark_green)
        }
    }

    override fun onConfirm() {
        binding.viewPager.adapter = ArticleAdapter(
            articles.filter { articleModel ->
                articleModel == TagsDialog.tagsForFilter.forEach { it }
            },
            this
        )
    }

    override fun onReject() {
    }

}