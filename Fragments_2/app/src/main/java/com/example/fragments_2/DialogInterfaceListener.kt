package com.example.fragments_2

interface DialogInterfaceListener {
    fun onConfirm(selectedItems: BooleanArray, selectedTags: List<ArticleTag>)
}