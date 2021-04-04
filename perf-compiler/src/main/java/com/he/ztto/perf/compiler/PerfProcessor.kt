package com.he.ztto.perf.compiler

import com.google.auto.service.AutoService
import com.he.ztto.perf.annotations.Monitor
import com.he.ztto.perf.compiler.utils.Logger
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
class PerfProcessor : AbstractProcessor(){

    override fun init(processingEnvironment: ProcessingEnvironment?) {
        super.init(processingEnvironment)
        Logger.init(processingEnv.messager)
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        val annotations = HashSet<String>()
        annotations.add(Monitor::class.java.canonicalName)
        return annotations
    }

    override fun process(annotations: MutableSet<out TypeElement>?,
                         roundEnvironment: RoundEnvironment?): Boolean {
        Logger.info("process\n")

        if (!annotations.isNullOrEmpty()) {
            val elements = roundEnvironment?.getElementsAnnotatedWith(Monitor::class.java)
            if (elements.isNullOrEmpty()) {
                return true
            }

            Logger.info("process ===> ${elements.size}")

        }


        return false
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latest()
    }

}