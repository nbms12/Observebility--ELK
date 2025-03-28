# Observebility--ELK

AIM : Design and setup Elastic search for Continous Monitoring Java application logs

Desc: I have Developed  basic  Java application using Maven and configured structured logging with Logback. post  then setted up the Elastic Stack (Elasticsearch, Kibana, and Filebeat) to collect, store, and visualize logs efficiently. At the end  have a fully functional logging setup that helps in centralized log management, making it easier to analyze and troubleshoot issues.


steps 
1.

Pre reqsuites: 
java jdk17, maven , 

sudo apt install openjdk-17-jdk -y

sudo apt install maven -y


2. Creating a Java Application

   mkdir my-java-app
   
   cd my-java-app

   mvn archetype:generate -DgroupId=com.example -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false


   ![image](https://github.com/user-attachments/assets/096d635d-0d9a-4bbb-b41e-a5409f04fe40)


   cd my-app

   

![image](https://github.com/user-attachments/assets/5f60f065-e6fd-46f6-88f3-87fd01a9e79d)
