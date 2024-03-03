plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    //Necesario para implementar Navigation
    id("androidx.navigation.safeargs.kotlin")
    id ("com.google.gms.google-services")
}

android {
    namespace = "com.utad.ud5_pmdm_sergio_alvarez_villaverde"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.utad.ud5_pmdm_sergio_alvarez_villaverde"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    viewBinding{
        enable = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Sirven para el Navigation. Es necesario implementar el plugin de SaveArgs.
    val navVersion = "2.6.0"
    implementation ("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation ("androidx.navigation:navigation-ui-ktx:$navVersion")

    //Firebase dependencias básicas
    val firebase_version = "32.2.3"
    implementation(platform("com.google.firebase:firebase-bom:$firebase_version"))

    //Firebase - RealTime Database
    implementation("com.google.firebase:firebase-database-ktx")

    //PaperDB
    implementation ("io.github.pilgr:paperdb:2.7.2")

    //Implementacion de Corrutinas.
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    //Implementacion de Retrofit
    val retrofit = "2.9.0"
    val interceptor = "4.11.0"

    implementation ("com.squareup.retrofit2:retrofit:$retrofit")
    //Retrofit: Transforma los JSONS que recibimos a DataClass
    implementation ("com.squareup.retrofit2:converter-gson:$retrofit")
    //Retrofit: Muestra por consola lso datos de las peticiones que realizamos y las respuestas que recibimos
    implementation ("com.squareup.okhttp3:logging-interceptor:$interceptor")

    //MVVM
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.activity:activity-ktx:1.3.1")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
}