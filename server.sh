#!/bin/bash
if [ $1 == “run” ]
then
${TOMCAT_HOME}/bin/catalina.sh run
elif [ $1 == "start" ]
then
${TOMCAT_HOME}/bin/catalina.sh start
elif [ $1 == "restart" ]
then
${TOMCAT_HOME}/bin/catalina.sh stop && ${TOMCAT_HOME}/bin/catalina.sh run
elif [ $1 == "stop" ]
then
${TOMCAT_HOME}/bin/catalina.sh stop
elif [ $1 == "debug" ]
then
${TOMCAT_HOME}/bin/catalina.sh jpda run
echo "only support run,restart,stop"
fi