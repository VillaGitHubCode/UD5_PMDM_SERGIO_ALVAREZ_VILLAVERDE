package com.utad.ud5_pmdm_sergio_alvarez_villaverde

import android.app.Application
import com.google.firebase.FirebaseApp
import io.paperdb.Paper

class DealsApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(applicationContext)
        Paper.init(applicationContext)
    }
}