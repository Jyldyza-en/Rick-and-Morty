package kg.tutorialapp.taskfortimelysoft.ui.episodes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.tutorialapp.taskfortimelysoft.data.model.Episode
import kg.tutorialapp.taskfortimelysoft.databinding.ItemEpisodesBinding

class EpisodesAdapter(private val listener: EpisodeItemListener) : RecyclerView.Adapter<EpisodeViewHolder>() {

    interface EpisodeItemListener {
        fun onClickedEpisode(episodeId: Int)
    }

    private val items = ArrayList<Episode>()

    fun setItems(items: ArrayList<Episode>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val binding: ItemEpisodesBinding = ItemEpisodesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) = holder.bind(items[position])
}

class EpisodeViewHolder(private val itemBinding: ItemEpisodesBinding, private val listener: EpisodesAdapter.EpisodeItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var episode: Episode

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Episode) {
        this.episode = item
        itemBinding.name.text = item.name
        itemBinding.airDate.text = item.air_date
        itemBinding.episodeCode.text = item.episode

    }

    override fun onClick(v: View?) {
        episode.id?.let { listener.onClickedEpisode(it) }
    }
}
