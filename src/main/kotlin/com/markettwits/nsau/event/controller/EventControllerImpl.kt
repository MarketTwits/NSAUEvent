package com.markettwits.nsau.event.controller

import com.markettwits.nsau.event.datastore.EventDataStore
import com.markettwits.nsau.event.model.Event
import io.ktor.server.plugins.*

class EventControllerImpl(
    private val eventStore: EventDataStore,
) : EventController {


    override suspend fun getEvents(limit: Int?, offset: Int, name: String): Result<List<Event>> {
        return runCatching {
            val filteredEvents = eventStore.getEvents().drop(offset).let { events ->
                if (name.isNotBlank()) {
                    events.filter { it.title.contains(name, ignoreCase = true) }
                } else {
                    events
                }
            }
            if (limit != null) {
                filteredEvents.take(limit)
            } else {
                filteredEvents
            }
        }
    }

    override suspend fun getByTagId(tagId: Int): Result<List<Event>> {
        return runCatching {
            eventStore.getByTagId(tagId)
        }
    }

    override suspend fun getByName(name: String): Result<List<Event>> =
        runCatching {
            if (name.isEmpty()) {
                throw NoSuchElementException("Event with name $name not found")
            }
            eventStore.getByName(name)
        }

    override suspend fun getById(eventId: Int): Result<Event> =
        runCatching {
            eventStore.getById(eventId) ?: throw NotFoundException("Not found event with ID $eventId")
        }

}