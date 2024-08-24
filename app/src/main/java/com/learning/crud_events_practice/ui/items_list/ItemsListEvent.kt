package com.learning.crud_events_practice.ui.items_list

import com.learning.crud_events_practice.data.Items

sealed class ItemsListEvent {
    data class OnDeleteItemClick(val item: Items) : ItemsListEvent()
    data class OnDoneChange(val item: Items, val isDone: Boolean) : ItemsListEvent()
    data class OnItemClick(val item: Items) : ItemsListEvent()
    data class OnAmountAddClick(val item: Items) : ItemsListEvent()
    data class OnAmountDeleteClick(val item: Items) : ItemsListEvent()

    object OnAddItemClick : ItemsListEvent()
    object OnUndoItemClick : ItemsListEvent()
}