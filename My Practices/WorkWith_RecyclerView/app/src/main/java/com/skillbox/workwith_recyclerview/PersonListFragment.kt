package com.skillbox.workwith_recyclerview

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.workwith_recyclerview.databinding.FragmentUserListBinding

class PersonListFragment : Fragment(R.layout.fragment_user_list) {

    private val binding by viewBinding(FragmentUserListBinding::bind)

    private lateinit var persons: List<Person>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        persons = listOf(
            Person.Developer(resources.getString(R.string.text_tyler),
                "https://cspromogame.ru//storage/upload_images/avatars/899.jpg",
                31,
                "Kotlin"
            ),
            Person.Developer(
                resources.getString(R.string.text_shepp),
                "https://cspromogame.ru//storage/upload_images/avatars/2012.jpg",
                30,
                "Java"
            ),
            Person.User(
                resources.getString(R.string.text_e0212),
                "https://cspromogame.ru//storage/upload_images/avatars/3975.jpg",
                31
            ),
            Person.User(
                resources.getString(R.string.text_rez),
                "https://cspromogame.ru//storage/upload_images/avatars/891.jpg",
                37
            ),
        )
    }

    private var personAdapter: PersonAdapter? = null

    override fun onDestroyView() {
        super.onDestroyView()
        personAdapter = null
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
        binding.addFab.setOnClickListener { addPerson() }
        personAdapter?.updatePersons(persons)
//        userAdapter?.notifyDataSetChanged()
        personAdapter?.notifyItemRangeInserted(0, persons.size)
    }

    private fun addPerson() {
        val newUser = persons.random()
        persons = listOf(newUser) + persons
        personAdapter?.updatePersons(persons)
        personAdapter?.notifyItemInserted(0)
        binding.userList.scrollToPosition(0)
    }

    private fun deletePerson(position: Int) {
        persons = persons.filterIndexed { index, _ -> index != position }
        personAdapter?.updatePersons(persons)
        personAdapter?.notifyItemRemoved(position)
    }

    private fun initList() {
        personAdapter = PersonAdapter { position -> deletePerson(position) }
        with(binding.userList) {
            adapter = personAdapter
            layoutManager = LinearLayoutManager(this.context)
            setHasFixedSize(true)
        }
    }

}