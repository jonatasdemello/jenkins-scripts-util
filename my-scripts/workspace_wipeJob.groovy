/*** BEGIN META {
  "name" : "wipe workspace",
  "comment" : "Wipe workspace for job filer name",
  "parameters" : [],
  "core": "1.300",
  "authors" : [
	{ name : "Xello" }
  ]
} END META**/
import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*

// Wipe workspaces for a set of jobs on all nodes

// The script wipes workspaces of certain jobs on all nodes.
// Execute it from <Jenkins host>/computer/(master)/script 
// Change the TODO part to specify which jobs to affect

import hudson.model.*

for (item in Hudson.instance.items)
{
  jobName = item.getFullDisplayName()
  // check that job is not building
  if (!item.isBuilding())
  {
    // TODO: Modify the following condition to select which jobs to affect
    if (jobName == "MyJob")
    {
      println("Wiping out workspaces of job " + jobName)
      customWorkspace = item.getCustomWorkspace()
      println("Custom workspace = " + customWorkspace)
 
      for (node in Hudson.getInstance().getNodes())
      {
        println("  Node: " + node.getDisplayName())
        workspacePath = node.getWorkspaceFor(item)
        if (workspacePath == null)
        {
          println("    Could not get workspace path")
        }
        else
        {
          if (customWorkspace != null)
          {
            workspacePath = node.getRootPath().child(customWorkspace)
          }
 
          pathAsString = workspacePath.getRemote()
          if (workspacePath.exists())
          {
            workspacePath.deleteRecursive()
            println("    Deleted from location " + pathAsString)
          }
          else
          {
            println("    Nothing to delete at " + pathAsString)
          }
        }
      }
    }
  }
  else
  {
    println("Skipping job " + jobName + ", currently building")
  }
}
