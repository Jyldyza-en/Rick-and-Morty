package kg.tutorialapp.taskfortimelysoft.ui.locations

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.tutorialapp.taskfortimelysoft.data.model.Location
import kg.tutorialapp.taskfortimelysoft.databinding.ItemLocationsBinding


class LocationAdapter(private val listener: LocationItemListener) : RecyclerView.Adapter<LocationViewHolder>() {

    interface LocationItemListener {
        fun onClickedLocation(locationId: Int)
    }

    private val items = ArrayList<Location>()

    fun setItems(items: ArrayList<Location>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val binding: ItemLocationsBinding = ItemLocationsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) = holder.bind(items[position])
}

class LocationViewHolder(private val itemBinding: ItemLocationsBinding, private val listener: LocationAdapter.LocationItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var location: Location

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Location) {
        this.location = item
        itemBinding.name.text = item.name
        itemBinding.dimension.text = item.dimension
        itemBinding.type.text = item.type

    }

    override fun onClick(v: View?) {
        location.id?.let { listener.onClickedLocation(it) }
    }
}
