package com.gabriel.kotlinstars.core.platform

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject


abstract class BaseFragment(@LayoutRes layoutIdRes : Int = 0) : Fragment(layoutIdRes) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
