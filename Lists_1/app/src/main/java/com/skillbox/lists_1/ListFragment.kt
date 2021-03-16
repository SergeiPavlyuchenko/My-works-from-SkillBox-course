package com.skillbox.lists_1

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.lists_1.databinding.FragmentListBinding

class ListFragment : Fragment(R.layout.fragment_list), DialogInterfaceListener {

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

    override fun onDestroyView() {
        super.onDestroyView()
        gamesAdapter = null
    }

    private fun launchDialog() {
        InputDataDialog().show(childFragmentManager, "Show input dialog")
    }

    private fun initList() {
        gamesAdapter = GamesAdapter { position -> deleteGame(position) }
        with(binding.gamesListRecyclerView) {
            adapter = gamesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
           /* if (adapter?.itemCount == 0) {
                val image: Drawable = ContextCompat.getDrawable(requireContext(), R.drawable.keep_it_clear)!!
                val keepClear: GameGenre = GameGenre.KeepClear(image = image)
                gamesAdapter?.updateGames(listOf(keepClear))
                gamesAdapter?.notifyItemInserted(0)
            }*/
        }
    }


    private fun deleteGame(position: Int) {
        games = games.filterIndexed { index, _ -> index != position }
        gamesAdapter?.updateGames(games)
        gamesAdapter?.notifyItemRemoved(position)
    }

    override fun onConfirm(game: GameGenre) {
        /*val keepClear = games[0].let { it as? GameGenre.KeepClear }
        if (games[0] == keepClear) games = emptyList()*/
        games = listOf(game) + games
        gamesAdapter?.updateGames(games)
        gamesAdapter?.notifyItemInserted(0)
        binding.gamesListRecyclerView.scrollToPosition(0)
    }

    companion object {
        private const val KEY_GAMES = "Games"
    }
}