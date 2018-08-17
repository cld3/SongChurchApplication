package com.example.cld.songchurchapplication.dataLayer

import com.example.cld.songchurchapplication.models.ChristianModel
import com.example.cld.songchurchapplication.models.ChurchModel
import com.example.cld.songchurchapplication.models.WorshipGroupModel

interface Repository {
    fun getChurchList() : List<ChurchModel>
    fun addChurch(church: ChurchModel)
    fun getChristianList() : List<ChristianModel>
    fun addChurch(christian: ChristianModel)
    fun getWorshipGroupList() : List<WorshipGroupModel>
    fun addWorshipGroup(worshipGroup: WorshipGroupModel)
}