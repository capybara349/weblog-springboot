#!/bin/bash
# ====================================================================
#  脚本名称: restart.sh
#  功能描述: 重启 Spring Boot 应用（weblog-web）
#  动态获取 Java 路径
# ====================================================================

# 1. 动态获取 Java 绝对路径
JAVA_CMD=$(command -v java 2>/dev/null)
if [ -z "${JAVA_CMD}" ]; then
    echo "错误：未找到 java 命令，请确保 JAVA_HOME 已配置且 java 在 PATH 中。"
    exit 1
fi
echo "使用 Java: ${JAVA_CMD}"

# 2. 应用配置
APP_NAME="weblog-web-0.0.1-SNAPSHOT.jar"
APP_PATH="/app/weblog/${APP_NAME}"
JAVA_OPTS="-Xms300m -Xmx300m"
SPRING_PROFILES="prod"
PID_FILE="/app/weblog/app.pid"
STARTUP_LOG="/app/weblog/startup.log"

# 3. 进程检测函数（精确匹配 java 进程）
function get_pid() {
    pgrep -f "java.*${APP_NAME}" 2>/dev/null
    # 若 pgrep 不可用，可使用 ps：
    # ps -ef | grep "java.*${APP_NAME}" | grep -v grep | awk '{print $2}'
}

# 4. 停止应用
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

# 5. 启动应用
function start_app() {
    echo "启动应用..."
    cd /app/weblog
    > ${STARTUP_LOG}
    nohup ${JAVA_CMD} ${JAVA_OPTS} -jar ${APP_PATH} --spring.profiles.active=${SPRING_PROFILES} >> ${STARTUP_LOG} 2>&1 &
    local new_pid=$!
    echo "Java 进程已启动，PID: ${new_pid}"

    # 等待几秒检查进程是否存活
    sleep 5
    if kill -0 ${new_pid} 2>/dev/null; then
        echo "应用启动成功，PID: ${new_pid}"
        echo ${new_pid} > ${PID_FILE}
    else
        echo "错误：应用启动后立即退出，请检查启动日志："
        echo "========== 启动日志（${STARTUP_LOG}）=========="
        cat ${STARTUP_LOG}
        echo "============================================="
        exit 1
    fi
}

# 6. 主流程
echo "========== 开始重启 ${APP_NAME} =========="
stop_app
sleep 2
start_app
echo "========== 重启完成 =========="