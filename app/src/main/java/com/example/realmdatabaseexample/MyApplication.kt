package com.example.realmdatabaseexample

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

            val realmConfig = RealmConfiguration.Builder(this)
                    .name(Realm.DEFAULT_REALM_NAME)
                    .schemaVersion(0)
                    .deleteRealmIfMigrationNeeded()
                    .build()
            Realm.setDefaultConfiguration(realmConfig)
    }
}