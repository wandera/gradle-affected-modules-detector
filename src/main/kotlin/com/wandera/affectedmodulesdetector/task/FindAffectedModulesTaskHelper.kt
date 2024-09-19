package com.wandera.affectedmodulesdetector.task

import com.wandera.affectedmodulesdetector.system.GitCommandExecutor
import com.wandera.affectedmodulesdetector.graph.Graph
import com.wandera.affectedmodulesdetector.graph.GraphFinder
import com.wandera.affectedmodulesdetector.graph.Node
import com.wandera.affectedmodulesdetector.output.LogPrintOutput
import com.wandera.affectedmodulesdetector.output.toPrintableText

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
        // Modules that contain code changes or all modules
        val baseAffectedModules = getBaseAffectedModules(baseBranch)
        // Modules that contain code changes and modules that depend on them
        val affectedModules = graphFinder.findDependentNodes(baseAffectedModules)

        LogPrintOutput.printTaskOutput(createOutputText(baseAffectedModules, affectedModules))

        return affectedModules
    }

    private fun getBaseAffectedModules(comparedBranch: String): Set<Node> {
        // Map directories to modules, if directoryName is not a module name,
        // it is converted to null
        val parsedModules = getChangedDirectoryNames(comparedBranch)
            .map { graph.nodes.find { node -> node.name == it } }

        return if (parsedModules.contains(null)) {
            // If there are changed directories that are not module names,
            // all modules are considered affected
            graph.nodes
        } else {
            parsedModules.filterNotNull().toSet()
        }
    }

    private fun getChangedDirectoryNames(comparedBranch: String): List<String> {
        val changeFilePaths = GitCommandExecutor.retrieveGitDiff(comparedBranch)
        // Extract first part of the path that is usually the directory name
        return changeFilePaths.map { it.split("/")[0].trim() }
    }

    private fun createOutputText(changedModules: Set<Node>, affectedNModules: Set<Node>): String =
        """
        Changed modules: ${changedModules.toPrintableText()}
        
        Affected modules: ${affectedNModules.toPrintableText()}
        """
}
