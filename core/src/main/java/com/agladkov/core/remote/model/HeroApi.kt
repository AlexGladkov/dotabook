package com.agladkov.core.remote.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.*
import kotlinx.serialization.json.Json


@Serializable
data class HeroApi(val id: Int, val name: String, val localized_name: String, val primary_attr: String,
                   val attack_type: String, val roles: List<String>, val legs: Int) {

    companion object  {
        @UnstableDefault
        fun toObject(stringValue: String): HeroApi {
            return Json.nonstrict.parse(serializer(), stringValue)
        }
    }
}

// Extension for serialization
@UnstableDefault
fun HeroApi.toJson(): String {
    return Json.stringify(HeroApi.serializer(), this)
}