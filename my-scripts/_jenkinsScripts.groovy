Jenkins API
-----------
	https://javadoc.jenkins-ci.org/

	https://jenkins.io/doc/book/managing/script-console/
	https://jenkins-le-guide-complet.github.io/html/sec-hudson-home-directory-contents.html

Groovy Language:
	https://groovy-lang.org/syntax.html

Plugin:
	https://plugins.jenkins.io/throttle-concurrents/


Master:	
	http://ci.careercruising.com:8080/
	http://ci.careercruising.com:8080/script

Node:
	http://ci.careercruising.com:8080/label/docker/

Runner:
	http://run.careercruising.com:8082/
	

Delete folders
	https://www.ghacks.net/2017/07/18/how-to-delete-large-folders-in-windows-super-fast/

https://medium.com/faun/how-to-get-jenkins-build-job-details-b8c918087030


list methods on a class instance
	- thing.metaClass.methods*.name.sort().unique()

determine a class from an instance
	- thing.class
	- thing.getClass()

	jenkins.getInstance().metaClass.methods*.name.sort().unique()


EnvVars.masterEnvVars.get("JENKINS_HOME")


In order to do so first find the directory of JENKINS_HOME  with this command for example :
	
	println "env".execute().text

Find out which jobs are taking the most spaces with :
	
	println "du -mh [JENKINS_HOME]/jobs".execute().text

Suppress the directory taking the most space(you need admin rights)

	println "rm -rf   [JENKINS_HOME]/jobs/[my_job_to_clean_up]/builds".execute().text


https://github.com/samrocketman/jenkins-bootstrap-jervis.git
https://github.com/samrocketman/jenkins-bootstrap-shared.git
https://github.com/samrocketman/demo-jenkins-world-2017.git
https://github.com/samrocketman/jenkins-script-console-scripts.git
https://github.com/cloudbees/jenkins-scripts.git
https://github.com/jenkinsci/jenkins-scripts.git



/*** BEGIN META {
  "name" : "Hello World example",
  "comment" : "print some cool <b>stuff</b>",
  "parameters" : [],
  "core": "1.300",
  "authors" : [
	{ name : "Joe Bloggs" }
  ]
} END META**/
println("hello world")

	