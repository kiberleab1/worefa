package com.khhhm.worefa

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.khhhm.worefa.adapters.MainViewPagerAdapter
import com.khhhm.worefa.adapters.dummy.DummyContent
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*




class HomeFragment : Fragment(){

    private lateinit var viewPager: ViewPager
    private lateinit var mainPageAdapter:MainViewPagerAdapter
   private lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_home,container,false)
        val fragmentManager=super.getFragmentManager()
        if(fragmentManager!=null) {
            view.main_view_pager.adapter = MainViewPagerAdapter(fragmentManager,this.context)
            view.home_tab_menu.setupWithViewPager(view.main_view_pager)
        }

        view.home_tab_menu.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"))
        view.home_tab_menu.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"))

        return view
    }

}