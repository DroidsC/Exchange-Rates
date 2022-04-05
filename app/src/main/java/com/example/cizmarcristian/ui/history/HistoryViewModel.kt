package com.example.cizmarcristian.ui.history

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cizmarcristian.data.repository.ExchangeRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

private const val BASE_CURRENCY = "EUR"
private const val DAYS_IN_HISTORY = 10

class HistoryViewModel @Inject constructor(private val exchangeRepository: ExchangeRepository) :
    ViewModel() {

    private val historicDays =
        MutableList(DAYS_IN_HISTORY) { mapOf("RON" to 0f, "USD" to 0f, "BGN" to 0f) }
    private val _historicDaysLiveData = MutableLiveData(historicDays)
    val historicDaysLiveData: LiveData<MutableList<Map<String, Float>>> = _historicDaysLiveData

    private val currencies = "RON,USD,BGN"
    private val disposable = CompositeDisposable()

    /**
     * Makes multiple API requests to get the exchange rates from the most recent days.
     * Since the free version of the API doesn't allow getting the exchange rate values from
     * multiple days using a single request, we unfortunately have to make multiple requests
     */
    fun generateHistoricRequests() {
        val cal = Calendar.getInstance()
        repeat(DAYS_IN_HISTORY) { repeatCount ->
            cal.add(Calendar.DAY_OF_MONTH, -1)
            val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val dateString = format.format(cal.time)
            getHistoricRate(dateString, DAYS_IN_HISTORY - 1 - repeatCount)
        }
    }

    /**
     * Makes an API request to get the exchange rates from a specific day and saves the result
     * at a specific position of a list
     */
    private fun getHistoricRate(date: String, idx: Int) {
        disposable.add(
            exchangeRepository.getHistoricRates(date, BASE_CURRENCY, currencies)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _historicDaysLiveData.value = _historicDaysLiveData.value.apply {
                        it.rates?.let { rates -> this?.set(idx, rates) }
                    }
                }, {})
        )
    }
}