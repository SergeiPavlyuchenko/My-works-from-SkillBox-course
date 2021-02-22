package com.example.fragments_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.example.fragments_2.databinding.FragmentArticleBinding


class ArticleFragment : Fragment(R.layout.fragment_article) {
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    private val fragment: Fragment?
        get() = parentFragment

    private val itemSelectListener: ItemSelectListener?
        get() = fragment?.let { it as? ItemSelectListener }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.textViewArticle.setText(requireArguments().getInt(KEY_TEXT))
        binding.imageViewArticle.setImageResource(requireArguments().getInt(KEY_IMAGE))
        val position = requireArguments().getParcelable<PositionState>(KEY_POSITION)?.positions
        binding.createBadgeButton.setOnClickListener { itemSelectListener?.onItemSelected() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {

        private const val KEY_TEXT = "article text"
        private const val KEY_IMAGE = "article image"
        private const val KEY_POSITION = "article position on the field"


        val tags: List<ArticleTag> = listOf(
            ArticleTag.INFO,
            ArticleTag.GK,
            ArticleTag.LB, ArticleTag.CB, ArticleTag.RB,
            ArticleTag.LM, ArticleTag.CDM, ArticleTag.CAM, ArticleTag.RM,
            ArticleTag.LW, ArticleTag.RW, ArticleTag.ST
        )

        fun newInstance(
            @StringRes text: Int,
            @DrawableRes image: Int,
            position: PositionState
        ): ArticleFragment {
            return ArticleFragment().withArguments {
                putInt(KEY_TEXT, text)
                putInt(KEY_IMAGE, image)
                putParcelable(KEY_POSITION, position)
            }
        }
    }


}