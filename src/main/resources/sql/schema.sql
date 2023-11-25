-- 테이블 초기화
drop table if exists plan;
drop table if exists expense;
drop table if exists travel;

-- 테이블 생성
-- travel
create table if not exists travel
(
    id          bigint       not null auto_increment,
    nation      varchar(3)   not null,
    start_at    datetime(6),
    end_at      datetime(6),
    thumbnail   text,
    created_at  datetime(6)  not null,
    modified_at datetime(6)  not null,
    deleted_at  datetime(6),
    title       varchar(255) not null,
    primary key (id)
) engine = InnoDB;


-- plan
create table if not exists plan
(
    id          bigint       not null auto_increment,
    title       varchar(255) not null,
    memo        varchar(255),
    start_at    datetime(6)  not null,
    end_at      datetime(6)  not null,
    travel_id   bigint,
    created_at  datetime(6)  not null,
    modified_at datetime(6)  not null,
    deleted_at  datetime(6),
    primary key (id),
    constraint plan_travel_id_fk foreign key (travel_id) references travel (id)
) engine = InnoDB;


-- expense
create table if not exists expense
(
    id          bigint         not null auto_increment,
    amt         decimal(38, 2) not null,
    travel_id   bigint,
    created_at  datetime(6)    not null,
    modified_at datetime(6)    not null,
    deleted_at  datetime(6),
    primary key (id),
    constraint expense_travel_id_fk foreign key (travel_id) references travel(id)
) engine = InnoDB;
