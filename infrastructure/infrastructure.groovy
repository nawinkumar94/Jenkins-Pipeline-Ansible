//https://jenkinsci.github.io/job-dsl-plugin/#path/pipelineJob
BRANCH_NAME = 'master'
GIT_URL = 'https://github.com/nawinkumar94/Two-Tier-System-in-Ansible.git'
GIT_CREDENTIALS_ID = 'e8e4495f-fc6c-444e-9b5e-c7d93c44e05b'
AWS_CREDENTIALS_ID = '53d7be66-682e-47a5-9dc9-29bff7b17feb'

pipelineJob('AnsibleInfrastructure') {
  displayName('Scm_Ansible')
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
                        branch('*/main')
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
