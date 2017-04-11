package com.desafio.douglas.wmovie.activity;


import android.os.SystemClock;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.desafio.douglas.wmovie.R;
import com.desafio.douglas.wmovie.activity.utils_test.RecyclerViewMatcher;
import com.desafio.douglas.wmovie.util.ConexaoUtils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class FluxoBasicoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testeFluxo() {

        if (ConexaoUtils.isInternetAtiva(mActivityTestRule.getActivity())){

            ViewInteraction appCompatImageView = onView(
                    allOf(withId(R.id.search_button), withContentDescription("Pesquisar"),
                            withParent(allOf(withId(R.id.search_bar),
                                    withParent(withId(R.id.menu_search)))),
                            isDisplayed()));
            appCompatImageView.perform(click());

            ViewInteraction linearLayoutCompat = onView(
                    allOf(withId(R.id.menu_search),
                            childAtPosition(
                                    childAtPosition(
                                            withId(R.id.action_bar),
                                            1),
                                    0),
                            isDisplayed()));
            linearLayoutCompat.check(matches(isDisplayed()));

            ViewInteraction searchAutoComplete = onView(
                    allOf(withId(R.id.search_src_text),
                            withParent(allOf(withId(R.id.search_plate),
                                    withParent(withId(R.id.search_edit_frame)))),
                            isDisplayed()));
            searchAutoComplete.perform(replaceText("Batman returns"), closeSoftKeyboard());

            ViewInteraction searchAutoComplete2 = onView(
                    allOf(withId(R.id.search_src_text), withText("Batman returns"),
                            withParent(allOf(withId(R.id.search_plate),
                                    withParent(withId(R.id.search_edit_frame)))),
                            isDisplayed()));
            searchAutoComplete2.perform(pressImeActionButton());


            ViewInteraction frameLayout = onView(
                    allOf(withId(R.id.card_view),
                            childAtPosition(
                                    allOf(withId(R.id.recycler_view_search),
                                            childAtPosition(
                                                    withId(R.id.activity_fotos_listagem),
                                                    0)),
                                    0),
                            isDisplayed()));
            SystemClock.sleep(1500);
            frameLayout.check(matches(isDisplayed()));

            onView(withRecyclerView(R.id.recycler_view_search).atPosition(0))
                    .check(ViewAssertions.matches(hasDescendant(withText("Batman Returns"))));

            onView(withId(R.id.recycler_view_search)).perform(scrollToPosition(2));

            ViewInteraction recyclerView = onView(
                    allOf(withId(R.id.recycler_view_search),
                            withParent(allOf(withId(R.id.activity_fotos_listagem),
                                    withParent(withId(android.R.id.content)))),
                            isDisplayed()));
            recyclerView.perform(actionOnItemAtPosition(0, click()));

            onView(withId(R.id.toolbar)).check(ViewAssertions.matches(isDisplayed()));

            onView(withText(R.string.app_name)).check(ViewAssertions.matches(withParent(withId(R.id.toolbar))));

            ViewInteraction imageView = onView(
                    allOf(withId(R.id.image_detail),
                            childAtPosition(
                                    allOf(withId(R.id.collapsing_toolbar),
                                            childAtPosition(
                                                    withId(R.id.detail_app_bar),
                                                    0)),
                                    0),
                            isDisplayed()));
            imageView.check(matches(isDisplayed()));

            ViewInteraction imageButton2 = onView(
                    allOf(withId(R.id.fab_detail),
                            childAtPosition(
                                    allOf(withId(R.id.detail_coordinator),
                                            childAtPosition(
                                                    withId(android.R.id.content),
                                                    0)),
                                    2),
                            isDisplayed()));
            imageButton2.check(matches(isDisplayed()));

            ViewInteraction imageButton3 = onView(
                    allOf(withId(R.id.fab_detail),
                            childAtPosition(
                                    allOf(withId(R.id.detail_coordinator),
                                            childAtPosition(
                                                    withId(android.R.id.content),
                                                    0)),
                                    2),
                            isDisplayed()));
            imageButton3.check(matches(isDisplayed()));

            ViewInteraction floatingActionButton = onView(
                    allOf(withId(R.id.fab_detail),
                            withParent(allOf(withId(R.id.detail_coordinator),
                                    withParent(withId(android.R.id.content)))),
                            isDisplayed()));
            floatingActionButton.perform(click());

            ViewInteraction imageButton4 = onView(
                    allOf(withId(R.id.fab_detail),
                            childAtPosition(
                                    allOf(withId(R.id.detail_coordinator),
                                            childAtPosition(
                                                    withId(android.R.id.content),
                                                    0)),
                                    2),
                            isDisplayed()));
            imageButton4.check(matches(isDisplayed()));

            ViewInteraction imageButton5 = onView(
                    allOf(withId(R.id.fab_detail),
                            childAtPosition(
                                    allOf(withId(R.id.detail_coordinator),
                                            childAtPosition(
                                                    withId(android.R.id.content),
                                                    0)),
                                    2),
                            isDisplayed()));
            imageButton5.check(matches(isDisplayed()));

            ViewInteraction appCompatImageButton = onView(
                    allOf(withContentDescription("Navegar para cima"),
                            withParent(allOf(withId(R.id.toolbar),
                                    withParent(withId(R.id.collapsing_toolbar)))),
                            isDisplayed()));
            appCompatImageButton.perform(click());

            ViewInteraction recyclerView2 = onView(
                    allOf(withId(R.id.recycler_view_movies),
                            withParent(allOf(withId(R.id.activity_fotos_listagem),
                                    withParent(withId(android.R.id.content)))),
                            isDisplayed()));
            recyclerView2.perform(actionOnItemAtPosition(0, click()));


            ViewInteraction appCompatImageButton2 = onView(
                    allOf(withContentDescription("Navegar para cima"),
                            withParent(allOf(withId(R.id.toolbar),
                                    withParent(withId(R.id.collapsing_toolbar)))),
                            isDisplayed()));
            appCompatImageButton2.perform(click());

            ViewInteraction frameLayout2 = onView(
                    allOf(withId(R.id.card_view),
                            childAtPosition(
                                    allOf(withId(R.id.recycler_view_movies),
                                            childAtPosition(
                                                    withId(R.id.activity_fotos_listagem),
                                                    0)),
                                    0),
                            isDisplayed()));
            frameLayout2.check(matches(isDisplayed()));

            ViewInteraction textView2 = onView(
                    allOf(withId(R.id.txt_movies_title), withText("Batman Returns"),
                            childAtPosition(
                                    allOf(withId(R.id.linear_repo_info),
                                            childAtPosition(
                                                    IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                                    1)),
                                    0),
                            isDisplayed()));
            textView2.check(matches(withText("Batman Returns")));

            ViewInteraction appCompatImageView2 = onView(
                    allOf(withId(R.id.search_close_btn), withContentDescription("Limpar consulta"),
                            withParent(allOf(withId(R.id.search_plate),
                                    withParent(withId(R.id.search_edit_frame)))),
                            isDisplayed()));
            appCompatImageView2.perform(click());

            ViewInteraction recyclerView3 = onView(
                    allOf(withId(R.id.recycler_view_movies),
                            withParent(allOf(withId(R.id.activity_fotos_listagem),
                                    withParent(withId(android.R.id.content)))),
                            isDisplayed()));
            recyclerView3.perform(actionOnItemAtPosition(0, click()));

            ViewInteraction textView3 = onView(
                    allOf(withId(R.id.txt_detail_title), withText("Batman Returns"),
                            childAtPosition(
                                    childAtPosition(
                                            IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                            0),
                                    1),
                            isDisplayed()));
            textView3.check(matches(withText("Batman Returns")));

            ViewInteraction floatingActionButton2 = onView(
                    allOf(withId(R.id.fab_detail),
                            withParent(allOf(withId(R.id.detail_coordinator),
                                    withParent(withId(android.R.id.content)))),
                            isDisplayed()));
            floatingActionButton2.perform(click());

        }
    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
