// Licensed under MIT
// author : Damien Nozay

// scan all jobs and check if the last build was aborted (e.g. maintenance)
// and output user / timestamp
 
jobs = Jenkins.instance.getAllItems()
jobs.each { j ->
  if (j instanceof com.cloudbees.hudson.plugins.folder.Folder) { return }
  numbuilds = j.builds.size()
  if (numbuilds == 0) { return }
  lastbuild = j.builds[numbuilds - 1]
  if (lastbuild.result == Result.ABORTED) {
    println 'JOB: ' + j.fullName
    abortCause = lastbuild.getAction(InterruptedBuildAction).getCauses()[0]
    println '  -> lastbuild: ' + lastbuild.displayName + ' = ' + lastbuild.result + ', cause: ' + abortCause.shortDescription + ', time: ' + lastbuild.timestampString2
  }
}

''