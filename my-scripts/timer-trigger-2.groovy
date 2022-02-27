// Jenkins Groovy Script to show a sorted list of timer triggers with corresponding goals
//
// derived from
// https://wiki.jenkins-ci.org/display/JENKINS/Display+timer+triggers
//
// created 2012-07-17
// by WF (http://www.bitplan.com)
 
 
// needed imports
// see http://javadoc.jenkins-ci.org/
import hudson.model.*
import hudson.triggers.*
import hudson.maven.*
   
// Information about TimerTrigger and the job it belongs to
class Timer {
  // name of job
  String name;
  // goals of (maven) - job
  String goals;
  // full timer specification e.g. 0 2 * * * for 2:00 am
  String spec;
  // extracted minute
  int minute;
  // extracted hour
  int hour;
   
  // construct a timer with the given name, goals and timer specification
  Timer(pname,pgoals,pspec) {
    name=pname;
    goals=pgoals;
    spec=pspec;
    // split the timer spec
    def parts=pspec.split(" ")
    // get the minute and hour parts
    minute=parts[0].toInteger()
    hour=parts[1].toInteger()
  }
}
 
// start with an empty list of timers
def timers = []
 
// loop over all Hudson jobs
for(item in Hudson.instance.items) {
  // look for triggers
  for(trigger in item.triggers.values()) {
    // that are timer triggers
    if(trigger instanceof TimerTrigger) {
      // extract Timer information
      // check if goals are available
      def goals=""
      if (item instanceof MavenModuleSet) {
        goals=item.goals
      }
      // create a new timer information
      def timer=new Timer(item.name,goals,trigger.spec)
      // add it to the list
      timers.add(timer)
    }
  }
}
 
// sort all timers first by hour, then by minute
timers.sort{a,b -> a.hour <=> b.hour ?: a.minute <=> b.minute}
 
// print a formatted list of all timer information
 
// print header
printf("%-5s | %-50s | %s\n","Time","Job","Goals")
printf("%-5s-+-%-50s-+-%s\n","-----","--------------------------------------------------","----------------------------------------")
 
// loop over timers
for (timer in timers) {
  // print a formatted line
  printf("%02d:%02d | %-50s | %s\n",timer.hour,timer.minute,timer.name,timer.goals)  // println(timer.spec + '\n')
     
}