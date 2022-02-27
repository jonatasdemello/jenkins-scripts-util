// Enable or Disable concurrentBuild 

import hudson.model.*

def jobs = Hudson.instance.items
  
jobs.findAll { !it.disabled && it.concurrentBuild }
.each {
	println "\t  ${it.name} - ${it.concurrentBuild}";
	
	// to disable concurrent Build uncomment the line below:
	//it.concurrentBuild = false;
}
