// show plugin dependency

def plugins = jenkins.model.Jenkins.instance.getPluginManager().getPlugins()
plugins.each {
    println "${it.getShortName()} (${it.getVersion()}) => ${it.getDependencies()}\n"
}
