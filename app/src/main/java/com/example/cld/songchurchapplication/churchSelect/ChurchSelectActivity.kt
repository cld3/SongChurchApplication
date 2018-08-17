package com.example.cld.songchurchapplication.churchSelect

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.cld.songchurchapplication.R
import com.example.cld.songchurchapplication.dataLayer.RepositoryImpl
import com.example.cld.songchurchapplication.models.ChristianModel
import com.example.cld.songchurchapplication.models.ChurchModel
import com.example.cld.songchurchapplication.models.WorshipGroupModel

class ChurchSelectActivity : AppCompatActivity() {

    val repositoryImpl = RepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repositoryImpl.addChurch(
                ChurchModel(
                        "qwe",
                        "dsds",
                        "ds",
                        mutableListOf()
                )
        )
    }

    override fun onStart() {
        super.onStart()


    }
}
