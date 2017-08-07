# Wedding Rest #
## Technologies Used ##
- Java 8
- Maven 3
    - spark-core 2.5
    - mysql-connector-java 6.0.6
    - persistence-api 1.0.2
    - commons-lang3 3.6
    - commons-beanutils 1.9.3
    - gson 2.8.1
    - junit 4.12
    - slf4j-simple 1.7.21
    - spring-core 4.3.9.RELEASE
    - spring-context 4.3.9.RELEASE
    - spring-jdbc 4.3.9.RELEASE
    - spring-test 4.3.9.RELEASE
    - guava 22.0
    - itext 2.1.7
- MySQL 5.6

## Configure Database ##
### Create new database and use it ###
```
create database wedding;
use wedding;
```

### Create new user and configure roles ###
```
CREATE USER w3dd1ng IDENTIFIED BY '9cEmpKmY';
grant usage on *.* to w3dd1ng@localhost identified by '9cEmpKmY';
grant all privileges on wedding.* to w3dd1ng@localhost;
```

### Create tables ###
```
CREATE TABLE GIFT (
	ID_GIFT INT NOT NULL AUTO_INCREMENT,
	TITLE VARCHAR(30) NOT NULL,
	DESCRIPTION VARCHAR(256) NOT NULL,
	IMG_PATH VARCHAR(256) NOT NULL,
	TOTAL_PRICE DECIMAL(10,2),
	CONSUMED_PRICE DECIMAL(10,2),
	INDEX_ORDER INT NOT NULL,
	PRIMARY KEY (ID_GIFT)
);

CREATE TABLE GIFTMSG (
	ID_MSG INT NOT NULL AUTO_INCREMENT,
	ID_GIFT INT,
	MSG VARCHAR(256),
	SENDER VARCHAR(256) NOT NULL,
	AMOUNT DECIMAL(10,2) NOT NULL,
	INSERT_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (ID_MSG)
);
```
