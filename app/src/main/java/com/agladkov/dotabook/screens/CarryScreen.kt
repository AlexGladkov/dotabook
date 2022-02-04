package com.agladkov.dotabook.screens

import android.os.Bundle
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.agladkov.dotabook.domain.models.Hero
import com.agladkov.dotabook.R
import com.agladkov.dotabook.helpers.State
import com.agladkov.dotabook.viewmodels.CarryViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@ExperimentalFoundationApi
@Composable
fun CarryScreen(
    viewModel: CarryViewModel,
    navController: NavController
) {
    val viewState = viewModel.state.observeAsState()

    when (val state = viewState.value) {
        is State.LoadedState<*> -> HeroesListView(state.data) {
            val json = Json {
                ignoreUnknownKeys = true
            }

            val string = json.encodeToString(it)
//            val bundle = Bundle()
//            bundle.putParcelable(Keys.Hero.title, it)
//            navController.navigate(R.id.carryAntipickFragment, bundle)
        }
        is State.NoItemsState -> NoHeroesView()
        is State.LoadingState -> LoadingHeroesView()
        is State.ErrorState -> NoHeroesView()
    }

    LaunchedEffect(key1 = Unit, block = {
        viewModel.fetchCarries()
    })
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
