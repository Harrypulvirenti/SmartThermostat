import extensions.applyDefault

plugins {
    id(GradlePlugins.androidLibrary)
    id(GradlePlugins.kotlinAndroid)
    id(GradlePlugins.kotlinAndroidExtensions)
}

android {
    applyDefault()
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libraries.kotlinStdlib)

    //    Android
    implementation(Libraries.appCompat)
    implementation(Libraries.lifecycleViewModel)

    //    Coroutine
    implementation(Libraries.coroutinesCore)
    implementation(Libraries.coroutinesAndroid)
}
