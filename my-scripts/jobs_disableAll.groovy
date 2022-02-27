// Disable all jobs
// This script disables all jobs in your Jenkins server

import hudson.model.*
 
disableChildren(Hudson.instance.items)
 
def disableChildren(items) {
  for (item in items) {
    if (item.class.canonicalName == 'com.cloudbees.hudson.plugins.folder.Folder') {
        disableChildren(((com.cloudbees.hudson.plugins.folder.Folder) item).getItems())
    } else if (item.class.canonicalName != 'org.jenkinsci.plugins.workflow.job.WorkflowJob') {
      item.disabled=true
      item.save()
      println(item.name)
    }
  }
}

// or 
Jenkins.instance
	.getAllItems(org.jenkinsci.plugins.workflow.job.WorkflowJob.class)
	.each {i -> 
		i.setDisabled(true); 
		i.save() 
	}

// or

Jenkins.instance
	.getAllItems(hudson.model.AbstractProject.class)
	.each {i -> 
		i.setDisabled(true); 
		i.save() 
	}


