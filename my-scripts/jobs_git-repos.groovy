//https://serverfault.com/questions/777966/github-webhook-not-triggering-a-job-because-pipeline-doesnt-have-a-matching-rep

// That will loop through all your jobs and output the values for the repos that it's checking against.

import com.cloudbees.jenkins.GitHubRepositoryNameContributor;

for (Item job : Jenkins.getInstance().getAllItems(Item.class)) {
  // println("JOB: : "+ job.name);
  println("JOB: : "+ job.name + " : "+ GitHubRepositoryNameContributor.parseAssociatedNames(job))
}
