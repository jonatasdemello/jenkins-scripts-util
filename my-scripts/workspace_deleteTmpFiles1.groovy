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

 
def counter = 0
 
// Create a ref for closure
def delClos
 
// Define closure
delClos = {
    it.eachDir( delClos );
    if(it.getName().contains("workspace-files")) {
        it.eachFile {
            if(it.getName().endsWith(".tmp") ){
                println "Deleting file ${it.canonicalPath}";
                it.delete()
                counter++;
            }
        }
    }
}
 
// Apply closure
for(item in Hudson.instance.items) {
    println "Applying on " + item.rootDir
    delClos( item.rootDir )
}
println counter + " files deleted"
