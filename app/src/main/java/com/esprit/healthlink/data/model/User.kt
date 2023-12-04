package com.esprit.healthlink.data.model

data class Doctors(val doctors: List<Doctor>)



data class Doctor(
    val _id: String,
    val name: String,
    val lastName: String,
    val password: String,
    val numTel: Int,
    val role: String,

    val ratings: List<Any>, // You might want to replace `Any` with the actual type of ratings

    val picture: String,
    val diplomaVerification: DiplomaVerification
)

data class PatientSignupRequest(
    val name: String,
    val lastName: String,
    val password: String,
    val numTel: String,
    val dateNaiss: String,
    val otp: String
)

data class User(
    val _id: String,
    val name: String,
    val lastName: String,
    val password: String,
    val numTel: Int,
    val role: String,
    val dateNaiss : String,
    val Doctors: List<Any>,
    val ratings: List<Any>, // You might want to replace `Any` with the actual type of rating
    val picture: String,
    val diplomaVerification: DiplomaVerification
)

data class SendOtpRequest(
    val numTel: String
)
data class LoginRequest(
    val numTel: String,
    val password: String
)

data class Message(
    val message: String
)
data class DiplomaVerification(
    val isVerified: Boolean,
    val verificationDocument: String?, // Replace `String?` with the actual type of verificationDocument
    val specialization: List<String>
)

