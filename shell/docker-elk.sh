docker pull elasticsearch:5.6.4
docker pull kibana:5.6.4
docker pull logstash:5.6.4
docker pull mobz/elasticsearch-head:5

docker run --name es9502 -p 9502:9200 -p 9503:9300\
 -v /opt/docker/es/es1.yml:/usr/share/elasticsearch/config/elasticsearch.yml\
 -v /opt/docker/es/data1:/usr/share/elasticsearch/data\
 -d elasticsearch:5.6.4
  
docker run --name es9512 -p 9512:9200 -p 9513:9300\
 -v /opt/docker/es/es2.yml:/usr/share/elasticsearch/config/elasticsearch.yml\
 -v /opt/docker/es/data2:/usr/share/elasticsearch/data\
 -d elasticsearch:5.6.4  
  
docker run --name esh9501 -p 9501:9100 -d mobz/elasticsearch-head:5
  
#master-------------------------------------------------
 #集群名称 所有节点要相同
cluster.name: "es_netsharp"
#本节点名称
node.name: master
#作为master节点
node.master: true
#是否存储数据
node.data: true
# head插件设置
http.cors.enabled: true
http.cors.allow-origin: "*"
#设置可以访问的ip 这里全部设置通过
network.bind_host: 0.0.0.0
#设置节点 访问的地址 设置master所在机器的ip
network.publish_host: 172.17.0.10
#slave-------------------------------------------------
cluster.name: "es_netsharp"
#子节点名称
node.name: node
#不作为master节点
node.master: false
node.data: true

http.cors.enabled: true
http.cors.allow-origin: "*"

network.bind_host: 0.0.0.0
network.publish_host: 172.17.0.11
#设置master地址
discovery.zen.ping.unicast.hosts: [172.17.0.1]
#-------------------------------------------------------

#查看集群状态
curl http://localhost:9502/cat/health?v
curl http://localhost:9502/
curl http://localhost:9511/
curl http://localhost:9501/

#---------------------------------------------
#logstash
docker run -it --rm logstash:5.6.4 -e 'input { stdin { } } output { stdout { } }'

docker run --name logstash\
        -v /opt/docker/ns9101/logs:/opt/data/ns9101/logs\
        -v /opt/docker/ns9102/logs:/opt/data/ns9102/logs\
        -v /opt/docker/logstash/config-dir:/config-dir \
        -p 9540:9540\
        -d logstash:5.6.4\
        logstash -f /config-dir/logstash.conf

#---------------------------------------------
#kibana
docker run --name kibana5601 -e ELASTICSEARCH_URL=http://172.17.0.1:9502 -p 5601:5601 --network host -d kibana:5.6.4
curl http://localhost:5601

