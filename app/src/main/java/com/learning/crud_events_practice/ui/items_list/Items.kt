package com.learning.crud_events_practice.ui.items_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learning.crud_events_practice.data.Items

@Composable
fun Items(
    item: Items,
    onEvent: (ItemsListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = {
                    onEvent(ItemsListEvent.OnDeleteItemClick(item))
                }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Удалить")
                }

            }
            item.description.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = it)
            }

        }
        Checkbox(checked = item.isDone, onCheckedChange = { isChecked ->
            onEvent(ItemsListEvent.OnDoneChange(item, isChecked))
        })

    }
}