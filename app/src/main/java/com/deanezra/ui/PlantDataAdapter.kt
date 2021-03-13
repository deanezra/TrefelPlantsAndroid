package com.deanezra.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.deanezra.network.model.Plant
import com.deanezra.databinding.ItemPlantBinding
import javax.inject.Inject

class PlantDataAdapter @Inject constructor():
    RecyclerView.Adapter<PlantDataAdapter.NetworkDataViewHolder>() {

    private lateinit var binding: ItemPlantBinding

    private var networkDataList = mutableListOf<Plant>()

    var onItemClick: ((recordList: Plant) -> Unit)? = null

    var context: Context? = null

    fun addItemList(networkDataList: MutableList<Plant>) {
        this.networkDataList = networkDataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NetworkDataViewHolder {
        binding = ItemPlantBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return NetworkDataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NetworkDataViewHolder, position: Int) {
        holder.bindView(networkDataList.get(position))
    }

    override fun getItemCount(): Int {
        return networkDataList.size
    }

    inner class NetworkDataViewHolder(val binding: ItemPlantBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView(plant: Plant) {
            binding.tvCommonName.text = plant.commonName
            binding.tvScientificName.text = plant.scientificName
            binding.tvYear.text = plant.year

            context?.let {
                Glide.with(it).load(plant.imageUrl).into(binding.plantImageView)
            }

            itemView.setOnClickListener {
                onItemClick?.invoke(plant)
            }
        }
    }
}
