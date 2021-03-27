package com.he.ztto.perf.gradle

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project


inline fun <reified T : BaseExtension> Project.getAndroid() = extensions.getByName("android") as T