package bangkit.project.fed.data.api

import com.google.gson.annotations.SerializedName

data class PredictResponse(

	@field:SerializedName("phase")
	val phase: List<PhaseItem?>? = null,

	@field:SerializedName("imageURL")
	val imageURL: String? = null,

	@field:SerializedName("label")
	val label: String? = null,

	@field:SerializedName("userId")
	val userId: String? = null,

	@field:SerializedName("timestamp")
	val timestamp: String? = null
)

data class PhaseItem(

	@field:SerializedName("kelas")
	val kelas: String? = null,

	@field:SerializedName("probabilitas")
	val probabilitas: Any? = null
)
