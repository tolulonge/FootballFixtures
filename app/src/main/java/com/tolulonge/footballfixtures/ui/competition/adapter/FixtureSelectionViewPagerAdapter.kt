package com.tolulonge.footballfixtures.ui.competition.adapter

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.tolulonge.footballfixtures.R
import com.tolulonge.footballfixtures.ui.competition.competitionfixtures.MatchDayFixtureFragment
import com.tolulonge.footballfixtures.ui.fixtures.FixturesFragment


class FixtureSelectionViewPagerAdapter(fm: Fragment) :  FragmentStateAdapter(fm){

    override fun createFragment(position: Int): Fragment {

        return MatchDayFixtureFragment.newInstance(position + 1)
    }

    override fun getItemCount(): Int {
        return 2
    }

//    override fun getPageTitle(position: Int): CharSequence? {
//        return context.resources.getString(TAB_TITLES[position])
//    }
}

