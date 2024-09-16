package com.wandera.affectedmodulesdetector.graph

import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency

/**
 * Factory for creating graph of dependencies among gradle subprojects (modules).
 *
 * @property rootProject gradle root project.
 */
class ModuleDependencyGraphFactory(
    private val rootProject: Project,
) {

    /**
     * Creates graph of dependencies among gradle subprojects (modules).
     *
     * @return graph of module dependencies.
     */
    fun createGraph(): Graph {
        val nodes = rootProject.subprojects.map { Node(it.name) }.toSet()
        val edges = rootProject.subprojects.flatMap { createNodeEdges(it) }.toSet()
        return Graph(nodes, edges)
    }

    private fun createNodeEdges(module: Project): Set<Edge> {
        val sourceNode = Node(module.name)
        return getModuleDependencies(module).map { Edge(sourceNode, Node(it.name)) }.toSet()
    }

    private fun getModuleDependencies(module: Project): Set<Project> {
        // Check all modules except the current one
        val searchedModules = rootProject.subprojects - module

        return module.configurations.flatMap { configuration ->
            configuration.dependencies
                .mapNotNull { dependency: Dependency ->
                    // Check if dependency is a module
                    searchedModules.find { it.name == dependency.name }
                }
        }.toSet()
    }
}
