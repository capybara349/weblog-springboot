#!/bin/bash
# ====================================================================
#  脚本名称: restart.sh
#  功能描述: 重启 Spring Boot 应用（weblog-web）
#  依赖: bash, java, ls, grep, kill, pgrep (或 ps)
# ====================================================================

set -e  # 遇到错误立即退出

# 1. 定位 Java
JAVA_CMD=$(command -v java 2>/dev/null || true)
if [ -z "${JAVA_CMD}" ]; then
    echo "错误：未找到 java 命令，请检查 PATH 或 JAVA_HOME。"
    exit 1
fi
echo "[INFO] 使用 Java: ${JAVA_CMD}"

# 2. 应用目录
APP_DIR="/app/weblog"
echo "[INFO] 应用目录: ${APP_DIR}"

# 3. 查找最新的 jar 文件（动态匹配，不关心版本号细节）
APP_JAR=$(ls -t ${APP_DIR}/weblog-web-*.jar 2>/dev/null | head -1)
if [ -z "${APP_JAR}" ]; then
    echo "[ERROR] 在 ${APP_DIR} 下未找到任何 weblog-web-*.jar 文件！"
    echo "当前目录内容："
    ls -l ${APP_DIR}
    exit 1
fi
echo "[INFO] 使用 jar 文件: ${APP_JAR}"

# 4. JVM 参数与 Spring 配置
JAVA_OPTS="-Xms300m -Xmx300m"
SPRING_PROFILES="prod"
PID_FILE="${APP_DIR}/app.pid"
STARTUP_LOG="${APP_DIR}/startup.log"

# 5. 获取进程 PID（精确匹配该 jar 的 java 进程）
function get_pid() {
    # 优先使用 ps，避免 pgrep 在不同环境下行为差异
    ps -ef | grep "java.*weblog-web.*\.jar" | grep -v grep | awk '{print $2}' | head -1
}

# 6. 停止应用（优雅→强制）
function stop_app() {
    local pid=$(get_pid)
    if [ -z "${pid}" ]; then
        echo "[INFO] 应用未运行，无需停止。"
        return 0
    fi

    echo "[INFO] 检测到运行中的 PID: ${pid}，尝试停止..."
    kill -15 ${pid} 2>/dev/null || true
    echo "[INFO] 已发送 SIGTERM 信号，等待进程退出..."

    local timeout=30
    local count=0
    while [ $count -lt $timeout ]; do
        # 每次检查前打印计数（便于调试）
        echo "[DEBUG] 检查进程是否还在，第 $((count+1)) 次..."
        local current_pid=$(get_pid)
        if [ -z "${current_pid}" ]; then
            echo "[INFO] 应用已优雅停止。"
            return 0
        fi
        sleep 1
        ((count++))
    done

    echo "[WARN] 优雅停止超时（${timeout}秒），强制终止进程 ${pid} ..."
    kill -9 ${pid} 2>/dev/null || true
    sleep 2
    if [ -z "$(get_pid)" ]; then
        echo "[INFO] 强制终止成功。"
    else
        echo "[ERROR] 无法终止进程 ${pid}，请手动检查。"
        exit 1
    fi
}

# 7. 启动应用（后台运行，并验证）
function start_app() {
    echo "[INFO] 启动应用..."
    cd ${APP_DIR}
    > ${STARTUP_LOG}

    # 后台启动，并将输出重定向到日志文件
    nohup ${JAVA_CMD} ${JAVA_OPTS} -jar ${APP_JAR} --spring.profiles.active=${SPRING_PROFILES} >> ${STARTUP_LOG} 2>&1 &
    local new_pid=$!
    echo "[INFO] Java 进程已启动，PID: ${new_pid}"

    # 等待几秒，检查进程是否存活
    sleep 5
    if kill -0 ${new_pid} 2>/dev/null; then
        echo "[INFO] 应用启动成功，PID: ${new_pid}"
        echo ${new_pid} > ${PID_FILE}
    else
        echo "[ERROR] 应用启动后立即退出，请检查启动日志："
        echo "========== 启动日志（${STARTUP_LOG}）=========="
        cat ${STARTUP_LOG}
        echo "============================================="
        exit 1
    fi
}

# 8. 主流程
echo "========== 开始重启 =========="
stop_app
sleep 2
start_app
echo "========== 重启完成 =========="