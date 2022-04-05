package com.example.cizmarcristian.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.cizmarcristian.R
import com.example.cizmarcristian.databinding.FragmentHistoryBinding
import com.example.cizmarcristian.util.ViewModelProviderFactory
import com.example.cizmarcristian.util.generateErrorText
import com.example.cizmarcristian.util.viewBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class HistoryFragment : DaggerFragment() {

    private val binding by viewBinding(FragmentHistoryBinding::bind)
    private var viewModel: HistoryViewModel? = null

    @set:Inject
    var providerFactory: ViewModelProviderFactory? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, providerFactory!!)[HistoryViewModel::class.java]

        initChart(binding.chartRon)
        initChart(binding.chartUsd)
        initChart(binding.chartBgn)
        observeChartData()
        viewModel?.generateHistoricRequests()
    }

    private fun initChart(chart: LineChart) {
        with(chart) {
            legend.isEnabled = false
            axisRight.isEnabled = false
            description.isEnabled = false
            invalidate()
        }
    }

    private fun observeChartData() {
        viewModel?.historicDaysLiveData?.observe(viewLifecycleOwner) {
            val ronArray: ArrayList<Entry> = ArrayList()
            val usdArray: ArrayList<Entry> = ArrayList()
            val bgnArray: ArrayList<Entry> = ArrayList()
            for (i in it.indices) {
                val valRon = it[i]?.get("RON")
                if (valRon != null && valRon != 0f) {
                    ronArray.add(Entry(i.toFloat(), valRon.toFloat()))
                }
                val valUsd = it[i]?.get("USD")
                if (valUsd != null && valUsd != 0f) {
                    usdArray.add(Entry(i.toFloat(), valUsd.toFloat()))
                }
                val valBgn = it[i]?.get("BGN")
                if (valBgn != null && valBgn != 0f) {
                    bgnArray.add(Entry(i.toFloat(), valBgn.toFloat()))
                }
            }
            setDataToChart(binding.chartRon, ronArray)
            setDataToChart(binding.chartUsd, usdArray)
            setDataToChart(binding.chartBgn, bgnArray)
        }
    }

    private fun setDataToChart(chart: LineChart, dataArray: ArrayList<Entry>) {
        val lineDataSet = LineDataSet(dataArray, "")
        val data = LineData(lineDataSet)
        data.setDrawValues(false)
        chart.data = data
        chart.invalidate()
    }
}