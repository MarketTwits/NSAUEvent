package com.markettwits.nsau.hashtag.model

import kotlinx.serialization.Serializable

@Serializable
data class HashTag(val id: Int, val tag: String)