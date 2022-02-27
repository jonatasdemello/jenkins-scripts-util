// Update Email

// The first step was to successfully find all projects that have 
// a specific email address among the recipients. 

import hudson.model.*
import hudson.maven.reporters.MavenMailer
  
Hudson.instance.items
.findAll { job -> 
	job.metaClass.hasProperty(job, 'reporters') 
	&& job.reporters?.findAll{ it instanceof MavenMailer}.any{ reporter -> reporter.recipients =~ 'daniel@example.com' }
}.each { job -> if (job)
	println "${job?.name} has Daniel as a recipient"
}

// The next step was to actually change the recipient to me.
	
import hudson.model.*
import hudson.maven.reporters.MavenMailer 
 
def from = 'oldguy@example.com'
def mine = 'me@example.com'
def mvnmailer = { job -> (job.metaClass.hasProperty (job, 'reporters')) ? job.reporters?.findAll { it instanceof MavenMailer } : [] }
  
def projects = Hudson.instance.items.findAll { job ->
   job.metaClass.hasProperty (job, 'reporters') &&
      mvnmailer(job).any{ reporter -> reporter.recipients =~ from }
   }
   
println "$projects.size project(s) found"
if (projects?.size() > 0)

projects.each { job -> 
         mvnmailer (job).each {it.recipients = it.recipients.replaceAll(from, mine) }
         job.save() // pretty useless if you change it but you don't save it
      }

// On line 6 you can see a closure, basically I needed a tasteful way to find all the MavenMailer instances of a job. 
// At line 10 I’m using it to filter for the old recipient and at 
// line 15 replace the old recipient with the new one. 
// Finally it’s mandatory to actually persist your changes, otherwise it was just another 
// futile exercise in wasting computing cycles.
