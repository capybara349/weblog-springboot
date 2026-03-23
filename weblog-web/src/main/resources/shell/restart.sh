#!/bin/bash
# ====================================================================
#  脚本名称: restart.sh
#  功能描述: 重启 Spring Boot 应用（weblog-web）
#  使用方式: ./restart.sh
# ====================================================================

APP_NAME="weblog-web-0.0.1-SNAPSHOT.jar"
APP_PATH="/app/weblog/${APP_NAME}"
JAVA_OPTS="-Xms256m -Xmx256m"
SPRING_PROFILES="prod"
PID_FILE="/app/weblog/app.pid"   # 可选，记录 PID 便于管理

# 检测应用是否正在运行
function get_pid() {
    ps -ef | grep "${APP_NAME}" | grep -v grep | awk '{print $2}'
}

# 停止应用（优雅停止，超时强制 kill）
function stop_app() {
    local pid=$(get_pid)
    if [ -z "${pid}" ]; then
        echo "应用未运行，无需停止。"
        return 0
    fi

    echo "检测到运行中的 PID: ${pid}，尝试停止..."
    kill -15 ${pid}
    local timeout=30
    local count=0
    while [ $count -lt $timeout ]; do
        if [ -z "$(get_pid)" ]; then
            echo "应用已优雅停止。"
            return 0
        fi
        sleep 1
        ((count++))
    done

    echo "优雅停止超时，强制终止进程 ${pid} ..."
    kill -9 ${pid}
    sleep 2
    if [ -z "$(get_pid)" ]; then
        echo "强制终止成功。"
    else
        echo "错误：无法终止进程 ${pid}，请手动检查。"
        exit 1
    fi
}

# 启动应用
function start_app() {
    echo "启动应用..."
    cd /app/weblog
    source /etc/profile   # 确保 java 命令可用
    nohup java -jar ${APP_PATH} ${JAVA_OPTS} --spring.profiles.active=${SPRING_PROFILES} > /dev/null 2>&1 &
    local new_pid=$!
    echo "应用已启动，PID: ${new_pid}"
    echo ${new_pid} > ${PID_FILE}
}

# 主流程
echo "========== 开始重启 ${APP_NAME} =========="
stop_app
sleep 2
start_app
echo "========== 重启完成 =========="