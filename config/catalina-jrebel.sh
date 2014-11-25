#!/bin/bash
export JAVA_OPTS="-javaagent:/usr/local/JRebel/jrebel.jar -Drebel.thymeleaf_plugin=true $JAVA_OPTS"
`dirname $0`/catalina.sh $@
