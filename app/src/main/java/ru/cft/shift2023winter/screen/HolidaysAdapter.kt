package ru.cft.shift2023winter.screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.cft.shift2023winter.R
import ru.cft.shift2023winter.data.Holidays
import ru.cft.shift2023winter.databinding.HolidaysItemBinding

class HolidaysAdapter: RecyclerView.Adapter<HolidaysAdapter.HolidayHolder>() {

    var holidaysList: List<Holidays> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    class HolidayHolder(item: View): RecyclerView.ViewHolder(item) {

        val binding = HolidaysItemBinding.bind(item)
        fun bind(holiday: Holidays) = with(binding){
            date.text = holiday.date
            localName.text = holiday.localName
            name.text = holiday.name
            if (holiday.fixed)
                 fixed.text = "yes"
            else fixed.text = "no"
            if (holiday.global)
                global.text = "yes"
            else global.text = "no"
            if (holiday.launchYear != 0)
               launchYear.text = holiday.launchYear.toString()
            else launchYear.text = "-"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolidayHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.holidays_item, parent, false)
        return HolidayHolder(view)
    }

    override fun onBindViewHolder(holder: HolidayHolder, position: Int) {
        holder.bind(holidaysList[position])
    }

    override fun getItemCount(): Int {
        return holidaysList.size
    }
}