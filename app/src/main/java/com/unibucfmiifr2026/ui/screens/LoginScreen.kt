package com.unibucfmiifr2026.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.unibucfmiifr2026.BuildConfig
import com.unibucfmiifr2026.R
import com.unibucfmiifr2026.utils.isValidEmail
import com.unibucfmiifr2026.utils.isValidPassword


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onLoginClick: (email: String, password: String) -> Unit = {_,_->},
    onRegisterClick: () -> Unit = {},
    isLoading: Boolean = false,
    authError: String? = null
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    val invalidEmailError = stringResource(R.string.email_error)
    val invalidPasswordError = stringResource(R.string.password_error)

    if(BuildConfig.DEBUG){
        email="eve.holt@reqres.in"
        password="cityslicka"
    }

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
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChange = { newValue ->
                email = newValue
                emailError = null
            },
            label = {
                Text(
                    stringResource(R.string.email)
                )
            },
            leadingIcon = {
                Icon(Icons.Default.Email, "email")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            isError = emailError != null,
            supportingText = emailError?.let {
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
            value = password,
            onValueChange = { newValue ->
                password = newValue
                passwordError = null
            },
            label = {
                Text(
                    stringResource(R.string.password)
                )
            },
            leadingIcon = {
                Icon(Icons.Default.Password, "password")
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"

                    )
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            isError = passwordError != null,
            supportingText = passwordError?.let {
                {
                    Text(
                        text = it
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                var valid = true
                if (!email.isValidEmail()) {
                    emailError = invalidEmailError
                    valid = false
                }
                if (!password.isValidPassword()) {
                    passwordError = invalidPasswordError
                    valid = false
                }
                if (valid) {
                    onLoginClick(email, password)
                }
            },
            enabled = !isLoading

        ) {
            when (isLoading) {
                false -> Text(stringResource(R.string.login))
                true -> CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 2.dp
                )
            }
        }
        authError?.let { error ->
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(
            onClick = onRegisterClick
        ) {
            Text(
                stringResource(R.string.go_to_register)

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}