package com.nico.projetopadroesnico.Features.Home.Fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nico.projetopadroesnico.Common.Extension.androidLazy
import com.nico.projetopadroesnico.Common.Fragment.BaseFragment
import com.nico.projetopadroesnico.Common.Util.InfiniteScrollListener
import com.nico.projetopadroesnico.Features.RecyclerList.Adapter.NewsAdapter
import com.nico.projetopadroesnico.Features.RecyclerList.Adapter.NewsDelegateAdapter
import com.nico.projetopadroesnico.Features.RecyclerList.Model.RedditNews
import com.nico.projetopadroesnico.Features.Home.Presenter.HomePresenter
import com.nico.projetopadroesnico.R
import kotlinx.android.synthetic.main.fragment_master.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch


open class HomeFragment : BaseFragment(), NewsDelegateAdapter.onViewSelectedListener {

    lateinit var presenter : HomePresenter
    private var redditNews: RedditNews? = null
    private var isVisibleToUser = false

    override fun onItemSelected(url: String?) {
        if (url.isNullOrEmpty()) {
            Snackbar.make(news_list, "No URL assigned to this news", Snackbar.LENGTH_LONG).show()
        } else {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }
    private val newsAdapter by androidLazy { NewsAdapter(this) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, icicle: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_master, container, false)
    }

    companion object {
        private val KEY_REDDIT_NEWS = "redditNews"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = HomePresenter()

        news_list.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            layoutManager = linearLayout
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ requestNews() }, linearLayout))
        }

        news_list.adapter = newsAdapter

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        this.isVisibleToUser = isVisibleToUser
        if (isVisibleToUser) {
            requestNews()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val news = newsAdapter.getNews()
    }
    private fun requestNews() {
        /**
         * first time will send empty string for 'after' parameter.
         * Next time we will have redditNews set with the next page to
         * navigate with the 'after' param.
         */

        job = launch(UI) {
            try {
                val retrievedNews = presenter.getNews(redditNews?.after.orEmpty())
                redditNews = retrievedNews
                newsAdapter.addNews(retrievedNews.news)
            } catch (e: Throwable) {
                if (isVisible) {
                    Snackbar.make(news_list, e.message.orEmpty(), Snackbar.LENGTH_INDEFINITE)
                            .setAction("RETRY") { requestNews() }
                            .show()
                }
            }
        }
    }
}