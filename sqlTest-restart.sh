#/bin/bash
app=sqlTest-api
env=$1
PID=`ps -ef | grep java | grep $app |awk '{print $2}'`

if [ ! $env ]
  then
  echo "Please specify args 'test|prod'"
  exit
fi

if [ -n "$PID" ]; then
	#kill -15 $PID
	echo "before: $PID"

    kill -15 $PID
    #每5秒判断进程是否已经被杀掉，如果超过60秒没有被杀掉，强制杀
    i=5
    while [ $i -le 60 ];do
      sleep 5
      echo "===After $i seconds==="
      AfterPID=`ps -ef | grep java | grep $app |awk '{print $2}'`


      if [ ! ${AfterPID} ]; then
        echo "===$app already been killed==="
        break
      else
        if [ $i -lt 60 ];then
          echo "===$app is being killed==="
          i=$[i+5]
        else
          echo "==kill -9 start=="
	      kill -9 $PID
          echo "==kill -9 end=="
        fi
      fi
    done
  

   nohup  java -Dspring.profiles.active=$env -jar  /usr/local/app/sqlTest/$app.jar > /dev/null 2>&1 &
   sleep 3
   newPID=`ps -ef | grep java | grep $app |awk '{print $2}'`

   echo "now   : $newPID"
else
   echo "starting"
   nohup  java -Dspring.profiles.active=$env -jar  /usr/local/app/sqlTest/$app.jar > /dev/null 2>&1 &
fi
