#!groovy

import org.codehaus.groovy.runtime.StackTraceUtils
import groovy.io.FileType

def dev_namespace = "a1"
def test_namespace = "a2"
def projectName = "projectName"
def source_git = "https://some/test-pipeline"

pipeline {
    agent {
        label "master"
    }

    stages {
        stage('deploy config to DEV') {
            steps {
                script {
                    openshift.withCluster("openshift-DEV") {
                        openshift.withProject("z-jakov-test") {
                            def cm = openshift.selector("cm")

                            def containerImages = new ArrayList()

                            cm.withEach {
                                def cMap = it.object()
                                def name = cMap.metadata.name
                                println "VRIJEDNOST " + name
                                println cMap
                            }

                            def cloneDir = new File("${projectName}_configuration")
                            println "cloneDir " + cloneDir.absolutePath
                            println cloneDir

                            def aa = InetAddress.getAllByName("google.com").collect {
                                it.toString().split('/')[1]
                            }
                            println "11 xxxxxxxxxxxxxxxxxxxxxxxxxxx > " + aa
                            println "22 xxxxxxxxxxxxxxxxxxxxxxxxxxx >  $aa"
                            println "33 xxxxxxxxxxxxxxxxxxxxxxxxxxx >  ${aa}"

                            def command = "git clone ${source_git} ${cloneDir.absolutePath}"

                            sh("rm -rf ${cloneDir.absolutePath}")

                            withGit(authenticate: true, cmd: "${command}")

                            // OVO JE DOBAR PRIMJER
                            // *****/_git/Jenkins_pipeline_library?path=%2Fvars%2FcollectContainerImages.groovy&version=GBmaster

                            ws(cloneDir.absolutePath)
                                    {
                                        def commonDir = new File("${cloneDir.absolutePath}/config/common")
                                        final foundFiles = sh(script: "ls -1 ${commonDir}", returnStdout: true).split()

                                        // def customers = new XmlSlurper().parse(commonDir)

                                        foundFiles.each
                                                {
                                                    println "nasao file ${it}"
                                                    if (it.endsWith(".yaml"))
                                                        println "YAML FILE!!"
                                                    def yamlFilePath = "${commonDir.absolutePath}/${it}"
                                                    def yamlFile = new File("${yamlFilePath}")

                                                    // TESTING....
                                                    //USPIEJVAMO PARSATI XML
                                                    //def customers = new XmlSlurper().parse(yamlFile)
                                                    def someBolean = isGolden()
                                                    println "XXXXXXXXXXXXXXXXXXXXXXXX someBolean " + someBolean

                                                    def sourceDc = readYaml file: yamlFilePath

                                                    println "KLASA ========== " + sourceDc.getClass()

                                                    //println "FULL PATH " + yamlFile
                                                }

                                        //sh("ls -halt ${commonDir}")

                                        def commonDirList = []
                                        //sh("ls -halt")
                                    }

                            sh("rm -rf ${cloneDir.absolutePath}")

                            def workspace = pwd()
                            println "workspace " + workspace
                        }
                    }
                }
            }
        }
    }
}


def isGolden() {
    return true
}





