#mvn clean install
#cd target
#mkdir dependency
#cd dependency
#jar -xf ../*.jar
#cd ..
#cd ..
#echo "zikassssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss"
#docker login

docker build -t zikas1996/annonces:latest .
docker push zikas1996/annonces:latest
#docker run -d -p 8055:8080 zikas1996/annonces:latest
