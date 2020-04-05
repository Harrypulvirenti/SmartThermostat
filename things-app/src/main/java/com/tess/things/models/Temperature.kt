package com.tess.things.models

import com.tess.extensions.kotlin.or

private const val TEMPERATURE_DEFAULT_VALUE = "--"

inline class Temperature(private val temperature: String? = null) {

    val value: String
        get() = temperature.or(TEMPERATURE_DEFAULT_VALUE)
}

fun String?.asTemperature() =
    Temperature(this)
