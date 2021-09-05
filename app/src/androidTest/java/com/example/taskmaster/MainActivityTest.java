package com.example.taskmaster;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;


import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {




    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);



        @Test
        public void testBtn1() {
            onView(withId(R.id.btn1)).perform(click());

            onView(withId(R.id.taskNameId)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
            onView(withId(R.id.taskBodyId)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
            onView(withId(R.id.stateId)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
            onView(withId(R.id.addTaskText)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        }

        @Test
         public void testTextView(){
            onView(withId(R.id.myTaskId)).check(matches(withText("My Tasks")));

        }

        @Test
        public void testGoToSettings(){
            String userNAme = "noor";
            onView(withId(R.id.settingsBtn)).perform(ViewActions.click());
            onView(withId(R.id.usernameInput)).perform(typeText(userNAme));
            onView(withId(R.id.saveBtn)).perform(ViewActions.click());
            onView(withId(R.id.usernameText)).check(matches(withText(userNAme+"'s tasks")));

        }

        @Test
        public void testHomeworkBtn(){
            onView(withId(R.id.homeworkBtn)).perform(click());
            onView(withId(R.id.titleText)).check(matches(withText("Do Homework")));
        }

        @Test
        public void testCoffeeBtn(){
            onView(withId(R.id.coffeBtn)).perform(click());
            onView(withId(R.id.titleText)).check(matches(withText("Make a Cofffe")));
        }

        @Test
        public void testGameBtn(){
            onView(withId(R.id.gameBtn)).perform(click());
            onView(withId(R.id.titleText)).check(matches(withText("Play a Game")));
        }

        @Test
        public void testAllTaskPage(){
            onView(withId(R.id.btn2)).perform(click());
            onView(withId(R.id.allTaskText)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
            pressBack();
            onView(withId(R.id.TaskRecyclerView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        }

}