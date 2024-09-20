package com.wandera.affectedmodulesdetector.output

import com.wandera.affectedmodulesdetector.graph.Node

/**
 * Convert set to text printable into console.
 */
fun Set<Node>.toPrintableText(): String = map { it.name }.toString().let {
    it.substring(1, it.length - 1)
}
