package com.tolulonge.footballfixtures.presentation.ui.competition.competitionfixtures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tolulonge.footballfixtures.R
import com.tolulonge.footballfixtures.databinding.FragmentMatchDayFixtureBinding
import com.tolulonge.footballfixtures.presentation.adapters.FixturesAdapter


class MatchDayFixtureFragment : Fragment() {

    private var _binding: FragmentMatchDayFixtureBinding? = null
    private lateinit var fixturesAdapter: FixturesAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
//            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMatchDayFixtureBinding.inflate(inflater, container, false)
        val root = binding.root

        val textView: TextView = binding.txtCompetitionName
//        pageViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
     //   fixturesAdapter.differ.submitList(fixtures)

        fixturesAdapter.setOnItemClickListener {
            Toast.makeText(requireContext(), it.homeTeamName, Toast.LENGTH_SHORT).show()
            findNavController().navigate(
                R.id.match_detail_fragment
            )
        }
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): MatchDayFixtureFragment {
            return MatchDayFixtureFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
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
}

