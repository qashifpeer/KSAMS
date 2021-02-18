package com.kashifpeer.ksams.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kashifpeer.ksams.R
import com.kashifpeer.ksams.viewmodel.ClasswiseViewModel
import kotlinx.android.synthetic.main.fragment_classwise.view.*
import kotlinx.coroutines.InternalCoroutinesApi


class ClasswiseFragment : Fragment() {

    @InternalCoroutinesApi
    private lateinit var mClasswiseViewModel: ClasswiseViewModel

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_classwise, container, false)

        val adapter = ClasswiseAdapter()
        val recyclerView = view.recViewClasswise
        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        // UserViewModel
        mClasswiseViewModel = ViewModelProvider(this).get(ClasswiseViewModel::class.java)
        mClasswiseViewModel.readAllData.observe(viewLifecycleOwner, Observer { classwise ->
            adapter.setData(classwise)
        })
        view.addClass_fa.setOnClickListener {
            findNavController().navigate(R.id.action_classwiseFragment_to_addClasswiseFragment)
        }
        // Add menu
        setHasOptionsMenu(true)


        return view
    }


}