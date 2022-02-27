// Licensed under MIT
// author : Damien Nozay
// ---------------------------------------------------------
// This script goes through all the jobs and checks if they are using the Groovy Postbuild
// if they are, then it computes the hash value, and checks against the ones that are approved.
// ---------------------------------------------------------
 
 
import org.jenkinsci.plugins.scriptsecurity.scripts.*;
import org.jenkinsci.plugins.scriptsecurity.sandbox.groovy.*;
import hudson.model.RootAction;
import org.jvnet.hudson.plugins.groovypostbuild.*;
 
 
// instance containing the approvals
// list of approved hashes: println instance.approvedScriptHashes
 
ScriptApproval instance = Jenkins.getInstance().getExtensionList(RootAction.class).get(ScriptApproval.class);
approvedScriptHashes = instance.approvedScriptHashes
 
 
import java.util.*
import java.security.MessageDigest;
 
def hash(String script, String language) {
    MessageDigest digest = MessageDigest.getInstance("SHA-1");
    digest.update(language.getBytes("UTF-8"));
    digest.update((byte) ':');
    digest.update(script.getBytes("UTF-8"));
    return Util.toHexString(digest.digest());
}
 
 
jobs = hudson.model.Hudson.instance.getAllItems(FreeStyleProject)
for (job in jobs) {
  for (publisher in job.publishersList) {
    if (publisher instanceof GroovyPostbuildRecorder) {
      hashval = hash(publisher.script.script, "groovy")
      println "#" * 80
      println "job: " + job.getFullName()
      println "script hash: " + hashval
      println "approved: " + (hashval in approvedScriptHashes)
      println "script: "
      println "-" * 80
      println publisher.script.script
      println "#" * 80
    }
  }
}