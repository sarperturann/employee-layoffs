package com.study.employeelayoffs.employee

import java.util.Date
import javax.persistence.*

@Entity
data class Employee(
        @Id
        @SequenceGenerator(name = PERSON_SEQUENCE, sequenceName = PERSON_SEQUENCE, initialValue = 1, allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = PERSON_SEQUENCE)
        val id: Long = 1,
        var name: String = "",
        var lastName: String? = null,
        var job: String = "",
        var position: String? = null,
        var team: String? = null,
        var startingDate: Date? = null,
        var lastUpdateDate: Date? = null,
        var linesOfCodeWritten: Int? = 0,
) {
    companion object {
        const val PERSON_SEQUENCE: String = "PERSON_SEQUENCE"
    }
}
