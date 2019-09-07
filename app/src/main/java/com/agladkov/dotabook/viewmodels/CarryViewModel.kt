package com.agladkov.dotabook.viewmodels

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.agladkov.domain.CarryRepository
import com.agladkov.domain.models.Hero
import com.agladkov.dotabook.extensions.default
import com.agladkov.dotabook.extensions.set
import com.agladkov.dotabook.helpers.State
import kotlinx.coroutines.*
import java.lang.Exception


class CarryViewModel(val repository: CarryRepository) : LifecycleObserver {
    private val TAG = CarryViewModel::class.java.simpleName

    val state: MutableLiveData<State> = MutableLiveData<State>().default(initialValue = State.LoadingState())

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun fetchCarries() {
        state.set(newValue = State.LoadingState())
        CoroutineScope(Dispatchers.IO).launch {
            val heroes = repository.fetchLocalCarries().await()
            if (heroes.isEmpty()) {
                launch(Dispatchers.Main) {
                    state.set(newValue = State.NoItemsState())
                }
            } else {
                launch(Dispatchers.Main) {
                    state.set(newValue = State.LoadedState(data = heroes))
                }
            }
        }
//        GlobalScope.launch(Dispatchers.IO) {
//            try {
//                val heroes = repository.fetchCarries().await()
//                if (heroes.isEmpty()) {
//                    withContext(Dispatchers.Main) {
//                        state.set(newValue = State.NoItemsState())
//                    }
//                } else {
//                    withContext(Dispatchers.Main) {
//                        state.set(newValue = State.LoadedState(data = heroes))
//                    }
//                }
//            } catch (e: Exception) {
//                Log.e(TAG, "error ${e.localizedMessage}")
//                e.printStackTrace()
//            }
//        }
    }
}