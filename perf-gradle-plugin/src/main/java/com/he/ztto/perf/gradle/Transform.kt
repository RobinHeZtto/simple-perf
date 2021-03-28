package com.he.ztto.perf.gradle

import org.gradle.api.logging.Logging
import java.io.File
import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream

private val logger = Logging.getLogger("transformStreamToArray")


fun File.transformFileToByArray(output: File, transformer: (ByteArray) -> ByteArray = { it -> it }) {
    when {
        isDirectory -> {
            val base = this.toURI()
            this.search().forEach {
                it.transformFileToByArray(File(output, base.relativize(it.toURI()).path), transformer)
            }
        }
        isFile -> {
            when (output.extension.toLowerCase()) {
                "jar" -> {
                    JarOutputStream(output.touch().outputStream()).use { dest ->
                        JarFile(this).use { jar ->
                            jar.entries().asSequence().forEach { entry ->
                                dest.putNextEntry(JarEntry(entry.name))
                                if (!entry.isDirectory) {
                                    when (entry.name.substringAfterLast('.', "")) {
                                        "class" -> jar.getInputStream(entry).use { src ->
                                            logger.info("Transforming ${this.absolutePath}!/${entry.name}")
                                            src.transformStreamToArray(transformer).redirect(dest)
                                        }
                                        else -> jar.getInputStream(entry).use { src ->
                                            src.copyTo(dest)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                "class" -> inputStream().use {
                    logger.info("Transforming ${this.absolutePath}")
                    it.transformStreamToArray(transformer).redirect(output)
                }
                else -> this.copyTo(output, true)
            }
        }
        else -> TODO("Unexpected file: ${this.absolutePath}")
    }
}