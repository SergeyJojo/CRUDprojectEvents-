package com.learning.crud_events_practice.ui.add_item

sealed class AddEditItemEvent {
    data class OnTitleChange(val title: String) : AddEditItemEvent()
    data class OnDescriptionChange(val description: String) : AddEditItemEvent()
    object OnSaveItemClick : AddEditItemEvent()
}