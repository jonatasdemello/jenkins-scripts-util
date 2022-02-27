
Validate all XML files in the current directory and below
find -type f -name "*.xml" -exec xmllint --noout {} \; 


/*** BEGIN META {
  "name" : "Hello World example",
  "comment" : "print some cool <b>stuff</b>",
  "parameters" : [],
  "core": "1.300",
  "authors" : [
	{ name : "Joe Bloggs" }
  ]
} END META**/
println("hello world")

import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*

	<properties>
		<jenkins.model.BuildDiscarderProperty>
		  <strategy class="hudson.tasks.LogRotator">
			<daysToKeep>-1</daysToKeep>
			<numToKeep>20</numToKeep>
			<artifactDaysToKeep>-1</artifactDaysToKeep>
			<artifactNumToKeep>20</artifactNumToKeep>
		  </strategy>
		</jenkins.model.BuildDiscarderProperty>
	</properties>

    <jenkins.model.BuildDiscarderProperty>
      <strategy class="hudson.tasks.LogRotator">
        <daysToKeep>-1</daysToKeep>
        <numToKeep>20</numToKeep>
        <artifactDaysToKeep>-1</artifactDaysToKeep>
        <artifactNumToKeep>-1</artifactNumToKeep>
      </strategy>
    </jenkins.model.BuildDiscarderProperty>
	
	
// --------------------------------------------------------------------------------------
add LogRotation

emails:
coryt@xello.world;arpitac@xello.world;stevew@xello.world;alenap@xello.world

solutions@xello.world

http://ci.careercruising.com:8080/job/CC.Storage.UploadCare/configure


  <publishers>
    <hudson.tasks.Mailer plugin="mailer@1.32">
      <recipients>coryt@xello.world;arpitac@xello.world;stevew@xello.world;alenap@xello.world</recipients>
      <dontNotifyEveryUnstableBuild>false</dontNotifyEveryUnstableBuild>
      <sendToIndividuals>true</sendToIndividuals>
    </hudson.tasks.Mailer>
  </publishers>
  
// --------------------------------------------------------------------------------------
// List all jobs
// --------------------------------------------------------------------------------------
// this script will print the name of all jobs including jobs inside of a folder and the folders themselves:

	Jenkins.instance.getAllItems(AbstractItem.class).each {
		println(it.fullName)
	};

// This script will print the name of all jobs including jobs inside of a folder, but not the folders themselves.

	Jenkins.instance.getAllItems(Job.class).each{ 
		println it.name + " - " + it.class
	}

// This script will recursively print the name of all jobs implementing the AbstractProject class, i.e. Freestyle and Maven jobs.

	Jenkins.instance.getAllItems(AbstractProject.class).each {it ->
	println it.fullName;
	}

// --------------------------------------------------------------------------------------



def dryRun = false

def daysToKeepBuild = -1 // Days to keep builds
def numToKeepBuild = 20  // Max # of builds to keep
def artifactDaysToKeep = -1 // Days to keep artifacts
def artifactNumToKeep = 20  // Max # of builds to keep with artifacts


import jenkins.model.*
import hudson.model.*

Jenkins.instance
	.getAllItems(AbstractProject.class)
	.findAll { it.logRotator }
	.each { 
		it.logRotator.perform(it)  // run log rotator
	}

Hudson.instance.items
	.findAll{ !it.logRotator && !it.disabled }
	.each { 
		job -> job.logRotator = new hudson.tasks.LogRotator (daysToKeepBuild, numToKeepBuild, artifactDaysToKeep, artifactNumToKeep);
		println "$job.name"
	}

Jenkins.instanceOrNull
.allItems(hudson.model.Job)
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


// --------------------------------------------------------------------------------------

// Find projects that habe logRotator enabled

import hudson.model.*
def jobs = Hudson.instance.items
 
jobs.findAll { it.logRotator && !it.disabled }
.each {
   println it.name;
   if (it.logRotator.daysToKeep >= 0){
      println "\t daysToKeep for ${it.logRotator.daysToKeep} day(s)"
   }
   if (it.logRotator.numToKeep >= 0){
      println "\t numToKeep ${it.logRotator.numToKeep} build(s)"
   }
   if (it.logRotator.artifactDaysToKeep >= 0){
      println "\t artifactDaysToKeep for ${it.logRotator.artifactDaysToKeep} day(s)"
   }
   if (it.logRotator.artifactNumToKeep >= 0){
      println "\t artifactNumToKeep of ${it.logRotator.artifactNumToKeep} builds"
   }
}

// --------------------------------------------------------------------------------------
