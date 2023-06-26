findJobsByShellStepsBody.groovy

import hudson.util.*
import hudson.model.*
import hudson.tasks.*

def jobName;
def shellscript;

// param: searchText
// param text: docker/master/templates

def result = "The following Projects contain string \"" + searchText + "\" in a shell step.\n"
  
def projects = Hudson.instance.getAllItems(Job.class)
  .findAll{ it instanceof FreeStyleProject || it instanceof Project  }
  .each{
    jobName = it.getDisplayName();
    it.getBuildersList().findAll{ it instanceof Shell }
    .each{
      shellscript = it.getContents();
      if(shellscript.indexOf(searchText) > 0){
        result += jobName + "\n";
      }
    }
  }

result
