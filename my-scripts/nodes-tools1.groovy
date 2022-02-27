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