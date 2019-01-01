docker run -d \
  --name kibana \
  -e ELASTICSEARCH_URL=http://172.17.0.1:9200 \
  -v /etc/localtime:/etc/localtime \
  -p 5601:5601 \
  dockerhub-public.gongsibao.com/kibana/kibana-oss:6.3.2
  
#-v /home/work/docker/data/kibana/config/kibana.yml:/usr/share/kibana/config/kibana.yml \
#-e SERVER_NAME=kibana.cluster2.ged.gongsibao.com \
#docker.elastic.co  
  
  
#说明：
#172.17.0.1:9200-> http://172.17.0.1:9200