package com.tolulonge.footballfixtures.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tolulonge.footballfixtures.R
import com.tolulonge.footballfixtures.core.util.*
import com.tolulonge.footballfixtures.databinding.ItemFixtureBinding
import com.tolulonge.footballfixtures.presentation.state.MatchStatus
import com.tolulonge.footballfixtures.presentation.state.PresentationTodayFixture

class FixturesAdapter : RecyclerView.Adapter<FixturesAdapter.FixturesViewHolder>() {

    inner class FixturesViewHolder(private val binding: ItemFixtureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(todayFixture: PresentationTodayFixture) {
            with(todayFixture) {
                homeTeamLogo?.let { binding.imgHomeTeamLogo.loadSvgOrOther(it) }
                awayTeamLogo?.let { binding.imgAwayTeamLogo.loadSvgOrOther(it) }
                binding.txtHomeTeam.text = homeTeamName ?: ""
                binding.txtAwayTeam.text = awayTeamName ?: ""
                binding.txtHomeTeamScore.text = (homeTeamScore ?: "").toString()
                binding.txtAwayTeamScore.text = (awayTeamScore ?: "").toString()
                when (status) {
                    MatchStatus.IN_PLAY -> {
                        binding.txtFullTimeIndicator.text = "LV"
                        binding.imgLiveIndicator.setBackgroundColor(
                            ContextCompat.getColor(
                                binding.root.context,
                                R.color.green
                            )
                        )

                    }
                    MatchStatus.PAUSED -> {
                        binding.imgLiveIndicator.setBackgroundColor(
                            ContextCompat.getColor(
                                binding.root.context,
                                R.color.yellow
                            )
                        )
                        binding.txtFullTimeIndicator.text = "PS"
                    }
                    MatchStatus.FINISHED -> {
                        binding.txtFullTimeIndicator.text = "FT"
                        binding.imgLiveIndicator.setBackgroundColor(
                            ContextCompat.getColor(
                                binding.root.context,
                                R.color.brown
                            )
                        )
                    }
                    MatchStatus.TIMED -> {
                        binding.txtFullTimeIndicator.text = "NS"
                        binding.imgLiveIndicator.setBackgroundColor(
                            ContextCompat.getColor(
                                binding.root.context,
                                R.color.black
                            )
                        )
                    }

                    else -> {
                        binding.apply {
                            txtFullTimeIndicator.invisible()
                            imgLiveIndicator.invisible()
                            imgNavigateToMatchDetails.hide()
                            imgHomeTeamLogo.invisible()
                            imgAwayTeamLogo.invisible()
                        }
                    }
                }
            }
            binding.imgNavigateToMatchDetails.setOnClickListener {
                onItemClickListener?.let {
                    it(todayFixture)
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<PresentationTodayFixture>() {
        override fun areItemsTheSame(
            oldItem: PresentationTodayFixture,
            newItem: PresentationTodayFixture
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PresentationTodayFixture,
            newItem: PresentationTodayFixture
        ): Boolean {
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
        val animation =
            AnimationUtils.loadAnimation(holder.itemView.context, android.R.anim.slide_in_left)
        val todayFixture = differ.currentList[position]
        holder.apply {
            itemView.startAnimation(animation)
            bind(todayFixture)
        }
    }

    private var onItemClickListener: ((PresentationTodayFixture) -> Unit)? = null

    fun setOnItemClickListener(listener: (PresentationTodayFixture) -> Unit) {
        onItemClickListener = listener
    }

}

