package com.skillbox.lists_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.lists_2.databinding.FragmentListBinding

class ListFragment : Fragment(R.layout.fragment_list), DialogInterfaceListener {

    private val binding by viewBinding(FragmentListBinding::bind)

    private var games: List<GameGenre> = emptyList()
    private var gamesAdapter: GamesAdapter by AutoClearedValue<GamesAdapter>()
    private lateinit var userLayoutManager: RecyclerView.LayoutManager


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_GAMES, StateGameGenres(games))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.getParcelable<StateGameGenres>(KEY_GAMES)?.let { games = it.gameGenres }
        layoutManagerManagement()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initList(userLayoutManager)
        binding.addFab.setOnClickListener { launchDialog() }
    }

    /* override fun onDestroyView() {
         super.onDestroyView()
         gamesAdapter = null
     }*/

    private fun launchDialog() {
        InputDataDialog().show(childFragmentManager, "Show input dialog")
    }

    private fun initList(userLayoutManager: RecyclerView.LayoutManager) {
        gamesAdapter = GamesAdapter { position -> deleteGame(position) }
        with(binding.gamesListRecyclerView) {
            adapter = gamesAdapter
            layoutManager = userLayoutManager
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

    private fun layoutManagerManagement() {
        when {
            arguments?.getString(KEY_VERTICAL) == KEY_VERTICAL -> userLayoutManager =
                LinearLayoutManager(requireContext())
            arguments?.getString(KEY_HORIZONTAL) == KEY_HORIZONTAL -> userLayoutManager =
                LinearLayoutManager(requireContext()).apply { orientation = LinearLayoutManager.HORIZONTAL }
            arguments?.getString(KEY_GRID) == KEY_GRID -> userLayoutManager =
                GridLayoutManager(requireContext(), 3)
            arguments?.getString(KEY_STAGGER_GRID) == KEY_STAGGER_GRID -> userLayoutManager =
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)
        }
    }

    companion object {
        private const val KEY_GAMES = "Games"
        const val KEY_VERTICAL = "ListFragment with vertical layoutManager of RecyclerView"
        const val KEY_HORIZONTAL = "ListFragment with horizontal layoutManager of RecyclerView"
        const val KEY_GRID = "ListFragment with grid layoutManager of RecyclerView"
        const val KEY_STAGGER_GRID = "ListFragment with staggeredGrid layoutManager of RecyclerView"

        fun newInstance(keyOfLayoutManager: String): ListFragment {
            return ListFragment().withArguments {
                putString(keyOfLayoutManager, keyOfLayoutManager)
            }
        }
    }
}