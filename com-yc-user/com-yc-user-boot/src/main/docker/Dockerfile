FROM java:8
VOLUME /tmp
ADD com-yc-user-core.jar com-yc-user-core.jar
RUN sh -c 'touch /com-yc-user-core.jar'
ENV TZ=chengjiaxiong
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS /com-yc-user-core.jar" ]