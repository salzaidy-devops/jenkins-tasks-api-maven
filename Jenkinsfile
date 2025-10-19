pipeline {

    agent any

    parameters {
        string(name: 'NEW_VERSION', defaultValue: '1.0.0', description: 'Version number for the build')
        boolean(name: 'RUN_TESTS', defaultValue: true, description: 'Whether to run tests')
        choice(name: 'ENVIRONMENT', choices: ['dev', 'staging', 'prod'], description: 'Deployment environment')

    }

    stages {

        stage("build") {
            steps {
                echo 'building the application...'
            }
        }

        stage("test") {
            when {
                // Conditional execution based on the RUN_TESTS parameter
                expression {  params.RUN_TESTS }
            }
            steps {
                echo 'testing the application...'
            }
        }


        stage("deploy") {
            steps {
                echo 'deploying the application...'
                echo "deploying version ${params.ENVIRONMENT} ENVIRONMENT"
            }
        }
    }
}