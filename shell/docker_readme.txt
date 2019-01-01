docker run固定ip
--------------------------
启动 Docker的时候，用 --network 参数，可以指定网络类型，如：
docker run -itd --name test1 --network bridge --ip 172.17.0.10 centos:latest /bin/bash