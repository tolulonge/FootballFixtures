package com.tolulonge.footballfixtures.presentation.ui.competition.competitionfixtures

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tolulonge.footballfixtures.R
import com.tolulonge.footballfixtures.core.util.*
import com.tolulonge.footballfixtures.databinding.FragmentMatchDayFixtureBinding
import com.tolulonge.footballfixtures.presentation.adapters.FixturesAdapter
import com.tolulonge.footballfixtures.presentation.event.FootballFixturesEvent
import com.tolulonge.footballfixtures.presentation.state.*
import com.tolulonge.footballfixtures.presentation.ui.competition.CompetitionsFragmentDirections
import com.tolulonge.footballfixtures.presentation.viewmodels.CompetitionFixturesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MatchDayFixtureFragment : Fragment() {

    private var _binding: FragmentMatchDayFixtureBinding? = null
    private lateinit var fixturesAdapter: FixturesAdapter
    private val competitionFixturesViewModel: CompetitionFixturesViewModel by viewModels()
    private var presentationCompetitionX: PresentationCompetitionX? = null
    private var currentMatchDay: String? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sectionNumber = arguments?.getInt(ARG_SECTION_NUMBER) ?: 0
        presentationCompetitionX = arguments?.getParcelable(PRESENTATION_COMPETITION_X)
        currentMatchDay = if (sectionNumber == 0) {
            val matchDay = presentationCompetitionX?.currentMatchDay ?: 1
            competitionFixturesViewModel.setMatchDay(matchDay)
            matchDay.toString()
        }else{
            val matchDay = presentationCompetitionX?.nextMatchDay ?: 1
            competitionFixturesViewModel.setMatchDay(matchDay)
            matchDay.toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMatchDayFixtureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setHasOptionsMenu(true)
        setupViews(presentationCompetitionX)
        subscribeToObservables()

        competitionFixturesViewModel.onEvent(FootballFixturesEvent.GetCompetitionFixtures(
            presentationCompetitionX?.competitionCode ?: ""
        ))

        fixturesAdapter.setOnItemClickListener {
            val action = CompetitionFixturesFragmentDirections.actionCompetitionFixturesFragmentToMatchDetailFragment(it)
            findNavController().navigate(
               action
            )
        }
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"
        private const val PRESENTATION_COMPETITION_X = "presentation_competition_x"


        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int,presentationCompetition: PresentationCompetitionX): MatchDayFixtureFragment {
            return MatchDayFixtureFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putParcelable(PRESENTATION_COMPETITION_X,presentationCompetition)
                }
            }
        }
    }

    private fun subscribeToObservables(){

        lifecycleScope.launchWhenStarted {

            competitionFixturesViewModel.fixtures.collectLatest { state ->
                when(state){
                    CompetitionFixturesFragmentUiState.Empty -> {
                        handleEmptyScenario()
                    }
                    is CompetitionFixturesFragmentUiState.Error -> {
                        if (state.message.isNotEmpty())
                            binding.root.showSnackBarWithAction(state.message, "Retry") {
                                reloadCompetitionFixtures(presentationCompetitionX?.competitionCode ?: "")
                            }
                    }
                    is CompetitionFixturesFragmentUiState.Loaded -> {
                        if (state.message.isNotEmpty())
                            binding.root.showSnackBar(state.message)
                            fixturesAdapter.differ.submitList(state.data.map { it.toPresentationTodayFixture() })

                    }
                    is CompetitionFixturesFragmentUiState.Loading -> {
                        if (state.isLoading){
                            binding.matchDayFixtures.progressBar.show()
                        }else{
                            binding.matchDayFixtures.progressBar.hide()
                        }
                    }
                }
            }

        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_fixtures, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_reload_fixutres -> {
                reloadCompetitionFixtures(
                    presentationCompetitionX?.competitionCode ?: ""
                )
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerView(){
        fixturesAdapter = FixturesAdapter()
        binding.matchDayFixtures.recyclerView.adapter = fixturesAdapter
    }

    private fun reloadCompetitionFixtures(competitionCode: String){
        competitionFixturesViewModel.onEvent(FootballFixturesEvent.RefreshCompetitionFixtures(
            competitionCode
        ))
    }

    private fun setupViews(presentationCompetitionX: PresentationCompetitionX?){
        binding.apply {
            txtCompetitionCountryName.text = presentationCompetitionX?.competitionCountryName ?: "Not Found"
            txtCompetitionName.text = presentationCompetitionX?.competitionName ?: "Not Found"
            imgCompetitionEmblem.loadSvgOrOther(presentationCompetitionX?.competitionEmblem)
            imgCompetitionCountry.loadSvgOrOther(presentationCompetitionX?.competitionCountryEmblem)
            txtMatchDayValue.text = currentMatchDay ?: "N/A"
        }
    }

    private fun handleEmptyScenario(){
        fixturesAdapter.differ.submitList(listOf(PresentationTodayFixture(
            null,"",MatchStatus.UNAVAILABLE,"No Data Available","",
            null,null,"",""
            ,"","",null,null,null
        )))
    }
}

