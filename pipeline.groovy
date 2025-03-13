pipeline{
    agent any
     stages{
        stage("Preparation"){
            steps{
                git credentialsId: 'b23d0142-2185-4005-ac0e-6cb7c062ce27', url: 'https://github.com/vikriramdani8/my-next-app.git'
                echo 'Success Cloning'
            }
        }
        stage("Build"){
            steps{
                dir("/var/lib/jenkins/workspace/nextapp"){
                    nodejs("my-nodejs") {
                        sh 'npm install'
                        sh 'npm run build'
                    }
                    echo 'Success Build nextapp'
                }
            }
        }
        stage("Move File"){
            steps{
                 script {
                    sh '''
                        mv /var/lib/jenkins/workspace/nextapp/dist/* /var/www/vikri.my.id
                    '''
                }
            }
        }
    }
}