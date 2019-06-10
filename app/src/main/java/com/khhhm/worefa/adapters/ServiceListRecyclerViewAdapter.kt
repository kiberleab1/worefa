package com.khhhm.worefa.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.khhhm.worefa.R


import com.khhhm.worefa.ServiceListFragment.OnListFragmentInteractionListener
import com.khhhm.worefa.data.entitys.Services
import com.khhhm.worefa.databinding.FragmentCompanyBinding
import com.khhhm.worefa.databinding.FragmentServicelistBinding
import com.khhhm.worefa.dummy.DummyContent.DummyItem

import kotlinx.android.synthetic.main.fragment_servicelist.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class ServiceListRecyclerViewAdapter(

    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<ServiceListRecyclerViewAdapter.ViewHolder>() {
    private var listOfServices:List<Services> = emptyList()
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as DummyItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding= FragmentServicelistBinding.inflate(inflater)

        return ViewHolder(binding)
    }
    internal fun setService(services:List<Services>){
        listOfServices=services
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =holder.bind(listOfServices[position])

    override fun getItemCount(): Int = listOfServices.size

    inner class ViewHolder(val binding: FragmentServicelistBinding) : RecyclerView.ViewHolder(binding.root) {
        internal fun bind(services: Services){
            binding.services=services
            binding.executePendingBindings()
        }

    }
}
