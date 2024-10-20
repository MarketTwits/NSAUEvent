package com.markettwits.nsau.event.controller

import com.markettwits.nsau.event.model.Event

interface EventController {

    suspend fun getEvents(limit: Int?, offset: Int, name: String): Result<List<Event>>

    suspend fun getByTagId(tagId: Int): Result<List<Event>>

    suspend fun getByName(name: String): Result<List<Event>>

    suspend fun getById(eventId: Int): Result<Event>
}
