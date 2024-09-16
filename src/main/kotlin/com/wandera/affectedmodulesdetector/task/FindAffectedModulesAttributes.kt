package com.wandera.affectedmodulesdetector.task

import com.wandera.affectedmodulesdetector.extension.PluginExtension

/**
 * Attributes for [FindAffectedModulesTask].
 *
 * @property baseBranch Git branch name used for detecting changed modules.
 */
data class FindAffectedModulesAttributes(
    val baseBranch: String,
    val outputFileType: String,
)

/**
 * Retrieves relevant attributes from [PluginExtension]
 * and saves them to [FindAffectedModulesAttributes].
 *
 * @return [FindAffectedModulesAttributes]
 */
fun PluginExtension.toFindAffectedModulesAttributes(): FindAffectedModulesAttributes =
    FindAffectedModulesAttributes(baseBranch)
