#!/bin/bash 
OWN_NAME=setantenv.sh

if [ "$0" == "./$OWN_NAME" ]; then
	echo * Please call as ". ./$OWN_NAME", not ./$OWN_NAME !!!---
	echo * Also please DO NOT set back the executable attribute
	echo * On this file. It was cleared on purpose.
	
	chmod -x ./$OWN_NAME
	exit
fi
PLATFORM_HOME=`pwd`
export -p PLATFORM_HOME
export -p ANT_OPTS="-Xmx512m -Dfile.encoding=UTF-8"
export -p ANT_HOME=$PLATFORM_HOME/apache-ant-1.9.1
chmod +x "$ANT_HOME/bin/ant"
export -p PATH=$ANT_HOME/bin:$PATH

export -p TOMCAT_HOME=$PLATFORM_HOME/apache-tomcat-8.0.35
export -p PATH=$TOMCAT_HOME/bin:$PATH

if [ "$1" == "wx" ]
then
cp ./env/local-$1.properties ./env/local.properties
elif [ "$1" == "dev" ]
then
cp ./env/local-$1.properties ./env/local.properties
elif [ "$1" == "qas" ]
then
cp ./env/local-$1.properties ./env/local.properties
elif [ "$1" == "prd" ]
then
cp ./env/local-$1.properties ./env/local.properties
else
cp ./env/local-local.properties ./env/local.properties
fi




