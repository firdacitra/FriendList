package com.firda.tugas12

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RvAdapter(
    private val context: Context,
    private val onItemClick: (position: Int, data: Friend) -> Unit,
    private val onDeleteClick: (position: Int, data: Friend) -> Unit,
    private val onUpdateClick: (position: Int, data: Friend) -> Unit
) : RecyclerView.Adapter<RvAdapter.FriendViewHolder>() {

    private var listItem = emptyList<Friend>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_teman, parent, false)
        return FriendViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val item = listItem[position]
        holder.nama.text = item.name
        holder.sekolah.text = item.school
        holder.hobi.text = item.hobby

        holder.itemView.setOnClickListener { onItemClick(position, item) }
        holder.deleteButton.setOnClickListener { onDeleteClick(position, item) }
        holder.updateButton.setOnClickListener { onUpdateClick(position, item) }
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Friend>) {
        this.listItem = list
        notifyDataSetChanged()
    }

    class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama: TextView = itemView.findViewById(R.id.nama)
        val sekolah: TextView = itemView.findViewById(R.id.sekolah)
        val hobi: TextView = itemView.findViewById(R.id.hobi)
        val deleteButton: Button = itemView.findViewById(R.id.delete_button)
        val updateButton: Button = itemView.findViewById(R.id.update_button)
    }
}
