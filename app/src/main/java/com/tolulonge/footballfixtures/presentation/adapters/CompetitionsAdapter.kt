package com.tolulonge.footballfixtures.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tolulonge.footballfixtures.databinding.ItemCompetitionBinding

class CompetitionsAdapter : RecyclerView.Adapter<CompetitionsAdapter.CompetitionsViewHolder>() {

    inner class CompetitionsViewHolder(private val binding: ItemCompetitionBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(competition: Competition) {
            binding.txtCompetitionName.text = competition.competitionName
            binding.imgNavigateToCompetitionDetails.setOnClickListener {
                onItemClickListener?.let {
                    it(competition)
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Competition>() {
        override fun areItemsTheSame(oldItem: Competition, newItem: Competition): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Competition, newItem: Competition): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionsViewHolder {
        val binding =
            ItemCompetitionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CompetitionsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CompetitionsViewHolder, position: Int) {
        val animation = AnimationUtils.loadAnimation(holder.itemView.context, android.R.anim.slide_in_left)
        val fixture = differ.currentList[position]
        holder.apply {
            itemView.startAnimation(animation)
            bind(fixture)
        }
    }

    private var onItemClickListener: ((Competition) -> Unit)? = null

    fun setOnItemClickListener(listener: (Competition) -> Unit) {
        onItemClickListener = listener
    }

}

data class Competition(
    val id: Int,
    val competitionName: String,
   )
