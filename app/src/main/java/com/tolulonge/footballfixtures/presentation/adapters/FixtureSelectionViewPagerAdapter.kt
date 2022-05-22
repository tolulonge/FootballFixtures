package com.tolulonge.footballfixtures.presentation.adapters


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.tolulonge.footballfixtures.presentation.ui.competition.competitionfixtures.MatchDayFixtureFragment



class FixtureSelectionViewPagerAdapter(fm: Fragment) :  FragmentStateAdapter(fm){

    override fun createFragment(position: Int): Fragment {

        return MatchDayFixtureFragment.newInstance(position + 1)
    }

    override fun getItemCount(): Int {
        return 2
    }

}

