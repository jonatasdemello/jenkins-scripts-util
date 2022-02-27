// Code snippet that outputs all job names:

def hi = hudson.model.Hudson.instance
	hi.getItems(hudson.model.Project).each {project ->
	println(project.displayName)
}
