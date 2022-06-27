create database sprint_2;
use sprint_2;
create table criminal_case_list
(
    `id`                mediumtext                    not null,
    `created_date`      varchar(30)                   not null,
    `modified_date`     varchar(30)                   not null,
    `number`            varchar(10)                   not null
        primary key,
    `case_type`         varchar(30)                   not null,
    `description`       varchar(5000) charset utf8mb3 not null,
    `status`            varchar(30)                   not null,
    `notes`             varchar(2000) charset utf8mb3 null,
    `lead_investigator` varchar(20)                   not null
);

create index criminal_case_list_detective_list_badge_number_fk
    on criminal_case_list (lead_investigator);

create table detective_list
(
    `id`              mediumtext                  not null,
    `modified_date`   varchar(30)                 not null,
    `user_name`       varchar(10)                 not null,
    `first_name`      varchar(20) charset utf8mb3 not null,
    `last_name`       varchar(20) charset utf8mb3 not null,
    `password`        varchar(15)                 not null,
    `hiring_date`     varchar(30)                 not null,
    `badge_number`    varchar(20)                 not null
        primary key,
    `rank`          varchar(20)                 not null,
    `armed`           tinyint(1)                  not null,
    `employee_status` varchar(20)                 not null,
    constraint detective_list_badge_number_uindex
        unique (badge_number),
    constraint detective_list_user_name_uindex
        unique (user_name)
);

create table case_detective
(
    `case_number`            varchar(10) not null,
    `detective_badge_number` varchar(20) not null,
    constraint case_detective_criminal_case_list_number_fk
        foreign key (case_number) references criminal_case_list (number)
            on update cascade on delete cascade,
    constraint case_detective_detective_list_badge_number_fk
        foreign key (detective_badge_number) references detective_list (badge_number)
            on update cascade on delete cascade
);

create table storages
(
    `id`            mediumtext  not null,
    `version`       int         not null,
    `create_time`   varchar(20) not null,
    `modified_time` varchar(20) not null,
    `name`          varchar(30) not null,
    `location`      varchar(50) not null
);