
def creds = com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(
    com.cloudbees.plugins.credentials.common.StandardUsernameCredentials.class,
    Jenkins.instance,
    null,
    null
)

for(c in creds) {
  if(c instanceof com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey){
    println(String.format("id=%s  desc=%s key=%s\n", c.id, c.description, c.privateKeySource.getPrivateKeys()))
  }
  if (c instanceof com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl){
    println(String.format("id=%s  desc=%s user=%s pass=%s\n", c.id, c.description, c.username, c.password))
  }
}

