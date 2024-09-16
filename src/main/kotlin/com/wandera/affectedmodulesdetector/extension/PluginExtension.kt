package com.wandera.affectedmodulesdetector.extension

/**
 * Extension contains attributes provided by the plugin user
 * to adapt the plugin behavior for their usage.
 *
 * @property baseBranch Git branch name to compare the changes with.
 */
open class PluginExtension(
    var baseBranch: String = "master",
    var outputFileType: String = "csv",
)
