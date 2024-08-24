package com.learning.crud_events_practice.ui.add_item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.learning.crud_events_practice.data.Items
import com.learning.crud_events_practice.data.ItemsRepository
import com.learning.crud_events_practice.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditItemViewModel @Inject constructor(
    private val repository: ItemsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var item by mutableStateOf<Items?>(null)
        private set

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent> { }
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val itemId = savedStateHandle.get<Int>("itemId")!!
        if (itemId != -1) {
            viewModelScope.launch {
                repository.getItemById(itemId)?.let { item ->
                    title = item.title
                    description = item.description
                    this@AddEditItemViewModel.item = item


                }
            }

        }
    }

    fun onEvent(event: AddEditItemEvent) {
        when (event) {
            is AddEditItemEvent.OnDescriptionChange -> description = event.description
            is AddEditItemEvent.OnSaveItemClick -> {
                viewModelScope.launch {
                    if (title.isBlank()) {
                        sendUiEvent(
                            UiEvent.ShowSnackBar(
                                message = "Заголовок не может быть пустой"
                            )
                        )
                        return@launch
                    }
                    repository.insertItem(
                        Items(
                            title = title,
                            description = description,
                            isDone = item?.isDone ?: false,
                            id = item?.id,
                            amount = +1
                        )


                    )
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }

            is AddEditItemEvent.OnTitleChange -> title = event.title
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}