// Go to Script Console under Manage Jenkins, 
// this script will print the name of all jobs including jobs inside 
// of a folder and the folders themselves:

Jenkins.instance.getAllItems(AbstractItem.class)
.each {
	println(it.fullName)
};

// This script will print the name of all jobs including jobs inside of a folder, 
// but not the folders themselves.

Jenkins.instance.getAllItems(Job.class)
.each {
	println it.name + " - " + it.class
}

// This script will recursively print the name of all jobs implementing 
// the AbstractProject class, i.e. Freestyle and Maven jobs.

Jenkins.instance.getAllItems(AbstractProject.class)
.each {it ->
	println it.fullName;
}

import jenkins.model.*
import hudson.model.*

Jenkins.instance.getAllItems(AbstractItem.class)
.each { println(it.fullName) };


jenkins.model.Jenkins.instance
.getAllItems(jenkins.model.ParameterizedJobMixIn.ParameterizedJob.class)
.findAll{it -> it.disabled}
.each {it -> println it.fullName; }


jenkins.model.Jenkins.instance
.getAllItems(jenkins.model.ParameterizedJobMixIn.ParameterizedJob.class)
.findAll(){job -> job.isDisabled()}
.each {
	println it.fullName;
}


// show job - status - workplace

def hi = hudson.model.Hudson.instance
hi.getItems(hudson.model.Job).each {
  job ->
	println "${job.displayName} - ${job.isDisabled()} - ${job.workspace}";
    // println(job.displayName)
    // println(job.isDisabled())
    // println(job.workspace)
}


// Here's how you can do it on multiple slaves: 
// create a multi-configuration job (also called "matrix job") 
// that runs on all the slaves. 
// On each slave the following system Groovy script will give you 
// for every job its workspace on that slave (as well as enabled/disabled flag):

def hi = hudson.model.Hudson.instance

def thr = Thread.currentThread()
def build = thr?.executable
def node = build.executor.owner.node

hi.getItems(hudson.model.Job).each {
  job ->
    println("---------")
    println(job.displayName)
    println(job.isDisabled())
    println(node.getWorkspaceFor(job))
  }

// As the script runs on the slave itself you can wipe out the workspace directly from it. 
// Of course, the worskspace may not exist, but it's not a problem. 
// Note that you write the script only once - 
// Jenkins will run it on all the slaves you specify in the matrix job automatically.




// Because of it doesn't show jobs in folders
// There are 2 methods:

// 1) Recursion

import com.cloudbees.hudson.plugins.folder.*

void processFolder(Item folder) {
	folder.getItems().each{
		if(it instanceof Folder) {
			processFolder(it)
		} else {
			processJob(it)
		}
	}
}
void processJob(Item job){
	println job.name
}

Jenkins.instance.getItems().each{
	if(it instanceof Folder){
		processFolder(it)
	} else {
		processJob(it)
	}
}

// 2) Using getAllItems which is recursive by itself

import hudson.matrix.*
import jenkins.model.*;
import com.cloudbees.hudson.plugins.folder.*

Jenkins.getInstance().getAllItems()
.each {
	// MavenModule is superfluous project returned by getAllItems()
	if (!(it instanceof MavenModule || it instanceof MatrixConfiguration)) {
	println it
	}
}

