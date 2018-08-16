package com.example.cld.songchurchapplication

import android.app.Application
import com.example.cld.songchurchapplication.di.DaggerDataComponent
import com.example.cld.songchurchapplication.di.DataComponent

class App : Application(){
    lateinit var dataComponent: DataComponent
    private set

    override fun onCreate() {
        super.onCreate()

        dataComponent = DaggerDataComponent.builder().build()
    }
}