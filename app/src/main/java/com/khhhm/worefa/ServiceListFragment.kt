package com.khhhm.worefa

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.khhhm.worefa.adapters.ServiceListRecyclerViewAdapter

import com.khhhm.worefa.dummy.DummyContent
import com.khhhm.worefa.dummy.DummyContent.DummyItem
import com.khhhm.worefa.viewmodels.ServiceViewModel

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [ServiceListFragment.OnListFragmentInteractionListener] interface.
 */
class ServiceListFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null
    private lateinit var servicesViewModel:ServiceViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val serviceListRecyclerViewAdapter:ServiceListRecyclerViewAdapter
        val view = inflater.inflate(R.layout.fragment_servicelist_list, container, false)
        servicesViewModel= ServiceViewModel(activity?.application!!)
                //ViewModelProviders.of(this).get(ServiceViewModel::class.java)
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                serviceListRecyclerViewAdapter = ServiceListRecyclerViewAdapter(listener)
                serviceListRecyclerViewAdapter.setService(servicesViewModel.getCompanyServices(5))
                adapter=serviceListRecyclerViewAdapter
            }

           // servicesViewModel.insertService()
           // servicesViewModel.getCompanyServices(5).observe(this, Observer { services ->serviceListRecyclerViewAdapter.setService(services) })

        }
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

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ServiceListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
