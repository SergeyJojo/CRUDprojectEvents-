package com.learning.crud_events_practice.ui.add_item

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.learning.crud_events_practice.ui.items_list.ItemsListViewModel
import com.learning.crud_events_practice.util.UiEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddEditItemScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditItemViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }

                else -> Unit
            }
        }

    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onEvent(AddEditItemEvent.OnSaveItemClick) }) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "Сохранить")

            }
        }

    ) {
        Column(Modifier.fillMaxSize()) {
            TextField(
                value = viewModel.description,
                onValueChange = { viewModel.onEvent(AddEditItemEvent.OnDescriptionChange(it)) },
                placeholder = { Text(text = "Описание") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )

        }

    }

}