// Code snippet that gets statuses for all the latest builds:

def hi = hudson.model.Hudson.instance
hi.getItems(hudson.model.Project).each {project ->
  println(project.displayName)
  println(project.lastBuild.result)
}
