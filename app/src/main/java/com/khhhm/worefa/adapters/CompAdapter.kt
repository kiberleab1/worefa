package com.khhhm.worefa.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.khhhm.worefa.R
import com.khhhm.worefa.data.entitys.Company
import com.khhhm.worefa.databinding.FragmentCompanyBinding
import java.util.Random

/**
 * Created by abdalla on 12/18/17.
 */

public class CompAdapter(private val mContext: Context, private val mEmailData: List<Company>) : RecyclerView.Adapter<CompAdapter.MailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding=FragmentCompanyBinding.inflate(inflater,parent,false)
        return MailViewHolder(binding.layout,binding)
    }

    override fun onBindViewHolder(holder: MailViewHolder, position: Int) {
        /*holder.mIcon.setText(mEmailData[position].address.substring(0,1))
        holder.mSender.text = mEmailData[position].name
        holder.mEmailDetails.text = mEmailData[position].address
        */
        holder.bind(mEmailData[position].address,mEmailData[position].name,mEmailData[position].name)
        val mRandom = Random()
        val color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256))
       //                (holder.mIcon.background as GradientDrawable).setColor(color)


    }

    override fun getItemCount(): Int {
        return mEmailData.size
    }

    class MailViewHolder(itemView: View,binding:FragmentCompanyBinding) : RecyclerView.ViewHolder(itemView) {

        val TAG=FragmentCompanyBinding::class.java.simpleName
        val mBinding:FragmentCompanyBinding
        //var mIcon: TextView
       /* var mSender: TextView
        var mEmailDetails: TextView
*/
        init {
    //
    //        mIcon = itemView.findViewById(R.id.tvIcon)
  //          mSender = itemView.findViewById(R.id.tvEmailSender)
   //         mEmailDetails = itemView.findViewById(R.id.tvEmailDetails)*/

            mBinding=binding
        }
        fun bind(icon:String,address:String,name:String){
            mBinding.tvIcon.text=icon.substring(0,1)
            mBinding.tvEmailDetails.text=address
            mBinding.tvEmailSender.text=name
        }
    }
}


