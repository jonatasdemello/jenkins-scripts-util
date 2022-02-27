// author : Ahmed Mubbashir Khan
// ---------------------------------------------------------
// This script goes through all the jobs print there currently associated JDK
// ---------------------------------------------------------
def jobs = hudson.model.Hudson.instance.items

jobs.each { job ->
  try{
  println("${job.name}\n\t${job.getJDK()}")
  }
  catch(Exception e){
    //swalloing which will be thrown by fodlers and other such jobs
  }
}
