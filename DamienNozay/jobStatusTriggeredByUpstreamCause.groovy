// author : Ahmed Mubbashir Khan
// Licensed under MIT
// ---------------------------------------------------------
// This script prints out information of last dwonstream job of Upstream job
// e.g. printInformationOfDownstreamJobs("ChangeListner", 11, "All Tests")
// will print all the downstream jobs invodked by ChangeListner build 11 in the view "All Tests"
// ---------------------------------------------------------

import hudson.model.*
//printInformationOfDownstreamJobs("ChangeListner", 11, "All Tests")

def printInformationOfDownstreamJobs(jobName, buildnumber, viewName){
  def upStreamBuild = Jenkins.getInstance().getItemByFullName(jobName).getBuildByNumber(buildnumber)
   println "${upStreamBuild.fullDisplayName} " +
   "${upStreamBuild.getCause(hudson.model.Cause.UpstreamCause).upstreamRun} " +
    "Triggerd at: ${upStreamBuild.getTime()}"
  def cause_pattern = /.*${jobName}.*${buildnumber}.*/
  println "Cause pattern: ${cause_pattern}"
  def view = Hudson.instance.getView(viewName)
  def buildsByCause = []
   // For each item in the view 
  view.getItems().each{ 
    def jobBuilds=it.getBuilds() // get all the builds 
    jobsBuildsByCause = jobBuilds.findAll { build ->    
    build != null &&
    build.getCause(hudson.model.Cause.UpstreamCause)!= null &&
    build.getCause(hudson.model.Cause.UpstreamCause).upstreamRun==~cause_pattern
    }
    buildsByCause.addAll(jobsBuildsByCause)
  }
   // printing information 
  buildsByCause.each{ d_build->
   // def d_build = job.lastBuild
    println("Build: ${d_build.fullDisplayName}->"+
     "result:${d_build.result}->${d_build.buildStatusSummary.message}, " +
     "(was triggered by:${d_build.getCause(hudson.model.Cause.UpstreamCause).upstreamRun})" )
  }
}
