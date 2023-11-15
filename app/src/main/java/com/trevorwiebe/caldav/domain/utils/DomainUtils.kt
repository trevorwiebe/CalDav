package com.trevorwiebe.caldav.domain.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun parseLocalDateTime(dateString: String): LocalDateTime {
    var dateStringVar = dateString
    if(dateString.length == 8){
        dateStringVar += "T000000Z"
    } else if(dateString.length == 15){
        dateStringVar += "Z"
    }
    val pattern = "yyyyMMdd'T'HHmmss'Z'"
    val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(pattern)
    return LocalDateTime.parse(dateStringVar, dateTimeFormatter)
}

fun parseLocalDate(dateString: String): LocalDate {
    val pattern = if(dateString.length == 8){
        "yyyyMMdd"
    }else{
        "yyyyMMdd'T'HHmmss"
    }
    val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(pattern)
    return LocalDate.parse(dateString, dateTimeFormatter)
}