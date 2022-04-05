package com.example.cizmarcristian.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.example.cizmarcristian.R
import com.example.cizmarcristian.databinding.FragmentSettingsBinding
import com.example.cizmarcristian.util.ViewModelProviderFactory
import com.example.cizmarcristian.util.viewBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SettingsFragment : DaggerFragment() {

    private val binding by viewBinding(FragmentSettingsBinding::bind)
    private var viewModel: SettingsViewModel? = null

    @set:Inject
    var providerFactory: ViewModelProviderFactory? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, providerFactory!!)[SettingsViewModel::class.java]

        initObservers()
        viewModel?.getSavedRefreshRate()
        viewModel?.getSavedCurrency()
        viewModel?.getCurrencies()
    }

    override fun onResume() {
        super.onResume()
        initRefreshRateDropdown()
        initCurrencyDropdown()
    }

    private fun initObservers() {
        viewModel?.refreshRate?.observe(viewLifecycleOwner) {
            binding.refreshRateDropdown.setText(it.toString(), false)
        }
        viewModel?.mainCurrency?.observe(viewLifecycleOwner) {
            binding.currencyDropdown.setText(it, false)
        }
    }

    private fun initRefreshRateDropdown() {
        val items = listOf(3, 5, 15)
        val rateAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, items)
        binding.refreshRateDropdown.setAdapter(rateAdapter)
        binding.refreshRateDropdown.setOnItemClickListener { _, _, i, _ ->
            viewModel?.setSavedRefreshRate(items[i])
        }
    }

    private fun initCurrencyDropdown() {
        viewModel?.currencies?.observe(viewLifecycleOwner) { list ->
            if (list == null) return@observe
            val currencyAdapter = ArrayAdapter(
                requireContext(),
                R.layout.dropdown_item,
                list.map { "${it.first}: ${it.second}" })
            binding.currencyDropdown.setAdapter(currencyAdapter)
            binding.currencyDropdown.setOnItemClickListener { _, _, i, _ ->
                viewModel?.setSavedCurrency(list[i].first)
            }
        }
    }
}