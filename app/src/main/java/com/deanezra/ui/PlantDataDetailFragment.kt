package com.deanezra.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.deanezra.databinding.DialogPlantDataDetailBinding
import com.deanezra.network.model.Plant

class PlantDataDetailFragment(private var plant: Plant) :
    BottomSheetDialogFragment() {

    private var _binding: DialogPlantDataDetailBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogPlantDataDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    private fun hideAllFieldsAtStart() {
        binding.detailsCommonNameTextView.visibility = View.GONE
        binding.detailsFamilyTextView.visibility = View.GONE
        binding.detailsScientificNameTextView.visibility = View.GONE
        binding.detailsYearTextView.visibility = View.GONE
        binding.detailsBibliographyTextView.visibility = View.GONE
        binding.detailsStatusTextView.visibility = View.GONE
        binding.detailsRankTextView.visibility = View.GONE
        binding.detailsGenusTextView.visibility = View.GONE
        binding.detailsAuthorTextView.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideAllFieldsAtStart()

        // Set the values into the view if there are populated:
        plant.commonName?.let {
            binding.detailsCommonNameTextView.text = "${it} details:"
            binding.detailsCommonNameTextView.visibility = View.VISIBLE
        }

        plant.family?.let {
            binding.detailsFamilyTextView.text = "Family: ${it}"
            binding.detailsFamilyTextView.visibility = View.VISIBLE
        }

        plant.scientificName?.let {
            binding.detailsScientificNameTextView.text = "Scientific name: ${it}"
            binding.detailsScientificNameTextView.visibility = View.VISIBLE
        }

        plant.year?.let {
            binding.detailsYearTextView.text = "Year: " + plant.year
            binding.detailsYearTextView.visibility = View.VISIBLE
        }

        plant.bibliography?.let {
            binding.detailsBibliographyTextView.text = "Bibliography: ${it}"
            binding.detailsBibliographyTextView.visibility = View.VISIBLE
        }

        plant.status?.let {
            binding.detailsStatusTextView.text = "Status: ${it}"
            binding.detailsStatusTextView.visibility = View.VISIBLE
        }

        plant.rank?.let {
            binding.detailsRankTextView.text = "Rank: ${it}"
            binding.detailsRankTextView.visibility = View.VISIBLE
        }

        plant.genus?.let {
            binding.detailsGenusTextView.text = "Genus: ${it}"
            binding.detailsGenusTextView.visibility = View.VISIBLE
        }

        plant.author?.let {
            binding.detailsAuthorTextView.text = "Author: ${it}"
            binding.detailsAuthorTextView.visibility = View.VISIBLE
        }

        plant.imageUrl?.let {
            Glide.with(context!!).load(it).into(binding.plantImageView);
        }

        binding.ivDialogClose.setOnClickListener{
            dismiss()
        }

    }
}