package com.gramzin.gifless

import android.app.Application
import android.content.Context
import com.gramzin.gifless.di.component.AppComponent
import com.gramzin.gifless.di.component.DaggerAppComponent

class MyApp: Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

val Context.appComponent: AppComponent
    get() = when(this){
        is MyApp -> appComponent
        else -> applicationContext.appComponent
    }