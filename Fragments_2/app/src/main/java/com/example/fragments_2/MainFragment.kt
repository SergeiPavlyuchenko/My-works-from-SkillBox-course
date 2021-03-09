package com.example.fragments_2

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fragments_2.databinding.FragmentMainBinding
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.random.Random

class MainFragment : Fragment(R.layout.fragment_main), ItemSelectListener, DialogInterfaceListener {

    private val binding by viewBinding(FragmentMainBinding::bind)

    private var selectedItems = BooleanArray(AppData.TAGS.size) { true }

    private var filteredArticles: List<ArticleTag> = AppData.TAGS.toList()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBooleanArray(TagsDialog.KEY_SELECTED, selectedItems)
        outState.putParcelable(KEY_ARTICLES, PositionState(filteredArticles))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        savedInstanceState?.getBooleanArray(TagsDialog.KEY_SELECTED)?.let { selectedItems = it }
        savedInstanceState?.getParcelable<PositionState>(KEY_ARTICLES)?.let { filteredArticles = it.positions }

        launchAdapter(AppData.ARTICLES)

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
        TagsDialog().newInstance(selectedItems).show(childFragmentManager, "TagsDialogTag")
    }

    override fun onItemSelected() {
        binding.tabLayout.getTabAt(Random.nextInt(6))?.orCreateBadge?.apply {
            number++
            badgeGravity = BadgeDrawable.BOTTOM_END
            backgroundColor = resources.getColor(R.color.dark_green)
        }
    }

    private fun launchAdapter(articles: List<ArticleModel>) {
        binding.viewPager.adapter = ArticleAdapter(
            articles,
            this
        )

        binding.dotsIndicator.setViewPager2(binding.viewPager)

        binding.viewPager.setPageTransformer { page, position ->
            DepthTransformation().transformPage(page, position)
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            var increment = 0
            articles.forEach {
                if (position == increment)
                    tab.text = it.name
                increment++
            }
            /*  when (position) {
                  0 -> tab.text = "Инфо"
                  1 -> tab.text = "Дмитрий\nСычев"
                  2 -> tab.text = "Дмитрий\nЛоськов"
                  3 -> tab.text = "Алексей\nМиранчук"
                  4 -> tab.text = "Антон\nМиранчук"
                  5 -> tab.text = "Дмитрий\nБаринов"
              }*/
        }.attach()
    }

    override fun onConfirm(selectedItems: BooleanArray, selectedTags: List<ArticleTag>) {
        this.selectedItems = selectedItems
        this.filteredArticles = selectedTags
        val articles: List<ArticleModel> = AppData.ARTICLES.filter { articleModel ->
            articleModel.position.positions.any { selectedTags.contains(it) }
        }
        launchAdapter(articles)
    }

    companion object {
        private const val KEY_ARTICLES = "filtered articles"
    }

}