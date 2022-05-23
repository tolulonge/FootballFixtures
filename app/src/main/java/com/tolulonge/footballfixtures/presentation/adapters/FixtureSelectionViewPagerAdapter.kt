package com.tolulonge.footballfixtures.presentation.adapters


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tolulonge.footballfixtures.presentation.state.PresentationCompetitionX

import com.tolulonge.footballfixtures.presentation.ui.competition.competitionfixtures.MatchDayFixtureFragment



class FixtureSelectionViewPagerAdapter(fm: Fragment, private val presentationCompetitionX: PresentationCompetitionX) :  FragmentStateAdapter(fm){

    override fun createFragment(position: Int): Fragment {

        return MatchDayFixtureFragment.newInstance(position, presentationCompetitionX)
    }

    override fun getItemCount(): Int {
        return 2
    }

}

