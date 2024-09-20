package com.wandera.affectedmodulesdetector.graph

/**
 * Class represents graph edge with direction from source to target.
 *
 * @property source source node of the edge.
 * @property target target node of the edge.
 */
data class Edge(
    val source: Node,
    val target: Node,
)
