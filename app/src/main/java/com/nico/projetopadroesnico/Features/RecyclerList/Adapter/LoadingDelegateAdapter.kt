package com.nico.projetopadroesnico.Features.RecyclerList.Adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.droidcba.kedditbysteps.commons.adapter.ViewType
import com.droidcba.kedditbysteps.commons.adapter.ViewTypeDelegateAdapter
import com.nico.projetopadroesnico.Common.Extension.inflate
import com.nico.projetopadroesnico.R


class LoadingDelegateAdapter : ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): LoadingViewHolder {
        return LoadingViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class LoadingViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.news_item_loading))
}