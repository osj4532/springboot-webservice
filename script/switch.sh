# 엔진엑스가 바라보는 스프링 부트를 최신 버전으로 변경

#!/usr/bin/env bash

ASBPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.bash

function switch_proxy(){
  IDLE_PORT=$(find_idle_port)

  echo "> 전환할 Port: $IDLE_PORT"
  echo "> Port 전환"
  echo 'set \$service_url http://127.0.0.1:${IDLE_PORT};' |
  sodo tee /etc/nginx/conf.d/service_url.inc

  echo "> 엔진엑스 Reload"
  sudo service nginx reload
}