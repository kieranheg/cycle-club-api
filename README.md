**Docker**

MS SQL dB and JDBC driver v8.0.12

Install & Start MS SQL container
```
$ docker run -p 3306:3306 -d --name mysql -e MYSQL_ROOT_PASSWORD=password mysql/mysql-server
```
Log into MySQL within the docker container using the docker exec command:
```
$ docker exec -it mysql bash
bash-4.2# mysql -uroot -ppassword
mysql>
```
`mssql` Database is `cycling_club` and login is `admin/password` 

Make dB Accessible from Mac
```
mysql> CREATE USER 'admin'@'%' IDENTIFIED BY 'password';
mysql> GRANT ALL PRIVILEGES ON * . * TO 'admin'@'%';
```

Remove SHA error
```
ALTER USER 'admin'@'%' IDENTIFIED WITH mysql_native_password BY 'password';
```

Ref https://dzone.com/articles/docker-for-mac-mysql-setup




