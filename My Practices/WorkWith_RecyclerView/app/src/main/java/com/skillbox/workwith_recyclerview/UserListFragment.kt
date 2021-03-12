package com.skillbox.workwith_recyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.workwith_recyclerview.databinding.FragmentUserListBinding

class UserListFragment: Fragment(R.layout.fragment_user_list) {

    private val binding by viewBinding(FragmentUserListBinding::bind)

    private lateinit var users: List<User>

    private var userAdapter: UserAdapter? = null

    override fun onDestroyView() {
        super.onDestroyView()
        userAdapter = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        users = listOf(
            User (resources.getString(R.string.text_tyler),
                "https://cspromogame.ru//storage/upload_images/avatars/899.jpg",
                31,
                true),
            User (resources.getString(R.string.text_shepp),
                "https://cspromogame.ru//storage/upload_images/avatars/2012.jpg",
                30,
                true),
            User (resources.getString(R.string.text_e0212),
                "https://cspromogame.ru//storage/upload_images/avatars/3975.jpg",
                31,
                false),
            User (resources.getString(R.string.text_rez),
                "https://cspromogame.ru//storage/upload_images/avatars/891.jpg",
                37,
                false),
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
        binding.addFab.setOnClickListener { addUser() }
        userAdapter?.updateUsers(users)
//        userAdapter?.notifyDataSetChanged()
        userAdapter?.notifyItemRangeInserted(0,users.size)
    }

    private fun addUser() {
        val newUser = users.random()
        users = listOf(newUser) + users
        userAdapter?.updateUsers(users)
        userAdapter?.notifyItemInserted(0)
        binding.userList.scrollToPosition(0)
    }

    private fun deleteUser(position: Int) {
        users = users.filterIndexed { index, _ -> index != position  }
        userAdapter?.updateUsers(users)
        userAdapter?.notifyItemRemoved(position)
    }

    private fun initList() {
        userAdapter = UserAdapter { position -> deleteUser(position) }
        with(binding.userList) {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this.context)
            setHasFixedSize(true)
        }
    }

}