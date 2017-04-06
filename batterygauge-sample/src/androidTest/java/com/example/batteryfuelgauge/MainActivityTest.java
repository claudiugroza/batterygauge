package com.example.batteryfuelgauge;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.content.Context;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import claug.batterygauge.BatteryGauge;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

  @Rule
  public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(
      MainActivity.class);

  @Test
  public void mainActivityTest() {
    Context context = mActivityTestRule.getActivity();
    BatteryGauge.init(context);

    BatteryGauge.logStats();

    ViewInteraction appCompatButton3 = onView(
        allOf(withId(R.id.button), withText("Run pause"), isDisplayed()));
    appCompatButton3.perform(click());

    // Added a sleep statement to match the app's execution delay.
    // The recommended way to handle such scenarios is to use Espresso idling resources:
    // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    BatteryGauge.logStats();

    ViewInteraction appCompatButton4 = onView(
        allOf(withId(R.id.button), withText("Run pause"), isDisplayed()));
    appCompatButton4.perform(click());

    // Added a sleep statement to match the app's execution delay.
    // The recommended way to handle such scenarios is to use Espresso idling resources:
    // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    BatteryGauge.logStats();

    ViewInteraction appCompatButton5 = onView(
        allOf(withId(R.id.button), withText("Run pause"), isDisplayed()));
    appCompatButton5.perform(click());
  }

}
