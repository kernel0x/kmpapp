package com.kernel.kmpproject.ui.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kernel.kmpproject.domain.model.Index
import com.kernel.kmpproject.domain.model.Indexes
import kotlinx.coroutines.Job

class IndexesAdapter(private val listener: (Index) -> Unit) : RecyclerView.Adapter<IndexViewHolder>() {

  var items: Indexes = emptyList()
    set(value) {
      field = value
      notifyDataSetChanged()
    }

  override fun getItemCount() = items.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndexViewHolder {
    return IndexViewHolder(parent)
  }

  override fun onBindViewHolder(holder: IndexViewHolder, position: Int) {
    holder.bind(items[position])
    holder.itemView.setOnClickListener {
      listener.invoke(items[position])
    }
  }
}

