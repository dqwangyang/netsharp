docker run -d \
  --name filebeat \
  -v /etc/localtime:/etc/localtime \
  -v /opt/docker/filebeat/filebeat.yml:/usr/share/filebeat/filebeat.yml \
  -v /opt/docker/filebeat/prospectors.d:/usr/share/filebeat/prospectors.d \
  -v /opt/docker/ns9101/logs/:/opt/docker/ns9101/logs/ \
  --privileged=true \
  -u root\
  dockerhub-public.gongsibao.com/beats/filebeat:6.3.2