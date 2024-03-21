-- spring batch schema
create table if not exists batch_job_instance
(
    job_instance_id bigint       not null primary key,
    version         bigint,
    job_name        varchar(100) not null,
    job_key         varchar(32)  not null,
    constraint job_inst_un unique (job_name, job_key)
) engine=innodb;

create table if not exists batch_job_execution
(
    job_execution_id bigint not null primary key,
    version          bigint,
    job_instance_id  bigint not null,
    create_time      datetime(6) not null,
    start_time       datetime(6) default null,
    end_time         datetime(6) default null,
    status           varchar(10),
    exit_code        varchar(2500),
    exit_message     varchar(2500),
    last_updated     datetime(6),
    constraint job_inst_exec_fk foreign key (job_instance_id)
        references batch_job_instance (job_instance_id)
) engine=innodb;

create table if not exists batch_job_execution_params
(
    job_execution_id bigint       not null,
    parameter_name   varchar(100) not null,
    parameter_type   varchar(100) not null,
    parameter_value  varchar(2500),
    identifying      char(1)      not null,
    constraint job_exec_params_fk foreign key (job_execution_id)
        references batch_job_execution (job_execution_id)
) engine=innodb;

create table if not exists batch_step_execution
(
    step_execution_id  bigint       not null primary key,
    version            bigint       not null,
    step_name          varchar(100) not null,
    job_execution_id   bigint       not null,
    create_time        datetime(6) not null,
    start_time         datetime(6) default null,
    end_time           datetime(6) default null,
    status             varchar(10),
    commit_count       bigint,
    read_count         bigint,
    filter_count       bigint,
    write_count        bigint,
    read_skip_count    bigint,
    write_skip_count   bigint,
    process_skip_count bigint,
    rollback_count     bigint,
    exit_code          varchar(2500),
    exit_message       varchar(2500),
    last_updated       datetime(6),
    constraint job_exec_step_fk foreign key (job_execution_id)
        references batch_job_execution (job_execution_id)
) engine=innodb;

create table if not exists batch_step_execution_context
(
    step_execution_id  bigint        not null primary key,
    short_context      varchar(2500) not null,
    serialized_context text,
    constraint step_exec_ctx_fk foreign key (step_execution_id)
        references batch_step_execution (step_execution_id)
) engine=innodb;

create table if not exists batch_job_execution_context
(
    job_execution_id   bigint        not null primary key,
    short_context      varchar(2500) not null,
    serialized_context text,
    constraint job_exec_ctx_fk foreign key (job_execution_id)
        references batch_job_execution (job_execution_id)
) engine=innodb;

create table if not exists batch_step_execution_seq
(
    id         bigint  not null,
    unique_key char(1) not null,
    constraint unique_key_un unique (unique_key)
) engine=innodb;

insert into batch_step_execution_seq (id, unique_key)
select *
from (select 0 as id, '0' as unique_key) as tmp
where not exists(select * from batch_step_execution_seq);

create table if not exists batch_job_execution_seq
(
    id         bigint  not null,
    unique_key char(1) not null,
    constraint unique_key_un unique (unique_key)
) engine=innodb;

insert into batch_job_execution_seq (id, unique_key)
select *
from (select 0 as id, '0' as unique_key) as tmp
where not exists(select * from batch_job_execution_seq);

create table if not exists batch_job_seq
(
    id         bigint  not null,
    unique_key char(1) not null,
    constraint unique_key_un unique (unique_key)
) engine=innodb;

insert into batch_job_seq (id, unique_key)
select *
from (select 0 as id, '0' as unique_key) as tmp
where not exists(select * from batch_job_seq);
