package com.khhhm.worefa.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.khhhm.worefa.AppointFragment
import com.khhhm.worefa.CompanyFragment
import com.khhhm.worefa.ServiceListFragment


class MainViewPagerAdapter(val fragmentManager: FragmentManager,val context:Context?) : FragmentPagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return 4

    }

    override fun getItem(position: Int): Fragment {

        if (position == 0) {
            return AppointFragment()
        }
        else if(position==1){
            return CompanyFragment()
        }else if(position==2){
            Log.i("service List","Fragment")
            return ServiceListFragment()
        }
        return AppointFragment()

    }

    var imagesResId = listOf(
        com.khhhm.worefa.R.drawable.ic_appoint,
        com.khhhm.worefa.R.drawable.ic_appoint,
        com.khhhm.worefa.R.drawable.ic_appoint,
        com.khhhm.worefa.R.drawable.ic_appoint

    )
    var title=listOf(
        "Home",
        "Company",
        "Settings",
        "About"
    )



    override fun getPageTitle(position: Int): CharSequence? {
        if(context!=null) {
            var image: Drawable? = ContextCompat.getDrawable(context, imagesResId[position])
             var width=image?.intrinsicWidth
            var height=image?.intrinsicHeight
            if(width != null && height != null) {
                image?.setBounds(0, 0, width,height)
            }
            var sb:SpannableString = SpannableString(" "+title[position])
           // var imageSpan: ImageSpan = ImageSpan(image,ImageSpan.ALIGN_BASELINE)
            //sb.setSpan(imageSpan,0,1,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            return sb
        }
      return ""
    }

}
