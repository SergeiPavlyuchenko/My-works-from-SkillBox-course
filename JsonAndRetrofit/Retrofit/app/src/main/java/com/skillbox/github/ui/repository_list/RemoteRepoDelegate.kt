package com.skillbox.github.ui.repository_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import com.skillbox.github.databinding.ItemRemoteRepoBinding

class RemoteRepoDelegate :
    AbsListItemAdapterDelegate<RemoteRepo, RemoteRepo, RemoteRepoDelegate.RemoteRepoHolder>() {


    override fun isForViewType(
        item: RemoteRepo,
        items: MutableList<RemoteRepo>,
        position: Int
    ): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): RemoteRepoHolder {
        return RemoteRepoHolder(
            ItemRemoteRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        item: RemoteRepo,
        holder: RemoteRepoHolder,
        payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }

    class RemoteRepoHolder(
       private val binding: ItemRemoteRepoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: RemoteRepo) {
            binding.nameRepoTextView.text = repo.name

            Glide.with(binding.root.context)
                .load("")
                .centerCrop()
                .into(binding.avatarImageView)
        }
    }

}