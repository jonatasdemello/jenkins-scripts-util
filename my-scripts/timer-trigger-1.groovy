// show Timer trigger

import hudson.model.*
import hudson.triggers.*
 
class Timer {
  String name;
  String spec;
  int minute;
  int hour;
 
  Timer(pname,pspec) {
    name=pname;
    spec=pspec;
    def parts=pspec.split(" ")
    minute=parts[0].toInteger()
    hour=parts[1].toInteger()
  }
}
 
def timers = []
 
for(item in Hudson.instance.items) {
  for(trigger in item.triggers.values()) {
    if(trigger instanceof TimerTrigger) {
      def timer=new Timer(item.name,trigger.spec)
      timers.add(timer)
    }
  }
}
 
timers.sort{a,b -> a.hour <=> b.hour ?: a.minute <=> b.minute}
for (timer in timers) {
  printf("%02d:%02d %s\n",timer.hour,timer.minute,timer.name)
  // println(timer.spec + '\n')
 
}