package com.markettwits.nsau.hashtag.controller

import com.markettwits.nsau.hashtag.model.HashTag

interface HashTagController {

    fun getHashTags(limit: Int?, offset: Int): Result<List<HashTag>>

    fun getHashTagById(id : Int): Result<HashTag>

}