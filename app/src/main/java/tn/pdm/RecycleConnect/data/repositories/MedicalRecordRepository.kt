// MedicalRecordRepository.kt
package tn.pdm.RecycleConnect.data.repositories

import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import tn.pdm.RecycleConnect.data.api.RetrofitInstance
import tn.pdm.RecycleConnect.data.models.MedicalRecord
import java.io.File

class MedicalRecordRepository() {

    suspend fun getAllMedicalRecords(): List<MedicalRecord> {
        val response = RetrofitInstance.apimedicalrecord.getAllMedicalRecords()
        if (response.isSuccessful) {
            // If the network call is successful, return the body
            return response.body() ?: listOf()
        } else {
            // If the network call failed, throw an exception or handle the error
            throw Exception(response.errorBody()?.string() ?: "Unknown error")
        }
    }

    suspend fun createMedicalRecord(medicalRecord: MedicalRecord) {
        try {
            val response = RetrofitInstance.apimedicalrecord.createMedicalRecord(medicalRecord)
            if (response.isSuccessful && response.body() != null) {
                // Handle the successful response here
            } else {
                // Handle unsuccessful response or error here
                Log.e("MedicalRecordRepository", "Server responded with: ${response.code()}")
            }
        } catch(e: Exception) {
            // Handle exceptions like network issues here
            Log.e("MedicalRecordRepository", "Error creating medical record", e)
        }
    }

    // Function to fetch medical record by id
    suspend fun getMedicalRecordById(medicalRecordId: String): MedicalRecord {
        val response = RetrofitInstance.apimedicalrecord.getMedicalRecordById(medicalRecordId)
        if (response.success) {
            return response.events
        } else {
            // Handle the error or return an empty medical record
            return MedicalRecord()
        }
    }

    // Add more repository methods as needed
}
