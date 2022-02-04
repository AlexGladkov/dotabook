package com.agladkov.dotabook.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.compose.rememberImagePainter
import com.agladkov.core.remote.HeroesProvider
import com.agladkov.core.remote.implementations.HeroesProviderImpl
import com.agladkov.domain.converters.HeroesConverter
import com.agladkov.domain.converters.HeroesConverterImpl
import com.agladkov.domain.implementations.CarryRepositoryImpl
import com.agladkov.domain.models.Hero

import com.agladkov.dotabook.R
import com.agladkov.dotabook.di.App
import com.agladkov.dotabook.helpers.Keys
import com.agladkov.dotabook.helpers.State
import com.agladkov.dotabook.viewmodels.CarryViewModel
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class CarryFragment : Fragment() {
    private val heroesConverter: HeroesConverter = HeroesConverterImpl()
    private val heroesProvider: HeroesProvider = HeroesProviderImpl()
    private val viewModel = CarryViewModel(
        repository = CarryRepositoryImpl(
            heroesConverter = heroesConverter,
            roomAppDatabase = App.roomAppDatabase, heroesProvider = heroesProvider
        )
    )
    private lateinit var recyclerCarries: ComposeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
    }

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(context = requireContext()).apply {
        setContent {
            CarryScreen(viewModel = viewModel, findNavController())
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun CarryScreen(
    viewModel: CarryViewModel,
    navController: NavController
) {
    val viewState = viewModel.state.observeAsState()

    when (val state = viewState.value) {
        is State.LoadedState<*> -> HeroesListView(state.data) {
            val bundle = Bundle()
            bundle.putParcelable(Keys.Hero.title, it)
             navController.navigate(R.id.carryAntipickFragment, bundle)
        }
        is State.NoItemsState -> NoHeroesView()
        is State.LoadingState -> LoadingHeroesView()
        is State.ErrorState -> NoHeroesView()
    }
}

@Composable
fun NoHeroesView() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center), text = "No heroes found",
            color = Color.Black, fontWeight = FontWeight.Medium, fontSize = 16.sp
        )
    }
}

@Composable
fun LoadingHeroesView() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = Color.Green
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun HeroesListView(data: List<Any?>, onHeroClick: (Hero) -> Unit) {
    val heroes = data.mapNotNull { it as? Hero }

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        cells = GridCells.Fixed(count = 4),
        content = {
            heroes.forEach {
                item {
                    Box(modifier = Modifier
                        .clickable {
                            onHeroClick.invoke(it)
                        }
                        .height(60.dp)) {
                        Image(
                            painter = rememberImagePainter(it.avatar),
                            contentDescription = it.name
                        )
                    }
                }
            }
        })
}
