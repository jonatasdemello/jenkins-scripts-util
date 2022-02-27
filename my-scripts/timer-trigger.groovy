// show Timer trigger

import hudson.model.*
import hudson.triggers.*
 
for(item in Hudson.instance.items) {
    for(trigger in item.triggers.values()) {
        if(trigger instanceof TimerTrigger) {
            println("--- Timer trigger for " + item.name + " ---")
            println(trigger.spec + '\n')
        }
    }
}