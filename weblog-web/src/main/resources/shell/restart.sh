#!/bin/bash
set -x  # 开启调试输出
APP_NAME=weblog-web-0.0.1-SNAPSHOT.jar

pid=$(ps -ef | grep "$APP_NAME" | grep -v grep | awk '{print $2}')

if [ -n "$pid" ]; then
    echo "检测到服务已启动，pid 是 $pid"
    kill -9 $pid
    echo "已 kill 进程"
else
    echo "服务未启动"
fi

# 再次检查，若未启动则启动
sleep 2
pid=$(ps -ef | grep "$APP_NAME" | grep -v grep | awk '{print $2}')
if [ -z "$pid" ]; then
    echo "启动服务..."
    source /etc/profile
    nohup java -Xms300m -Xmx300m -jar /app/weblog/$APP_NAME --spring.profiles.active=prod > /dev/null 2>&1 &
    echo "服务已重新启动"
else
    echo "服务已经启动，pid=$pid"
fi