# create database simple_rpa;
# use simple_rpa;

CREATE TABLE work_statements
(
    statement_id   int         NOT NULL AUTO_INCREMENT,
    robot_id       int         NOT NULL,
    email          varchar(50) NOT NULL,
    statement_name varchar(20) NOT NULL,
    contents       varchar(45) NOT NULL,
    deleted        tinyint(1)  NOT NULL DEFAULT '0',
    scheduler_cron varchar(45)          DEFAULT NULL COMMENT '크론표현식',
    is_active      tinyint(1)           DEFAULT '0',
    created_at     datetime(6) NOT NULL DEFAULT (CURRENT_DATE),
    updated_at     datetime(6)          DEFAULT NULL,
    PRIMARY KEY (statement_id),
    UNIQUE KEY statementid_UNIQUE (statement_id),
    CONSTRAINT robot_id FOREIGN KEY (robot_id) REFERENCES robots (robot_id),
    UNIQUE KEY email_UNIQUE (email)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

drop table work_statements;

CREATE TABLE robots
(
    robot_id   int         NOT NULL AUTO_INCREMENT,
    robot_name varchar(20) NOT NULL,
    ip         varchar(20) NOT NULL,
    port_num   int         NOT NULL,
    user       varchar(20) NOT NULL,
    password   varchar(20) NOT NULL,
    running    tinyint(1)  NOT NULL default false,
    deleted    tinyint(1)  NOT NULL default false,
    created_at datetime(6) NOT NULL,
    updated_at datetime(6)          DEFAULT NULL,
    PRIMARY KEY (`robot_id`),
    UNIQUE KEY `robotid_UNIQUE` (`robot_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

drop table robots;

INSERT INTO work_statements(statement_id, robot_id, email, statement_name, contents, scheduler_cron)
VALUES (1, 1, 'test@gmail.com', 'test', 'ls -al', '0/10 * * * * ?');