package com.he.ztto.perf.gradle

import com.android.build.gradle.AppExtension
import com.he.ztto.perf.gradle.transform.PerfTransform
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.LogLevel

class PerfPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.logger.log(LogLevel.ERROR, "==========>")

        target.getAndroid<AppExtension>().apply {
            registerTransform(PerfTransform())
        }
    }
}