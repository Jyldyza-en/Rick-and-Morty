package kg.tutorialapp.taskfortimelysoft.ui.episodesdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import kg.tutorialapp.taskfortimelysoft.data.model.Episode
import kg.tutorialapp.taskfortimelysoft.databinding.FragmentEpisodeDetailBinding
import kg.tutorialapp.taskfortimelysoft.util.Resource
import kg.tutorialapp.taskfortimelysoft.util.autoCleared

@AndroidEntryPoint
class EpisodeDetailFragment: Fragment() {

    private var binding: FragmentEpisodeDetailBinding by autoCleared()
    private val viewModel: EpisodeDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEpisodeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let { viewModel.start(it) }
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.episode.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    bindEpisode(episode = it.data!!)
                    binding.progressBar.visibility = View.GONE
                    binding.episodeCl.visibility = View.VISIBLE
                }

                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.episodeCl.visibility = View.GONE
                }
            }
        })
    }

    private fun bindEpisode(episode: Episode) {
        binding.name.text = episode.name
        binding.airDate.text = episode.air_date
        binding.episode.text = episode.episode
        binding.created.text = episode.created
    }
}