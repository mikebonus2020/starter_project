package com.example.noteapp.ui.detail

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.noteapp.util.UiEvent
import kotlinx.coroutines.flow.collect
import java.util.*

@Composable
fun AddEditTodoScreen(
    onPopBackStack: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Task Detail",
                        color = Color.White
                    )
                },
                backgroundColor = Color.Black
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(DetailEvent.OnSaveTodoClick)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save"
                )
            }
        }
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            TextField(
                value = viewModel.title,
                onValueChange = {
                    viewModel.onEvent(DetailEvent.OnTitleChange(it))
                },
                placeholder = {
                    Text(text = "Title")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = viewModel.description,
                onValueChange = {
                    viewModel.onEvent(DetailEvent.OnDescriptionChange(it))
                },
                placeholder = {
                    Text(text = "Description")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(10.dp))

            DatePickerDialog()

        }
    }
}

@Composable
fun DatePickerDialog(
    viewModel: DetailViewModel = hiltViewModel()
) {

    val mContext = LocalContext.current
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    // DATE-PICKER
    var mDate = remember { mutableStateOf("") }

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker?, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "${mMonth + 1}/$mDayOfMonth/$mYear"
        }, mYear, mMonth, mDay
    )



    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {
                mDatePickerDialog.show()

            }, colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black
            )
        ) {
            Text(
                text = "SELECT DATE",
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        Text(
            color = Color.DarkGray,
            modifier = Modifier.wrapContentSize(),
            text = mDate.value,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }



    viewModel.onEvent(DetailEvent.OnDateChange(mDate.value))
}

