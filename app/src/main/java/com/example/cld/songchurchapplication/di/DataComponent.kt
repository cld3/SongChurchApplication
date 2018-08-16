package com.example.cld.songchurchapplication.di

import com.example.cld.songchurchapplication.signIn.SignInActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface DataComponent {
    fun inject(signInActivity: SignInActivity)
}