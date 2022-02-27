// Licensed under MIT
// author : Damien Nozay
// ---------------------------------------------------------
// Retroactively add badges to promoted builds.
// ---------------------------------------------------------
 
import  hudson.plugins.promoted_builds.*;
import org.jvnet.hudson.plugins.groovypostbuild.*;
 
// customize these
project_name = "project/full/name"
promotion_name = "promotion_process_name"
 
// look up promoted builds for project + promotion process.
project = Jenkins.instance.getItemByFullName(project_name)
action = project.getAction(PromotedProjectAction.class)
promotion = action.getProcess(promotion_name)
promoted_builds = action.getPromotions(promotion)
 
 
// check this other gist:
// https://gist.github.com/dnozay/fc528b43cf27755017cc
 
def add_release_version(promo_build) {
    target = promo_build.target;
    // access the promotion build environment and the RELEASE_VERSION parameter.
    release_version = promo_build.environment.get('RELEASE_VERSION');
    // create the summary with the gold star icon and attach to target.
    GroovyPostbuildSummaryAction action = new GroovyPostbuildSummaryAction("star-gold.png");
    target.getActions().add(action);
    // customize text for the summary.
    action.appendText("RELEASE VERSION = " + release_version, false);
    // also add a short text that will appear in the build history
    target.getActions().add(GroovyPostbuildAction.createShortText(release_version));
    // save build
    target.save();
}
 
// do stuff; e.g. add release version in the description.
for (Promotion promo: promoted_builds) {
  add_release_version(promo)
}