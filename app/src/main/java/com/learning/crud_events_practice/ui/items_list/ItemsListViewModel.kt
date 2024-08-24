package com.learning.crud_events_practice.ui.items_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.crud_events_practice.data.Items
import com.learning.crud_events_practice.data.ItemsRepository
import com.learning.crud_events_practice.util.Routes
import com.learning.crud_events_practice.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsListViewModel @Inject constructor(private val repository: ItemsRepository) :
    ViewModel() {

    val items = repository.getAllItems()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedItem: Items? = null

    fun onEvent(event: ItemsListEvent) {

        when (event) {
            is ItemsListEvent.OnItemClick -> sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_ITEMS + "?itemId=${event.item.id}"))

            is ItemsListEvent.OnAddItemClick -> sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_ITEMS))

            is ItemsListEvent.OnDeleteItemClick -> {
                viewModelScope.launch {
                    deletedItem = event.item
                    repository.deleteItem(event.item)
                    sendUiEvent(
                        UiEvent.ShowSnackBar(
                            message = "Предмет удален",
                            action = "Отменить"

                        )
                    )
                }
            }

            is ItemsListEvent.OnDoneChange -> {
                viewModelScope.launch {
                    repository.insertItem(
                        event.item.copy(
                            isDone = event.isDone

                        )
                    )

                }
            }

            is ItemsListEvent.OnUndoItemClick -> {
                deletedItem?.let { item ->
                    viewModelScope.launch {
                        repository.insertItem(item)
                    }
                }
            }
        }
    }


    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}