package com.deanezra.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// The Trefle plants api returns a list of plants in Json array called 'data',
// a links object (with urls) and a meta object (with a total counts of all plants).
// So we use this 'base' class to allow Retrofit serialize the data into a list of Plant objects:
class BasePlants {
    @SerializedName("data")
    @Expose
    var plantlist: MutableList<Plant>? = null

    // For simplicity, We dont care about links and meta in this app
    /*
    @SerializedName("links")
    @Expose
    var links: Int? = null

    @SerializedName("meta")
    @Expose
    var meta: String? = null

     */
}
