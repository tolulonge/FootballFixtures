package com.tolulonge.footballfixtures.presentation.ui.fixtures

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.tolulonge.footballfixtures.R
import com.tolulonge.footballfixtures.core.util.loadSvgOrOther
import com.tolulonge.footballfixtures.databinding.FragmentMatchDetailBinding
import com.tolulonge.footballfixtures.presentation.state.MatchStatus
import com.tolulonge.footballfixtures.presentation.state.PresentationTodayFixture

class MatchDetailFragment : Fragment() {
    private var _binding: FragmentMatchDetailBinding? = null
    private val binding get() = _binding!!
    private val args: MatchDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMatchDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(args.matchDetail)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViews(fixture: PresentationTodayFixture) {
        with(fixture) {
            binding.apply {
                txtMatchCountryName.text = countryOfFixture
                txtMatchCompetitionName.text = competitionName
                txtMatchDateAndTime.text = date
                txtMatchDetailHomeTeam.text = homeTeamName
                txtMatchDetailAwayTeam.text = awayTeamName
                txtMatchDetailAwayScore.text = (awayTeamScore ?: "").toString()
                txtMatchDetailHomeScore.text = (homeTeamScore ?: "").toString()
                txtMatchDetailReferee.text = getString(R.string.referee_name, refereeName ?: "")
                homeTeamLogo?.let { imgMatchDetailHomeTeam.loadSvgOrOther(it) }
                awayTeamLogo?.let { imgMatchDetailAwayTeam.loadSvgOrOther(it) }
                countryFlag?.let { imgMatchCountryFlag.loadSvgOrOther(it) }
                competitionEmblem?.let { imgMatchCompetition.loadSvgOrOther(it) }
                getMatchStatus(status)
            }
        }
    }


    private fun getMatchStatus(matchStatus: MatchStatus?) {
        when (matchStatus) {
            MatchStatus.IN_PLAY -> {
                binding.apply {
                    txtMatchDetailStatus.text = "LV"
                    imgLiveIndicator.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.green
                        )
                    )
                }

            }
            MatchStatus.PAUSED -> {
                binding.apply {
                    txtMatchDetailStatus.text = "PS"
                    imgLiveIndicator.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.yellow
                        )
                    )
                }
            }
            MatchStatus.FINISHED -> {
                binding.apply {
                    txtMatchDetailStatus.text = "FT"
                    imgLiveIndicator.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.brown
                        )
                    )
                }
            }
            MatchStatus.TIMED -> {
                binding.apply {
                    txtMatchDetailStatus.text = "NS"
                    imgLiveIndicator.setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.black
                        )
                    )
                }
            }
            else -> {}
        }
    }

}