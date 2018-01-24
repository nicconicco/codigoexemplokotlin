package com.nico.projetopadroesnico.Features.RecyclerList.Model

data class RedditNews(
        val after: String,
        val before: String,
        val news: List<RedditNewsItem>)