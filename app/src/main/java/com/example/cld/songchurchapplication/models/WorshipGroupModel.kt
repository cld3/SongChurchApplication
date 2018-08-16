package com.example.cld.songchurchapplication.models

data class WorshipGroupModel(
        var id: String,
        var name: String,
        var christians: List<ChristianModel>
) {
}