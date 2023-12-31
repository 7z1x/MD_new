package bangkit.project.fed.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import bangkit.project.fed.data.EggData
import bangkit.project.fed.data.api.FirestoreHelper
import bangkit.project.fed.databinding.FragmentHomeBinding
import bangkit.project.fed.ui.home.adapter.LibraryRvAdapter
import bangkit.project.fed.ui.home.adapter.RecentRvAdapter
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: HomeViewModel
    private lateinit var libraryAdapter: LibraryRvAdapter
    private lateinit var recentAdapter: RecentRvAdapter
    private lateinit var eggList : List<EggData>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()
        getLibraryList()
        getRecentList()

        return binding.root
    }

    private fun getRecentList() {

        val auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid

        val currentUser = auth.currentUser
        currentUser?.uid?.let {
            viewModel.fetchEggDataByRecentDate(it)
        }

        recentAdapter = RecentRvAdapter(requireContext())
        binding.recentRv.adapter = recentAdapter
        binding.recentRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val firestoreHelper = FirestoreHelper()
        firestoreHelper.getDataEggByRecentDate(uid) {eggdataList ->
            val limitedList = eggdataList.take(3)
            recentAdapter.submitList(limitedList)
        }
        recentAdapter.notifyDataSetChanged()

    }

    private fun getLibraryList() {

        val auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid

        libraryAdapter = LibraryRvAdapter(requireContext())
        binding.LibraryRv.adapter = libraryAdapter
        binding.LibraryRv.layoutManager = LinearLayoutManager(requireContext())

        val firestoreHelper = FirestoreHelper()
        firestoreHelper.getDataEggByUserId(uid) {eggDataList ->
            libraryAdapter.submitList(eggDataList)
        }
        libraryAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}