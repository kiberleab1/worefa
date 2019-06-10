package com.khhhm.worefa

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import com.khhhm.worefa.adapters.MyCompanyRecyclerViewAdapter

import com.khhhm.worefa.dummy.DummyContent
import com.khhhm.worefa.dummy.DummyContent.DummyItem
import com.khhhm.worefa.viewmodels.CompanyViewModel


class CompanyFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var companyViewModel: CompanyViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_company_list, container, false)
        val application=activity?.application
        val myCompanyRecyclerViewAdapter:MyCompanyRecyclerViewAdapter
        if(application!=null) {
            companyViewModel = ViewModelProviders.of(this).get(CompanyViewModel::class.java)
        }// Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)

                }

                addItemDecoration(DividerItemDecoration(activity?.application, DividerItemDecoration.VERTICAL))


                            //         companyViewModel.getCompanys().observe(, Observer { companys->companys.let{adapter} })
                myCompanyRecyclerViewAdapter= MyCompanyRecyclerViewAdapter( listener)
                adapter=myCompanyRecyclerViewAdapter
            }
            companyViewModel.getCompanys().observe(this, Observer { companys -> myCompanyRecyclerViewAdapter.setCompanys(companys)  })
            companyViewModel.reload()
        }
       //if( myCompanyRecyclerViewAdapter==null){myCompanyRecyclerViewAdapter= MyCompanyRecyclerViewAdapter( listener)}

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
//            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: DummyItem?)
    }

}
