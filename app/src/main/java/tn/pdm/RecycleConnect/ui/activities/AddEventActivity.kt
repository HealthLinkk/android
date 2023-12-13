package tn.pdm.RecycleConnect.ui.activities
import EventsViewModel
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import tn.pdm.RecycleConnect.R
import tn.pdm.RecycleConnect.data.models.Events
import tn.pdm.RecycleConnect.data.repositories.EventsRepository
import tn.pdm.RecycleConnect.data.viewmodels.EventsViewModelFactory
import tn.pdm.RecycleConnect.databinding.ActivityAddEventBinding
import java.util.Calendar

    class AddEventActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEventBinding
    private lateinit var viewModel: EventsViewModel
    private lateinit var getContent: ActivityResultLauncher<String>
    lateinit var tvStartDate: TextView
    lateinit var btnShowdDatePicker: Button
    private val calendar = Calendar.getInstance()

    private val repository = EventsRepository()

    // Declare imageUri at the class level
    private var imageUri: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //date picker
        tvStartDate = findViewById(R.id.textViewStartDate)
        btnShowdDatePicker = findViewById(R.id.btnShowdDatePicker)
        binding.floatingActionButton.setOnClickListener {
            finish()
        }
        btnShowdDatePicker.setOnClickListener {
            val datePicker = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    val selectedCalendar = Calendar.getInstance()
                    selectedCalendar.set(year, month, dayOfMonth)

                    // Check if the selected date is in the past
                    if (selectedCalendar.timeInMillis < System.currentTimeMillis()) {
                        // Show a message or handle it as you prefer
                        // For now, just log a message
                        Log.d("AddEventActivity", "Selected date is in the past.")
                    } else {
                        tvStartDate.text = "$dayOfMonth/${month + 1}/$year"
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        // Instantiate the EventsViewModel
        viewModel = ViewModelProvider(this, EventsViewModelFactory(repository))
            .get(EventsViewModel::class.java)

        // Initialize the ActivityResultLauncher
        getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            // Update the UI - ImageView with the selected image
            binding.imageViewSelectedPhoto.setImageURI(uri)
            // Update the imageUri
            imageUri = getRealPathFromURI(uri!!).toString()
            Log.d("AddEventActivity", "onCreate: $imageUri")
        }


        // Binding buttonPickPhoto to ActivityResultLauncher
        binding.buttonPickPhoto.setOnClickListener {
            getContent.launch("image/*")
        }
        //log the selected image uri to the console

        // Binding buttonCreateEvent to coroutine
        binding.buttonCreateEvent.setOnClickListener {
            // Create an event object
            val nameEvent = binding.editTextEventName.text.toString()
            val descriptionEvent = binding.editTextEventDescription.text.toString()
            val addressEvent = binding.editTextAddressEvent.text.toString()
            //val startEvent = tvStartDate.text.toString()

            val event = Events(
                nameEvent = nameEvent,
                PhotoEvent = imageUri,
                startEvent = calendar.time,
                descriptionEvent = descriptionEvent,
                addressEvent = addressEvent,
            )
            //Log StartEvent
            Log.d("AddEventActivity", "onCreate: $event")

            // Launch a coroutine to call the suspend function
            lifecycleScope.launch {
                try {
                    // Try to create the event
                    viewModel.createEvent(event, imageUri)

                    // If successful, navigate to MainActivity
                    val intent = Intent(this@AddEventActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Finish current activity to prevent going back to it
                } catch (e: Exception) {
                    // Handle any errors here
                    Log.e("AddEventActivity", "Error creating event: ${e.message}")
                    // You can show a toast or display an error message to the user
                }
            }
        }

    }
    private fun getRealPathFromURI(uri: Uri): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(uri, projection, null, null, null)
        cursor?.use {
            val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            it.moveToFirst()
            return it.getString(columnIndex)
        }
        return null
    }

}

