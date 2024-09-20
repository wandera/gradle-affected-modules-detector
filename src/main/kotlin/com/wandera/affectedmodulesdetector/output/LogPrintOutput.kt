package com.wandera.affectedmodulesdetector.output

import com.wandera.affectedmodulesdetector.graph.Node
import com.wandera.affectedmodulesdetector.task.FindAffectedModulesAttributes

/**
 * Object used for printing logs into console.
 */
object LogPrintOutput {

    /**
     * Prints input of the task into console.
     *
     * @param inputText Input text.
     */
    fun printTaskInput(inputText: String) {
        printSection("INPUT", inputText)
    }

    /**
     * Prints output of the task into console.
     *
     * @param outputText Output text.
     */
    fun printTaskOutput(outputText: String) {
        printSection("OUTPUT", outputText)
    }

    private fun printSection(title: String, content: String) {
        println(
        """
        
        ------------------ $title ------------------
        $content
        ------------------ $title ------------------
        """.trimIndent()
        )
    }
}
