package com.tolulonge.footballfixtures.ui.competition.competitionfixtures

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tolulonge.footballfixtures.R
import com.tolulonge.footballfixtures.databinding.FragmentCompetitionFixturesBinding
import com.tolulonge.footballfixtures.presentation.adapters.FixtureSelectionViewPagerAdapter


class CompetitionFixturesFragment : Fragment() {
    private var _binding: FragmentCompetitionFixturesBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCompetitionFixturesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sectionsPagerAdapter = FixtureSelectionViewPagerAdapter(this)
        Toast.makeText(context, "I am getting here", Toast.LENGTH_SHORT).show()

        val viewPager = binding.pager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabLayout

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = context?.resources?.getString(TAB_TITLES[position])
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2
)

