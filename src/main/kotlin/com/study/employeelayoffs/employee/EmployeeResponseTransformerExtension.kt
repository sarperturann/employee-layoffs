package com.study.employeelayoffs.employee

import com.study.employeelayoffs.employee.dto.AddEmployeeRequest
import com.study.employeelayoffs.employee.dto.EmployeeResponse
import java.util.Calendar
import java.util.Date

fun Employee?.toEmployeeResponse(): EmployeeResponse {
    val timeSpent = calculateTimeSpent(this?.startingDate);
    val timeSpentTillLastUpdate = calculateTimeSpent(this?.lastUpdateDate);
    return EmployeeResponse(
            id = this?.id ?: 1L,
            fullName = "${this?.lastName}, ${this?.name}",
            jobTitle = "${this?.position} ${this?.job}",
            timeSpent = timeSpent,
            timeSpentTillLastUpdate = timeSpentTillLastUpdate,
            efficiency = calculateEfficiency(this?.linesOfCodeWritten, timeSpentTillLastUpdate),
            team = this?.team ?: "No Information",
            startingDate = this?.startingDate.toString(),
            job = this?.job ?: "No Information",
    )
}

fun AddEmployeeRequest?.toEmployee(): Employee {
    return Employee(
        name = this?.name ?: "",
        lastName = this?.lastName,
        job = this?.job ?: "",
        position = this?.position,
        team = this?.team,
        startingDate = this?.startingDate,
        lastUpdateDate = this?.lastUpdateDate,
        linesOfCodeWritten = this?.linesOfCodeWritten
    )
}

fun calculateTimeSpent(date: Date?): String {
    return if(date != null){
        val currentDate = Calendar.getInstance().time
        val diff = currentDate.time - date.time
        val diffInDays = diff / (24 * 60 * 60 * 1000)
        "$diffInDays days"
    } else {
        "Missing Information"
    }
}

fun calculateEfficiency(loc: Int?, timeSpentTillLastUpdate: String): String {
    if( loc == null || timeSpentTillLastUpdate == "Missing Information") return "Not enough data";
    val daysString = timeSpentTillLastUpdate.split(" ")[0].toInt();
    return if (daysString != 0){
        val efficiency = loc.toDouble().div(daysString.toDouble())
        efficiency.toString()
    } else {
        "Not enough data"
    }
}