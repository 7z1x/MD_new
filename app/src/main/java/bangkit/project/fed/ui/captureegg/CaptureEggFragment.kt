package bangkit.project.fed.ui.captureegg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import bangkit.project.fed.databinding.FragmentCaptureBinding

class CaptureEggFragment : Fragment() {

    private var _binding: FragmentCaptureBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(CaptureEggViewModel::class.java)

        _binding = FragmentCaptureBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}