package com.khhhm.worefa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.khhhm.worefa.AppointFragment.OnListFragmentInteractionListener

import com.khhhm.worefa.data.entitys.Appointment

class MainActivity : AppCompatActivity(), OnListFragmentInteractionListener {

    override fun onListFragmentInteraction(item: Any) {
        var fragment:Any
        if(item is Appointment) {
            fragment=AppointFragment()
            supportFragmentManager.beginTransaction().add(R.id.main_frame, fragment).addToBackStack(null)
                .commit()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val homeFragment=HomeFragment()

        supportFragmentManager.beginTransaction().replace(R.id.main_frame,homeFragment).commit()
        setContentView(R.layout.activity_main)


    }

}
