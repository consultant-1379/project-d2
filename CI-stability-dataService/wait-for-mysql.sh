#!/bin/sh
# wait-for-mysql.sh

set -e

host="$1"
shift

until mysql -h "$host" -u "root" -p; do
	>&2 echo "mysql is unavailable - sleeping"
	sleep 1
done

>&2 echo "Mysql is up - executing command"

exec "$@"