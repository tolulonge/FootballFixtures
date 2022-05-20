package com.tolulonge.footballfixtures.ui.fixtures.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tolulonge.footballfixtures.databinding.ItemFixtureBinding

class FixturesAdapter : RecyclerView.Adapter<FixturesAdapter.FixturesViewHolder>() {

    inner class FixturesViewHolder(private val binding: ItemFixtureBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(fixture: Fixture) {
            binding.txtHomeTeam.text = fixture.homeTeam
            binding.txtAwayTeam.text = fixture.awayTeam
            binding.imgNavigateToMatchDetails.setOnClickListener {
                onItemClickListener?.let {
                    it(fixture)
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Fixture>() {
        override fun areItemsTheSame(oldItem: Fixture, newItem: Fixture): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Fixture, newItem: Fixture): Boolean {
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
        val fixture = differ.currentList[position]
        holder.bind(fixture)
    }

    private var onItemClickListener: ((Fixture) -> Unit)? = null

    fun setOnItemClickListener(listener: (Fixture) -> Unit) {
        onItemClickListener = listener
    }

}

data class Fixture(
    val id: Int,
    val date: String,
    val homeTeam: String,
    val awayTeam: String,
    val homeTeamScore: Int,
    val awayTeamScore: Int)
