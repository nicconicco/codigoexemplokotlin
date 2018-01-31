package com.nico.projetopadroesnico.Features.Injection.Fragment

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.internal.zzahn.runOnUiThread
import com.nico.projetopadroesnico.Application.NicoApplication
import com.nico.projetopadroesnico.Common.Extension.errorDialog
import com.nico.projetopadroesnico.Common.Extension.parseHtml
import com.nico.projetopadroesnico.Common.Fragment.BaseFragment
import com.nico.projetopadroesnico.Features.Injection.Model.WikiHomepage
import com.nico.projetopadroesnico.Features.Injection.Presenter.InjectPagePresenter
import com.nico.projetopadroesnico.Features.Injection.Presenter.InjectPageView
import com.nico.projetopadroesnico.R
import kotlinx.android.synthetic.main.fragment_di.*
import javax.inject.Inject


class InjectFragment : BaseFragment(), InjectPageView {

    private var isVisibleToUser = false
    @Inject lateinit var presenter: InjectPagePresenter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        NicoApplication.getInstance().wikiComponent.injectFragment(this)

        if (isVisibleToUser) {
            initView()
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        if (isVisibleToUser) {
            initView()
        }
    }

    private fun initView() {
        presenter.setView(this)
        presenter.loadHomepage()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, icicle: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_di, container, false)
    }

    override fun displayLoading() {
        wait_progress_bar.post {
            wait_progress_bar.visibility = View.VISIBLE
            homepage_sv.visibility = View.GONE
        }
    }

    override fun dismissLoading() {
        wait_progress_bar.post {
            wait_progress_bar.visibility = View.GONE
            homepage_sv.visibility = View.VISIBLE
        }
    }

    override fun displayHomepage(result: WikiHomepage) {
        homepage_tv.post {
            homepage_tv.text = result.htmlContent.parseHtml()
        }
    }

    override fun displayError(error: String?) {
        Log.e("ERROR", error)
        runOnUiThread {
            activity?.let {
                R.string.error.errorDialog(it)
            }
        }
    }

}