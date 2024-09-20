package com.wandera.affectedmodulesdetector.graph

/**
 * Class searching through graph.
 *
 * @property graph Graph to search through.
 */
class GraphFinder(
    private val graph: Graph,
) {

    /**
     * Finds all nodes that are dependent on given nodes. It doesn't support graph with cycles.
     *
     * @param nodes Nodes to find dependent nodes for.
     * @return Input nodes and nodes dependent on them.
     */
    fun findDependentNodes(nodes: Set<Node>): Set<Node> = if (nodes.isEmpty()) {
        emptySet()
    } else {
        val sourceNodes = nodes.flatMap { node: Node-> getSourceNodes(node) }.toSet()
        nodes + findDependentNodes(sourceNodes)
    }

    private fun getSourceNodes(node: Node): Set<Node> {
        return graph.edges.filter { it.target == node }.map { it.source }.toSet()
    }
}
