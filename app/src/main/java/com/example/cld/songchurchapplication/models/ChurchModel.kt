package com.example.cld.songchurchapplication.models

data class ChurchModel (
        var id: String,
        var name: String,
        var city: String,
        var worshipGroups: List<WorshipGroupModel>
){
}