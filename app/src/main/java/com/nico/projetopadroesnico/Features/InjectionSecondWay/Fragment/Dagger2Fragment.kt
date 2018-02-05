package com.nico.projetopadroesnico.Features.InjectionSecondWay.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nico.projetopadroesnico.Common.Fragment.BaseFragment
import com.nico.projetopadroesnico.Features.InjectionSecondWay.Activity.Dagger2Activity
import com.nico.projetopadroesnico.R
import kotlinx.android.synthetic.main.fragment_di_2.*
import org.jetbrains.anko.startActivity


class Dagger2Fragment: BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnDagger.setOnClickListener {
            context?.startActivity<Dagger2Activity>()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, icicle: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_di_2, container, false)
    }
}