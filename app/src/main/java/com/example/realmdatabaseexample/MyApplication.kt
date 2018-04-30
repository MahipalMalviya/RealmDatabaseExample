package com.example.realmdatabaseexample

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

            Realm.init(this)
            val realmConfig = RealmConfiguration.Builder()
                    .name(Realm.DEFAULT_REALM_NAME)
                    .schemaVersion(1)
                    .deleteRealmIfMigrationNeeded()
                    .build()
            Realm.setDefaultConfiguration(realmConfig)
    }
}