package com.work.list

import android.app.Application
import timber.log.Timber.DebugTree

import timber.log.Timber


class AppClass : Application(){

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

    }
}