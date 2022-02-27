// How to check if "Restrict where this project can be run" is checked or not in Jenkins?
// getAssignedNode()

// https://javadoc.jenkins-ci.org/hudson/model/AbstractProject.html#getAssignedLabel()

// config.xml - Look for assignedNode property


/*** BEGIN META {
  "name" : "Show labels overview",
  "comment" : "Show an overview of all labels defined and which slaves have which labels",
  "parameters" : [],
  "core": "1.300",
  "authors" : [
    { name : "Stefan Heintz" },
    { name : "Nico Mommaerts" },
    { name : "Rob Fagen" }
  ]
} END META**/

import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*

println sprintf("%20s %s","Job", "AssignedLabel")
println "-------------------- | -------------------------"

Jenkins.instance
.getAllItems(AbstractProject.class)
.findAll { !it.disabled && it.name.contains("Web") } // filter by name here
.each { 
	//println it.getAssignedLabel()?.getExpression() + "\t \t" + it.fullName
	println sprintf("%20s | %s", it.getAssignedLabel()?.getExpression(), it.fullName)
}
	
// or 
/*
import hudson.model.*
def jobs = Hudson.instance.items
jobs.findAll { !it.disabled }
.each {
	println "${it.name} - ${it.getAssignedLabel()}";
}
*/

                 Job AssignedLabel
          nodejs-v12 	 	CAMS3.Web
              master 	 	CAMS3.Web.Branch
              master 	 	CAMS3.Web.Hotfix
          nodejs-v12 	 	CAMS3.Web.Test
              master 	 	CC.Web
                null 	 	CC3.ResumeBuilder.Web
              master 	 	CC3.Web
              master 	 	CC3.Web.Branch
              master 	 	CC3.Web.Hotfix