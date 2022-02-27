// adapted from https://github.com/tkrzeminski/jenkins-groovy-scripts/blob/master/show-all-credentials.groovy
// ---------------------------------------------------------
// This script goes through all the credentials and tries to find a matching item.
// Useful to find the secret that needs to be updated (e.g. it was leaked in log or similar).
// ---------------------------------------------------------

// find needle in credentials
needle = 'xxxxxxx'

import jenkins.model.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.impl.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey
import com.cloudbees.jenkins.plugins.awscredentials.AWSCredentialsImpl
import org.jenkinsci.plugins.plaincredentials.StringCredentials
import org.jenkinsci.plugins.plaincredentials.impl.FileCredentialsImpl

def showRow = { credentialType, secretId, content = null, username = null, password = null, description = null ->
  println("━" * 80)
  println("type".padLeft(20) + "┃ ${credentialType}")
  println("description".padLeft(20) + "┃ ${description}")
  println("secret id".padLeft(20) + "┃ ${secretId}")
  println("username".padLeft(20) + "┃ ${username}")
  println("password".padLeft(20) + "┃ ${password}")
  println("content:")
  println("┅" * 80)
  println(content)
  println("━" * 80)
}

// set Credentials domain name (null means is it global)
domainName = null

credentialsStore = Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0]?.getStore()
domain = new Domain(domainName, null, Collections.<DomainSpecification>emptyList())

credentialsStore?.getCredentials(domain).each{
  if(it instanceof UsernamePasswordCredentialsImpl && (it.username == needle || it.password?.getPlainText() == needle))
    showRow("user/password", it.id, null, it.username, it.password?.getPlainText(), it.description)
  if(it instanceof BasicSSHUserPrivateKey && (it.passphrase?.getPlainText() == needle))
    showRow("ssh priv key", it.id, null, it.passphrase?.getPlainText(), it.privateKeySource?.getPrivateKey(), it.description)
  if(it instanceof AWSCredentialsImpl && (it.accessKey == needle || it.secretKey?.getPlainText() == needle))
    showRow("aws", it.id, null, it.accessKey, it.secretKey?.getPlainText(), it.description)
  if(it instanceof StringCredentials && (it.secret?.getPlainText()?.contains(needle)))
    showRow("secret text", it.id, it.secret?.getPlainText(), null, null, it.description)
  if(it instanceof FileCredentialsImpl && (it.content?.text?.contains(needle)))
    showRow("secret file", it.id, it.content?.text, null, null, it.description)
}

return