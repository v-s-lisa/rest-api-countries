package ru.cft.shift2023winter.data

data class Holidays (
    val date: String,
    val localName: String,
    val name: String,
    val countryCode: String,
    val fixed: Boolean,
    val global: Boolean,
    val counties: List<String>,
    val launchYear: Int,
    val types: List<String>
)