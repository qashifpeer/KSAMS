package com.kashifpeer.ksams.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kashifpeer.ksams.R
import com.kashifpeer.ksams.model.Student
import kotlinx.android.synthetic.main.std_row.view.*

class StdAdapter: RecyclerView.Adapter<StdAdapter.StdViewHolder>() {
    private var stdList = emptyList<Student>()

    class StdViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StdViewHolder {
        return StdViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.std_row, parent, false))

    }

    override fun onBindViewHolder(holder: StdViewHolder, position: Int) {
        val currentItem = stdList[position]

        holder.itemView.viewName_txt.text = currentItem.stdName
        holder.itemView.viewFatherName_txt.text = currentItem.fatherName
        holder.itemView.viewClass_txt.text = currentItem.stdClass
        holder.itemView.viewAdNum_txt.text = currentItem.adNum.toString()

        holder.itemView.stdRowLayout.setOnClickListener {

            val action =ViewStdFragmentDirections.actionViewStdFragmentToUpdateStdFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return stdList.size
    }
    fun setData(student: List<Student>){
        this.stdList = student
        notifyDataSetChanged()
    }
}

