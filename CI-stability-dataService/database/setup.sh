#!/bin/bash
set -e

# for debugging
# echo `service mysql status`

echo '1.starting mysql....'
# starting mysql
service mysql start
sleep 3
echo `service mysql status`

echo '2.creating tables....'
# creating tables
mysql < /mysql/CreateTable.sql
echo '3.finishing creating tables....'

sleep 3

# echo `service mysql status`
echo `mysql container start successfully`

tail -f /dev/null