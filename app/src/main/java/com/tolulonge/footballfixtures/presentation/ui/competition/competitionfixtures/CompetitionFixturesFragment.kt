package com.tolulonge.footballfixtures.presentation.ui.competition.competitionfixtures

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tolulonge.footballfixtures.R
import com.tolulonge.footballfixtures.databinding.FragmentCompetitionFixturesBinding
import com.tolulonge.footballfixtures.presentation.adapters.FixtureSelectionViewPagerAdapter


class CompetitionFixturesFragment : Fragment() {
    private var _binding: FragmentCompetitionFixturesBinding? = null

    private val binding get() = _binding!!

    val args: CompetitionFixturesFragmentArgs by navArgs()


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

        val sectionsPagerAdapter = FixtureSelectionViewPagerAdapter(this,args.competitionFixture)

        val viewPager = binding.pager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabLayout

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = context?.resources?.getStringArray(R.array.tab_titles)?.get(position)
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
