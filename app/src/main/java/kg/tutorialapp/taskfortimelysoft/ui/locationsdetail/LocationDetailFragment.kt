package kg.tutorialapp.taskfortimelysoft.ui.locationsdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import dagger.hilt.android.AndroidEntryPoint
import kg.tutorialapp.taskfortimelysoft.data.model.Character
import kg.tutorialapp.taskfortimelysoft.data.model.Location
import kg.tutorialapp.taskfortimelysoft.databinding.FragmentCharacterDetailBinding
import kg.tutorialapp.taskfortimelysoft.databinding.FragmentLocationDetailBinding
import kg.tutorialapp.taskfortimelysoft.util.Resource
import kg.tutorialapp.taskfortimelysoft.util.autoCleared
@AndroidEntryPoint
class LocationDetailFragment : Fragment() {

    private var binding: FragmentLocationDetailBinding by autoCleared()
    private val viewModel: LocationDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLocationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.location.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindLocation(it.data!!)
                    binding.progressBar.visibility = View.GONE
                    binding.locationCl.visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.locationCl.visibility = View.GONE
                }
            }
        })
    }

    private fun bindLocation(location: Location) {
        binding.name.text = location.name
        binding.typeLocation.text = location.type
        binding.dimension.text = location.dimension
        binding.createdLocation.text = location.created
    }
}