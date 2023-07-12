package com.example.liveznav.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.liveznav.R
import com.example.liveznav.data.Country

class MyListAdapter internal constructor(context: OnItemClickListener) : ListAdapter<Country, MyListAdapter.ArticleHolder>(
    DIFF_CALLBACK
) {
    private var listener: OnItemClickListener = context

    internal fun getItemAtPosition(position: Int): Country {
        return getItem(position)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ArticleHolder {
        val itemView = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.main_activity_list_item, viewGroup, false)
        return ArticleHolder(itemView)
    }



    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        val item = getItemAtPosition(position)

        holder.apply {
            tvCountryName.text = item.country_id
            tvProbability.text = item.probability.toString()

        }

    }


    interface OnItemClickListener {
        fun onItemClick(country: Country)
    }

    inner class ArticleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val tvCountryName: TextView = itemView.findViewById(R.id.tvCountry)
        internal val tvProbability: TextView = itemView.findViewById(R.id.tvProbability)
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(oldNote: Country, newNote: Country): Boolean {
                return oldNote.country_id == newNote.country_id
            }

            override fun areContentsTheSame(oldNote: Country, newNote: Country): Boolean {
                return oldNote.probability == newNote.probability

            }
        }
    }
}
