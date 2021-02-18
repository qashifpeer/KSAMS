package com.kashifpeer.ksams.fragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kashifpeer.ksams.R
import com.kashifpeer.ksams.model.Classwise
import kotlinx.android.synthetic.main.class_row.view.*
import kotlinx.android.synthetic.main.std_row.view.*

class ClasswiseAdapter: RecyclerView.Adapter<ClasswiseAdapter.ClasswiseViewHolder>(){

    private var classList = emptyList<Classwise>()

    class ClasswiseViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClasswiseViewHolder {
        return ClasswiseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.class_row, parent, false))
    }

    override fun onBindViewHolder(holder: ClasswiseViewHolder, position: Int) {
        val currentItem = classList[position]

        holder.itemView.viewClass.text = currentItem.className


        holder.itemView.classRowLayout.setOnClickListener {

            /*val action=ClasswiseFragmentDirections.actionClasswiseFragmentToAddClasswiseFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
*/
        }
    }

    override fun getItemCount(): Int {
        return classList.size
    }
    fun setData(classwise: List<Classwise>){
        this.classList = classwise
        notifyDataSetChanged()
    }
}