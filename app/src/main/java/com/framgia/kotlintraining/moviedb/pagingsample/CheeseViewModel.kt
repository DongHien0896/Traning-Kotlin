package com.framgia.kotlintraining.moviedb.pagingsample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Config
import androidx.paging.toLiveData

class CheeseViewModel(app: Application) : AndroidViewModel(app) {

    private val dao = CheeseDb.get(app).cheeseDao()

    val allCheese = dao.allCheeseByName().toLiveData(
        Config(
            pageSize = 30,
            enablePlaceholders = true,
            maxSize = 200
        )
    )

    fun insert(text: CharSequence) = ioThread {
        dao.insert(Cheese(id = 0, name = text.toString()))
    }
}