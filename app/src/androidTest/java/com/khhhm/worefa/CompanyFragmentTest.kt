package com.khhhm.worefa

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.khhhm.worefa.adapters.MyCompanyRecyclerViewAdapter
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@LargeTest
class CompanyFragmentTest {

    @Rule
    @JvmField
    var activityScenarioRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun onCreateView() {
        onView(withId(R.id.Clist)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun onAttach() {
    }
}