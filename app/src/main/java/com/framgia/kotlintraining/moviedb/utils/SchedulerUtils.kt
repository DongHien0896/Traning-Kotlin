@file:Suppress("UNREACHABLE_CODE")

package com.framgia.kotlintraining.moviedb.utils

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

/**
 * Class SchedulerUtils.
 */

/**
 * Create a transformer to compose an Flowable.
 * Transformer to subscribe on separate thread and observe it
 * in Schedulers.io
 *
 * @param <T> Rule of Observable object
</T> */
fun <T> applySyncSchedulersIO(): FlowableTransformer<T, T> {
    return FlowableTransformer { func ->
        func.subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
    }
}

/**
 * Create a transformer to compose an Flowable.
 * Transformer to subscribe on separate thread and observe it
 * in UI thread
 *
 * @param <T> Rule of Observable object
</T> */
fun <T> applyAsyncSchedulers(): FlowableTransformer<T, T> {
    return FlowableTransformer { func ->
        func.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}

/**
 * Create a transformer to compose an Flowable.
 * Transformer to subscribe on current thread and observe it
 * in this thread
 * Using in synchronous API request
 *
 * @param <T> Rule of Observable object
</T> */
fun <T> applySyncSchedulers(): FlowableTransformer<T, T> {
    return FlowableTransformer { func ->
        func.subscribeOn(Schedulers.trampoline())
            .observeOn(Schedulers.trampoline())
    }
}

fun applyAsyncSchedulersCompletable(): CompletableTransformer {
    return CompletableTransformer { func ->
        func.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

fun applySyncSchedulersIOCompletable(): CompletableTransformer {
    return CompletableTransformer { completable ->
        completable.subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
    }
}

fun <T> applyAsyncSchedulersSingle(): SingleTransformer<T, T> {
    return SingleTransformer { func ->
        func.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> applySyncSchedulersSingleIO(): SingleTransformer<T, T> {
    return SingleTransformer { func ->
        func.subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
    }
}

fun <T> convertData(): Function<T, Flowable<T>> {
    return Function { response -> Flowable.just(response) }
}