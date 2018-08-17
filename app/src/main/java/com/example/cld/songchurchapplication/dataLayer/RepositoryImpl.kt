package com.example.cld.songchurchapplication.dataLayer

import android.util.Log
import com.example.cld.songchurchapplication.models.ChristianModel
import com.example.cld.songchurchapplication.models.ChurchModel
import com.google.firebase.firestore.FirebaseFirestore
import com.example.cld.songchurchapplication.models.WorshipGroupModel


class RepositoryImpl {

    var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun getChurches(): List<ChurchModel> {
        val churches = mutableListOf<ChurchModel>()
        firestore.collection("church")
                .get()
                .addOnSuccessListener {
                    for (document in it.documents) {
                        val worshipGroup = getWorshipGroups(document.reference.path)
                        churches.add(ChurchModel(
                                document.id,
                                document["name"].toString(),
                                document["city"].toString(),
                                worshipGroup
                        ))
                    }
                }
        return churches
    }

    fun addChurch(church: ChurchModel) {
        firestore.collection("church")
                .add(mapOf(
                        "name" to church.name,
                        "city" to church.city
                ))
    }


    fun getWorshipGroups(churchPath: String): List<WorshipGroupModel> {
        val worshipGroups = mutableListOf<WorshipGroupModel>()
        firestore.collection("$churchPath/worshipGroup")
                .get()
                .addOnSuccessListener {
                    for (document in it.documents) {
                        val christians = getChristians(document.reference.path)
                        worshipGroups.add(WorshipGroupModel(
                                document.id,
                                document["name"].toString(),
                                christians
                        ))
                    }
                }
        return worshipGroups
    }


    fun addWorshipGroup(churchPath: String, worshipGroup: WorshipGroupModel) {
        firestore.collection("$churchPath/worshipGroup")
                .add(mapOf(
                        "name" to worshipGroup.name
                ))
    }

    fun getChristians(worshipGroupPath: String): List<ChristianModel> {
        val christians = mutableListOf<ChristianModel>()
        firestore.collection("$worshipGroupPath/christians")
                .get()
                .addOnSuccessListener {
                    for (document in it.documents) {
                        Log.d("qq", "christians $document")
                        christians.add(ChristianModel(
                                document.id,
                                document["name"].toString(),
                                document["surname"].toString(),
                                document["email"].toString(),
                                document["imageUrl"].toString()))
                    }
                }
        return christians
    }

    fun addChristians(worshipPath: String, christian: ChristianModel) {
        firestore.collection("$worshipPath/christians")
                .add(mapOf(
                        "name" to christian.name,
                        "surname" to christian.surname,
                        "email" to christian.email,
                        "imageUrl" to christian.imageUrl
                        ))
    }
}
