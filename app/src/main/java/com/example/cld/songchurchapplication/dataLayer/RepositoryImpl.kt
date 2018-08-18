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
                        churches.add(ChurchModel(
                                document.id,
                                document["displayName"].toString(),
                                document["city"].toString()
                        ))
                    }
                }
        return churches
    }

    fun addChurch(church: ChurchModel) {
        firestore.collection("church")
                .add(mapOf(
                        "displayName" to church.name,
                        "city" to church.city
                ))
    }


    fun getWorshipGroups(churchPath: String): List<WorshipGroupModel> {
        val worshipGroups = mutableListOf<WorshipGroupModel>()
        firestore.collection("$churchPath/worshipGroup")
                .get()
                .addOnSuccessListener {
                    for (document in it.documents) {
                        worshipGroups.add(WorshipGroupModel(
                                document.id,
                                document["displayName"].toString()
                        ))
                    }
                }
        return worshipGroups
    }


    fun addWorshipGroup(churchPath: String, worshipGroup: WorshipGroupModel) {
        firestore.collection("$churchPath/worshipGroup")
                .add(mapOf(
                        "displayName" to worshipGroup.name
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
                                document["churchId"].toString(),
                                document["worshipGroupId"].toString(),
                                document["displayName"].toString(),
                                document["surname"].toString(),
                                document["email"].toString(),
                                document["phoneNumber"].toString(),
                                document["imageUrl"].toString()
                        ))
                    }
                }
        return christians
    }

    fun addChristians(worshipPath: String, christian: ChristianModel) {
        firestore.collection("$worshipPath/christians")
                .add(mapOf(
                        "churchId" to christian.displayName,
                        "worshipGroupId" to christian.displayName,
                        "displayName" to christian.displayName,
                        "surname" to christian.surname,
                        "email" to christian.email,
                        "phoneNumber" to christian.phoneNumber,
                        "imageUrl" to christian.imageUrl
                ))
    }
}
