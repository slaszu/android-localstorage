package pl.slaszu.localstorage.sample.application

import kotlinx.serialization.Serializable

@Serializable
data class SampleParamModel(
    val key: String,
    val value: String
)