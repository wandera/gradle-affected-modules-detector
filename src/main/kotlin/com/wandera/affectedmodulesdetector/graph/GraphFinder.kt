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
     * Finds all nodes that are dependent on given nodes.
     *
     * @param nodes Nodes to find dependent nodes for.
     * @return Input nodes and nodes dependent on them.
     */
    fun findDependentNodes(nodes: Set<Node>): Set<Node> {
        val dependentNodes = mutableSetOf(*nodes.toTypedArray())
        findDependentNodesRecursive(nodes, dependentNodes)
        return dependentNodes
    }

    private fun findDependentNodesRecursive(
        nodes: Set<Node>,
        dependentNodes: MutableSet<Node>,
    ) {
        // Source nodes are used for optimization to  avoid searching same node too many times
        val sourceNodes = mutableSetOf<Node>()
        val oldDependentNodesSize = dependentNodes.size

        nodes.forEach { node ->
            graph.edges.forEach { edge ->
                if (edge.target == node) {
                    dependentNodes.add(edge.source)
                    sourceNodes.add(edge.source)
                }
            }
        }

        if (oldDependentNodesSize < dependentNodes.size) {
            findDependentNodesRecursive(sourceNodes, dependentNodes)
        }
    }
}
