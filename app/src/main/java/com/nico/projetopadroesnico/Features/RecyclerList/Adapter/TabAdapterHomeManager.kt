package com.nico.projetopadroesnico.Features.RecyclerList.Adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.nico.projetopadroesnico.Features.Bank.Fragment.BankFragment
import com.nico.projetopadroesnico.Features.Maps.Fragment.MapsFragment
import com.nico.projetopadroesnico.Features.Home.Fragment.HomeFragment
import com.nico.projetopadroesnico.Features.Injection.Fragment.InjectFragment
import com.nico.projetopadroesnico.R

/**
 * Created by 653835 on 27/11/2017.
 */


class TabsAdapterHomeManager(private val context: Context, fm: FragmentManager, private val listfragment: MutableList<Fragment>) : FragmentPagerAdapter(fm) {

    override fun getCount(): Int = listfragment.size

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> context.getString(R.string.noticias)
            1 -> context.getString(R.string.banco)
            2 -> context.getString(R.string.mapa)
            3 -> context.getString(R.string.injecao_dependencia)
            else -> {
                context.getString(R.string.hoje)
            }
        }
    }

    override fun getItem(position: Int): Fragment {

        return return when (position) {
            0 -> HomeFragment()
            1 -> BankFragment()
            2 -> MapsFragment()
            3 -> InjectFragment()
            else -> {
                HomeFragment()
            }
        }
    }
}
