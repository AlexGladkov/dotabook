package com.agladkov.dotabook.data.remote.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.*
import kotlinx.serialization.json.Json


@Serializable
data class HeroApi(val id: Int, val name: String, val localized_name: String, val primary_attr: String,
                   val attack_type: String, val roles: List<String>, val legs: Int)

// Extension for serialization
fun HeroApi.toJson(): String {
    return Json.encodeToString(this)
}