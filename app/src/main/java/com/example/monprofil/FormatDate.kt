package com.example.monprofil

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun stringToDate(date: String): String{
    val d = LocalDate.parse(date)
    return d.dayOfMonth.toString()+" "+d.month.getDisplayName(
        TextStyle.SHORT,
        Locale.getDefault()
    )+". "+d.year.toString()

}