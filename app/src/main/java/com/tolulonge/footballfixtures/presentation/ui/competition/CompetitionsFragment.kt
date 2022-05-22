package com.tolulonge.footballfixtures.presentation.ui.competition

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tolulonge.footballfixtures.R
import com.tolulonge.footballfixtures.core.util.hide
import com.tolulonge.footballfixtures.core.util.show
import com.tolulonge.footballfixtures.core.util.showSnackBar
import com.tolulonge.footballfixtures.core.util.showSnackBarWithAction
import com.tolulonge.footballfixtures.databinding.FragmentCompetitionsBinding
import com.tolulonge.footballfixtures.presentation.adapters.Competition
import com.tolulonge.footballfixtures.presentation.adapters.CompetitionsAdapter
import com.tolulonge.footballfixtures.presentation.event.FootballFixturesEvent
import com.tolulonge.footballfixtures.presentation.state.CompetitionsFragmentUiState
import com.tolulonge.footballfixtures.presentation.state.FixtureFragmentUiState
import com.tolulonge.footballfixtures.presentation.viewmodels.CompetitionsViewModel
import com.tolulonge.footballfixtures.presentation.viewmodels.FixtureViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CompetitionsFragment : Fragment() {

    private var _binding: FragmentCompetitionsBinding? = null
    private lateinit var competitionsAdapter: CompetitionsAdapter
    private val competitionsViewModel by viewModels<CompetitionsViewModel>()
    private var menu: Menu? = null

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
        setHasOptionsMenu(true)
        setUpRecyclerView()

        subscribeToObservables()

        competitionsAdapter.setOnItemClickListener {
            val action = CompetitionsFragmentDirections.actionNavigationCompetitionsToCompetitionFixturesFragment(it)
            findNavController().navigate(action)
        }

        binding.noDataTextView.setOnClickListener {
            competitionsViewModel.onEvent(FootballFixturesEvent.RefreshEvents)
        }
    }


    private fun setUpRecyclerView(){
        competitionsAdapter = CompetitionsAdapter()
        binding.recyclerViewCompetitions.adapter = competitionsAdapter
    }


    private fun subscribeToObservables(){
        lifecycleScope.launchWhenStarted {

            competitionsViewModel.competitionsList.collectLatest {
                when(it){
                    CompetitionsFragmentUiState.Empty -> {
                        handleDataAndEmptyScenarios(false)
                    }
                    is CompetitionsFragmentUiState.Error -> {
                        if (it.message.isNotEmpty())
                            binding.root.showSnackBarWithAction(it.message, "Retry") {
                                competitionsViewModel.onEvent(FootballFixturesEvent.RefreshEvents)
                            }
                    }
                    is CompetitionsFragmentUiState.Loaded -> {
                        val isAvailable = it.data.isNotEmpty()
                        handleDataAndEmptyScenarios(isAvailable)
                        if (it.message.isNotEmpty())
                            binding.root.showSnackBar(it.message)
                        competitionsAdapter.differ.submitList(it.data)

                    }
                    is CompetitionsFragmentUiState.Loading -> {
                        handleDataAndEmptyScenarios(true)
                        if (it.isLoading){
                            binding.progressBar.show()
                        }else{
                            binding.progressBar.hide()
                        }
                    }
                }


            }

        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        this.menu = menu
        inflater.inflate(R.menu.menu_fixtures, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_reload_fixutres -> {
                competitionsViewModel.onEvent(FootballFixturesEvent.RefreshEvents)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun handleDataAndEmptyScenarios(isAvailable: Boolean){
        if(isAvailable){
            binding.apply {
                noDataImageView.hide()
                noDataTextView.hide()

            }
        }else{
            binding.apply {
                noDataImageView.show()
                noDataTextView.show()
                progressBar.hide()
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
