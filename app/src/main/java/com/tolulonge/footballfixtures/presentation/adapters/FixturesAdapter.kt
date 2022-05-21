package com.tolulonge.footballfixtures.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tolulonge.footballfixtures.databinding.ItemFixtureBinding
import com.tolulonge.footballfixtures.presentation.state.PresentationTodayFixture

class FixturesAdapter : RecyclerView.Adapter<FixturesAdapter.FixturesViewHolder>() {

    inner class FixturesViewHolder(private val binding: ItemFixtureBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todayFixture: PresentationTodayFixture) {
            binding.txtHomeTeam.text = todayFixture.homeTeamName
            binding.txtAwayTeam.text = todayFixture.awayTeamName
            binding.imgNavigateToMatchDetails.setOnClickListener {
                onItemClickListener?.let {
                    it(todayFixture)
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<PresentationTodayFixture>() {
        override fun areItemsTheSame(oldItem: PresentationTodayFixture, newItem: PresentationTodayFixture): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PresentationTodayFixture, newItem: PresentationTodayFixture): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixturesViewHolder {
        val binding =
            ItemFixtureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FixturesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: FixturesViewHolder, position: Int) {
        val todayFixture = differ.currentList[position]
        holder.bind(todayFixture)
    }

    private var onItemClickListener: ((PresentationTodayFixture) -> Unit)? = null

    fun setOnItemClickListener(listener: (PresentationTodayFixture) -> Unit) {
        onItemClickListener = listener
    }

}

