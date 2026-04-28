plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.compose)
	id("com.google.gms.google-services")
	alias(libs.plugins.ksp)
}

android {
	namespace = "com.unibucfmiifr2026"
	compileSdk {
		version = release(36) {
			minorApiLevel = 1
		}
	}

	defaultConfig {
		applicationId = "com.unibucfmiifr2026"
		minSdk = 28
		targetSdk = 36
		versionCode = 1
		versionName = "1.0.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}
	buildFeatures {
		compose = true
		buildConfig = true
	}

	flavorDimensions += "environment"
	productFlavors {
		create("dev") {
			dimension = "environment"
			applicationIdSuffix = ".dev"
			versionCode = 100
			versionName = "1.0.0-dev"
			//resValue("string", "app_name", "DEV DSDM")
		}
		create("qa") {
			dimension = "environment"
			applicationIdSuffix = ".qa"
			versionCode = 212
			versionName = "1.0.6-qa"
			//resValue("string", "app_name", "QA DSDM")
		}
		create("prod") {
			dimension = "environment"
		}
	}
}

dependencies {
	implementation(libs.androidx.room.runtime)
	ksp(libs.androidx.room.compiler)
	implementation(libs.androidx.room.ktx)
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.compose.ui)
	implementation(libs.androidx.compose.ui.graphics)
	implementation(libs.androidx.compose.ui.tooling.preview)
	implementation(libs.androidx.compose.material3)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.androidx.activity)
	implementation(libs.androidx.constraintlayout)
	implementation(libs.recyclerview.animators)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.compose.ui.test.junit4)
	debugImplementation(libs.androidx.compose.ui.tooling)
	debugImplementation(libs.androidx.compose.ui.test.manifest)
	implementation(libs.androidx.compose.material.icons.extended)
	implementation(platform(libs.firebase.bom))
	implementation(libs.firebase.analytics)
	implementation(libs.firebase.auth)
	implementation(libs.retrofit)
	implementation(libs.retrofit.converter.gson)
	implementation(libs.okhttp.logging.interceptor)
	implementation(libs.kotlinx.coroutines.android)
	implementation(libs.coil.compose)
}