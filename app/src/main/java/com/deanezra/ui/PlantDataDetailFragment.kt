package com.deanezra.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.deanezra.R
import com.deanezra.network.model.Plant
import kotlinx.android.synthetic.main.dialog_plant_data_detail.*
import kotlinx.android.synthetic.main.dialog_plant_data_detail.ivPlantImage
import kotlinx.android.synthetic.main.item_plant.*
import kotlinx.android.synthetic.main.item_plant.view.*

class PlantDataDetailFragment(private var plant: Plant) :
    BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.dialog_plant_data_detail, container, false)

    private fun hideAllFieldsAtStart() {
        tvDialogDataDetailCommonName.visibility = View.GONE
        tvDialogDataDetailFamily.visibility = View.GONE
        tvDialogDataDetailScientificName.visibility = View.GONE
        tvDialogDataDetailYear.visibility = View.GONE
        tvDialogDataDetailBibliography.visibility = View.GONE
        tvDialogDataDetailStatus.visibility = View.GONE
        tvDialogDataDetailRank.visibility = View.GONE
        tvDialogDataDetailGenus.visibility = View.GONE
        tvDialogDataDetailAuthor.visibility = View.GONE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideAllFieldsAtStart()

        // Set the values into the view if there are populated:
        plant.commonName?.let {
            tvDialogDataDetailCommonName.text = "${it} details:"
            tvDialogDataDetailCommonName.visibility = View.VISIBLE
        }

        plant.family?.let {
            tvDialogDataDetailFamily.text = "Family: ${it}"
            tvDialogDataDetailFamily.visibility = View.VISIBLE
        }

        plant.scientificName?.let {
            tvDialogDataDetailScientificName.text = "Scientific name: ${it}"
            tvDialogDataDetailScientificName.visibility = View.VISIBLE
        }

        plant.year?.let {
            tvDialogDataDetailYear.text = "Year: " + plant.year
            tvDialogDataDetailYear.visibility = View.VISIBLE
        }

        plant.bibliography?.let {
            tvDialogDataDetailBibliography.text = "Bibliography: ${it}"
            tvDialogDataDetailBibliography.visibility = View.VISIBLE
        }

        plant.status?.let {
            tvDialogDataDetailStatus.text = "Status: ${it}"
            tvDialogDataDetailStatus.visibility = View.VISIBLE
        }

        plant.rank?.let {
            tvDialogDataDetailRank.text = "Rank: ${it}"
            tvDialogDataDetailRank.visibility = View.VISIBLE
        }

        plant.genus?.let {
            tvDialogDataDetailGenus.text = "Genus: ${it}"
            tvDialogDataDetailGenus.visibility = View.VISIBLE
        }

        plant.author?.let {
            tvDialogDataDetailAuthor.text = "Author: ${it}"
            tvDialogDataDetailAuthor.visibility = View.VISIBLE
        }

        plant.imageUrl?.let {
            Glide.with(context!!).load(it).into(ivPlantImage);
        }

        ivDialogClose.setOnClickListener{
            dismiss()
        }

    }


    private fun getDataDetail(): String {
        var plantData = ""

            if (plant!=null) {
                plantData = " Common Name-> " + plant.commonName + " \n\n"+
                    " Family-> " + plant.family + " \n\n"+
                    " Family Common Name -> " + plant.familyCommonName + " \n\n"+
                    " Scientify name -> " + plant.scientificName + " \n\n"
        }
        return plantData
    }
}