
#update yum.repos.d
#cp /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.bak
#mv /opt/init/Centos-7.repo /etc/yum.repos.d/CentOS-Base.repo
#yum install wget -y

#下面代码在阿里云centos7上测试通过

export initpath=/opt
cd $initpath

#----------------------------------
#jdk
wget http://download.oracle.com/otn-pub/java/jdk/8u181-b13/96a7b8442fe848ef90c96a2fad6ed6d1/jdk-8u181-linux-x64.tar.gz --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie"
tar -xzvf jdk-8u181-linux-x64.tar.gz
rm -rf jdk-8u181-linux-x64.tar.gz

#----------------------------------
#tomcat
wget http://mirrors.hust.edu.cn/apache/tomcat/tomcat-8/v8.5.32/bin/apache-tomcat-8.5.32.tar.gz
tar -xzvf apache-tomcat-8.5.32.tar.gz
rm -rf apache-tomcat-8.5.32.tar.gz

#----------------------------------
#gradle
wget http://services.gradle.org/distributions/gradle-4.1-all.zip
yum install zip -y
yum install unzip -y
unzip gradle-4.1-all.zip
rm -rf gradle-4.1-all.zip

#----------------------------------
#/etc/profile
export JAVA_HOME=$initpath/jdk1.8.0_181
export JRE_HOME=$JAVA_HOME/jre
export TOMCAT_HOME=$initpath/apache-tomcat-8.5.32
export GRADLE_HOME=/$initpath/gradle-4.1

echo "" >> /etc/profile
echo "export JAVA_HOME=$initpath/jdk1.8.0_181" >> /etc/profile
echo "export JRE_HOME=$JAVA_HOME/jre" >> /etc/profile
echo "export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib" >> /etc/profile
echo "export TOMCAT_HOME=$TOMCAT_HOME" >> /etc/profile
echo "export GRADLE_HOME=$GRADLE_HOME" >> /etc/profile
echo "export PATH=$PATH:$JAVA_HOME/bin:$GRADLE_HOME/bin" >> /etc/profile
source /etc/profile
java -version
gradle

#----------------------------------
#git
yum install git -y
ssh-keygen
cd ~
cat .ssh
cat id_rsa.pub


