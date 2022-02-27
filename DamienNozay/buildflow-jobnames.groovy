// Licensed under MIT
// author : Damien Nozay

// for all builds from build-flow-plugin whose parameters include a GIT_BRANCH paramater,
// change the displayName to include branch and build number
 
import com.cloudbees.plugins.flow.*;
 
jobs = Jenkins.instance.getAllItems(BuildFlow);
jobs.each { it ->
    it.builds.each { b -> 
        GIT_BRANCH = b.envVars['GIT_BRANCH']
        ( GIT_BRANCH =~ /(?:refs\/remotes\/)?(.+)/ ).each { full,branch ->
            b.displayName = branch + ' (#' + b.number + ')'
        }
    }
}
