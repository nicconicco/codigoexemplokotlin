package com.nico.projetopadroesnico.Features.Home.Activity

import android.os.Bundle
import com.cognizant.dor.Common.Extensions.setupToolbar
import com.nico.projetopadroesnico.Common.Activity.BaseActivity
import com.nico.projetopadroesnico.Features.RecyclerList.Adapter.TabsAdapterHomeManager
import com.nico.projetopadroesnico.Features.Home.Presenter.HomePresenter
import com.nico.projetopadroesnico.R
import kotlinx.android.synthetic.main.activity_master.*

class HomeActivity : BaseActivity() {


    lateinit var presenter : HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_master)
        initView()
    }

    private fun initView() {
        // Toolbar
        val actionBar = setupToolbar(R.id.toolbar_manager)
        actionBar.title = "Example"
        actionBar.setDisplayHomeAsUpEnabled(false)
        actionBar.setHomeButtonEnabled(false)

        presenter = HomePresenter()
        setupViewPagerTabs()
    }

    override fun onResume() {
        super.onResume()
        viewPager.currentItem = 2
    }
    private fun setupViewPagerTabs() {

        val list = presenter.getFragmentsForViewPager()
        viewPager.offscreenPageLimit = list.size

        viewPager.adapter = TabsAdapterHomeManager(context, supportFragmentManager, list)
        tabLayout.setupWithViewPager(viewPager)
    }
}
