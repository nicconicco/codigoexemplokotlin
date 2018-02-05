package com.nico.projetopadroesnico.Features.Home.Presenter

import android.support.v4.app.Fragment
import com.nico.projetopadroesnico.Features.Home.Fragment.HomeFragment
import com.nico.projetopadroesnico.Features.RecyclerList.HttpService.MasterService
import com.nico.projetopadroesnico.Features.RecyclerList.Model.RedditNews
import com.nico.projetopadroesnico.Features.RecyclerList.Model.RedditNewsItem
import com.nico.projetopadroesnico.Features.RecyclerList.Model.RedditNewsResponse
import ru.gildor.coroutines.retrofit.Result
import ru.gildor.coroutines.retrofit.awaitResult

class HomePresenter {


    suspend fun getNews(after: String, limit: String = "10"): RedditNews {
        val result = MasterService().getNews(after, limit).awaitResult()
        return when (result) {
            is Result.Ok -> process(result.value)
            is Result.Error -> throw Throwable("HTTP error: ${result.response.message()}")
            is Result.Exception -> throw result.exception
            else -> {
                throw Throwable("Something went wrong, please try again later.")
            }
        }
    }

    private fun process(response: RedditNewsResponse): RedditNews {
        val dataResponse = response.data
        val news = dataResponse.children.map {
            val item = it.data
            RedditNewsItem(item.author, item.title, item.num_comments,
                    item.created, item.thumbnail, item.url)
        }
        return RedditNews(
                dataResponse.after.orEmpty(),
                dataResponse.before.orEmpty(),
                news)
    }

    fun getFragmentsForViewPager(): MutableList<Fragment> {
        val master = HomeFragment()
        var array = mutableListOf<Fragment>()

        array.add(master)
        array.add(master)
        array.add(master)
        array.add(master)
        array.add(master)

        return array
    }
}