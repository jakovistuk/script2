def dev_namespace = "z-jakov-test"
def test_namespace = "z-jakov-test-test"


pipeline {
    agent {
        label 'master'
    }

    environment {
        FOO = "bar"
    }


    stages {
        stage('tag source code') {
            steps {
                testGit()
                sh 'printenv'
                sh 'ls'
            }
        }

        stage('build artifact') {
            steps {
                //input message: 'Approve Deploy?', ok: 'Yes'
                sh 'ls'
            }
        }

        // begin parallel
        /*
         stage('analysis and testing')
         {
         parallel
         {
         stage('static code analysis')
         {
         steps
         {
         sh 'ls'
         }
         }
         stage('unit testing')
         {
         steps
         {
         sh 'ls'
         }
         }
         stage('integration testing')
         {
         steps
         {
         sh 'ls'
         }
         }
         }
         }
         */

        // end parallel

        /*
         stage('send artefact to nexus')
         {
         steps
         {
         sh 'ls'
         }
         }
         stage('build image')
         {
         steps
         {
         sh 'ls'
         }
         }
         stage('send image to nexus')
         {
         steps
         {
         sh 'ls'
         }
         }
         */

        stage('deploy config to DEV')
                {
                    steps
                            {
                                testGit()

                                environment
                                        {
                                            FOO2 = "bar"
                                        }


                                sh 'printenv'
                                println "BEGIN"
                                println dev_namespace
                                println test_namespace
                                println "END"

                                sh 'ls'
                            }
                }

        stage('DEPLOY to DEV')
                {
                    steps
                            {
                                sh 'ls'
                            }
                }

        /*
         stage('deploy config to TEST')
         {
         steps
         {
         sh 'ls'
         }
         }
         stage('DEPLOY to TEST')
         {
         steps
         {
         sh 'ls'
         }
         }
         */

    }

}
