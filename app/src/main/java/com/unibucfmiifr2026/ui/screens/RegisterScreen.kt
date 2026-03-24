package com.unibucfmiifr2026.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.unibucfmiifr2026.R
import com.unibucfmiifr2026.utils.isMatchingPassword
import com.unibucfmiifr2026.utils.isValidEmail
import com.unibucfmiifr2026.utils.isValidName
import com.unibucfmiifr2026.utils.isValidPassword


@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    onRegisterClick: () -> Unit = {},
    onLoginClick: () -> Unit = {}
) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPassword by remember { mutableStateOf("") }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var fullName by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var nameError by remember { mutableStateOf<String?>(null) }
    var matchingPasswordError by remember { mutableStateOf<String?>(null) }


    val invalidEmailError = stringResource(R.string.email_error)
    val invalidPasswordError = stringResource(R.string.password_error)
    val invalidNameError = stringResource(R.string.name_error)
    val invalidMatchingPasswordError = stringResource(R.string.matching_password_error)



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.create_account),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(32.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = fullName,
            onValueChange = { newValue ->
                fullName = newValue
                nameError = null
            },
            label = {
                Text(
                    stringResource(R.string.full_name)
                )
            },
            leadingIcon = {
                Icon(Icons.Default.Person, "name")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next

            ),
            isError = nameError != null,
            supportingText = nameError?.let {
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
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = confirmPassword,
            onValueChange = { newValue ->
                confirmPassword = newValue
                matchingPasswordError = null
            },
            label = {
                Text(
                    stringResource(R.string.confirm_password)
                )
            },
            leadingIcon = {
                Icon(Icons.Default.Password, "confirm password")
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        confirmPasswordVisible = !confirmPasswordVisible
                    }
                ) {
                    Icon(
                        imageVector = if (confirmPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (confirmPasswordVisible) "Hide  confirmed password" else "Show confirmed password"

                    )
                }
            },
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
                    isError = matchingPasswordError != null,
            supportingText = matchingPasswordError?.let {
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
                if (!confirmPassword.isMatchingPassword(password)) {
                    matchingPasswordError = invalidMatchingPasswordError
                    valid = false
                }
                if (!fullName.isValidName()) {
                    nameError = invalidNameError
                    valid = false
                }
                if (valid) {
                    onRegisterClick()
                }
            }

        ) {
            Text(
                stringResource(R.string.register)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(
            onClick = onLoginClick
        ) {
            Text(
                stringResource(R.string.go_to_login)

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}