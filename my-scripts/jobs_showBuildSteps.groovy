// Display jobs group by the build steps they use

import hudson.model.*
import hudson.tasks.*
 
//All the projects on which we can apply the getBuilders method
def allProjects = Hudson.instance.items.findAll{ it instanceof Project }
 
//All the registered build steps in the current Jenkins Instance
def allBuilders = Builder.all()
 
//Group the projects by the build steps used
def projectsGroupByBuildSteps = allBuilders.inject([:]){
   map, builder ->  
   map[builder.clazz.name] = allProjects.findAll{it.builders.any{ it.class.name.contains(builder.clazz.name)}}.collect{it.name}
   map
}
 
//presentation
projectsGroupByBuildSteps.each{
   println """--- $it.key ---
   \t$it.value\n"""
}