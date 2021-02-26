FROM java:8
VOLUME /tmp
ADD com-yc-gateway.jar /
MAINTAINER chengjiaxiong
ENTRYPOINT java ${PORT:=-Dserver.port=9999} ${JVM:=-Xms256m -Xmx256m -Xmn100m -Xss256k} -jar com-yc-gateway.jar
