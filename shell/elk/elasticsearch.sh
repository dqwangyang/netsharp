docker run --name es9200 -p 9200:9200 -p 9300:9300 \
-d dockerhub-public.gongsibao.com/elasticsearch/elasticsearch-oss:6.3.2
 
 
  -v /opt/docker/es/es1.yml:/usr/share/elasticsearch/config/elasticsearch.yml\
 -v /opt/docker/es/data1:/usr/share/elasticsearch/data\
 #docker.elastic.co  

max file descriptors [4096] for elasticsearch process is too low, increase to at least [65536]
# vi /etc/security/limits.conf
增加
* soft nofile 65536
* hard nofile 131072
* soft nproc 2048
* hard nproc 4096
切换用户生效

https://www.cnblogs.com/XiongMaoMengNan/p/8073852.html
max virtual memory areas vm.max_map_count [65530] is too low, increase to at least [262144]
# vi /etc/sysctl.conf
增加
vm.max_map_count=655360
# sysctl -p




#--------------------------------------------------------------------------
#elasticsearch.yml默认配置
cluster.name: "docker-cluster"
network.host: 0.0.0.0

# minimum_master_nodes need to be explicitly set when bound on a public IP
# set to 1 to allow single node clusters
# Details: https://github.com/elastic/elasticsearch/pull/17288
discovery.zen.minimum_master_nodes: 1

#jvm.options 默认1G，需要修改
-Xms2g
-Xmx2g
