package com.agladkov.domain

import com.agladkov.domain.models.Hero
import kotlinx.coroutines.Deferred

interface CarryRepository {
    suspend fun fetchCarries(): Deferred<List<Hero>>
    suspend fun fetchLocalCarries(): Deferred<List<Hero>>
}