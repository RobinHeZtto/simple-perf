package com.he.ztto.perf.gradle.transform

import com.android.build.api.transform.*

class PerfTransformInvocation(private val invocation: TransformInvocation)
    : TransformInvocation {

    override fun getContext(): Context = invocation.context

    override fun getInputs(): MutableCollection<TransformInput> = invocation.inputs

    override fun getReferencedInputs(): MutableCollection<TransformInput> = invocation.referencedInputs

    override fun getSecondaryInputs(): MutableCollection<SecondaryInput> = invocation.secondaryInputs

    override fun getOutputProvider(): TransformOutputProvider = invocation.outputProvider

    override fun isIncremental(): Boolean = invocation.isIncremental

    fun doFullTransform() {
        invocation.inputs.parallelStream().forEach { input ->
            input.directoryInputs.parallelStream().forEach {
                it.file
            }
            input.jarInputs.parallelStream().forEach {

            }
        }
    }
}