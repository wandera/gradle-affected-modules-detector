package com.wandera.affectedmodulesdetector.task

import com.wandera.affectedmodulesdetector.system.GitCommandExecutor
import com.wandera.affectedmodulesdetector.graph.Graph
import com.wandera.affectedmodulesdetector.graph.GraphFinder
import com.wandera.affectedmodulesdetector.graph.Node
import com.wandera.affectedmodulesdetector.output.LogPrintOutput

/**
 * Helper class for [FindAffectedModulesTask] using only internal API.
 */
class FindAffectedModulesTaskHelper(
    private val graph: Graph
) {

    private var graphFinder: GraphFinder = GraphFinder(graph)

    /**
     * Finds affected modules, those are modules that contain code changes and modules
     * that depend on them.
     *
     * @param baseBranch Git branch name used for detecting changed modules.
     * @return Set of affected modules.
     */
    fun getAffectedModules(baseBranch: String): Set<Node> {
        // Modules that contain code changes
        val changedModules = getChangedModules(baseBranch)
        // Modules that contain code changes and modules that depend on them
        val affectedModules = graphFinder.findDependentNodes(changedModules)

        LogPrintOutput.printTaskOutput(changedModules, affectedModules)

        return affectedModules
    }

    private fun getChangedModules(comparedBranch: String): Set<Node> =
        GitCommandExecutor.retrieveGitDiff(comparedBranch)
            .map {
                // Extract first part of the path that is usually the module name
                it.split("/")[0].trim()
            }
            .mapNotNull {
                // Filter all changed files outside of modules
                graph.nodes.find { node -> node.name == it }
            }
            .toSet()
}
