package com.khhhm.worefa

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.khhhm.worefa.data.entitys.User
import com.khhhm.worefa.viewmodels.UserViewModel
import com.khhhm.worefa.databinding.FragmentLoginBinding

class SignupFragment : Fragment() {
  //  private lateinit var  binding;
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View?{
        // Inflate the layout for this fragment
           // val  view:View?=inflater.inflate(R.layout.fragment_login, container, false)
        val binding:FragmentLoginBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        val view:View=binding.root
        userViewModel=ViewModelProviders.of(this).get(UserViewModel::class.java)

         binding.user
        binding.userViewModel=this.userViewModel

        binding.executePendingBindings()
        return view
    }


}