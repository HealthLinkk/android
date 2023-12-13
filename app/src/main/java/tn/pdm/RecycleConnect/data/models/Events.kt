package tn.pdm.RecycleConnect.data.models

import java.util.Date

data class Events(
    val _id: String,
    val nameEvent: String,
    val startEvent: Date,
    val endEvent: Date,
    val descriptionEvent: String,
    val PhotoEvent: String,
    val addressEvent: String,
    val interested: List<String>,
    val going: List<String>,
    val organizer: String,
    val createdAt: Date = Date(),
)//constructor for only nameEvent, descriptionEvent, addressEvent
{
    constructor(
        nameEvent: String,
        PhotoEvent: String,
        startEvent: Date,
        descriptionEvent: String,
        addressEvent: String
    ) : this(
        "",
        nameEvent,
        startEvent,
        Date(),
        descriptionEvent,
        PhotoEvent,
        addressEvent,
        emptyList(),
        emptyList(),
        "",
        Date()
    )
    //empty constructor
    constructor() : this(
        "",
        "",
        Date(),
        Date(),
        "",
        "",
        "",
        emptyList(),
        emptyList(),
        "",
        Date()
    )


}