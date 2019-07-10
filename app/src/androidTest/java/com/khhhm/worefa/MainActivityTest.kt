package com.khhhm.worefa


import android.view.View
import android.view.ViewGroup
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        val textView = onView(allOf(withText("home"), childAtPosition(childAtPosition(withId(R.id.viewpagertab), 0), 0), isDisplayed()))
        textView.perform(click())

        val textView2 = onView(
            allOf(
                withText("Companys"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.viewpagertab),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        textView2.perform(click())

        val relativeLayout = onView(
            allOf(
                withId(R.id.layoutas),
                childAtPosition(
                    allOf(
                        withId(R.id.Alist),
                        withParent(withId(R.id.main_view_pager))
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        relativeLayout.perform(click())

        val textView3 = onView(
            allOf(
                withText("About "),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.viewpagertab),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        textView3.perform(click())

        val textView4 = onView(
            allOf(
                withText("Settings"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.viewpagertab),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        textView4.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
