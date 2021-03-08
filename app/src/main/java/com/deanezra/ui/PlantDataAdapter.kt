package com.deanezra.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deanezra.network.model.Plant
import com.deanezra.R
import kotlinx.android.synthetic.main.item_plant.view.*
import javax.inject.Inject

class PlantDataAdapter @Inject constructor():
    RecyclerView.Adapter<PlantDataAdapter.NetworkDataViewHolder>() {

    private var networkDataList = mutableListOf<Plant>()

    var onItemClick: ((recordList: Plant) -> Unit)? = null

    var context: Context? = null
    var layoutInflater: LayoutInflater? = null

    fun addItemList(networkDataList: MutableList<Plant>) {
        this.networkDataList = networkDataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NetworkDataViewHolder {
        if (layoutInflater == null) {
            context = parent.context
            layoutInflater = LayoutInflater.from(parent.context)
        }

        val itemView = layoutInflater!!
            .inflate(R.layout.item_plant, parent, false)
        return NetworkDataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NetworkDataViewHolder, position: Int) {
        holder.bindView(networkDataList.get(position))
    }

    override fun getItemCount(): Int {
        return networkDataList.size
    }

    inner class NetworkDataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindView(plant: Plant) {
            itemView.tvCommonName.text = plant.commonName
            itemView.tvScientificName.text = plant.scientificName
            itemView.tvYear.text = plant.year

            Glide.with(context!!).load(plant.imageUrl).into(itemView.ivPlantImage);

            itemView.setOnClickListener {
                onItemClick?.invoke(plant)
            }
        }
    }
}
