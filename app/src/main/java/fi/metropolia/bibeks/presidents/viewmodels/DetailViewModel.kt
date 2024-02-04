package fi.metropolia.bibeks.presidents.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fi.metropolia.bibeks.presidents.utils.WikiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: WikiRepository) : ViewModel() {
    private val _wikiUiState = MutableStateFlow(0)
    val wikiUiState: StateFlow<Int> get() = _wikiUiState

    fun getHits(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val serverResp = repository.hitCountCheck(name)
                _wikiUiState.value = serverResp.query.searchinfo.totalhits
            } catch (e: Exception) {
                Log.e("DetailViewModel", "Error: ${e.message}")
            }
        }
    }
}

