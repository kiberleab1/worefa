package com.khhhm.worefa.adapters

import android.content.res.Resources
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.khhhm.worefa.R

import com.khhhm.worefa.AppointFragment.OnListFragmentInteractionListener
import com.khhhm.worefa.adapters.dummy.DummyContent.DummyItem
import com.khhhm.worefa.data.entitys.Appointment
import com.khhhm.worefa.data.entitys.Company
import com.khhhm.worefa.databinding.FragmentAppointBinding
import com.khhhm.worefa.databinding.FragmentCompanyBinding
import com.khhhm.worefa.databinding.FragmentCompanyBinding.inflate

import kotlinx.android.synthetic.main.fragment_appoint.view.*
import kotlin.random.Random

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyAppointRecyclerViewAdapter(
    private val fragmentListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyAppointRecyclerViewAdapter.ViewHolder>() {
    private var listOfAppointments:List<Appointment> = emptyList()
    private var listOfCompanys:List<Company> = emptyList()
    private val appointmentOnCliclListener: View.OnClickListener

    init {
        appointmentOnCliclListener = View.OnClickListener { v ->
            val item = v.tag as Appointment
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            fragmentListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding=FragmentAppointBinding.inflate(inflator)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        var appointmentItem=listOfAppointments[position]
        val color = Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
      //  holder.icon.background as Drawable
        holder.icon.text=appointmentItem.time.substring(0,1)
       (holder.icon.background as GradientDrawable).setColor(color)
        holder.bind(appointmentItem)
        with(holder.itemView){
                tag=appointmentItem
                setOnClickListener(appointmentOnCliclListener)
        }
    }
    fun setAppointments(appointments:List<Appointment>){
        listOfAppointments=appointments
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listOfAppointments.size

    inner class ViewHolder(val binding: FragmentAppointBinding) : RecyclerView.ViewHolder(binding.root) {
        var icon:TextView
       // var res:Resources= Resources.getSystem()
     //   var drawable:Drawable?= ResourcesCompat.getDrawable(res,R.drawable.rounded_drawable,null)
        init {
            icon=binding.root.findViewById(R.id.tvIcon)

     //       icon.background=drawable
        }
        internal fun  bind(appointment:Appointment){
            binding.appointment=appointment
            binding
            binding.executePendingBindings()
        }
    }
}
