#!/bin/bash

export JAVA_HOME=/usr/java/jdk1.6.0_24
/root/591/hadoop-0.19.0/bin/hadoop jar /root/591/hadoop-0.19.0/build/airavat-streaming.jar -mapper "/root/591/jikesrvm-3.0.0/dist/prototype_ia32-linux/rvm -cp /root/591/Airavat/build/jar/Airavat.jar MapperWrapper MyMapper" -hadoopPath "/root/591/hadoop-0.19.0/bin/hadoop" -tempDirPath "/tmp/airavat_temp" -reducer "java -cp /root/591/Airavat/build/jar/Airavat.jar ReducerWrapper CountReducer 0 3 0.1" -numReduceTasks 1 -input $1 -output $2
