/*
Runs log rotation on all jobs to free space
Log rotation normally runs after a build is completed. 
If you want to run log rotation on all of your jobs without having to build them
	for example after you have changed the retention settings, 
	or if you have disabled builds, 
	or if you have jobs that don't run very often
Enter the following script into the script console:
*/

import jenkins.model.*

Jenkins.instance.getAllItems(AbstractProject.class)
.findAll { it.logRotator }
.each {
  it.logRotator.perform(it)
}
