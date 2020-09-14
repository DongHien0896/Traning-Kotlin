package com.framgia.kotlintraining.moviedb.screen.firestore

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import com.framgia.kotlintraining.moviedb.R
import com.framgia.kotlintraining.moviedb.base.BaseFragment
import com.framgia.kotlintraining.moviedb.databinding.FragmentFirestoreBinding
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_firestore.*

class FirestoreFragment : BaseFragment<FragmentFirestoreBinding, FirestoreViewModel>() {

    private val KEY_NAME = "KEY_NAME"
    private val KEY_ROOM = "KEY_ROOM"

    companion object {
        fun newInstance() = FirestoreFragment()
    }

    override val viewModel: FirestoreViewModel get() = FirestoreViewModel()

    override val layoutRes: Int
        get() = R.layout.fragment_firestore

    override fun initComponent(viewBinding: FragmentFirestoreBinding) {
        setEvent()
    }

    private val documentRef = FirebaseFirestore.getInstance().document("sampleData/home")

    private fun setEvent() {
        btnSend.setOnClickListener {
            saveData()
        }
        btnFetch.setOnClickListener {
            fetchData()
        }
    }

    override fun onStart() {
        super.onStart()
        documentRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.d("-------->", "FirestoreFragment - Listen fail!")
                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
                val name = snapshot.getString(KEY_NAME)
                val room = snapshot.getString(KEY_ROOM)
                textFetch.text = "$name-----$room"
            } else {
                Log.d("-------->", "FirestoreFragment - Data null!")
            }
        }
    }

    private fun saveData() {
        val dataToSave = HashMap<String, Any>()
        dataToSave[KEY_NAME] = editTextName.text.toString()
        dataToSave[KEY_ROOM] = editTextRoom.text.toString()

        documentRef.set(dataToSave)
            .addOnSuccessListener {
                Log.d("-------->", "FirestoreFragment - initComponent: Add data success!")
            }
            .addOnFailureListener {
                Log.d("-------->", "FirestoreFragment - initComponent: Add data failure!")
            }
    }

    @SuppressLint("SetTextI18n")
    private fun fetchData() {
        documentRef.get()
            .addOnSuccessListener{ documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val name = documentSnapshot.getString(KEY_NAME)
                    val room = documentSnapshot.getString(KEY_ROOM)
                    textFetch.text = "$name-----$room"
                }
            }
            .addOnFailureListener{
                Log.d("-------->", "FirestoreFragment - fetchData: Fail!")
            }
    }
}