package com.trevorwiebe.caldav.presentation.calendarEvents

sealed class CalendarEventUiEvents{
    object ToggleViewState: CalendarEventUiEvents()
    data class ToggleCalendarVisibility(val calendarId: String): CalendarEventUiEvents()
}
