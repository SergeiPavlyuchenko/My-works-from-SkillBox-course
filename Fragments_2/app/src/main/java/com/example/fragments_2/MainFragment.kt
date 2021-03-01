package com.example.fragments_2

import android.os.Bundle
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

    private lateinit var checkedTagsMap: Map<ArticleTag, Boolean>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.getBooleanArray(TagsDialog.KEY_SELECTED)?.let { selectedItems = it }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBooleanArray(TagsDialog.KEY_SELECTED, selectedItems)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val items = selectedItems.toTypedArray()
        val result = AppData.TAGS.zip(items)


        checkedTagsMap = result.toMap()
        Log.w("+++++", "Items: $items\nResult: $result")

        TagsDialog().arguments?.getBooleanArray(TagsDialog.KEY_SELECTED)?.let { selectedItems = it }

        checkedTagsMap = AppData.TAGS.zip(selectedItems.toTypedArray()).toMap()

        /*val tempMap: MutableMap<ArticleTag, Boolean> = mutableMapOf()
        AppData.TAGS.forEach { tag ->
            selectedItems.forEach { item ->
                tempMap += tag to item
            }
        }
        checkedTagsMap = tempMap*/


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
            when (position) {
                0 -> tab.text = "Инфо"
                1 -> tab.text = "Дмитрий\nСычев"
                2 -> tab.text = "Дмитрий\nЛоськов"
                3 -> tab.text = "Алексей\nМиранчук"
                4 -> tab.text = "Антон\nМиранчук"
                5 -> tab.text = "Дмитрий\nБаринов"
            }
        }.attach()
    }

    override fun onConfirm() {
        val checkedArticles: List<ArticleTag> = checkedTagsMap.filterValues { it  }.keys.toList()
        val articles: List<ArticleModel> = AppData.ARTICLES.filter { articleModel ->
            articleModel.position.positions.any { checkedArticles.contains(it) }
        }
        launchAdapter(articles)
    }
}