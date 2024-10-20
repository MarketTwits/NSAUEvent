package com.markettwits.nsau.event.model

import com.markettwits.nsau.hashtag.model.HashTag
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlin.random.Random


@Serializable
data class Event(
    val id: Int = Random.nextInt(),
    val title: String,
    val content: String,
    val description: String,
    val imagePath : String,
    val hasTags: List<HashTag>,
    val createAt: LocalDateTime,
    val updateAt: LocalDateTime
)