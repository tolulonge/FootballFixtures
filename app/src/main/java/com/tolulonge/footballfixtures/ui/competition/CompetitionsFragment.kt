package com.tolulonge.footballfixtures.ui.competition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tolulonge.footballfixtures.R
import com.tolulonge.footballfixtures.databinding.FragmentCompetitionsBinding
import com.tolulonge.footballfixtures.ui.competition.adapter.Competition
import com.tolulonge.footballfixtures.ui.competition.adapter.CompetitionsAdapter
import com.tolulonge.footballfixtures.ui.fixtures.adapter.FixturesAdapter

class CompetitionsFragment : Fragment() {

    private var _binding: FragmentCompetitionsBinding? = null
    private lateinit var competitionsAdapter: CompetitionsAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCompetitionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        competitionsAdapter.differ.submitList(competitions)
        competitionsAdapter.setOnItemClickListener {
            Toast.makeText(requireContext(), it.competitionName, Toast.LENGTH_SHORT).show()
            findNavController().navigate(
                R.id.navigation_competitions
            )
        }

    }

    private fun setUpRecyclerView(){
        competitionsAdapter = CompetitionsAdapter()
        binding.recyclerViewCompetitions.adapter = competitionsAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

val competitions = listOf(Competition(1, "Ligue 1"),
    Competition(2, "Premier League"),
    Competition(3, "Bundesliga"),
    Competition(4, "La Liga"),
    Competition(5, "Serie A"),
    Competition(6, "Eredivisie"),
    Competition(7, "Primeira Liga"),
    Competition(8, "Champions League"),
    Competition(9, "Europa League"),
    Competition(10, "Copa del Rey"),)