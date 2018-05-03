#!/bin/bash
DIR=/home/am/eclipse-workspace1/doc3may/
DIRA=$DIR/doc3may-annotations/
DIRP=$DIR/doc3may-processor/
DIRM2=/home/am/.m2/repository/
javac \
-nowarn \
-classpath \
$DIRA/target/doc3may-annotations-0.0.1-SNAPSHOT.jar:$DIRP/target/doc3may-processor-0.0.1-SNAPSHOT.jar:$DIRM2/com/google/guava/guava/25.0-jre/guava-25.0-jre.jar \
-processor \
io.github.amarcinkowski.doc3may.processor.NewIntentProcessor \
$DIRP/src/test/java/org/doc3may/processor/X.java
