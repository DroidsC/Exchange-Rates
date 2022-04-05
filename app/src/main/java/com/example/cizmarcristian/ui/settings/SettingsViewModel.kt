package com.example.cizmarcristian.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cizmarcristian.data.repository.ExchangeRepository
import com.example.cizmarcristian.data.repository.PreferencesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val exchangeRepository: ExchangeRepository,
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    private val _currencies = MutableLiveData<List<Pair<String, String>>?>()
    val currencies: LiveData<List<Pair<String, String>>?> = _currencies

    private val _refreshRate = MutableLiveData<Int>()
    val refreshRate: MutableLiveData<Int> = _refreshRate

    private val _mainCurrency = MutableLiveData<String>()
    val mainCurrency: MutableLiveData<String> = _mainCurrency

    private val disposable = CompositeDisposable()

    fun getSavedRefreshRate() {
        _refreshRate.value = preferencesRepository.getRefreshRate()
    }

    fun setSavedRefreshRate(rate: Int) {
        preferencesRepository.saveRefreshRate(rate)
    }

    fun getSavedCurrency() {
        _mainCurrency.value = preferencesRepository.getMainCurrency()
    }

    fun setSavedCurrency(currency: String) {
        preferencesRepository.saveMainCurrency(currency)
    }

    /**
     * Get the currencies supported by the API
     */
    fun getCurrencies() {
        disposable.add(
            exchangeRepository.getCurrencies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    _currencies.value = response.symbols?.map { Pair(it.key, it.value) }
                }, {
                    _currencies.value = null
                })
        )
    }
}