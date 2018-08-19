package com.example.cld.songchurchapplication.ui.churchSelect

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.cld.songchurchapplication.dataLayer.Repository
import javax.inject.Inject

@InjectViewState
class ChurchSelectPresentor : MvpPresenter<ChurchSelectView>() {

    lateinit var repository: Repository

}