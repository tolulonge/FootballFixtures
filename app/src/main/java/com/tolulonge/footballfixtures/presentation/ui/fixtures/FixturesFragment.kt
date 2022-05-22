package com.tolulonge.footballfixtures.presentation.ui.fixtures

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.tolulonge.footballfixtures.R
import com.tolulonge.footballfixtures.core.util.hide
import com.tolulonge.footballfixtures.core.util.show
import com.tolulonge.footballfixtures.core.util.showSnackBar
import com.tolulonge.footballfixtures.core.util.showSnackBarWithAction
import com.tolulonge.footballfixtures.databinding.FragmentFixturesBinding
import com.tolulonge.footballfixtures.presentation.adapters.FixturesAdapter
import com.tolulonge.footballfixtures.presentation.event.FootballFixturesEvent
import com.tolulonge.footballfixtures.presentation.state.FixtureFragmentUiState
import com.tolulonge.footballfixtures.presentation.ui.competition.competitionfixtures.MatchDayFixtureFragment
import com.tolulonge.footballfixtures.presentation.viewmodels.FixtureViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.Exception

@AndroidEntryPoint
class FixturesFragment : Fragment() {

    private var _binding: FragmentFixturesBinding? = null
    private lateinit var fixturesAdapter: FixturesAdapter
    private val fixtureViewModel by viewModels<FixtureViewModel>()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFixturesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setUpRecyclerView()

        subscribeToObservables()

        fixturesAdapter.setOnItemClickListener {
            Toast.makeText(requireContext(), it.homeTeamName, Toast.LENGTH_SHORT).show()
            findNavController().navigate(
               R.id.match_detail_fragment
            )
        }

        binding.noDataTextView.setOnClickListener {
            fixtureViewModel.onEvent(FootballFixturesEvent.RefreshEvents)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerView(){
        fixturesAdapter = FixturesAdapter()
        binding.recyclerView.adapter = fixturesAdapter
    }

    private fun subscribeToObservables(){
        lifecycleScope.launchWhenStarted {

                fixtureViewModel.todayFixtures.collectLatest {
                    when(it){
                        FixtureFragmentUiState.Empty -> {
                            handleDataAndEmptyScenarios(false)
                        }
                        is FixtureFragmentUiState.Error -> {
                            if (it.message.isNotEmpty())
                                binding.root.showSnackBarWithAction(it.message, "Retry") {
                                    fixtureViewModel.onEvent(FootballFixturesEvent.RefreshEvents)
                                }
                        }
                        is FixtureFragmentUiState.Loaded -> {
                            val isAvailable = it.data.isNotEmpty()
                            handleDataAndEmptyScenarios(isAvailable)
                            if (it.message.isNotEmpty())
                                binding.root.showSnackBar(it.message)
                            fixturesAdapter.differ.submitList(it.data)

                        }
                        is FixtureFragmentUiState.Loading -> {
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
        inflater.inflate(R.menu.menu_fixtures, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_reload_fixutres -> {
                fixtureViewModel.onEvent(FootballFixturesEvent.RefreshEvents)
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
}
