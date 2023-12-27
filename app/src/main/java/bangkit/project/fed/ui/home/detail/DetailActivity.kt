package bangkit.project.fed.ui.home.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import bangkit.project.fed.data.EggData
import bangkit.project.fed.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val listEgg = mutableListOf<EggData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val label = intent.getStringExtra("label")
        val imageURL = intent.getStringExtra("imageURL")
        val timestamp = intent.getLongExtra("timestamp", 0)

        displayData(label, imageURL, timestamp)
    }

    private fun displayData(label: String?, imageURL: String?, timestamp: Long) {
        binding.recentName.text = label

        // Konversi timestamp ke format tanggal yang diinginkan
        val formattedDate = convertTimestampToDate(timestamp)
        binding.recentDate.text = formattedDate

        Glide.with(this)
            .load(imageURL)
            .into(binding.recentImage)

        val description = if (listEgg.isNotEmpty() && listEgg[0].phases.isNotEmpty()) {
            val phases = listEgg[0].phases
            val descriptionBuilder = StringBuilder()

            for (i in phases.indices) {
                val phase = phases[i]
                val kelas = phase.kelas ?: "Unknown Class"
                val probabilitas = phase.probabilitas ?: 0.0
                descriptionBuilder.append("Class: $kelas, Probability: $probabilitas \n")
            }

            descriptionBuilder.toString()
        } else {
            "No description available"
        }

        binding.Desc.text = description
    }

    private fun convertTimestampToDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault())
        val date = Date(timestamp)
        return sdf.format(date)
    }
}