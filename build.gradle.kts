// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

buildscript {
    repositories {
        google()  // Google repository for Gradle plugins
        mavenCentral()  // Maven Central repository for dependencies
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")  // Ensure to use the latest version
        classpath("com.google.gms:google-services:4.3.10")  // Google Services plugin for OAuth and other integrations
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}

