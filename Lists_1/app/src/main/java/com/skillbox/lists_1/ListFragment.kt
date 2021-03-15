package com.skillbox.lists_1

import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.skillbox.lists_1.databinding.FragmentListBinding

class ListFragment: Fragment(R.layout.fragment_list), DialogInterfaceListener {

    private val binding by viewBinding(FragmentListBinding::bind)

    private var games: List<GameGenre> = emptyList()
    private var gamesAdapter: GamesAdapter? = null

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_GAMES, StateGameGenres(games))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.getParcelable<StateGameGenres>(KEY_GAMES)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
        binding.addFab.setOnClickListener { launchDialog() }
    }

    private fun launchDialog() {
          InputDataDialog().show(childFragmentManager,"Show input dialog")
    }

    private fun initList() {
        gamesAdapter = GamesAdapter()
        with(binding.gamesListRecyclerView) {
            adapter = gamesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    override fun onConfirm(game: GameGenre) {
        games = listOf(game) + games
        gamesAdapter?.updateGames(games)
        gamesAdapter?.notifyItemInserted(0)
        binding.gamesListRecyclerView.scrollToPosition(0)
    }

    companion object {
        private const val KEY_GAMES = "Games"
    }
}