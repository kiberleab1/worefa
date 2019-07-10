package com.khhhm.worefa

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class AppointFragmentTest {

    private val ITEM_BELOW_THE_FOLD = 40

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun onCreate() {
        onView(ViewMatchers.withId(R.id.Clist)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun lastItem_NotDisplayed() {
        // Last item should not exist if the list wasn't scrolled down.
        onView(withText("last")).check(doesNotExist())
    }


    @Test
    fun onCreateView() {
    }

    @Test
    fun onAttach() {
    }

}