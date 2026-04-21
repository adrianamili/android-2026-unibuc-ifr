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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material.icons.filled.Streetview
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.unibucfmiifr2026.data.entities.AddressEntity
import com.unibucfmiifr2026.utils.isValidAddress
import com.unibucfmiifr2026.viewmodels.HomeViewModel
import kotlin.jvm.java

@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel(), logout: () -> Unit = {}, onAddressClick: (addressId: Long) -> Unit = {}) {
    val context = LocalContext.current
    val addresses = viewModel.addresses.collectAsState(initial = emptyList())
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            HomeHeader(viewModel)
        }
        item {
            Button(
                onClick = {
                    logout()
                    val intent = Intent(context, MainActivity::class.java)
                    (context as? ComponentActivity)?.apply {
                        this.startActivity(intent)
                        this.finish()
                    }
                }
            ) {
                Text(stringResource(R.string.logout))
            }
        }
        if(addresses.value.isEmpty()) {
            item {
                Text(
                    text = stringResource(R.string.no_address),
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        else{
            items(addresses.value){ address ->
                ListItem(address, onClick = onAddressClick)
            }
        }
    }
}

@Composable
fun HomeHeader(viewModel: HomeViewModel) {
    var street by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var streetError by remember { mutableStateOf<String?>(null) }
    val invalidStreet = stringResource(R.string.invalid_first_name)
    val invalidCity = stringResource(R.string.invalid_city)
    var cityError by remember { mutableStateOf<String?>(null) }
    Column(
        modifier = Modifier
            .fillMaxWidth(),
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
            value = street,
            onValueChange = { newValue ->
                street = newValue
                streetError = null
            },
            label = {
                Text(
                    stringResource(R.string.street)
                )
            },
            leadingIcon = {
                Icon(Icons.Default.Streetview, "street")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            isError = streetError != null,
            supportingText = streetError?.let {
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
            value = city,
            onValueChange = { newValue ->
                city = newValue
                cityError = null
            },
            label = {
                Text(
                    stringResource(R.string.city)
                )
            },
            leadingIcon = {
                Icon(Icons.Default.LocationCity, "city")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            isError = cityError != null,
            supportingText = cityError?.let {
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
                if (!street.isValidAddress()) {
                    streetError = invalidStreet
                    valid = false
                }
                if (!city.isValidAddress()) {
                    cityError = invalidCity
                    valid = false
                }
                if (valid) {
                    viewModel.addAddress(street = street, city = city)
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(R.string.add_address_button))
        }
    }
}

@Composable
fun ListItem(address: AddressEntity, onClick: (addressId: Long) -> Unit){
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onClick(address.id) }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = address.street,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = address.city,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
            )
            Text(
                text = address.country,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()

}