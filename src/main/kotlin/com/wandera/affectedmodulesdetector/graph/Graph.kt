package com.wandera.affectedmodulesdetector.graph

/**
 * Graph containing nodes connected by edges.
 */
data class Graph(
    val nodes: Set<Node>,
    val edges: Set<Edge>,
)
