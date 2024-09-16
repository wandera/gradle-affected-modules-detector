package com.wandera.affectedmodulesdetector.output

import com.wandera.affectedmodulesdetector.graph.Node
import java.io.File

/**
 * Writes output into a file.
 */
object FileOutputWriter {

    private const val AFFECTED_MODULES_FILE_NAME = "affected-modules.txt"
    private const val PLUGIN_DIRECTORY_NAME = "affected-modules-detector"

    /**
     * Writes all affected modules names into a .txt file. Every module name is on a separate line.
     *
     * @param directoryPath path to the root directory where the file should be saved
     * @param affectedModules set of affected modules
     */
    fun writeAffectedModulesIntoFile(directoryPath: String, affectedModules: Set<Node>) {
        val directory = createDirectory(directoryPath)
        writeToTxt(directory, affectedModules)
    }

    private fun createDirectory(directoryPath: String,): File {
        val directory = File(directoryPath, PLUGIN_DIRECTORY_NAME)

        if (!directory.exists()) {
            directory.mkdirs()
        }

        return directory
    }

    private fun writeToTxt(directory: File, affectedModules: Set<Node>) {
        File(directory.path, AFFECTED_MODULES_FILE_NAME)
            .outputStream()
            .bufferedWriter()
            .use { writer ->
                affectedModules.forEach {
                    writer.appendLine(it.name)
                }
            }
    }
}
