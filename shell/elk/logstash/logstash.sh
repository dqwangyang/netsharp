docker run -d \
  --name logstash \
  -v /etc/localtime:/etc/localtime \
  -v /opt/docker/logstash/pipeline/:/usr/share/logstash/pipeline/ \
  -v /opt/docker/logstash/patterns/:/usr/share/logstash/patterns/ \
  -p 5044:5044 \
dockerhub-public.gongsibao.com/logstash/logstash-oss:6.3.2
  
#docker.elastic.co
DATE_CN %{YEAR}[./-]%{MONTHNUM}[./-]%{MONTHDAY}
DATESTAMP_CN %{DATE_CN} %{TIME}