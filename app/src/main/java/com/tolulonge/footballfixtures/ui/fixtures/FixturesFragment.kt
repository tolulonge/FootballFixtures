package com.tolulonge.footballfixtures.ui.fixtures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tolulonge.footballfixtures.R
import com.tolulonge.footballfixtures.databinding.FragmentFixturesBinding
import com.tolulonge.footballfixtures.ui.fixtures.adapter.Fixture
import com.tolulonge.footballfixtures.ui.fixtures.adapter.FixturesAdapter

class FixturesFragment : Fragment() {

    private var _binding: FragmentFixturesBinding? = null
    private lateinit var fixturesAdapter: FixturesAdapter


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
        fixturesAdapter.differ.submitList(fixtures)

        fixturesAdapter.setOnItemClickListener {
            Toast.makeText(requireContext(), it.homeTeam, Toast.LENGTH_SHORT).show()
            findNavController().navigate(
               R.id.navigation_competitions
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
}

val fixtures = listOf(Fixture(1,"23","Chelsea","Man u",3,0),
    Fixture(1,"23","Chelsea","Man u",3,0),
    Fixture(1,"23","Chelsea","Man u",3,0),
    Fixture(1,"23","Chelsea","Man u",3,0),
    Fixture(1,"23","Chelsea","Man u",3,0),
    Fixture(1,"23","Chelsea","Man u",3,0),
    Fixture(1,"23","Chelsea","Man u",3,0),
    Fixture(1,"23","Chelsea","Man u",3,0),)