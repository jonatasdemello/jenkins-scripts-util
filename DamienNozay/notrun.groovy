// Licensed under MIT
// author : Damien Nozay

// list jobs not run in the last N days / last N months

import groovy.time.TimeCategory
use ( TimeCategory ) {
  // e.g. find jobs not run in last 3 months
  sometimeago = (new Date() - 3.months)
}

jobs = Jenkins.instance.getAllItems()
lastabort = null
jobs.each { j ->
  if (j instanceof com.cloudbees.hudson.plugins.folder.Folder) { return }


  numbuilds = j.builds.size()
  if (numbuilds == 0) {
    println 'JOB: ' + j.fullName
    println '  -> no build'
    return
  }

  lastbuild = j.builds[numbuilds - 1]
  if (lastbuild.timestamp.getTime() < sometimeago) {
    println 'JOB: ' + j.fullName
    println '  -> lastbuild: ' + lastbuild.displayName + ' = ' + lastbuild.result + ', time: ' + lastbuild.timestampString2
  }
}

''