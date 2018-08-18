package com.example.cld.songchurchapplication.models

data class ChristianModel(
        var id: String,
        var churchId: String,
        var worshipGroupId: String,
        var displayName: String,
        var surname: String,
        var email: String,
        var phoneNumber: String,
        var imageUrl: String
) {
}