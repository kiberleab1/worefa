package com.khhhm.worefa.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.khhhm.worefa.CompanyFragment
import com.khhhm.worefa.InfoDeskFragment
import com.khhhm.worefa.NewsFeedFragment

class CompanyViewPagerAdapter(val fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
       when(position){
           0 -> return CompanyFragment()
           1 -> return NewsFeedFragment()
           2 -> return InfoDeskFragment()
       }
        return InfoDeskFragment()
    }

    override fun getCount(): Int {
     return  3
    }
}