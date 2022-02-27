import hudson.model.*

for(item in Hudson.instance.items) {
  prop = item.getProperty(ParametersDefinitionProperty.class)
  if(prop != null) {
    println("--- Parameters for " + item.name + " ---")
    println item.
    for(param in prop.getParameterDefinitions()) {
      try {
        println(param.name + "	:	" + param.defaultValue)
      }
      catch(Exception e) {
        println(param.name)
      }
    }
    println()
  }
}
