//https://jenkinsci.github.io/job-dsl-plugin/#path/pipelineJob
BRANCH_NAME = 'master'
GIT_URL = 'https://github.com/nawinkumar94/Two-Tier-System-in-Ansible.git'
GIT_CREDENTIALS_ID = 'a0c3162c-f34d-4420-b302-a2bf1ffac98e'
AWS_CREDENTIALS_ID = 'd41efd75-3fc1-43d2-a403-4294fa986482'

pipelineJob('AnsibleInfrastructure') {
  displayName('Scm')
  description('Deploy Ansible Roles')
  logRotator {
        numToKeep(10)
        artifactNumToKeep(3)
    }

  parameters{
    stringParam('BRANCH_NAME',BRANCH_NAME,'Branch Details')
    stringParam('GIT_URL',GIT_URL,'Git Url Details')
    choiceParam('Inventory',['static','dynamic'])
    credentialsParam('GIT_CREDENTIALS_ID'){
      defaultValue(GIT_CREDENTIALS_ID)
    }
    credentialsParam('AWS_CREDENTIALS_ID'){
      defaultValue(AWS_CREDENTIALS_ID)
    }
  }


definition {
            cpsScm {
                    scm {
                        git{
                            remote {
                                credentials(GIT_CREDENTIALS_ID)
                                url("https://github.com/nawinkumar94/Jenkins-Pipeline-Ansible.git")
                            }
                        branch('master')
                        }
                    scriptPath('infrastructure/Jenkinsfile')
                    lightweight()
                }
        }
}
properties{
          rebuild {
                  autoRebuild(false)
                  rebuildDisabled(false)
          }
     }
}
