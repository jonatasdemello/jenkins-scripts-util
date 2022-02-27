/*** BEGIN META {
  "name" : "Wipeout Workspace Job",
  "comment" : "This script will go through a workspaces for one jobs and wipe them.",
  "parameters" : [ 'dryRun', 'jobName' ],
  "core": "1.499",
  "authors" : [
    { name : "jonatas" }
  ]
} END META**/
// For each project
jenkins.model.Jenkins.instance.getAllItems(hudson.model.AbstractProject).each { job ->
if ($job.name == jobName) {
   
    if (job.building) {
      println "Skipping job $job.name, currently building"
    } else {
      println "Wiping out workspace of $job.name"
      if (dryRun != 'true') {
        job.doDoWipeOutWorkspace()
      }
    }
  }
}
