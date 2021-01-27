package com.example.toolbarproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import com.skillbox.toolbarproject.R
import com.skillbox.toolbarproject.databinding.ActivityMainBinding


private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        Glide.with(this)
//                .load("https://photoplay.ru/sites/default/files/imce/2019/travel-photo_dk.jpg")
//                .centerCrop()
//                .into()


        initToolbar()

    }




    private fun showSum() {
        binding.textBorodino.visibility = View.GONE //Почему-то не срабатывают эти строки
        binding.textViewForSum.apply {
            isVisible = true
            text = "Sum = ${(5 + 10)}"
        }
        Handler().postDelayed({
            binding.textViewForSum.text = "Good Bye!"
            Handler().postDelayed({
                binding.textViewForSum.visibility = View.GONE
                binding.textBorodino.visibility = View.VISIBLE
            }, 2000)
        }, 2000)
    }

    private fun changeTitle() {
        binding.toolbar.title =
            if (binding.toolbar.title == resources.getString(R.string.app_name)) {
                resources.getString(R.string.app_name_optional)
            } else resources.getString(R.string.app_name)
    }

    private fun initToolbar() {

        // setNavigationOnClickListener - слушает кнопку навигации(в данном случае стрелка назад)
        binding.toolbar.setNavigationOnClickListener {
            Toast.makeText(this, "You are clicked navigation button", Toast.LENGTH_SHORT).show()
        }

        // setOnMenuItemClickListener - слушает какую кнопку меню мы нажимаем(нужно вернуть true - означает, что нажал)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_changeLabel -> {
                    changeTitle()
                    true
                }
                R.id.action_showToast -> {
                    Toast.makeText(
                        this,
                        "Current title: ${binding.toolbar.title}",
                        Toast.LENGTH_SHORT
                    ).show()
                    true
                }
                R.id.action_showFiveAndTenSum -> {
                    showSum()
                    true
                }
                else -> false
            }
        }

        //.menu.findItem(R.id.action_search) присваиваем переменной действие из меню
        val searchItem = binding.toolbar.menu.findItem(R.id.action_search)
        // setOnActionExpandListener - принимает объект MenuItem
        // а обе эти функции обрабатывают событие открытие/ закрытие раскрывающейся иконки списка
         searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
             override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                 binding.searchResultTextView.visibility = View.VISIBLE
                 return true
             }

             override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                 binding.searchResultTextView.visibility = View.GONE
                 return true
             }
         })

        //мы знаем что actionView у searchItem'a androidx.appcompat.widget.SearchView
        //потому что мы сами установили это в menu_toolbar.xml -
        // app:actionViewClass="androidx.appcompat.widget.SearchView" поэтому нам необходимо её закастить
        // как SearchView из библиотеки appCompat
        //
        // setOnQueryTextListener - это листенер пользовательского ввода для SearchView
        (searchItem.actionView as SearchView).setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                //onQueryTextSubmit - обрабатывает нажатие на кнопку лупы после ввода текста
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

                //onQueryTextChange - обрабатывает событие прямо во время пользовательского ввода
            override fun onQueryTextChange(newText: String?): Boolean {
                    resources.getString(R.string.text_borodino)
                        .split(" ")
                        .filter { it.contains(newText ?: "", true) }
                        .joinToString("\n")
                        .let { binding.searchResultTextView.text = it }
                    return true
                }
        })


    }
}
