FROM mysql:5.7

ENV MYSQL_ALLOW_EMPTY_PASSWORD yes

COPY setup.sh /mysql/setup.sh
COPY CreateTable.sql /mysql/CreateTable.sql

CMD ["sh", "/mysql/setup.sh"]