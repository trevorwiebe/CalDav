package com.trevorwiebe.caldav.data.auth

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class SecurePrefImpl(
    private val encryptedSharedPreferences: SharedPreferences
): SecurePref {

    override fun saveUser(authUser: AuthUser) {
        val userList = getUserList().toMutableList()
        userList.add(authUser)
        val userString = Gson().toJson(userList)
        encryptedSharedPreferences.edit()?.apply {
            putString("user", userString)
            apply()
        }
    }

    override fun getUserList(): List<AuthUser> {
        encryptedSharedPreferences.let {
            val userString = it.getString("user", null)
            return try{
                val userListType = object : TypeToken<ArrayList<AuthUser>>() {}.type
                Gson().fromJson(userString, userListType)
            }catch (e: Exception){
                return emptyList()
            }
        }
    }

}