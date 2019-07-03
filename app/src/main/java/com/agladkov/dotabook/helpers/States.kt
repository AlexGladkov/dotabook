package com.agladkov.dotabook.helpers

import com.agladkov.domain.models.Hero

sealed class State {
    class LoadingState: State()
    class LoadedState<T>(val data: List<T>): State()
    class NoItemsState: State()
    class ErrorState(val message: String): State()
}