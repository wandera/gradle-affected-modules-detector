package com.wandera.affectedmodulesdetector.output

import com.wandera.affectedmodulesdetector.graph.Node
import com.wandera.affectedmodulesdetector.output.LogPrint.toPrintableText
import java.io.File

object FileOutput {

    fun writeToCsv(directoryPath: String, affectedModules: Set<Node>) {
        val directoryName = "affected-modules-detector"
        val directory = createDirectory(directoryPath, directoryName)

        val fileName = "affected-modules.csv"
        val file = File(directory.path, fileName)
        file.outputStream()
            .bufferedWriter()
            .use { writer ->
                writer.write("${affectedModules.toPrintableText()} \n")
            }
    }

    private fun createDirectory(directoryPath: String, directoryName: String): File {
        val directory = File(directoryPath, directoryName)

        if (!directory.exists()) {
            directory.mkdirs()
        }

        return directory
    }
}
