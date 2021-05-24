package com.skillbox.github.ui.repository_list

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class RepositoriesAdapter(
    onItemClick: (position: Int) -> Unit
) : AsyncListDifferDelegationAdapter<RemoteRepo>(RepoDiffUtilCallback()) {

    init {
        delegatesManager.addDelegate(RemoteRepoDelegate(onItemClick))
    }

    class RepoDiffUtilCallback : DiffUtil.ItemCallback<RemoteRepo>() {
        override fun areItemsTheSame(oldItem: RemoteRepo, newItem: RemoteRepo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: RemoteRepo, newItem: RemoteRepo): Boolean {
            return oldItem == newItem
        }
    }
}