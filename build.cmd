rd /s /q marathon-backend
call mvn clean package -DskipTests
jpackage --name marathon-backend --input target/ --main-jar Marathon-0.0.1-SNAPSHOT.jar --main-class org.springframework.boot.loader.launch.JarLauncher --type app-image --java-options "-Xms256m -Xmx1024m" --java-options "-Dfile.encoding=UTF-8" --win-console
:: move ./app/marathon.db to ./marathon.db
move "marathon-backend/app/marathon.db" "marathon-backend/marathon.db"