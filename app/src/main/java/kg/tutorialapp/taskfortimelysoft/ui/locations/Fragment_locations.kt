package kg.tutorialapp.taskfortimelysoft.ui.locations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kg.tutorialapp.taskfortimelysoft.R
import kg.tutorialapp.taskfortimelysoft.databinding.FragmentLayoutLocationsBinding
import kg.tutorialapp.taskfortimelysoft.util.Resource
import kg.tutorialapp.taskfortimelysoft.util.autoCleared

@AndroidEntryPoint
class Fragment_locations: Fragment(R.layout.fragment_layout_locations), LocationAdapter.LocationItemListener {

    private var binding: FragmentLayoutLocationsBinding by autoCleared()
    private val viewModel: LocationViewModel by viewModels()
    private lateinit var adapter: LocationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLayoutLocationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = LocationAdapter(this)
        binding.locationsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.locationsRecyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.locations.observe(viewLifecycleOwner, Observer{
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onClickedLocation(locationId: Int) {
        findNavController().navigate(
            R.id.action_locationsFragment_to_locationDetailFragment,
            bundleOf("id" to locationId)
        )
    }


}