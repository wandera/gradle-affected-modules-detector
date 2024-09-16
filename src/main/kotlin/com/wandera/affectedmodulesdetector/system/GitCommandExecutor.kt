package com.wandera.affectedmodulesdetector.system

/**
 * Object contains methods to execute git commands.
 */
object GitCommandExecutor {

    /**
     * Retrieves the list of files changed between the current branch and the base branch.
     *
     * @param baseBranch Git branch name to compare the changes with.
     * @return list of file paths changed between the current branch and the base branch.
     */
    fun retrieveGitDiff(baseBranch: String): List<String> =
        Runtime.getRuntime()
            .exec("git diff --name-only $baseBranch")
            .inputStream
            .bufferedReader()
            .lines()
            .toList()
}
