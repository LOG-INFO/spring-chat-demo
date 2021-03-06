## Chat demo

### 기술스택

- JDK 11.0.2
- Kotlin 1.3.72
- Spring Boot 2.3.3
- MySQL 8.0.21
- JPA + Hibernate
- Kafka
- WebSocket
- Docker

1. WSL2, Ubuntu 설치 및 설정
2. Docker for Windows 설치 및 설정
3. MySQL 컨테이너 실행 및 접속
  - `docker pull mysql/mysql-server:latest`
  - `docker run -p 3306:3307 --name=mysql_test -e MYSQL_ROOT_PASSWORD=test -d mysql/mysql-server:latest`
  - `docker run -p 3306:3306 --name=mysql -e MYSQL_ROOT_PASSWORD=test -d mysql/mysql-server:latest`
  - `apt-get install mysql-client`
  - `mysql -uroot -p`
    - `CREATE USER 'test'@'localhost' IDENTIFIED BY 'test';`
    - `GRANT ALL PRIVILEGES ON *.* TO 'test'@'localhost' WITH GRANT OPTION`;`
    - `CREATE USER 'userId'@'%' IDENTIFIED BY 'test';`
    - `GRANT ALL PRIVILEGES ON *.* TO 'test'@'%' WITH GRANT OPTION;`
    - `CREATE DATABASE test;`
  - MySQL Workbench로 접속
4. Kafka 컨테이너 실행 및 접속
  - https://github.com/wurstmeister/kafka-docker
  - `git clone`
  - `docker-compose up -d`
  - Add more brokers
    - `docker-compose scale kafka=3`

