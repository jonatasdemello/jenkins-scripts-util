import hudson.model.*
import hudson.triggers.*
 
 
TriggerDescriptor SCM_TRIGGER_DESCRIPTOR = Hudson.instance.getDescriptorOrDie(SCMTrigger.class)
assert SCM_TRIGGER_DESCRIPTOR != null;
 
for(item in Hudson.instance.items)
{
  println("Working on project <$item.name>")
   
  def trigger = item.getTriggers().get(SCM_TRIGGER_DESCRIPTOR)
  if(trigger != null && trigger instanceof SCMTrigger)
  {
    print("> $trigger.spec")

  }
  else
  {
    println "> Nothing to do"
  }
}


// or

import hudson.model.*
import hudson.triggers.*
 
 
TriggerDescriptor SCM_TRIGGER_DESCRIPTOR = Hudson.instance.getDescriptorOrDie(SCMTrigger.class)
assert SCM_TRIGGER_DESCRIPTOR != null;
 
for(item in Hudson.instance.items)
{
  println("Working on project <$item.name>")
   
  def trigger = item.getTriggers().get(SCM_TRIGGER_DESCRIPTOR)
  if(trigger != null && trigger instanceof SCMTrigger)
  {
    print("> $trigger.spec")
    String[] parts = trigger.spec.split(" ");
     
    //Do wanted modifs
    if(parts[1] == "*" )
    {
      parts[1] = "7-21"
    }
    if(parts[4] == "*")
    {
      parts[4] = "1-5"
    }
    //end modifs
     
    StringBuilder newSpec = new StringBuilder();
    for(p in parts)
    {
      newSpec.append(p+" ");
    }
     
    println(" => $newSpec");
 
    def newTrigger = new SCMTrigger(newSpec.toString())
    newTrigger.job = item
 
    item.removeTrigger(SCM_TRIGGER_DESCRIPTOR)
    item.addTrigger(newTrigger)
  }
  else
  {
    println "> Nothing to do"
  }
}