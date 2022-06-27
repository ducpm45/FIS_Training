create table detectives
(
    id            bigint auto_increment
        primary key,
    created_date  datetime     null,
    modified_date datetime     null,
    version       int          null,
    armed         bit          null,
    badge_number  varchar(255) null,
    empl_status   varchar(255) null,
    first_name    varchar(255) null,
    hiring_date   datetime     null,
    last_name     varchar(255) null,
    password      varchar(128) null,
    `rank`        varchar(255) null,
    user_name     varchar(255) null,
    constraint UK_7x7r02cjuykwalbcl6a9nt0nc
        unique (user_name),
    constraint UK_cl1xn2jekbs0gxg2pjku83o8x
        unique (badge_number)
);

create table criminal_case
(
    id                bigint auto_increment
        primary key,
    created_date      datetime      null,
    modified_date     datetime      null,
    version           int           null,
    case_number       varchar(255)  null,
    case_status       varchar(255)  null,
    case_type         varchar(255)  null,
    detail_desc       varchar(5000) null,
    notes             varchar(3000) null,
    short_desc        varchar(1000) null,
    lead_detective_id bigint        null,
    constraint UK_4bgb2ykyoqbk57w34k6rivm5i
        unique (case_number),
    constraint FKq10pv8u7saef5as5ib0tt2das
        foreign key (lead_detective_id) references detectives (id)
);

create table case_detective
(
    case_id      bigint not null,
    detective_id bigint not null,
    primary key (case_id, detective_id),
    constraint FKjgg2sxton5tb4lelkaj58fch9
        foreign key (detective_id) references detectives (id),
    constraint FKq6rb11l03get7dexyxrbm1nf8
        foreign key (case_id) references criminal_case (id)
);

create table storages
(
    id            bigint auto_increment
        primary key,
    created_date  datetime     null,
    modified_date datetime     null,
    version       int          null,
    location      varchar(255) null,
    storage_name  varchar(255) null,
    constraint UK_srjfi2srq1cjv51k0a7jo54so
        unique (storage_name)
);

create table evidences
(
    id            bigint auto_increment
        primary key,
    created_date  datetime     null,
    modified_date datetime     null,
    version       int          null,
    archived      bit          null,
    item_name     varchar(255) null,
    notes         varchar(255) null,
    number        varchar(255) null,
    case_id       bigint       null,
    storage_id    bigint       null,
    constraint UK_j970i516nuhonm5bwwa72cj84
        unique (number),
    constraint FK2c5g63m476awntij0ti2uh18l
        foreign key (case_id) references criminal_case (id),
    constraint FKn9pq4gou97k3tt56mpogta9yi
        foreign key (storage_id) references storages (id)
);

create table track_entries
(
    id            bigint auto_increment
        primary key,
    created_date  datetime     null,
    modified_date datetime     null,
    version       int          null,
    date          datetime     null,
    reason        varchar(255) null,
    track_action  varchar(255) null,
    detective_id  bigint       null,
    evidence_id   bigint       null,
    constraint FKgvlrafg9ufjkj36358c8235nr
        foreign key (detective_id) references detectives (id),
    constraint FKtjuf85k6erbm9g952g5785mdy
        foreign key (evidence_id) references evidences (id)
);