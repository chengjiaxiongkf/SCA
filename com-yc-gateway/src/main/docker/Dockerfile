FROM java:8
ADD com-yc-gateway.jar com-yc-gateway.jar
MAINTAINER chengjiaxiong
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS /com-yc-gateway.jar" ]