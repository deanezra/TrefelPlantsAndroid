package com.deanezra.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Plant {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("common_name")
    @Expose
    var commonName: String? = null

    @SerializedName("slug")
    @Expose
    var slug: String? = null

    @SerializedName("scientific_name")
    @Expose
    var scientificName: String? = null

    @SerializedName("year")
    @Expose
    var year: String? = null

    @SerializedName("bibliography")
    @Expose
    var bibliography: String? = null

    @SerializedName("author")
    @Expose
    var author: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("rank")
    @Expose
    var rank: String? = null

    @SerializedName("family_common_name")
    @Expose
    var familyCommonName: String? = null

    @SerializedName("genus_id")
    @Expose
    var genusId: Int? = null

    @SerializedName("image_url")
    @Expose
    var imageUrl: String? = null

    @SerializedName("genus")
    @Expose
    var genus: String? = null

    @SerializedName("family")
    @Expose
    var family: String? = null
}
