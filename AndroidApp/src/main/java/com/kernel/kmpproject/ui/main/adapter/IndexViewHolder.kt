package com.kernel.kmpproject.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kernel.kmpproject.R
import com.kernel.kmpproject.domain.model.Index
import kotlinx.android.synthetic.main.item_index.view.*

class IndexViewHolder(view: ViewGroup) :
  RecyclerView.ViewHolder(LayoutInflater.from(view.context).inflate(R.layout.item_index, view, false)) {

  fun bind(index: Index) {
    with(itemView) {
      changes.text = index.changes.toString()
      indexName.text = index.indexName
      ticker.text = index.ticker
      price.text = index.price.toString()
    }
  }
}