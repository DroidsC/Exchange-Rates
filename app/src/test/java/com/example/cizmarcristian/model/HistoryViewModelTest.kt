package com.example.cizmarcristian.model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cizmarcristian.data.repository.ExchangeRepository
import com.example.cizmarcristian.data.repository.PreferencesRepository
import com.example.cizmarcristian.ui.settings.SettingsViewModel
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate

@RunWith(PowerMockRunner::class)
@PowerMockRunnerDelegate(JUnit4::class)
@PrepareForTest(value = [SettingsViewModel::class])
class HistoryViewModelTest {

    companion object {
        @BeforeClass
        @JvmStatic
        fun setupRxSchedulers() {
            RxJavaPlugins.reset()
            RxAndroidPlugins.reset()
            RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
            RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        }
    }

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val repository: ExchangeRepository = mock()
    private val preferencesRepository: PreferencesRepository = mock()
    private val viewModel = SettingsViewModel(repository, preferencesRepository)
    private val throwable: Throwable = mock()

    @Test
    fun getCurrencies_successful_shouldUpdateValue() {
        val key = "USD"
        val value = "American $"
        val c = CurrencyResponse("true", mapOf(key to value), null)
        val response = Single.just(c)
        val expected = listOf(Pair(key, value))

        Mockito.`when`(repository.getCurrencies())
            .thenReturn(response)
        viewModel.getCurrencies()
        Assert.assertEquals(expected, viewModel.currencies.value)
    }

    @Test
    fun getCurrencies_failure_shouldSetValueNull() {
        Mockito.`when`(repository.getCurrencies())
            .thenReturn(Single.error(throwable))
        viewModel.getCurrencies()
        Assert.assertEquals(null, viewModel.currencies.value)
    }
}