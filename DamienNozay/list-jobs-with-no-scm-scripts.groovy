// Licensed under MIT
// author : Ahmed Mubbashir Khan
// ---------------------------------------------------------
// This script goes through all the jobs and checks if they configured SCM is hudson.scm.NullSCM
// if they are, then prints it's info
// ---------------------------------------------------------
 

counter = 0
jobs = Jenkins.instance.getAllItems()
for (job in jobs) {
  if (job.scm instanceof hudson.scm.NullSCM){
  	println "Job= '${counter++}' '${job.name}' scm '${job.scm}'"
  }
}