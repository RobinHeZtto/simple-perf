package com.he.ztto.perf.gradle

import jdk.internal.org.objectweb.asm.*
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter
import org.gradle.api.logging.LogLevel
import java.io.File
import java.util.jar.JarFile

object Scanner {
    private val excludeJar = setOf<String>("org.jetbrains", "com.google", "androidx")

    fun shouldScan(jar: String): Boolean {
        return !excludeJar.contains(jar)
    }

    fun scanFile(file: File) {
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
            logger.log(LogLevel.ERROR, "scanClass >>> ${file.name}")
            val optFile = File(file.parent, file.name + ".opt")
            optFile.outputStream().use { outputStream ->
                file.inputStream().use {
                    val reader = ClassReader(it)
                    val writer = ClassWriter(reader, ClassWriter.COMPUTE_MAXS)
                    reader.accept(MyVisitor(writer), ClassReader.EXPAND_FRAMES)
                    outputStream.write(writer.toByteArray())
                }
            }
            file.delete()
            optFile.renameTo(file)
        }
    }


    class MyVisitor(cw: ClassWriter) : ClassVisitor(Opcodes.ASM5, cw) {
        override fun visitMethod(
            access: Int, name: String?, desc: String?, signature: String?,
            exceptions: Array<out String>?
        ): MethodVisitor {
            logger.log(LogLevel.ERROR, "MyVisitor1 >>> ${name}")
            val methodVisitor = cv.visitMethod(access, name, desc, signature, exceptions)
            return TraceMethodAdapter(api, methodVisitor, access, name!!, desc!!)
        }
    }

    private class TraceMethodAdapter constructor(
        api: Int,
        mv: MethodVisitor,
        access: Int,
        name: String,
        desc: String,
    ) : AdviceAdapter(api, mv, access, name, desc) {

        override fun onMethodEnter() {
            logger.log(LogLevel.ERROR, "MyVisitor1 >>> onMethodEnter")

            mv.visitMethodInsn(
                INVOKESTATIC,
                "com/he/ztto/perf/Method",
                "i",
                "()V",
                false
            )
        }

        override fun onMethodExit(opcode: Int) {

            logger.log(LogLevel.ERROR, "MyVisitor1 >>> onMethodExit")

            mv.visitMethodInsn(
                INVOKESTATIC,
                "com/he/ztto/perf/Method",
                "i",
                "()V",
                false
            )
        }
    }
}