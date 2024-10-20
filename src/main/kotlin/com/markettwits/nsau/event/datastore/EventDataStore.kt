package com.markettwits.nsau.event.datastore

import com.markettwits.nsau.event.model.Event

interface EventDataStore {

    fun insertEvent(event: Event)

    fun updateEvent(eventId: Int, event: Event)

    fun deleteEvent(eventId: Int)

    fun getByTagId(tagId: Int): List<Event>

    fun getByName(name: String): List<Event>

    fun getById(eventId: Int): Event?

    fun getEvents(): List<Event>

}