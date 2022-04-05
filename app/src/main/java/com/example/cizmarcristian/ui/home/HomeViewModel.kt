package com.example.cizmarcristian.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cizmarcristian.data.repository.ExchangeRepository
import com.example.cizmarcristian.data.repository.PreferencesRepository
import com.example.cizmarcristian.model.ExchangeRate
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val exchangeRepository: ExchangeRepository,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    private val _rates = MutableLiveData<ExchangeRate?>()
    val rates: LiveData<ExchangeRate?> = _rates

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private var mainCurrency: String = "EUR"
    private var refreshRate: Int = 3
    private val disposable = CompositeDisposable()

    fun updatePreferences() {
        preferencesRepository.getMainCurrency()?.let {
            mainCurrency = it
        }
        refreshRate = preferencesRepository.getRefreshRate()
    }

    /**
     * Updates the exchange rates every 'refreshRate * 1000L' seconds
     */
    fun getRatesPeriodically() {
        disposable.add(
            Observable.interval(0, refreshRate * 1000L, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    getRates()
                }
        )
    }

    /**
     * Makes an API request to get the exchange rates.
     * The variable 'mainCurrency' should be used instead of hardcoding 'EUR',
     * but the free version of the API doesn't allow using other currencies...
     */
    private fun getRates() {
        if (_loading.value == true) return
        _loading.value = true
        disposable.add(
            exchangeRepository.getExchangeRates("EUR")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _loading.value = false
                    _rates.value = it
                }, {
                    _loading.value = false
                    _rates.value = null
                })
        )
    }

    fun cancelOperations() {
        disposable.clear()
        _loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}