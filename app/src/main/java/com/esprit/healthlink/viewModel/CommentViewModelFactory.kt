import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.esprit.healthlink.data.model.CommentRepository
import com.esprit.healthlink.viewModel.CommentViewModel

class CommentViewModelFactory(private val commentRepository: CommentRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CommentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CommentViewModel(commentRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
