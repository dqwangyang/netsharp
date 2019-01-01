#-----------------------------------------------------
#dockerruncentos.sh
if [ ! -n "$1" ] ;then
    echo "you have not input a port!"
    return
else
    echo "the port  is $1"
fi


container=ns$1
echo container : $container

docker stop $container
docker rm $container
rm -rf $container

mkdir /opt/docker/$container

docker run --name $container -p $1:8080 -v /opt/docker/$container/webapps:/usr/local/tomcat/webapps -v /opt/docker/$container/logs:/home/xufangbo/logs -d tomcat

echo ok

#-----------------------------------------------------
#tomcatdeploy.sh
if [ ! -n "$1" ] ;then
    echo "you have not input a port!"
    return
else
    echo "the port  is $1"
fi

container=ns$1
echo container : $container

#cd /home/xufangbo/gongsibao/git/netsharp
#gradle deployPanda

rm -rf /opt/docker/$container/webapps/ROOT.war
cp /home/xufangbo/gongsibao/git/netsharp/netsharp-web/build/libs/netsharp-web-3.1.6-SNAPSHOT.war /opt/docker/$container/webapps/ROOT.war

echo ok

