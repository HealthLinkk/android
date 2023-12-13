package tn.pdm.RecycleConnect.data.models

import java.util.Date

// Donation.kt

data class Donations(
    val id: String,
    val dateDonation: Date = Date(),
    val isAnonymous: Boolean = false,
    val donor: String,
    val amountDonation: Int,
    val paymentMethod: String,
    val items: List<String>,
    val organization: String,
    val event: String
)
