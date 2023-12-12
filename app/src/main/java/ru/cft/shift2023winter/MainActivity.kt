package ru.cft.shift2023winter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import ru.cft.shift2023winter.data.NetRepository
import ru.cft.shift2023winter.screen.CountriesFragmentDirections

class MainActivity : AppCompatActivity() {

	val repository = NetRepository()

	private val navController get() = findNavController(R.id.fragmentContainer)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

	fun openDetails(countryId: String) {
		val action = CountriesFragmentDirections.actionCountriesFragmentToHolidaysFragment(countryId)
		navController.navigate(action)
	}
}