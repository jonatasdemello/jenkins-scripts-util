/*** BEGIN META {
  "name" : "cleanupUnusedWorkspaceInSlaves",
  "comment" : "When you delete jobs in Jenkins, the corresponding workspaces in the build slaves won't be deleted automatically <br> This Jenkins script will go to each slave and check if the jobs are already deleted in Jenkins master and delete the workspace.",
  "parameters" : [],
  "core": "1.300",
  "authors" : [
	{ name : "Xello" }
  ]
} END META**/
import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*

// When you delete jobs in Jenkins, the corresponding workspaces in the build slaves won't be deleted automatically. 
// This Jenkins script will go to each slave and check if the jobs are already deleted in Jenkins master and delete the workspace. 

import com.cloudbees.hudson.plugins.folder.Folder
import hudson.FilePath
import jenkins.model.Jenkins

def boolean isFolder(String name) {
    def item = Jenkins.instance.getItemByFullName(name)
    return item instanceof Folder
}

def deleteUnusedWorkspace(FilePath root, String path) {
    root.list().each { child ->
        String fullName = path + child.name
        if (isFolder(fullName)) {
            deleteUnusedWorkspace(root.child(child.name), "$fullName/")
        } else {
            if (Jenkins.instance.getItemByFullName(fullName) == null) {
                println "Deleting: $fullName "
                child.deleteRecursive()
            }
        }
    }
}

for (node in Jenkins.instance.nodes) {
    println "Processing $node.displayName"
    def workspaceRoot = node.rootPath.child("workspace");
    deleteUnusedWorkspace(workspaceRoot, "")
}
