package com.esprit.healthlink.data.model

data class Appointments(val appointments: List<Appointment>)


enum class AppointmentType {
    ONLINE,
    PHYSICAL
}

enum class State {
    Scheduled,
    Completed,
    Finished
}


data class Appointment(
    val _id: String,
    val doctorId: String,
    val patientId: String,
    val title: String,
    val startDateTime : String,
    val type: AppointmentType,
    val state: State,
    var isSelected: Boolean = false
)



data class createRendezVous(
    val doctorId: String,
    val patientId: String,
    val title: String,
    val startDateTime: String,
    val type: AppointmentType
)


