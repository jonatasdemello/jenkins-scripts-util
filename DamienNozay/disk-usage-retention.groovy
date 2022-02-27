// Licensed under MIT
// author : Damien Nozay
// ---------------------------------------------------------
// This script goes through all the jobs and checks the disk usage.
// prints some disk usage stats as well as the retention policy.
// ---------------------------------------------------------

// e.g.:
// 
// JOB: playground/test-python
//   -> lastbuild: #1 = FAILURE, time: 2015-02-12T20:56:16Z
//   -> builds=12, average=8 KB, max=9 KB, total=97 KB, worstCase=113 KB
// 
// 

def printDiscarder(job) {
  d = job.buildDiscarder
  if (d) {
     println("  -> keep: builds=(${d.daysToKeep} days, ${d.numToKeep} total); artifacts=(${d.artifactDaysToKeep} days, ${d.artifactNumToKeep} total)")
  } else {
     println("  -> no retention policy.")
  }
}

import hudson.plugins.disk_usage.BuildDiskUsageAction
import hudson.plugins.disk_usage.DiskUsageUtil

def getDiskUsage(build) {
  usage = null
  build.getTransientActions().each {
    action ->
    if (action instanceof BuildDiskUsageAction) {
      // println action.buildUsageString
      // println action.allDiskUsage
      usage = action
    }
  }
  return usage
}

def printDiskUsage(job) {
  maxUsage = 0
  totalUsage = 0
  numBuilds = 0
  job.builds.each() {
    build ->
    usage = getDiskUsage(build)
    if (usage.allDiskUsage > maxUsage) { maxUsage = usage.allDiskUsage }
    totalUsage += usage.allDiskUsage
    numBuilds += 1
    // println(" * build ${build.number} - ${usage.buildUsageString}")
  }
  averageUsage = 0
  if (numBuilds) { averageUsage = (totalUsage / numBuilds).longValue() }
  worstCase = numBuilds * maxUsage
  println("  -> builds=${numBuilds}, average=${DiskUsageUtil.getSizeString(averageUsage)}, max=${DiskUsageUtil.getSizeString(maxUsage)}, total=${DiskUsageUtil.getSizeString(totalUsage)}, worstCase=${DiskUsageUtil.getSizeString(worstCase)}")
}
 
jobs = Jenkins.instance.getAllItems()
jobs.each { j ->
  if (j instanceof com.cloudbees.hudson.plugins.folder.Folder) { return }
 
  numbuilds = j.builds.size()
  println 'JOB: ' + j.fullName
  if (numbuilds == 0) {
    println '  -> no build'
  } else {
    lastbuild = j.builds[numbuilds - 1]
    println '  -> lastbuild: ' + lastbuild.displayName + ' = ' + lastbuild.result + ', time: ' + lastbuild.timestampString2
  }
  printDiscarder(j)
  printDiskUsage(j)
  println ''
}
 
''
