package bangkit.project.fed.data.api

import com.google.gson.annotations.SerializedName

data class PredictResponse (
    @field:SerializedName("error")
    val error: Boolean,
    @field:SerializedName("message")
    val message: String
)