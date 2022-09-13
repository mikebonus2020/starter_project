package com.example.noteapp.ui.list

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteapp.model.Note

@Composable
fun TodoItem(
    note: Note,
    onEvent: (ListEvent) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = note.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(8.dp))

            note.description?.let {
                Text(text = it)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = note.date.toString(),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Row (
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {
                onEvent(ListEvent.OnDeleteTodoClick(note))
            }) {

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete"
                )
            }
        }
    }
}