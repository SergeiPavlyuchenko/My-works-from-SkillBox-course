package com.skillbox.lists_2

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.*
import by.kirich1409.viewbindingdelegate.viewBinding
import com.skillbox.lists_2.adapters.GamesAdapter
import com.skillbox.lists_2.databinding.FragmentListBinding
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator
import java.time.ZoneOffset

class ListFragment : Fragment(R.layout.fragment_list), DialogInterfaceListener {

    private val binding by viewBinding(FragmentListBinding::bind)

    private var games: List<GameGenre> = emptyList()
    private var gamesAdapter: GamesAdapter by AutoClearedValue<GamesAdapter>()
    private lateinit var userLayoutManager: RecyclerView.LayoutManager
    private var dividerItemDecoration: DividerItemDecoration? = null
    private var itemOffsetDecoration: RecyclerView.ItemDecoration? = null
    private val randomGames = List(10) { AppData.randomGames.random() }

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
        games = randomGames
        initList(userLayoutManager, dividerItemDecoration, itemOffsetDecoration)
        binding.addFab.setOnClickListener { launchDialog() }
    }

    /* override fun onDestroyView() {
         super.onDestroyView()
         gamesAdapter = null
     }*/

    private fun launchDialog() {
        InputDataDialog().show(childFragmentManager, "Show input dialog")
    }

    private fun initList(
        userLayoutManager: RecyclerView.LayoutManager,
        dividerItemDecoration: DividerItemDecoration?,
        itemOffsetDecoration: RecyclerView.ItemDecoration?
    ) {
        gamesAdapter = GamesAdapter { position -> deleteGame(position) }
        with(binding.gamesListRecyclerView) {
            dividerItemDecoration?.let { addItemDecoration(it) }
            itemOffsetDecoration?.let { addItemDecoration(it) }
            adapter = gamesAdapter
            layoutManager = userLayoutManager

            addOnScrollListener(endLessViewScroll(userLayoutManager))

            setHasFixedSize(true)
            itemAnimator = FlipInTopXAnimator()
        }

        val image: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.keep_it_clear)
        val keepClear: GameGenre = GameGenre.KeepClear(image = image)

        if (games.isEmpty()) {
            gamesAdapter.items = listOf(keepClear)
        } else {
            gamesAdapter.items = games
        }
    }

    private fun endLessViewScroll(layoutManager: RecyclerView.LayoutManager): EndlessRecyclerViewScrollListener {
        return when (layoutManager) {
            is LinearLayoutManager -> object :
                EndlessRecyclerViewScrollListener(LinearLayoutManager(requireContext())) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    gamesAdapter.items = gamesAdapter.items + randomGames
                }
            }
            is GridLayoutManager -> object :
                EndlessRecyclerViewScrollListener(GridLayoutManager(requireContext(), 5)) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    gamesAdapter.items = gamesAdapter.items + randomGames
                }
            }
            is StaggeredGridLayoutManager -> object : EndlessRecyclerViewScrollListener(
                StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL)) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    gamesAdapter.items = gamesAdapter.items + randomGames
                }
            }
            else -> error("Incorrect layoutManager")
        }
    }


    private fun deleteGame(position: Int) {
        games = games.filterIndexed { index, _ -> index != position }
        val image: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.keep_it_clear)
        val keepClear: GameGenre = GameGenre.KeepClear(image = image)

        if (games.isEmpty()) {
            gamesAdapter.items = listOf(keepClear)
        } else {
            gamesAdapter.items = games
        }

    }

    override fun onConfirm(game: GameGenre) {
        games = listOf(game) + games
        gamesAdapter.items = games
        binding.gamesListRecyclerView.scrollToPosition(0)
    }

    private fun layoutManagerManagement() {
        when {
            arguments?.getString(KEY_VERTICAL) == KEY_VERTICAL -> {
                userLayoutManager = LinearLayoutManager(requireContext())
                dividerItemDecoration =
                    DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            }
            arguments?.getString(KEY_HORIZONTAL) == KEY_HORIZONTAL -> userLayoutManager =
                LinearLayoutManager(requireContext()).apply {
                    orientation = LinearLayoutManager.HORIZONTAL
                }
            arguments?.getString(KEY_GRID) == KEY_GRID -> userLayoutManager =
                GridLayoutManager(requireContext(), 3).apply {
                    itemOffsetDecoration = ItemOffsetDecoration(requireContext())
                    spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                        override fun getSpanSize(position: Int): Int {
                            return if (position % 3 == 0) 2 else 1
                        }
                    }
                }
            arguments?.getString(KEY_STAGGER_GRID) == KEY_STAGGER_GRID -> userLayoutManager =
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
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