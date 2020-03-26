pipeline {
    agent {
        label 'master'
    }
    stages {
        stage('Stage 1') {
            steps {
                sh 'ls'
                sh 'ssa'

            }
        }
        stage('Stage 2') {
            steps {
                input('AAAAAAAAA')
                input message: 'Approve Deploy?', ok: 'Yes'
                sh 'ls'
            }
        }
        stage('Stage 3') {
            steps {
                sh 'ls'
            }
        }
    }
}