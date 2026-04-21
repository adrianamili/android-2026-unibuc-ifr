package com.unibucfmiifr2026.ui.screens

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.unibucfmiifr2026.MainActivity
import com.unibucfmiifr2026.R
import com.unibucfmiifr2026.utils.isValidName
import com.unibucfmiifr2026.viewmodels.AddressViewModel

@Composable
fun AddressDetailScreen(viewModel: AddressViewModel = viewModel(), addressId: Long) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var firstNameError by remember { mutableStateOf<String?>(null) }
    val invalidFirstName = stringResource(R.string.invalid_first_name)
    val invalidLastName = stringResource(R.string.invalid_last_name)
    var lastNameError by remember { mutableStateOf<String?>(null) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.welcome),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = firstName,
            onValueChange = { newValue ->
                firstName = newValue
                firstNameError = null
            },
            label = {
                Text(
                    stringResource(R.string.first_name)
                )
            },
            leadingIcon = {
                Icon(Icons.Default.Person, "first_name")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            isError = firstNameError != null,
            supportingText = firstNameError?.let {
                {
                    Text(
                        text = it
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = lastName,
            onValueChange = { newValue ->
                lastName = newValue
                lastNameError = null
            },
            label = {
                Text(
                    stringResource(R.string.last_name)
                )
            },
            leadingIcon = {
                Icon(Icons.Default.Person2, "last name")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            isError = lastNameError != null,
            supportingText = lastNameError?.let {
                {
                    Text(
                        text = it
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                var valid = true
                if (!firstName.isValidName()) {
                    firstNameError = invalidFirstName
                    valid = false
                }
                if (!lastName.isValidName()) {
                    lastNameError = invalidLastName
                    valid = false
                }
                if (valid) {
                    viewModel.addUser(firstName = firstName, lastName = lastName, addressId = addressId)
                }
            },
        ) {
            Text(stringResource(R.string.add_user_button))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddressDetailScreenPreview() {
    AddressDetailScreen(addressId = 0)

}