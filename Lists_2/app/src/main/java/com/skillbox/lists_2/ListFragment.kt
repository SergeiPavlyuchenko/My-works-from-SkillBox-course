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

class ListFragment : Fragment(R.layout.fragment_list), DialogInterfaceListener {

    private val binding by viewBinding(FragmentListBinding::bind)

    private var games: List<GameGenre> = emptyList()
    private var gamesAdapter: GamesAdapter by AutoClearedValue<GamesAdapter>()
    private lateinit var userLayoutManager: RecyclerView.LayoutManager
    private var dividerItemDecoration: DividerItemDecoration? = null
    private var itemOffsetDecoration: RecyclerView.ItemDecoration? = null
    private var isLastPage: Boolean = false
    private var isLoading: Boolean = false
    private var isPaginated: Boolean = false

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

            if (isPaginated) addOnScrollListener(getPaginationScrollListener(userLayoutManager))

            setHasFixedSize(true)
            itemAnimator = FlipInTopXAnimator()
        }

        val image: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.keep_it_clear)
        val keepClear: GameGenre = GameGenre.KeepClear(image = image)

        if (games.isEmpty() && !isPaginated) {
            gamesAdapter.items = listOf(keepClear)
        } else {
            gamesAdapter.items = games
        }
    }

    private fun getPaginationScrollListener(layoutManager: RecyclerView.LayoutManager): PaginationScrollListener {
        if (gamesAdapter.itemCount == 0) {
            gamesAdapter.items = gamesAdapter.items + AppData.randomGames.random()
        }
        return when (layoutManager) {
            is LinearLayoutManager -> object :
                PaginationScrollListener(layoutManager) {
                override fun isLastPage(): Boolean {
                    return isLastPage
                }

                override fun isLoading(): Boolean {
                    return isLoading
                }

                override fun loadMoreItems() {
                    if (gamesAdapter.itemCount <= 50) {
                        isLoading = true
                        getMoreData()
                    } else {
                        isLastPage = true
                        isLoading = false
                    }
                }
            }
            else -> error("Incorrect layoutManager")
        }
    }

    private fun getMoreData() {
//        rvAdapter.addData(list)
        val randomGames = List(5) { AppData.randomGames.random() }
        gamesAdapter.items = gamesAdapter.items + randomGames
        isLoading = false
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
            arguments?.getString(KEY_PAGINATION) == KEY_PAGINATION -> {
                userLayoutManager = LinearLayoutManager(requireContext())
                isPaginated = true
            }
        }
    }

    companion object {
        private const val KEY_GAMES = "Games"
        const val KEY_VERTICAL = "ListFragment with vertical layoutManager of RecyclerView"
        const val KEY_HORIZONTAL = "ListFragment with horizontal layoutManager of RecyclerView"
        const val KEY_GRID = "ListFragment with grid layoutManager of RecyclerView"
        const val KEY_STAGGER_GRID = "ListFragment with staggeredGrid layoutManager of RecyclerView"
        const val KEY_PAGINATION = "ListFragment with Pagination fun"

        fun newInstance(keyOfLayoutManager: String): ListFragment {
            return ListFragment().withArguments {
                putString(keyOfLayoutManager, keyOfLayoutManager)
            }
        }
    }
}