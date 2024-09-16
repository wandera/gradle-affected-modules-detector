package com.wandera.affectedmodulesdetector.output

import com.wandera.affectedmodulesdetector.graph.Node
import com.wandera.affectedmodulesdetector.task.FindAffectedModulesAttributes

/**
 * Object used for printing logs into console.
 */
object LogPrintOutput {

    fun printTaskInput(attrs: FindAffectedModulesAttributes) {
        println(
        """
            
        ------------------ INPUT ------------------
        Base branch: ${attrs.baseBranch}
        Output file type: ${attrs.outputFileType}
        ------------------ INPUT ------------------
        """.trimIndent()
        )
    }

    fun printTaskOutput(changedModules: Set<Node>, affectedModules: Set<Node>) {
        println(
        """
        
        ------------------ OUTPUT ------------------
        Changed modules: ${changedModules.toPrintableText()}
        
        Affected modules: ${affectedModules.toPrintableText()}
        ------------------ OUTPUT ------------------
        """.trimIndent()
        )
    }

    fun Set<Node>.toPrintableText(): String = map { it.name }.toString().let {
        it.substring(1, it.length - 1)
    }
}
