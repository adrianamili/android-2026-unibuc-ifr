package com.unibucfmiifr2026.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun UsersScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
    }

}

@Composable
@Preview(showBackground = true)
fun UsersScreenPreview() {
UsersScreen()
}