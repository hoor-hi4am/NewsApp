package com.route.newsapp.data.api.model

import com.google.gson.annotations.SerializedName
data class SourcesResponse(

    @field:SerializedName("sources")
	val sources: List<SourceDM>? = null,

    @field:SerializedName("status")
	val status: String? = null
)