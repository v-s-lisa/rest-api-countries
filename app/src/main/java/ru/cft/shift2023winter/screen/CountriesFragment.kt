package ru.cft.shift2023winter.screen

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.data.Country
import ru.cft.shift2023winter.MainActivity
import kotlinx.coroutines.launch

class CountriesFragment : Fragment(R.layout.fragment_countries) {

    private lateinit var progressBar: ProgressBar
    private lateinit var errorText: TextView
    private lateinit var countriesContent: LinearLayout

    private var countriesList: RecyclerView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = requireView().findViewById(R.id.progressBar)
        errorText = requireView().findViewById(R.id.errorText)
        countriesContent = requireView().findViewById(R.id.countriesContent)

        val countryList = requireView().findViewById<RecyclerView>(R.id.countriesList)
        this.countriesList = countryList

        countryList.adapter = CountryAdapter(::handleCountryClick)

        launchCountryLoading()
    }

    private fun launchCountryLoading() {
        showProgress()

        lifecycleScope.launch {
            try {
                val repository = MainActivity.repository
                val countries = repository.getAll()

                showContent(countries)
            } catch (ex: Exception) {
                showError(ex.message.orEmpty())
            }
        }
    }

    private fun showContent(countries: List<Country>) {
        progressBar.isVisible = false
        errorText.isVisible = false
        countriesContent.isVisible = true
        (countriesList?.adapter as? CountryAdapter)?.countries = countries
    }

    private fun handleCountryClick(country: Country) {
        MainActivity.openDetails(country.countryCode)
    }

    private fun showProgress() {
        progressBar.isVisible = true
        errorText.isVisible = false
        countriesContent.isVisible = false
    }

    private fun showError(message: String) {
        errorText.isVisible = true
        progressBar.isVisible = false
        countriesContent.isVisible = false

        errorText.text = message
    }

    override fun onDestroy() {
        countriesList?.adapter = null
        super.onDestroy()
    }
}