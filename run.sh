#!/bin/sh
mvn -s ./settings.xml -f pom.xml clean package
java -jar target/refrigerator-1.0.0.jar&echo $! > /root/pids/fridge_pid
wait
