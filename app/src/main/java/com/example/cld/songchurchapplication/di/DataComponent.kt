package com.example.cld.songchurchapplication.di

import com.example.cld.songchurchapplication.ui.churchSelect.ChurchSelectActivity
import com.example.cld.songchurchapplication.ui.churchSelect.ChurchSelectView
import com.example.cld.songchurchapplication.ui.signIn.SignInActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class])
interface DataComponent {
    fun inject(signInActivity: SignInActivity)
    fun inject(churchSelectActivity: ChurchSelectActivity)
}