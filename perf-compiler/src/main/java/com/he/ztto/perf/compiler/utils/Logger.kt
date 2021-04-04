package com.he.ztto.perf.compiler.utils

import javax.annotation.processing.Messager
import javax.tools.Diagnostic

object Logger {

    private lateinit var messager : Messager

    fun init(msg: Messager) {
        this.messager = msg
    }

    fun info(msg : CharSequence) {
        if (msg.isEmpty()) {
            return
        }
        messager.printMessage(Diagnostic.Kind.NOTE, PREFIX_OF_LOGGER + msg + "\n")
    }

    fun warn(msg : CharSequence) {
        if (msg.isEmpty()) {
            return
        }
        messager.printMessage(Diagnostic.Kind.WARNING, PREFIX_OF_LOGGER + msg)
    }


    fun error(msg : CharSequence) {
        if (msg.isEmpty()) {
            return
        }
        messager.printMessage(Diagnostic.Kind.ERROR, PREFIX_OF_LOGGER + msg)
    }
}