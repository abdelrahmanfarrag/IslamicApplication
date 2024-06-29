package com.islamic.local.preferences

interface IPreferencesRepository {
    fun setString(key:String,value:String)
    fun getString(key: String,defaultValue:String) :String?
}