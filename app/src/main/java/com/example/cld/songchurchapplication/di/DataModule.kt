package com.example.cld.songchurchapplication.di


import com.example.cld.songchurchapplication.dataLayer.Repository
import com.example.cld.songchurchapplication.dataLayer.RepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun getFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun getFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun getRepository(firestore: FirebaseFirestore): Repository {
        return RepositoryImpl(firestore)
    }
}