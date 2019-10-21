package tech.coderhub.android.helper

fun dateToString(year: Int, month: Int, dayOfMonth: Int): String {
    val yearString: String = year.toString()
    var monthString: String = (month+1).toString()
    var dayString: String = dayOfMonth.toString()
    if (monthString.length==1) {
        monthString = "0$monthString"
    }
    if (dayString.length==1) {
        dayString = "0$dayString"
    }
    return "$yearString-$monthString-$dayString"
}