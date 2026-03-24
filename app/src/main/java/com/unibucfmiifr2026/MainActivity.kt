package com.unibucfmiifr2026

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat.getString
import com.unibucfmiifr2026.ui.navigation.AuthNavigation
import com.unibucfmiifr2026.ui.screens.LoginScreen
import com.unibucfmiifr2026.ui.theme.UniBucFMIIFR2026Theme

class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			UniBucFMIIFR2026Theme {

				AuthNavigation()

			}
		}
		Log.e("TAG", "onCreate:")
	}

	override fun onStart() {
		super.onStart()

		Log.e("TAG", "onStart:")
	}

	override fun onResume() {
		super.onResume()

		Log.e("TAG", "onResume:")
	}

	override fun onPause() {
		super.onPause()

		Log.e("TAG", "onPause:")
	}

	override fun onStop() {
		super.onStop()

		Log.e("TAG", "onStop:")

		// stop background tasks
	}

	override fun onDestroy() {
		super.onDestroy()

		Log.e("TAG", "onDestroy:")
	}
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
//	Text(
//		text = "Hello $name!",
//		modifier = modifier
//	)
	val context = LocalContext.current

	Button(
		modifier = Modifier.fillMaxWidth(),
		onClick = {
			Log.e("TAG", "onClick:")

			val intent = Intent(context, MainViewsActivity::class.java)
			context.startActivity(intent)
			(context as? MainActivity)?.finish()
		}
	) {
		Text(
			text = stringResource(R.string.hello_world),
			modifier = modifier
		)
	}
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
	UniBucFMIIFR2026Theme {
		Greeting("Android")
	}
}
@Preview (showBackground = true)
@Composable
fun AuthNavigationPreview() {
	UniBucFMIIFR2026Theme {
		AuthNavigation()

	}
}