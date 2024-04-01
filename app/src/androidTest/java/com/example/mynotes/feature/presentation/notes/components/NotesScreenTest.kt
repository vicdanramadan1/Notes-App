package com.example.mynotes.featurenote.presentation.notes.components

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertIsDisplayed
import com.example.mynotes.R
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.example.mynotes.core.TestTags
import com.example.mynotes.di.AppModule
import com.example.mynotes.featurenote.presentation.MainActivity
import com.example.mynotes.featurenote.presentation.util.Screen
import com.example.mynotes.ui.theme.MyNotesTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class NotesScreenTest{

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup()
    {
        hiltRule.inject()
        composeRule.activity
            .setContent {
            val navController = rememberNavController()
            MyNotesTheme {
                NavHost(navController = navController, startDestination = Screen.NotesScreen.route){
                    composable(Screen.NotesScreen.route)
                    {
                        NotesScreen(navController = navController)
                    }
                }
            }
        }
    }

    @Test
    fun clickToggleOrderSection_isVisible()
    {
        val context = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION).assertDoesNotExist()
        composeRule.onNodeWithContentDescription(context.getString(R.string.sort_icon_description)).performClick()
        composeRule.onNodeWithTag(TestTags.ORDER_SECTION).assertIsDisplayed()
    }

    @Test
    fun checkScreenComposablesVisibility()
    {
        val context = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithContentDescription(context.getString(R.string.add_icon_description)).assertIsDisplayed()
        composeRule.onNodeWithContentDescription(context.getString(R.string.sort_icon_description)).assertIsDisplayed()
        composeRule.onNodeWithText(context.getString(R.string.notes_screen_title))
    }
}