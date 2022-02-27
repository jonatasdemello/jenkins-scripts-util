// Licensed under MIT
// author : Damien Nozay

// list jobs and their last build.

jobs = Jenkins.instance.getAllItems()
jobs.each { j ->
  if (j instanceof com.cloudbees.hudson.plugins.folder.Folder) { return }
  println 'JOB: ' + j.fullName
  numbuilds = j.builds.size()
  if (numbuilds == 0) {
    println '  -> no build'
    return
  }
  lastbuild = j.builds[numbuilds - 1]
    println '  -> lastbuild: ' + lastbuild.displayName + ' = ' + lastbuild.result + ', time: ' + lastbuild.timestampString2
}

// returns blank
''