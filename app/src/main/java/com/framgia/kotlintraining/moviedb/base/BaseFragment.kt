package com.framgia.kotlintraining.moviedb.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<ViewBinding : ViewDataBinding, ViewModel : BaseViewModel> : Fragment() {

    lateinit var viewModel: ViewModel

    abstract val layoutRes: Int

    abstract fun initComponent(viewBinding: ViewBinding)

    abstract fun getLayoutResource(): Int

    internal fun replaceFragment(
        fragment: Fragment,
        container: Int,
        TAG: String?,
        addToBackStack: Boolean? = false,
        transit: Int? = -1
    ) {
        val transaction = activity?.supportFragmentManager!!.beginTransaction()
            .replace(container, fragment)

        addToBackStack?.let { if (it) transaction.addToBackStack(TAG) }
        transit?.let { if (it != -1) transaction.setTransition(it) }
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewBinding: ViewBinding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        val view = viewBinding.root
        viewBinding.setLifecycleOwner(this)
        viewBinding.root.isClickable = true
        initComponent(viewBinding)
        lifecycle.addObserver(viewModel)
        return view
    }

    open fun onBackPress() {}
}