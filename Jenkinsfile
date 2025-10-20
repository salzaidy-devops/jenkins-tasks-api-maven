def gv


pipeline {

    agent any

    parameters {
        string(name: 'NEW_VERSION', defaultValue: '1.0.0', description: 'Version number for the build')
        booleanParam(name: 'RUN_TESTS', defaultValue: true, description: 'Whether to run tests')
        choice(name: 'ENVIRONMENT', choices: ['dev', 'staging', 'prod'], description: 'Deployment environment')

    }

    stages {
        stage('initialize') {
            steps {
                script {
                    gv = load 'Jenkins/pipeline/script.groovy'
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    gv.buildApp()
                }
            }
        }

        stage("test") {
            when {
                // Conditional execution based on the RUN_TESTS parameter
                expression {  params.RUN_TESTS }
            }

            steps {
                script {
                    gv.testApp()
                }
            }
        }


        stage('Deploy') {
            steps {
                script {
                    gv.deployApp()
                }
            }
        }
    }
}