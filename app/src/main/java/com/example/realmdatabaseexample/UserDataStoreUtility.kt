package com.example.realmdatabaseexample

import android.content.Context

class UserDataStoreUtility {

    companion object {

        @Volatile private var INSTANCE: UserDataStoreUtility? = null

        fun getInstance(context: Context): UserDataStoreUtility{
            if (INSTANCE == null){
                INSTANCE = UserDataStoreUtility()
            }
            return INSTANCE as UserDataStoreUtility
        }
    }

    fun insertUserData(userData: User){

    }
}