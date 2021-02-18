package com.kashifpeer.ksams.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kashifpeer.ksams.R
import com.kashifpeer.ksams.viewmodel.StdViewModel
import kotlinx.android.synthetic.main.fragment_view_std.view.*
import kotlinx.coroutines.InternalCoroutinesApi


class ViewStdFragment : Fragment() {

    @InternalCoroutinesApi
    private lateinit var mStdViewModel: StdViewModel

    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_view_std, container, false)

        //************** code for Spinner Class of Student****** Begins hre*****
       /* view.viewClassSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View,
                    position: Int,
                    id: Long
            ) {
                // do something upon option selection




                val selectedItem=parent!!.getItemAtPosition(position)
                //Toast.makeText(requireContext(), "$selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // can leave this empty
            }
        }
*/

        //************** code for Spinner Class of Student****** End hre*****



        // Recyclerview
        val adapter = StdAdapter()
        val recyclerView = view.recViewStd
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        // UserViewModel
        mStdViewModel = ViewModelProvider(this).get(StdViewModel::class.java)
        mStdViewModel.readAllData.observe(viewLifecycleOwner, Observer { student ->
            adapter.setData(student)
        })
        view.addStd_fa.setOnClickListener {
        findNavController().navigate(R.id.action_viewStdFragment_to_addStdFragment)
        }
        // Add menu
        setHasOptionsMenu(true)
        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    @InternalCoroutinesApi
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteAllStudents()
        }
        return super.onOptionsItemSelected(item)
    }


    @InternalCoroutinesApi
    private fun deleteAllStudents() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mStdViewModel.deleteAllStudents()
            Toast.makeText(
                    requireContext(),
                    "Successfully removed everything",
                    Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()    }


}