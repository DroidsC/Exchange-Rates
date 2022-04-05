package com.example.cizmarcristian.di

import com.example.cizmarcristian.ui.history.HistoryFragment
import com.example.cizmarcristian.ui.MainActivity
import com.example.cizmarcristian.ui.home.HomeFragment
import com.example.cizmarcristian.ui.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment?

    @ContributesAndroidInjector
    abstract fun contributeHistoryFragment(): HistoryFragment?

    @ContributesAndroidInjector
    abstract fun contributeSettingsFragment(): SettingsFragment?

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity?
}