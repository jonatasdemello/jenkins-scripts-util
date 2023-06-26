
import hudson.model.*

for (job in Hudson.instance.items) {
    def jobname = job.class.toString()
    if (job.disabled != true && jobname.contains('InheritanceProject')) {
        prop = job.getProperty(ParametersDefinitionProperty.class)
        if (prop != null) {
            println('-' * 30)
            println('JOB: ' + job.name)

            numbuilds = job.builds.size()
            if (numbuilds > 0) {
                println 'nextBuildNumber: ' + job.nextBuildNumber
                println 'lastbuildNumber: ' + job.builds[0].displayName

                println 'env-MAJOR_BUILD_NUMBER: ' + job.builds[0].envVars['MAJOR_BUILD_NUMBER']
                println 'env-MINOR_BUILD_NUMBER: ' + job.builds[0].envVars['MINOR_BUILD_NUMBER']

                println 'env-CC_WEB_BUILD_VN: ' + job.builds[0].envVars['CC_WEB_BUILD_VN']
                println 'env-BUILD_DISPLAY_NAME: ' + job.builds[0].envVars['BUILD_DISPLAY_NAME']
                println 'env-BUILD_ID: ' + job.builds[0].envVars['BUILD_ID']
                println 'env-BUILD_NUMBER: ' + job.builds[0].envVars['BUILD_NUMBER']
                println 'env-BUILD_TAG: ' + job.builds[0].envVars['BUILD_TAG']

            }
            println " -- params -- "
            for (param in prop.getParameterDefinitions()) {
                try {
                    println(param.name + '    :    ' + param.defaultValue)
                }
                catch (Exception e) {
                    println(param.name)
                }
            }
            println()
        }
    }
}

