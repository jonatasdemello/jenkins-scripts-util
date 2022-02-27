/*** BEGIN META {
  "name" : "wipe workspace disabled job",
  "comment" : "Deletes the workspace for all disabled jobs to save space",
  "parameters" : [],
  "core": "1.300",
  "authors" : [
	{ name : "Xello" }
  ]
} END META**/
import jenkins.*
import jenkins.model.*

Jenkins.instance.getAllItems(AbstractProject.class)
.findAll { it.disabled } // only disabled jobs
.each {
  println("Wiping workspace for " + it.fullName)
  it.doDoWipeOutWorkspace()
}

// Or, only disabled jobs

def hi = hudson.model.Hudson.instance
hi.getItems(hudson.model.Job)
.each { job -> if(job.isDisabled()) {
   println("Wiping workspace for " + job.displayName)
   job.doDoWipeOutWorkspace()
 }
}
