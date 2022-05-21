package com.tolulonge.footballfixtures.presentation.ui.fixtures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tolulonge.footballfixtures.R
import com.tolulonge.footballfixtures.databinding.FragmentFixturesBinding
import com.tolulonge.footballfixtures.presentation.adapters.FixturesAdapter
import com.tolulonge.footballfixtures.presentation.viewmodels.FixtureViewModel
import kotlinx.coroutines.flow.collectLatest

class FixturesFragment : Fragment() {

    private var _binding: FragmentFixturesBinding? = null
    private lateinit var fixturesAdapter: FixturesAdapter
//    private val fixtureViewModel by viewModels<FixtureViewModel>()


    // This property is only valid between onCreateView and
    // onDestroyView.
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
        setUpRecyclerView()
        subscribeToObservables()

        fixturesAdapter.setOnItemClickListener {
            Toast.makeText(requireContext(), it.homeTeamName, Toast.LENGTH_SHORT).show()
            findNavController().navigate(
               R.id.match_detail_fragment
            )
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
//        lifecycleScope.launchWhenStarted {
//            fixtureViewModel.todayFixtures.collectLatest {
//                fixturesAdapter.differ.submitList(it)
//            }
//        }

    }
}
