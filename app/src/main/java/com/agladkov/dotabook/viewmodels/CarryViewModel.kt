package com.agladkov.dotabook.viewmodels

import androidx.lifecycle.*
import com.agladkov.dotabook.domain.implementations.CarryRepository
import com.agladkov.dotabook.extensions.set
import com.agladkov.dotabook.helpers.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CarryViewModel @Inject constructor(val repository: CarryRepository) : ViewModel() {
    val state: MutableLiveData<State> = MutableLiveData<State>(State.LoadingState())

    fun fetchCarries() {
        state.set(newValue = State.LoadingState())
        viewModelScope.launch {
            try {
                val heroes = repository.fetchCarries()
                if (heroes.isEmpty()) {
                    withContext(Dispatchers.Main) {
                        state.set(newValue = State.NoItemsState())
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        state.set(newValue = State.LoadedState(data = heroes))
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}