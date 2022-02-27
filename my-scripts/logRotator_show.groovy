// Find projects that habe logRotator enabled
import hudson.model.*

println "\n\n-------- Jobs with LogRotator --------"  

def jobs = Hudson.instance.items

jobs.findAll { it.logRotator && !it.disabled }
.each {
   println it.name;
   if (it.logRotator.daysToKeep >= 0){
      println "\t daysToKeep: ${it.logRotator.daysToKeep} day(s)"
   }
   if (it.logRotator.numToKeep >= 0){
      println "\t numToKeep: ${it.logRotator.numToKeep} build(s)"
   }
   if (it.logRotator.artifactDaysToKeep >= 0){
      println "\t artifactDaysToKeep: ${it.logRotator.artifactDaysToKeep} day(s)"
   }
   if (it.logRotator.artifactNumToKeep >= 0){
      println "\t artifactNumToKeep: ${it.logRotator.artifactNumToKeep} build(s)"
   }
}

println "\n\n-------- Jobs without LogRotator --------"
jobs.findAll { !it.logRotator && !it.disabled }
.each {
  println it.name;
}



import jenkins.model.Jenkins
import hudson.model.Job
import jenkins.model.BuildDiscarderProperty
import hudson.tasks.LogRotator

Jenkins.instance
.allItems(Job)
.each { job ->
    if (job.isBuildable() && job.supportsLogRotator() && job.getProperty(BuildDiscarderProperty) == null) {
        println "Processing \"${job.fullDisplayName}\""
        if (!"true".equals(dryRun)) {
            // adding a property implicitly saves so no explicit one
            job.addProperty(new BuildDiscarderProperty(new LogRotator ( daysToKeep, numToKeep, artifactDaysToKeep, artifactNumToKeep)))
            println "${job.displayName} is updated"
        }
    }
}
return;




import hudson.model.*

Hudson.instance.items
.findAll{ !it.logRotator && !it.disabled }
.each { job ->
	job.logRotator = new hudson.tasks.LogRotator ( 30, 20) 
	// days to keep, num to keep, artifact days to keep, num to keep
	// println "$it.name" edited and changed to statement below
	println "$job.name"
}


import jenkins.model.*
Jenkins.instance.getAllItems(AbstractProject.class)
.findAll { !it.logRotator }
    .each { 
      //it.logRotator.perform(it)
      it.logRotator = new hudson.tasks.LogRotator (daysToKeepBuild, numToKeepBuild, artifactDaysToKeep, artifactNumToKeep);
      println it.name;
    }