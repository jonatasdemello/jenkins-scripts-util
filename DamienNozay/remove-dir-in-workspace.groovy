// Licensed under MIT
// author : Damien Nozay
// ---------------------------------------------------------
// This script cleans a subdir in all existing workspaces for a selected job.
// node -> check workspace (concurrent too) -> check subdir -> delete
// ---------------------------------------------------------

job = Jenkins.instance.getItemByFullName('SomeJobFolder/myJob')
subdir = 'dist'

println "Looking for job: " + job.fullName


import hudson.slaves.WorkspaceList
combinator = System.getProperty(WorkspaceList.class.getName(),"@");

for (node in Jenkins.instance.getNodes()) {
  println "Node: '" + node.getSelfLabel().toString() + "' (" + node.getAssignedLabels().join(",") + ")"
  workspacePathBase = node.getWorkspaceFor(job)
  
  // handle concurrent workspaces
  for (int i=1; ; i++) {
    // they are suffixed...
    workspacePath = i==1 ? workspacePathBase : workspacePathBase.withSuffix(combinator+i);
    // stop checking (unlikely to have higher suffix)
    if (!workspacePath.exists()) {
      break;
    } else {
      println "  * found workspace: " + workspacePath.getRemote()
      targetDir = workspacePath.child(subdir)
      if (targetDir.exists()) {
        println "  * found target directory"
        if (!job.isBuilding()) {
          println "  * removing directory (job is not building)"
          targetDir.deleteRecursive()
        } else {
          println "  * not removing directory (job is building)"
        }
      }
    }
  }
}
