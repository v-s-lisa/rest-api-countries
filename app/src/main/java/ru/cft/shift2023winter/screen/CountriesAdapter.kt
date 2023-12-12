package ru.cft.shift2023winter.screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.data.Country

class CountryAdapter(private val countryClickListener: (Country) -> Unit) : RecyclerView.Adapter<CountryViewHolder>() {

    var countries: List<Country> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.country_item, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position], countryClickListener)
    }

    override fun getItemCount(): Int =
        countries.size
}

class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val nameText get() = itemView.findViewById<TextView>(R.id.nameText)

    fun bind(country: Country, countryClickListener: (Country) -> Unit) {
        nameText.text = country.name

        itemView.setOnClickListener {
            countryClickListener(country)
        }
    }
}