package com.wandera.affectedmodulesdetector.task

import com.wandera.affectedmodulesdetector.graph.Graph
import com.wandera.affectedmodulesdetector.graph.Node
import com.wandera.affectedmodulesdetector.output.FileOutputWriter
import com.wandera.affectedmodulesdetector.output.LogPrintOutput
import org.gradle.api.DefaultTask
import org.gradle.api.file.ProjectLayout
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

/**
 * Task for finding affected modules.
 */
abstract class FindAffectedModulesTask : DefaultTask() {

    @Input
    lateinit var attrs: FindAffectedModulesAttributes

    @Input
    lateinit var graph: Graph

    @get:Inject
    abstract val layout: ProjectLayout

    @TaskAction
    fun findAffectedModules() {
        LogPrintOutput.printTaskInput("Base branch: ${attrs.baseBranch}")

        val affectedModules: Set<Node> =
            FindAffectedModulesTaskHelper(graph).getAffectedModules(attrs.baseBranch)
        FileOutputWriter.writeAffectedModulesIntoFile(buildPath(), affectedModules)
    }

    private fun buildPath() = layout.buildDirectory.asFile.get().path

}
