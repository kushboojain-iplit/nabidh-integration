FROM amazoncorretto:8
RUN yum --disablerepo=AmazonCorretto install -y gettext nc
RUN curl -o wait-for.sh 'https://raw.githubusercontent.com/eficode/wait-for/v2.2.3/wait-for'
COPY package/docker/scripts/start.sh start.sh
RUN chmod +x start.sh
RUN chmod +x wait-for.sh
COPY feed-integration-webapp/target/nabidh-atomfeed.jar .
ENV DEBUG_OPTS="-agentlib:jdwp=transport=dt_socket,address=8002,server=y,suspend=n"
CMD [ "./start.sh" ]

