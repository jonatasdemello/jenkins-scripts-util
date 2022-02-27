import hudson.plugins.emailext.*
import hudson.model.*
import hudson.maven.*
import hudson.maven.reporters.*
import hudson.tasks.*
 
for (item in Hudson.instance.items) {
  if (!(item instanceof ExternalJob)  && item.publishersList ) {
	for (publisher in item.publishersList) {
      if (publisher instanceof Mailer && publisher.recipients != "" ) {
        //println("> " + item.name +"     : " + publisher.recipients);
		printf("%50s | %s\n", item.name, publisher.recipients);
      } else
        if (publisher instanceof ExtendedEmailPublisher && publisher.recipientList != "") {
          //println("> " + item.name +"    : " + publisher.recipientList);
		  printf("%50s | %s\n", item.name, publisher.recipientList);
        }
	}
  }
}

// or

for (item in Hudson.instance.items) {
  if (!(item instanceof ExternalJob)  && item.publishersList ) {
    
	for (publisher in item.publishersList) {
     
		  // Search for default Mailer Publisher (doesn't exist for Maven projects)
		  if (publisher instanceof Mailer && publisher.recipients != "" ) {
            println("---------------------------");
    		println("JOB : " + item.name);
            
			println(">>> publisher 1: " + publisher + " : " + publisher.recipients);
		  } else
			// Or for Extended Email Publisher
			if (publisher instanceof ExtendedEmailPublisher && publisher.recipientList != "") {
                println("---------------------------");
    			println("JOB : " + item.name);
			  println(">>> publisher 2: " + publisher + " : " + publisher.recipientList);
			}
	}
  }
}

// or 	

import hudson.plugins.emailext.*
import hudson.model.*
import hudson.maven.*
import hudson.maven.reporters.*
import hudson.tasks.*

for (item in Hudson.instance.items) {
  if (!(item instanceof ExternalJob)  && item.publishersList ) {
	for (publisher in item.publishersList) {
      if (publisher instanceof Mailer && publisher.recipients != "" ) {
        println("> " + item.name +"     : " + publisher.recipients);
      } else
        if (publisher instanceof ExtendedEmailPublisher && publisher.recipientList != "") {
          println("> " + item.name +"    : " + publisher.recipientList);
        }
	}
  }
}


import hudson.plugins.emailext.*
import hudson.model.*
import hudson.maven.*
import hudson.maven.reporters.*
import hudson.tasks.*

for (item in Hudson.instance.items) {
  if (!(item instanceof ExternalJob)  && item.publishersList ) {
    
	for (publisher in item.publishersList) {
     
		  // Search for default Mailer Publisher (doesn't exist for Maven projects)
		  if (publisher instanceof Mailer && publisher.recipients != "" ) {
            println("---------------------------");
    		println("> " + item.name);
            
			println("     : " + publisher.recipients);
		  } else
			// Or for Extended Email Publisher
			if (publisher instanceof ExtendedEmailPublisher && publisher.recipientList != "") {
                println("---------------------------");
    			println("> " + item.name);
			  println("    : " + publisher.recipientList);
			}
	}
  }
}

 
// For each project
for (item in Hudson.instance.items) {
  println("JOB : " + item.name);
  // Find current recipients defined in project
  if (!(item instanceof ExternalJob)) {
    /*
	if (item instanceof MavenModuleSet) {
      println(">MAVEN MODULE SET");
      // Search for Maven Mailer Reporter
      println(">>Reporters");
      for (reporter in item.reporters) {
        if (reporter instanceof MavenMailer) {
          println(">>> reporter : " + reporter + " : " + reporter.recipients);
        }
      }
    } else
      if (item instanceof FreeStyleProject) {
        println(">FREESTYLE PROJECT");
      }
	 */
    println(">> Publishers");
	// only show existing ones
	for (publisher in item.publishersList) {
		if (publisher instanceof Mailer || publisher instanceof ExtendedEmailPublisher)
		{
		  // Search for default Mailer Publisher (doesn't exist for Maven projects)
		  if (publisher instanceof Mailer && publisher.recipients >0 ) {
			println(">>> publisher : " + publisher + " : " + publisher.recipients);
		  } else
			// Or for Extended Email Publisher
			if (publisher instanceof ExtendedEmailPublisher && publisher.recipientList) {
			  println(">>> publisher : " + publisher + " : " + publisher.recipientList);
			}
		}
	}
    
  } else {
    println("External Jobs cannot have MailNotificationsRecipients")
  }
  println("\n---------------------------");
}