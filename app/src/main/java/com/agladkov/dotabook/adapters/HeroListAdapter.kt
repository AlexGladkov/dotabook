package com.agladkov.dotabook.adapters

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.agladkov.domain.models.Hero
import com.agladkov.dotabook.R
import com.bumptech.glide.Glide

interface HeroClickHandler {
    fun onItemClick(item: Hero)
}

class HeroListAdapter : RecyclerView.Adapter<HeroListAdapter.HeroListViewHolder>(), AdapterData<Hero> {
    private val data: MutableList<Hero> = ArrayList()
    private var heroClickHandler: HeroClickHandler? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroListViewHolder {
        return HeroListViewHolder(itemView = LayoutInflater.from(parent.context).inflate(R.layout.cell_hero_list, parent, false),
            heroClickHandler = heroClickHandler)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: HeroListViewHolder, position: Int) {
        holder.bind(source = data[position])
    }

    override fun setData(items: List<Hero>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    fun attachClickHandler(heroClickHandler: HeroClickHandler) {
        this.heroClickHandler = heroClickHandler
    }

    class HeroListViewHolder(itemView: View, private val heroClickHandler: HeroClickHandler?) : RecyclerView.ViewHolder(itemView) {
        private val imgAvatar = itemView.findViewById<ImageView>(R.id.heroImage)
        private val cellView = itemView.findViewById<FrameLayout>(R.id.flHeroItem)

        fun bind(source: Hero) {
            cellView.setOnClickListener {
                heroClickHandler?.onItemClick(item = source)
            }

            Glide.with(itemView.context)
                .load(source.avatar)
                .fitCenter()
                .into(imgAvatar)
        }
    }
}