package com.example.fragments_2

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fragments_2.databinding.FragmentArticleBinding

class ArticleFragment : Fragment(R.layout.fragment_article) {
    private val binding by viewBinding(FragmentArticleBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.textViewArticle.setText(requireArguments().getInt(KEY_TEXT))
        binding.imageViewArticle.setImageResource(requireArguments().getInt(KEY_IMAGE))
    }

    companion object {

        private const val KEY_TEXT = "article text"
        private const val KEY_IMAGE = "article image"


        fun newInstance(
                @StringRes text: Int,
                @DrawableRes image: Int
        ): ArticleFragment {
            return ArticleFragment().withArguments {
                putInt(KEY_TEXT, text)
                putInt(KEY_IMAGE, image)
            }
        }
    }
}