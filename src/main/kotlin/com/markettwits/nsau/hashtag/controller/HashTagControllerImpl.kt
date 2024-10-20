package com.markettwits.nsau.hashtag.controller

import com.markettwits.nsau.hashtag.datastore.FakeHashTagsDataStore
import com.markettwits.nsau.hashtag.model.HashTag

class HashTagControllerImpl(private val dataSource: FakeHashTagsDataStore) :
    com.markettwits.nsau.hashtag.controller.HashTagController {

    override fun getHashTags(limit: Int?, offset: Int): Result<List<HashTag>> {
        return runCatching {
            val filteredEvents = dataSource.hashTags.drop(offset)
            if (limit != null) {
                filteredEvents.take(limit)
            } else {
                filteredEvents
            }
        }
    }

    override fun getHashTagById(id: Int): Result<HashTag> = kotlin.runCatching {
        dataSource.hashTags.find { it.id == id } ?: throw NoSuchElementException("No hash tag found with ID $id")
    }

}