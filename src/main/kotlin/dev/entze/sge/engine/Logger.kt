package dev.entze.sge.engine

import java.io.PrintStream

class Logger(private val logLevel: Int = 0,
             private val pre: String = "",
             private val post: String = "",
             private val tracePre: String = "Trace: ",
             val traceStream: PrintStream = System.out,
             private val tracePost: String = "",
             private val debugPre: String = "Debug: ",
             val debugStream: PrintStream = System.out,
             private val debugPost: String,
             private val infoPre: String = "Info: ",
             val infoStream: PrintStream = System.out,
             private val infoPost: String,
             private val warnPre: String = "Warn: ",
             val warnStream: PrintStream = System.err,
             private val warnPost: String = "",
             private val errorPre: String = "Error: ",
             val errorStream: PrintStream = System.err,
             private val errorPost: String = ""
) {

    private fun print(level: Int, stream: PrintStream, pre: String, preS: String, string: String, postS: String, post: String) {
        if (logLevel <= level) {
            stream.print("$pre$preS$string$postS$postS$post")
        }
    }

    fun tra_(string: String) {
        print(-2, traceStream, "", "", string, "", "")
    }

    fun tra(string: String) {
        print(-2, traceStream, pre, tracePre, string, tracePost, post)
    }

    fun trace(string: String) {
        tra("$string\n")
    }

    fun trace_(string: String) {
        tra_("$string\n")
    }

    fun deb_(string: String) {
        print(-1, debugStream, "", "", string, "", "")
    }

    fun deb(string: String) {
        print(-1, debugStream, pre, debugPre, string, debugPost, post)
    }

    fun debug(string: String) {
        deb("$string\n")
    }

    fun debug_(string: String) {
        deb_("$string\n")
    }

    fun inf_(string: String) {
        print(0, infoStream, "", "", string, "", "")
    }

    fun inf(string: String) {
        print(0, infoStream, pre, infoPre, string, infoPost, post)
    }

    fun info(string: String) {
        inf("$string\n")
    }

    fun info_(string: String) {
        inf_("$string\n");
    }

    fun war_(string: String) {
        print(1, warnStream, "", "", string, "", "")
    }

    fun war(string: String) {
        print(1, warnStream, pre, warnPre, string, warnPost, post)
    }

    fun warn(string: String) {
        war("$string\n")
    }

    fun warn_(string: String) {
        war_("$string\n");
    }

    fun err_(string: String) {
        print(2, errorStream, "", "", string, "", "")
    }

    fun err(string: String) {
        print(2, errorStream, pre, errorPre, string, errorPost, post)
    }

    fun error(string: String) {
        err("$string\n")
    }

    fun error_(string: String) {
        err_("$string\n");
    }


}