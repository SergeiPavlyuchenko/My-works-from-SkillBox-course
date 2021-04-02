package com.skillbox.lists_2

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.lists_2.databinding.FragmentListBinding

class ListFragment : Fragment(R.layout.fragment_list), DialogInterfaceListener {

    private val binding by viewBinding(FragmentListBinding::bind)

    private var games: List<GameGenre> = emptyList()
    private var gamesAdapter: GamesAdapter by AutoClearedValue<GamesAdapter>()



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_GAMES, StateGameGenres(games))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.getParcelable<StateGameGenres>(KEY_GAMES)?.let { games = it.gameGenres }
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList()
        binding.addFab.setOnClickListener { launchDialog() }
    }

   /* override fun onDestroyView() {
        super.onDestroyView()
        gamesAdapter = null
    }*/

    private fun launchDialog() {
        InputDataDialog().show(childFragmentManager, "Show input dialog")
    }

    private fun initList() {
        gamesAdapter = GamesAdapter { position -> deleteGame(position) }
        with(binding.gamesListRecyclerView) {
            adapter = gamesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
        gamesAdapter.updateGames(games, requireContext())
        gamesAdapter.notifyItemInserted(0)
    }


    private fun deleteGame(position: Int) {
        games = games.filterIndexed { index, _ -> index != position }
        gamesAdapter.updateGames(games, requireContext())
        gamesAdapter.notifyItemRemoved(position)

    }

    override fun onConfirm(game: GameGenre) {
        games = listOf(game) + games
        gamesAdapter.updateGames(games, requireContext())
        gamesAdapter.notifyItemInserted(0)
        binding.gamesListRecyclerView.scrollToPosition(0)
    }

    companion object {
        private const val KEY_GAMES = "Games"
    }
}