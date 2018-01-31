package com.nico.projetopadroesnico.Features.Injection.Presenter

import com.nico.projetopadroesnico.Features.Injection.Model.HomepageResult
import com.raywenderlich.android.droidwiki.network.InjectPageNetwork
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject


class InjectPresenterImpl @Inject constructor(private val homepage: InjectPageNetwork) : InjectPagePresenter {

    private lateinit var homepageView: InjectPageView

//  private val client: OkHttpClient = OkHttpClient()
//  private val api: WikiApi = WikiApi(client)
//  private val homepage: Homepage = Homepage(api)

    override fun setView(homepageView: InjectPageView) {
        this.homepageView = homepageView
    }

    override fun loadHomepage() {
        homepageView.displayLoading()
        homepage.get().enqueue(object : Callback {
            override fun onResponse(call: Call?, response: Response?) {
                homepageView.dismissLoading()
                if (response?.isSuccessful == true) {
                    response.let {
                        HomepageResult(it).homepage?.let {
                            homepageView.displayHomepage(it)
                        } ?: run {
                            homepageView.displayError(response.message())
                        }
                    }
                } else {
                    homepageView.displayError(response?.message())
                }
            }

            override fun onFailure(call: Call?, t: IOException?) {
                homepageView.displayError(t?.message)
                t?.printStackTrace()
            }
        })
    }
}