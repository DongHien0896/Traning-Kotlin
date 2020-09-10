package com.framgia.kotlintraining.moviedb.screen.firestore

import com.framgia.kotlintraining.moviedb.R
import com.framgia.kotlintraining.moviedb.base.BaseFragment
import com.framgia.kotlintraining.moviedb.databinding.FragmentFirestoreBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirestoreFragment : BaseFragment<FragmentFirestoreBinding, FirestoreViewModel>(){

    companion object {
        fun newInstance() = FirestoreFragment()
    }

    override val viewModel: FirestoreViewModel get() = FirestoreViewModel()

    override val layoutRes: Int
        get() = R.layout.fragment_firestore

    val fireStore = Firebase.firestore

    override fun initComponent(viewBinding: FragmentFirestoreBinding) {

    }
}