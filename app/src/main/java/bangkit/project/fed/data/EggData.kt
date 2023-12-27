package bangkit.project.fed.data

import com.google.firebase.Timestamp

data class EggData(
    val phases : List<Phase> = emptyList(),
    val userId: String? = null,
    val label: String? = null,
    val imageURL : String? = null,
    val timestamp : Timestamp? = null
)

data class Phase(
    val kelas : String? = null,
    val probabilitas : Double? = null,
)




