package com.nico.projetopadroesnico.Features.Injection.Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import com.nico.projetopadroesnico.Application.NicoApplication
import com.nico.projetopadroesnico.Common.Activity.BaseActivity
import com.nico.projetopadroesnico.Common.Extension.errorDialog
import com.nico.projetopadroesnico.Common.Extension.parseHtml
import com.nico.projetopadroesnico.Features.Injection.Model.WikiHomepage
import com.nico.projetopadroesnico.Features.Injection.Presenter.InjectPagePresenter
import com.nico.projetopadroesnico.Features.Injection.Presenter.InjectPageView
import com.nico.projetopadroesnico.R
import kotlinx.android.synthetic.main.activity_inject_example.*
import javax.inject.Inject


class InjectActivity : BaseActivity(), InjectPageView {

    @Inject lateinit var presenter: InjectPagePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inject_example)

       NicoApplication.getInstance().wikiComponent.inject(this)

        presenter.setView(this)
        presenter.loadHomepage()

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
            R.string.error.errorDialog(this)
        }
    }
}
