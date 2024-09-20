node('linux') 
{
        stage ('Poll') {
                checkout([
                        $class: 'GitSCM',
                        branches: [[name: '*/main']],
                        doGenerateSubmoduleConfigurations: false,
                        extensions: [],
                        userRemoteConfigs: [[url: 'https://github.com/zopencommunity/gperfport.git']]])
        }

        stage('Build') {
                build job: 'Port-Pipeline', parameters: [
                string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/gperfport.git'), 
                string(name: 'PORT_DESCRIPTION', value: 'GNU gperf is a tool which generates perfect hash functions.'),
                booleanParam(name: 'RUN_TESTS', value: true)
                ]
        }
}
