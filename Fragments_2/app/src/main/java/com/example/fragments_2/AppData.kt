package com.example.fragments_2

class AppData {
    companion object {
        val TAGS = ArticleTag.values()
        val ARTICLES = listOf(
            ArticleModel(
                R.string.text_lokomotiv,
                R.drawable.loko,
                PositionState(listOf(ArticleTag.INFO))
            ),
            ArticleModel(
                R.string.text_sychev,
                R.drawable.sychev,
                PositionState(listOf(ArticleTag.ST, ArticleTag.LW, ArticleTag.RW))
            ),
            ArticleModel(
                R.string.text_loskov,
                R.drawable.loskov,
                PositionState(listOf(ArticleTag.CAM, ArticleTag.RM))
            ),
            ArticleModel(
                R.string.text_miranchuk_al,
                R.drawable.miranchuk_al,
                PositionState(listOf(ArticleTag.CAM, ArticleTag.RM, ArticleTag.RW, ArticleTag.ST))
            ),
            ArticleModel(
                R.string.text_miranchuk_an,
                R.drawable.miranchuk_an,
                PositionState(listOf(ArticleTag.LM, ArticleTag.LW, ArticleTag.CAM, ArticleTag.RM))
            ),
            ArticleModel(
                R.string.text_barinov,
                R.drawable.barinov,
                PositionState(listOf(ArticleTag.CDM, ArticleTag.CB))
            )
        )
    }
}