DROP TABLE IF EXISTS endpoints;

create table endpoints(
    id BIGINT generated by default as identity not null ,
    app VARCHAR(100) not null ,
    uri VARCHAR(100) not null,
    ip varchar(15) not null ,
    stamp TIMESTAMP
);