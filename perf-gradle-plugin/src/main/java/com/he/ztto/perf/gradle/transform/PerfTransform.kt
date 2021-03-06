package com.he.ztto.perf.gradle.transform

import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import com.he.ztto.perf.gradle.Scanner
import com.he.ztto.perf.gradle.logger
import org.gradle.api.logging.LogLevel

class PerfTransform : Transform() {


    override fun getName(): String {
        return "PerfTransform"
    }

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> {
        return TransformManager.CONTENT_CLASS
    }

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    override fun isIncremental(): Boolean {
        return false
    }


    override fun transform(transformInvocation: TransformInvocation) {
        val outputProvider = transformInvocation.outputProvider

        transformInvocation.inputs.forEach {
            it.jarInputs.forEach { jar ->
                val dest = outputProvider.getContentLocation(
                        jar.name,
                        jar.contentTypes,
                        jar.scopes,
                        Format.JAR
                )


                logger.log(LogLevel.ERROR, "jar >>>  ${jar.name}")


                Scanner.scanFile(jar.file)


                FileUtils.copyFile(jar.file, dest)
            }

            it.directoryInputs.forEach { dir ->
                val dest = outputProvider.getContentLocation(
                        dir.name,
                        dir.contentTypes,
                        dir.scopes,
                        Format.DIRECTORY
                )

                logger.log(LogLevel.ERROR, "dir >>>  ${dir.name}")

                val fileTree = dir.file.walk()
                fileTree.filter { it ->
                    it.isFile
                }.forEach { it ->
                    Scanner.scanClass(it)
                }

                FileUtils.copyDirectory(dir.file, dest)
            }
        }
    }
}