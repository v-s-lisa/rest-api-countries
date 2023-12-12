package ru.cft.shift2023winter.screen

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.MainActivity
import kotlinx.coroutines.launch
import ru.cft.shift2023winter.data.Holidays

class HolidaysFragment : Fragment(R.layout.fragment_holidays) {

    private val args: HolidaysFragmentArgs by navArgs()

    private lateinit var progressBar: ProgressBar
    private lateinit var errorText: TextView
    private lateinit var holidaysContent: LinearLayout

    private var holidaysList: RecyclerView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = requireView().findViewById(R.id.progressBar)
        errorText = requireView().findViewById(R.id.errorText)
        holidaysContent = requireView().findViewById(R.id.holidaysContent)

        val holidaysList = requireView().findViewById<RecyclerView>(R.id.holidaysList)
        this.holidaysList = holidaysList

        holidaysList.adapter = HolidaysAdapter()

        showProgress()

        lifecycleScope.launch {
            try {
                val id = args.countryId
                val repository = MainActivity.repository
                val holidays = repository.getHolidayByCountryCode(id)
                showContent(holidays)

            } catch (ex: Exception) {
                showError(ex.message.orEmpty())
            }
        }
    }

    private fun showContent(holiday: List<Holidays>) {
        progressBar.isVisible = false
        errorText.isVisible = false
        holidaysContent.isVisible = true

        (holidaysList?.adapter as? HolidaysAdapter)?.holidaysList = holiday
    }

    private fun showProgress() {
        progressBar.isVisible = true
        errorText.isVisible = false
        holidaysContent.isVisible = false
    }

    private fun showError(message: String) {
        errorText.isVisible = true
        progressBar.isVisible = false
        holidaysContent.isVisible = false

        errorText.text = message
    }

    override fun onDestroy() {
        holidaysList?.adapter = null
        super.onDestroy()
    }
}