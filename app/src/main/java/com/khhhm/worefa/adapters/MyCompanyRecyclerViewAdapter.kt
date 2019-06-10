package com.khhhm.worefa.adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData


import com.khhhm.worefa.CompanyFragment.OnListFragmentInteractionListener
import com.khhhm.worefa.R
import com.khhhm.worefa.data.entitys.Company
import com.khhhm.worefa.databinding.FragmentCompanyBinding
import com.khhhm.worefa.dummy.DummyContent.DummyItem

import kotlinx.android.synthetic.main.fragment_company.view.*
import kotlin.random.Random

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class MyCompanyRecyclerViewAdapter(

    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MyCompanyRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private var listOfCompanys: List<Company> = emptyList()
    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Company
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
        //    mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      //  val view = LayoutInflater.from(parent.context)
       //     .inflate(R.layout.fragment_company, parent, false)
        val inflater=LayoutInflater.from(parent.context)
        val binding=FragmentCompanyBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val color = Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))
        //  holder.icon.background as Drawable

        (holder.icon.background as GradientDrawable).setColor(color)
        holder.bind(listOfCompanys[position])
    }
    //{
        /*val item = mValues.get(position)!!
        holder.mIdView.text = item.name
        holder.mContentView.text = item.address

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }*/
    //}
    internal fun setCompanys(companys:List<Company>){
        listOfCompanys=companys
        notifyDataSetChanged()
    }

    override fun getItemCount():Int =listOfCompanys.size
    inner class ViewHolder(val binding: FragmentCompanyBinding):RecyclerView.ViewHolder(binding.root){
        var icon:TextView
        init{
            icon=binding.tvIcon
        }
        fun bind(company:Company){
            binding.companyItem=company
            binding.executePendingBindings()
        }
    }
    //val mView: View) : RecyclerView.ViewHolder(mView) {
  //      val mIdView: TextView = mView.item_number
    //    val mContentView: TextView = mView.content

     //   override fun toString(): String {
     //       return super.toString() + " '" + mContentView.text + "'"
    //    }
  //  }
}
