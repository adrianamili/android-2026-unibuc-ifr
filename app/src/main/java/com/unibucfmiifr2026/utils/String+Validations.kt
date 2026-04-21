package com.unibucfmiifr2026.utils

import android.util.Patterns
import org.intellij.lang.annotations.Pattern

fun String.isValidEmail() =
     isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPassword() = length >= 6

fun String.isValidName() = length >= 4

fun String.isValidAddress() = length >= 4

fun String.isMatchingPassword(password: String) = this.isNotBlank() && this == password
