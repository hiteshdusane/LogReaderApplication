# LogReaderApplication
1.  Clone repository using ("https://github.com/hiteshdusane/LogReaderApplication.git") or download the zip and extract it to the folder.
2.  In hsql folder open the command prompt and execute this command -> "java -classpath lib/hsqldb.jar org.hsqldb.server.Server".
3.  Then in logreaderapplication folder (where pom.xml) is stored execute this command ->"mvn spring-boot:run", (maven intalltion is required).
4.  Or if you are using IDE then import the project in IDE as Maven project and right click on project and click "Run as -> Spring Boot Application".
5. Once the program execution starts it will ask for the file path then provide full filepath of the log file. (i.e. "C:\abc\pqr\logfile.txt").
