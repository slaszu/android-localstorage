package pl.slaszu.localstorage.sample.application

import kotlinx.serialization.Serializable

@Serializable
data class SampleModel(
    val name: String = "",
    val age: Int = 0,
    val params: List<SampleParamModel> = emptyList()
) {
    companion object {
        fun createFulfilled(): SampleModel {
            return SampleModel(
                name = "test",
                age= 10,
                params = listOf(
                    SampleParamModel("colour","red"),
                    SampleParamModel("style", "normal")
                )
            )
        }
    }
}