export MINIO_ROOT_USER=admin
export MINIO_ROOT_PASSWORD=wu82293382
minio server -address ":9009" -console-address ":50000" ~/data

cd fisco-3.0.0-rc1
bash nodes/127.0.0.1/stop_all.sh
bash nodes/127.0.0.1/start_all.sh
