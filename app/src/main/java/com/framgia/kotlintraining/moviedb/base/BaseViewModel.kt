package com.framgia.kotlintraining.moviedb.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver

abstract class BaseViewModel(application: Application) : LifecycleObserver, AndroidViewModel(application)