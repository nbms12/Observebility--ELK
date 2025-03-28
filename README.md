# Observebility--ELK

AIM : Design and setup Elastic search for Continous Monitoring for Java application logs

Desc: I have Developed  basic  Java application using Maven and configured structured logging with Logback. post  then setted up the Elastic Stack (Elasticsearch, Kibana, and Filebeat) to collect, store, and visualize logs efficiently. At the end  have a fully functional logging setup that helps in centralized log management, making it easier to analyze and troubleshoot issues.


steps 
1.

Pre reqsuites: 
java jdk17, maven , 

>sudo apt install openjdk-17-jdk -y

>sudo apt install maven -y


2. Creating a Java Application

   >mkdir my-java-app
   
   >cd my-java-app

   >mvn archetype:generate -DgroupId=com.example -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false


   ![image](https://github.com/user-attachments/assets/096d635d-0d9a-4bbb-b41e-a5409f04fe40)


   >cd my-app


3. Configuring the Java Application


     >nano pom.xml  ( This project is named my-app and will be packaged as a JAR.  )

     >nano src/main/java/com/example/App.java  ( open file and replace App.java code )


4. Create the resources directory and add a Logback configuration file.

     >mkdir -p src/main/resources

     >nano src/main/resources/logback.xml  ( open and replace code as it as )

     

     
5. Build the Application

     >mvn clean package

6.Setting Up Elastic Stack for Log Monitoring

   >curl -fsSL https://artifacts.elastic.co/GPG-KEY-elasticsearch | sudo gpg --dearmor -o /usr/share/keyrings/elasticsearch-keyring.gpg

   ( add repo )

   >echo "deb [signed-by=/usr/share/keyrings/elasticsearch-keyring.gpg] https://artifacts.elastic.co/packages/8.x/apt stable main" | sudo tee /etc/apt/sources.list.d/elastic-8.x.list


   >sudo apt update

  Install Elasticsearch.

 > sudo apt install -y elasticsearch

 > sudo nano /etc/elasticsearch/elasticsearch.yml  ( modify  Elasticsearch configuration for remote access. )

 ![image](https://github.com/user-attachments/assets/cb1a2d03-fc59-41d8-949a-34f4eaa0a4de)

 ![image](https://github.com/user-attachments/assets/bf8206b9-944b-48cf-8856-37a3399d0824)

 > sudo systemctl enable elasticsearch
 > sudo systemctl start elasticsearch
 > sudo systemctl status elasticsearch ( make it runnin status  )
 > curl -X GET "localhost:9200" ( verify it )

7. Install Kibana on the system

    >sudo apt install -y kibana

    >sudo nano /etc/kibana/kibana.yml

    edit below 3  details :

    server.port: 5601
   
    server.host: "0.0.0.0"
   
    elasticsearch.hosts: ["http://localhost:9200"]

    >sudo systemctl enable kibana
    >sudo systemctl start kibana
    >sudo systemctl status kibana  ( open port 5601 on ip ) 
    




   

![image](https://github.com/user-attachments/assets/5f60f065-e6fd-46f6-88f3-87fd01a9e79d)
