rm -rf ./libs/*

projects=("netsharp-core" "netsharp-basebiz" "netsharp-basebiz-web" "netsharp-cache-base" "netsharp-cache-plugin" "netsharp-cache-service" "netsharp-communication" "netsharp-panda" "netsharp-persistence" "netsharp-scrum" "netsharp-weixin" "weixin-sdk-mp" "weixin-sdk-ep" "weixin-sdk-tp");

for project in ${projects[*]}; 
do 
#echo "compile files('src/lib/$project-3.6.0-SNAPSHOT.jar')"
#echo $project;
cd $project
gradle jar
cp ./build/libs/*.jar ../libs/
cd ..
done;