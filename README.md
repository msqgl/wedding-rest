# Wedding Rest #
## Technologies Used ##
- Java 8
- Maven 3
    - spark-core
    - mysql-connector-java
    - persistence-api
    - commons-lang3
    - commons-beanutils
    - gson
    - junit
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
	TOTAL_PRICE DECIMAL(6,2) NOT NULL,
	CONSUMED_PRICE DECIMAL(6,2),
	PRIMARY KEY (ID_GIFT)
);

CREATE TABLE GIFTMSG (
	ID_MSG INT NOT NULL AUTO_INCREMENT,
	ID_GIFT INT,
	MSG VARCHAR(256),
	SENDER VARCHAR(256) NOT NULL,
	AMOUNT DECIMAL(6,2) NOT NULL,
	PRIMARY KEY (ID_MSG),
);
```