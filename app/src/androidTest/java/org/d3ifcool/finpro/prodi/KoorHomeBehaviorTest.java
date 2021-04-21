package org.d3ifcool.finpro.prodi;

import androidx.test.rule.ActivityTestRule;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.activities.ProdiMainActivitys;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 15/06/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * org.d3ifcool.finpro
 */
public class KoorHomeBehaviorTest {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityTestRule = new ActivityTestRule<>(LoginActivity.class);
    public ActivityTestRule<ProdiMainActivitys> koorMainActivityTestRule = new ActivityTestRule<>(ProdiMainActivitys.class);

    @Before
    public void init(){
        loginActivityTestRule.getActivity();
        koorMainActivityTestRule.getActivity();
    }

    @Test
    public void loginTest() throws InterruptedException {
        String username = "admin";
        String password = "admin";

        onView(withId(R.id.act_main_edittext_username)).perform(typeText(username));
        onView(withId(R.id.act_main_edittext_password)).perform(typeText(password));
        pressBack();
        onView(withId(R.id.act_main_button_login)).perform(click());
        Thread.sleep(5000);

    }


}
