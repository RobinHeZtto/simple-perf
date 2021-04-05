package com.he.ztto.perf.gradle

import jdk.internal.org.objectweb.asm.ClassReader
import jdk.internal.org.objectweb.asm.ClassWriter
import jdk.internal.org.objectweb.asm.MethodVisitor
import jdk.internal.org.objectweb.asm.Opcodes
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode
import org.gradle.api.logging.LogLevel
import java.io.File
import java.io.FileInputStream
import java.util.jar.JarFile
import jdk.internal.org.objectweb.asm.ClassVisitor as ClassVisitor

object Scanner {
    private val excludeJar = setOf<String>("org.jetbrains", "com.google", "androidx")

    fun shouldScan(jar : String) : Boolean{
        return !excludeJar.contains(jar)
    }

    fun scanFile(file : File) {
        if (file.exists()) {
           val jar = JarFile(file)
           val entries = jar.entries()

           while (entries.hasMoreElements()) {
               val jarEntry = entries.nextElement()
               logger.log(LogLevel.ERROR, "scan >>> ${jarEntry.name}")
           }
       }
    }

    fun scanClass(file: File) {
        logger.log(LogLevel.ERROR, "scanClass >>> ${file.name}")

        if (file.name.equals("App.class")) {
            modifyClass(file.inputStream())
        }
    }

    private fun modifyClass(inputStream: FileInputStream) {
        inputStream.use {
            val reader = ClassReader(it)
            val writer = ClassWriter(reader, 0)
            reader.accept(MyVisitor(writer), 0)
            writer.toByteArray()
        }
    }

    class MyVisitor(private val cw : ClassWriter) : ClassVisitor(Opcodes.ASM5, cw) {

        override fun visitMethod(access: Int, name: String?, desc: String?, signature: String?,
                                 exceptions: Array<out String>?): MethodVisitor {
            val mv =  super.visitMethod(access, name, desc, signature, exceptions)
            logger.log(LogLevel.ERROR, "MyVisitor >>> ${name}")

            if (name.equals("<clinit>")) {

            }

            return mv
        }


    }
}