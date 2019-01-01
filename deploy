workdir=/root/src/netsharp
tomcat=/root/opt/apache-tomcat-8.5.29
confdir=/root/deploy

rm $tomcat/webapps/ROOT.war
rm -rf $tomcat/webapps/ROOT
sh $tomcat/bin/shutdown.sh

cd $workdir
git reset --hard origin/master
git clean -fd
git pull

#rm -rf $workdir/gradle.properties
rm -rf $workdir/netsharp-web/src/main/resources/dev/conf/configuration.properties
rm -rf $workdir/netsharp-web/src/main/resources/dev/conf/database.properties
rm -rf $workdir/netsharp-test/src/test/resources/dev/conf/configuration.properties
rm -rf $workdir/netsharp-test/src/test/resources/dev/conf/database.properties

cp $confdir/configuration.properties $workdir/netsharp-web/src/main/resources/dev/conf/
cp $confdir/database.properties $workdir/netsharp-web/src/main/resources/dev/conf/
cp $confdir/configuration.properties $workdir/netsharp-test/src/test/resources/dev/conf
cp $confdir/database.properties $workdir/netsharp-test/src/test/resources/dev/conf
#cp $confdir/gradle.properties $workdir/

gradle war
cp $workdir/netsharp-web/build/libs/netsharp-web-2.7.8-SNAPSHOT.war $tomcat/webapps/ROOT.war
sh $tomcat/bin/startup.sh
