// author : Ahmed Mubbashir Khan
// ---------------------------------------------------------
// This script goes through all the jobs in a view, filters succesful and failed jobs seprately
// Then prints outs them along with the time they took
// ---------------------------------------------------------

import hudson.model.*
def str_view = "Pipeline Tests"
def view = Hudson.instance.getView(str_view)
def successfulJobs = view.getItems().findAll{job -> job.lastBuild != null && job.lastBuild.result == hudson.model.Result.SUCCESS}
def faildJobs = view.getItems().findAll{job -> job.lastBuild != null && job.lastBuild.result == hudson.model.Result.FAILURE}
def disabledJob = view.getItems().findAll{job -> job.disabled == true}
def enabledJob = view.getItems().findAll{job -> job.disabled != true}
println "Total jobs: " + view.getItems().size +" Successful: " +successfulJobs.size+
  " Failed: " + faildJobs.size + " Enabled jobs: " +enabledJob.size + " Disabled jobs: " +disabledJob.size 

println "Current Successful job:"
successfulJobs.each{job -> printInfo(job)}
println "Current Fail job:"
faildJobs.each{job -> printInfo(job)}
println "Current disabled job:"
disabledJob.each{job -> printInfo(job)}
println "Current enabled job:"
enabledJob.each{job -> printInfo(job)}

def printInfo(job){
  println "Job: ${job.name} build on ${job.getAssignedLabelString()}, "+
    "took ${job.lastBuild.getDurationString()} to build, is disabled : ${job.disabled}"
}