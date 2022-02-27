// Display Tools Location on All Nodes
// This script can help to get Jenkins tools localtion on all your slaves
// If you have many slaves, then it is difficult to view all configs for nodes. Run this small script and get all build tools.
// 
// Thanks o lot "domi" for http://scriptlerweb.appspot.com/script/show/49001

import hudson.model.*
import hudson.node_monitors.*
import hudson.slaves.*
import java.util.concurrent.*
import hudson.tools.ToolDescriptor;
import hudson.tools.ToolInstallation;
jenkins = Hudson.instance
   
TaskListener log;
   
def getEnviron(computer) {
   def env
   def thread = Thread.start("Getting env from ${computer.name}", { env = computer.environment })
   thread.join(2000)
   if (thread.isAlive()) thread.interrupt()
   env
}
 
def slaveAccessible(computer) {
    getEnviron(computer)?.get('PATH') != null
}
 
 
for (aSlave in jenkins.slaves) {
   def computer = aSlave.computer
   
     println "Checking computer ${computer.name}:"
     def isOK = (slaveAccessible(computer) && !computer.offline)
     if (isOK) {
 
      
        for (ToolDescriptor<?> desc : ToolInstallation.all()) {
            for (ToolInstallation inst : desc.getInstallations()) {
                println ('\tTool Name: ' + inst.getName());
                println ('\t\tTool Home: ' + inst.translateFor(aSlave,log));
            }  
 }  
    } else {
        println "  ERROR: can't get PATH from slave: node is offline."
    }
}
