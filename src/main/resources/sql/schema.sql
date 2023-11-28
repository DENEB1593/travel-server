-- 테이블 초기화
drop table if exists users;
drop table if exists plan;
drop table if exists expense;
drop table if exists travel;

-- 테이블 생성

-- users
create table if not exists users
(
    id          bigint       not null auto_increment    comment '사용지ID',
    auth_id     varchar(255) not null                   comment '인증사ID',
    nickname    varchar(255)                            comment '닉네임',
    email       varchar(255) not null                   comment '이메일',
    provider    varchar(20)  not null                   comment '인증 제공자',
    last_login_at  datetime(6)                          comment '마지막로그인일자',
    created_at  datetime(6)  not null                   comment '사용자가입일자',
    modified_at datetime(6)  not null                   comment '사용자정보수정일자',
    deleted_at  datetime(6)                             comment '사용자탈퇴일자',
    primary key (id)
) engine = InnoDB default charset=utf8mb4 collate=utf8mb4_unicode_ci;

-- travel
create table if not exists travel
(
    id          bigint       not null auto_increment    comment '여행ID',
    title       varchar(255) not null                   comment '여행명',
    nation      varchar(3)   not null                   comment '국가코드',
    start_at    datetime(6)                             comment '여행시작일자',
    end_at      datetime(6)                             comment '여행종료일자',
    thumbnail   text                                    comment '썸네일URL',
    created_at  datetime(6)  not null                   comment '여행생성일자',
    modified_at datetime(6)  not null                   comment '여행수정일자',
    deleted_at  datetime(6)                             comment '여행삭제일자',
    primary key (id)
) engine = InnoDB default charset=utf8mb4 collate=utf8mb4_unicode_ci;


-- plan
create table if not exists plan
(
    id          bigint       not null auto_increment   comment '계획ID',
    title       varchar(255) not null                  comment '계획명',
    memo        varchar(255)                           comment '메모',
    start_at    datetime(6)  not null                  comment '계획시작일자',
    end_at      datetime(6)  not null                  comment '계획종료일자',
    travel_id   bigint                                 comment '여행ID',
    created_at  datetime(6)  not null                  comment '계획생성일자',
    modified_at datetime(6)  not null                  comment '계획수정일자',
    deleted_at  datetime(6)                            comment '계획삭제일자',
    primary key (id),
    constraint plan_travel_id_fk2 foreign key (travel_id) references travel (id)
) engine = InnoDB default charset=utf8mb4 collate=utf8mb4_unicode_ci;

-- expense
create table if not exists expense
(
    id          bigint         not null auto_increment  comment '지출아이디',
    amt         decimal(38, 2) not null                 comment '지출액',
    spend_at    datetime(6)    not null                 comment '지출일자',
    travel_id   bigint                                  comment '여행ID',
    created_at  datetime(6)                             comment '지출생성일자',
    modified_at datetime(6)                             comment '지출수정일자',
    deleted_at  datetime(6)                             comment '지출삭제일자',
    primary key (id),
    constraint expense_travel_id_fk foreign key (travel_id) references travel(id)
) engine = InnoDB default charset=utf8mb4 collate=utf8mb4_unicode_ci;
