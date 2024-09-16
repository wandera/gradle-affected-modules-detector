package com.wandera.affectedmodulesdetector

import com.wandera.affectedmodulesdetector.extension.PluginExtension
import com.wandera.affectedmodulesdetector.graph.ModuleDependencyGraphFactory
import com.wandera.affectedmodulesdetector.task.FindAffectedModulesTask
import com.wandera.affectedmodulesdetector.task.toFindAffectedModulesAttributes
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Plugin providing tasks to detect code changes in gradle subprojects (modules).
 */
class AffectedModulesDetectorPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val configuration: PluginExtension =
            project.extensions.create(PLUGIN_EXTENSION_NAME, PluginExtension::class.java)

        project.tasks.register(
            FIND_AFFECTED_MODULES_TASK_NAME,
            FindAffectedModulesTask::class.java
        ) {
            it.attrs = configuration.toFindAffectedModulesAttributes()
            it.graph = ModuleDependencyGraphFactory(project).createGraph()
        }
    }

    companion object {

        /**
         * Name of the task that detects modules affected by code changes.
         */
        private const val FIND_AFFECTED_MODULES_TASK_NAME = "findAffectedModules"

        /**
         * Name of the plugin extension references by the user when setting up the plugin.
         */
        private const val PLUGIN_EXTENSION_NAME = "affectedModulesDetector"
    }
}
