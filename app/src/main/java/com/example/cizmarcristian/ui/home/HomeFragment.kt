package com.example.cizmarcristian.ui.home

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.cizmarcristian.R
import com.example.cizmarcristian.databinding.FragmentHomeBinding
import com.example.cizmarcristian.model.Rate
import com.example.cizmarcristian.util.ViewModelProviderFactory
import com.example.cizmarcristian.util.generateErrorText
import com.example.cizmarcristian.util.viewBinding
import dagger.android.support.DaggerFragment
import java.util.*
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private var viewModel: HomeViewModel? = null

    private val ratesAdapter = HomeAdapter()

    @set:Inject
    var providerFactory: ViewModelProviderFactory? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, providerFactory!!)[HomeViewModel::class.java]
        initAdapter()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel?.updatePreferences()
        viewModel?.getRatesPeriodically()
    }

    override fun onPause() {
        super.onPause()
        viewModel?.cancelOperations()
    }

    private fun initAdapter() {
        binding.ratesRv.adapter = ratesAdapter
    }

    private fun initObservers() {
        viewModel?.loading?.observe(viewLifecycleOwner) { loading ->
            binding.loadingPb.visibility = if (loading) View.VISIBLE else View.GONE
        }

        viewModel?.rates?.observe(viewLifecycleOwner) { result ->
            if (result == null || result.success == false) {
                val text = generateErrorText(result?.error)
                Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT).show()
                return@observe
            }
            val rates = result.rates?.toList()?.map { Rate(it.first, it.second) }
            val format = SimpleDateFormat("HH:mm:ss dd-MM-yy", Locale.getDefault())
            val date = Date(result.timestamp?.times(1000) ?: 0)
            val dateString = format.format(date)
            val deviceDate = format.format(System.currentTimeMillis())

            // The free version of the API only updates the exchange rates once every few hours,
            // which is why I'm also displaying the current time, not just the exchange rate time
            // from the API
            binding.timestampTv.text =
                String.format(getString(R.string.concat_string_api_device), dateString, deviceDate)

            ratesAdapter.submitList(rates)
        }
    }
}