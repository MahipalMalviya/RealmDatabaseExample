package com.example.realmdatabaseexample

import android.content.Context
import io.realm.Realm

class UserDataStoreUtility {

    companion object {

        @Volatile private var INSTANCE: UserDataStoreUtility? = null

        fun getInstance(): UserDataStoreUtility{
            if (INSTANCE == null){
                INSTANCE = UserDataStoreUtility()
            }
            return INSTANCE as UserDataStoreUtility
        }
    }



    fun insertUserData(userData: User){
        val myRealm = Realm.getDefaultInstance()
        myRealm.executeTransaction { realm: Realm? ->
            realm?.insertOrUpdate(userData)
        }
        myRealm.close()
    }

    fun updateUserData(userData: User,userId:Int?){
        val myRealm = Realm.getDefaultInstance()
        val user = myRealm.where(User::class.java)
                .equalTo("userId",userId)
                .findFirst()

        myRealm.beginTransaction()
        user?.userName = userData.userName
        user?.emailId = userData.emailId
        user?.phoneNumber = userData.phoneNumber

        if (user != null) {
            myRealm.insertOrUpdate(user)
        }
        myRealm.commitTransaction()
        myRealm.close()
    }

    fun getUserDataByUserId(userId: Int): User?{
        val myRealm = Realm.getDefaultInstance()
        val results = myRealm.where(User::class.java)
                .equalTo("userId",userId)
                .findAll()


        if (results != null) {
            for (userData in results) {
                return userData
            }
        }
        return null
    }

    fun deleteUserDataByPhoneNumber(userId:Int){
        val myRealm = Realm.getDefaultInstance()
        val result = myRealm.where(User::class.java)
                .equalTo("userId",userId)
                .findAll()

        myRealm.beginTransaction()
        result.deleteAllFromRealm()
        myRealm.commitTransaction()
        myRealm.close()
    }
}