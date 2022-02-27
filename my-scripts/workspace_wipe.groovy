/*** BEGIN META {
  "name" : "Wipe Workspace",
  "comment" : "Wipe workspace for job name",
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

Jenkins.instance.getAllItems(AbstractProject.class)
.findAll {
  !it.disabled && it.name.contains("Web")
}
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

