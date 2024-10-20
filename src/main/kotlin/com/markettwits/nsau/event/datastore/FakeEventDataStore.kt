package com.markettwits.nsau.event.datastore

import com.markettwits.nsau.event.model.Event
import com.markettwits.nsau.hashtag.model.HashTag
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object FakeEventDataStore : EventDataStore {
    private val events = mutableListOf<Event>()

    init {
        // Инициализируем фейковые данные
        events.addAll(generateFakeEvents())
    }

    override fun insertEvent(event: Event) {
        events.add(event)
    }

    override fun updateEvent(eventId: Int, event: Event) {
        val index = events.indexOfFirst { it.id.toInt() == eventId }
        if (index != -1) {
            events[index] = event
        }
    }

    override fun deleteEvent(eventId: Int) {
        events.removeIf { it.id == eventId }
    }

    override fun getByTagId(tagId: Int): List<Event> {
        return events.filter { event -> event.hasTags.any { it.id == tagId } }
    }

    override fun getByName(name: String): List<Event> {
        return events.filter { it.title.contains(name, ignoreCase = true) }
    }

    override fun getById(eventId: Int): Event? {
        return events.find { it.id.toInt() == eventId }
    }

    override fun getEvents(): List<Event> = events
}

private fun generateFakeEvents(): List<Event> {
    return listOf(
        Event(
            title = "Открытие научной конференции студентов",
            content = "Торжественное открытие ежегодной конференции студентов НГАУ. Участвуют ведущие преподаватели и студенты старших курсов.",
            description = "Конференция, на которой студенты представляют свои научные работы и проекты.",
            imagePath = "/images/conference.jpg",
            hasTags = listOf(HashTag(1, "наука"), HashTag(2, "конференция")),
            createAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updateAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        Event(
            title = "Лекция профессора Иванова: Агрономия будущего",
            content = "Профессор Иванов расскажет о современных подходах в агрономии, включая применение IT-технологий и дронов.",
            description = "Лекция от ведущего специалиста в области агрономии.",
            imagePath = "/images/lecture.jpg",
            hasTags = listOf(HashTag(3, "агрономия"), HashTag(4, "лекция")),
            createAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updateAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        Event(
            title = "Студенческий концерт ко Дню знаний",
            content = "Традиционный концерт, организованный студентами факультета искусств в честь Дня знаний.",
            description = "Концерт с участием студенческих коллективов и ансамблей.",
            imagePath = "/images/concert.jpg",
            hasTags = listOf(HashTag(5, "концерт"), HashTag(6, "искусство")),
            createAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updateAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        Event(
            title = "День открытых дверей в НГАУ",
            content = "Приглашаем будущих абитуриентов и их родителей на День открытых дверей, где можно познакомиться с факультетами и программами обучения.",
            description = "Информационное мероприятие для абитуриентов.",
            imagePath = "/images/open_day.jpg",
            hasTags = listOf(HashTag(7, "абитуриенты"), HashTag(8, "мероприятие")),
            createAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updateAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        Event(
            title = "Мастер-класс по садоводству",
            content = "Практическое занятие для всех желающих по основам садоводства на территории университетского сада.",
            description = "Мастер-класс по правильному уходу за растениями и деревьями.",
            imagePath = "/images/gardening.jpg",
            hasTags = listOf(HashTag(9, "садоводство"), HashTag(10, "мастер-класс")),
            createAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updateAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        Event(
            title = "Фестиваль студенческих научных проектов",
            content = "Учащиеся различных факультетов представят свои научные проекты на выставке. Победителей ждут призы.",
            description = "Научная выставка проектов студентов НГАУ.",
            imagePath = "/images/science_festival.jpg",
            hasTags = listOf(HashTag(11, "наука"), HashTag(12, "выставка")),
            createAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updateAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        Event(
            title = "Выставка экологических инициатив",
            content = "Студенты эколого-биологического факультета презентуют свои инициативы по улучшению экологической ситуации в регионе.",
            description = "Экологическая выставка, направленная на повышение осведомлённости студентов.",
            imagePath = "/images/ecology.jpg",
            hasTags = listOf(HashTag(13, "экология"), HashTag(14, "выставка")),
            createAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updateAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        Event(
            title = "Студенческий турнир по шахматам",
            content = "Участие в турнире могут принять студенты всех курсов и факультетов. Регистрация обязательна.",
            description = "Шахматный турнир для студентов университета.",
            imagePath = "/images/chess.jpg",
            hasTags = listOf(HashTag(15, "шахматы"), HashTag(16, "спорт")),
            createAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updateAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        Event(
            title = "Экскурсия по лабораториям НГАУ",
            content = "Уникальная возможность посетить исследовательские лаборатории НГАУ и ознакомиться с их работой.",
            description = "Экскурсия для студентов и преподавателей по лабораториям вуза.",
            imagePath = "/images/laboratory.jpg",
            hasTags = listOf(HashTag(17, "исследования"), HashTag(18, "экскурсия")),
            createAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updateAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        Event(
            title = "Соревнования по лёгкой атлетике",
            content = "Студенческие соревнования по бегу на различные дистанции. Все желающие могут участвовать или поддерживать.",
            description = "Спортивные мероприятия для студентов университета.",
            imagePath = "/images/athletics.jpg",
            hasTags = listOf(HashTag(19, "спорт"), HashTag(20, "лёгкая атлетика")),
            createAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updateAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        Event(
            title = "Кулинарный мастер-класс от шеф-повара",
            content = "Мастер-класс от известного шеф-повара с демонстрацией приготовления блюд из фермерских продуктов.",
            description = "Кулинарное событие, организованное аграрным факультетом.",
            imagePath = "/images/cooking.jpg",
            hasTags = listOf(HashTag(21, "кулинария"), HashTag(22, "мастер-класс")),
            createAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updateAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        Event(
            title = "Семинар по органическому земледелию",
            content = "Семинар для студентов агрономического факультета с участием специалистов в области органического сельского хозяйства.",
            description = "Образовательный семинар по органическому земледелию.",
            imagePath = "/images/agriculture.jpg",
            hasTags = listOf(HashTag(23, "агрономия"), HashTag(24, "семинар")),
            createAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updateAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        Event(
            title = "Конкурс на лучший студенческий стартап",
            content = "Конкурс студенческих стартапов с возможностью получить финансирование на развитие проекта.",
            description = "Конкурс для студентов, желающих представить свои идеи.",
            imagePath = "/images/startup.jpg",
            hasTags = listOf(HashTag(25, "стартап"), HashTag(26, "конкурс")),
            createAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updateAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        Event(
            title = "Публичная защита дипломных проектов",
            content = "Выпускники представляют свои дипломные проекты перед комиссией и слушателями.",
            description = "Открытая защита дипломов на различных факультетах.",
            imagePath = "/images/diploma_defense.jpg",
            hasTags = listOf(HashTag(27, "диплом"), HashTag(28, "защита")),
            createAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updateAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        Event(
            title = "Университетская ярмарка вакансий",
            content = "Мероприятие для студентов и выпускников, где ведущие компании региона предлагают вакансии и стажировки.",
            description = "Ярмарка вакансий для студентов НГАУ.",
            imagePath = "/images/job_fair.jpg",
            hasTags = listOf(HashTag(29, "вакансии"), HashTag(30, "стажировка")),
            createAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
            updateAt = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        )
    )
}