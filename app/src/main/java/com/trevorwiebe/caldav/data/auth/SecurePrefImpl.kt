package com.trevorwiebe.caldav.data.auth

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class SecurePrefImpl(
    private val encryptedSharedPreferences: SharedPreferences
): SecurePref {

    override fun saveAuthCalendar(authCalendar: AuthCalendar) {
        val calendarList = getAuthCalendarList().toMutableList()
        calendarList.add(authCalendar)
        val calendarString = Gson().toJson(calendarList)
        encryptedSharedPreferences.edit()?.apply {
            putString("auth_calendar", calendarString)
            apply()
        }
    }

    override fun getAuthCalendarList(): List<AuthCalendar> {
        encryptedSharedPreferences.let {
            val calendarString = it.getString("auth_calendar", null)
            return try{
                val calendarListType = object : TypeToken<ArrayList<AuthCalendar>>() {}.type
                Gson().fromJson(calendarString, calendarListType)
            }catch (e: Exception){
                return emptyList()
            }
        }
    }

}