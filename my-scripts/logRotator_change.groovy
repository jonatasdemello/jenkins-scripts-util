// NOTES:
// dryRun: true, to only list the jobs which would be changed
// daysToKeep:  If not -1, history is only kept up to this day.
// numToKeep:   If not -1, only this number of build logs are kept.
// artifactDaysToKeep: If not -1 nor null, artifacts are only kept up to this day.
// artifactNumToKeep:  If not -1 nor null, only this number of builds have their artifacts kept.

import hudson.model.*
import jenkins.model.Jenkins

def dryRun = false

def daysToKeepBuild = -1 // Days to keep builds
def numToKeepBuild = 20  // Max # of builds to keep
def artifactDaysToKeep = -1 // Days to keep artifacts
def artifactNumToKeep = 20  // Max # of builds to keep with artifacts

def jobs = Hudson.instance.items

jobs.findAll{ !it.logRotator && !it.disabled }
.each { job ->
    job.logRotator = new hudson.tasks.LogRotator (daysToKeepBuild, numToKeepBuild, artifactDaysToKeep, artifactNumToKeep)
    println "$job.name"
}

// OR

Jenkins.instanceOrNull.allItems(hudson.model.Job)
.each { job ->
    if (job.isBuildable() 
		&& job.supportsLogRotator() 
		&& job.getProperty(jenkins.model.BuildDiscarderProperty) == null) 
		{
			println "Processing \"${job.fullDisplayName}\""
			if (!"true".equals(dryRun)) {
				// adding a property implicitly saves so no explicit one
				try {
					job.setBuildDiscarder(new hudson.tasks.LogRotator ( daysToKeepBuild, numToKeepBuild, artifactDaysToKeep, artifactNumToKeep))
					println "${job.fullName} is updated"
				} catch (Exception e) {
					// Some implementation like for example the hudson.matrix.MatrixConfiguration supports a LogRotator but not setting it
					println "[WARNING] Failed to update ${job.fullName} of type ${job.class} : ${e}"
				}
        }
    }
}
return;