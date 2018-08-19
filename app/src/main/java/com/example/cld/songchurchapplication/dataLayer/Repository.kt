package com.example.cld.songchurchapplication.dataLayer

import android.util.Log
import com.example.cld.songchurchapplication.models.ChristianModel
import com.example.cld.songchurchapplication.models.ChurchModel
import com.example.cld.songchurchapplication.models.WorshipGroupModel
import com.google.firebase.firestore.FirebaseFirestore

interface Repository {
    fun getChurches(): List<ChurchModel>
    fun addChurch(church: ChurchModel)
    fun getWorshipGroups(churchPath: String): List<WorshipGroupModel>
    fun addWorshipGroup(churchPath: String, worshipGroup: WorshipGroupModel)
    fun getChristians(worshipGroupPath: String): List<ChristianModel>
    fun addChristians(worshipPath: String, christian: ChristianModel)
}