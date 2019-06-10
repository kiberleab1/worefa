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
import com.khhhm.worefa.adapters.MyAppointRecyclerViewAdapter

import com.khhhm.worefa.adapters.dummy.DummyContent
import com.khhhm.worefa.adapters.dummy.DummyContent.DummyItem
import com.khhhm.worefa.data.entitys.Appointment
import com.khhhm.worefa.viewmodels.AppointmentViewModel

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [AppointFragment.OnListFragmentInteractionListener] interface.
 */
class AppointFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1
    private lateinit var appointmentViewModel: AppointmentViewModel
    private var listener: OnListFragmentInteractionListener? = null

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
        val view = inflater.inflate(R.layout.fragment_appoint_list, container, false)

        val appointRecyclerViewAdapter:MyAppointRecyclerViewAdapter
        // Set the adapter
        appointmentViewModel=ViewModelProviders.of(this).get(AppointmentViewModel::class.java)
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                appointRecyclerViewAdapter = MyAppointRecyclerViewAdapter(listener)
                adapter=appointRecyclerViewAdapter
            }

            appointmentViewModel.getAllMyAppointments().observe(this, Observer { appoitments -> appointRecyclerViewAdapter.setAppointments(appoitments) })

        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
           throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
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
        fun onListFragmentInteraction(item: Any)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            AppointFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
