-- DROP TABLE IF EXISTS users cascade ;
-- DROP TABLE IF EXISTS category cascade ;
-- DROP TABLE IF EXISTS events cascade ;
-- drop table if exists compilations cascade ;
-- drop table if exists requests cascade ;
-- drop table if exists events_compilations cascade ;
drop table if exists comments cascade ;

create table if not exists users(
    id integer GENERATED BY DEFAULT AS IDENTITY primary key NOT NULL,
    email varchar(50) NOT NULL ,
    name varchar(50) not null unique
);

create table if not exists category(
    id integer generated by default as identity primary key not null ,
    name varchar(1000) not null unique
);

create table if not exists  events(
    id integer generated by default as identity primary key not null ,
    annotation varchar(1000),
    category_id integer,
    created_on TIMESTAMP,
    eventDate TIMESTAMP,
    description varchar(10000),
    initiator_id integer,
    paid boolean default false,
    participantLimit integer default (0),
    publishedOn TIMESTAMP,
    requestModeration boolean,
    title varchar (100),
    lat float,
    lon float,
    state varchar (10),
    constraint fk_events_to_users foreign key (initiator_id) references users (id),
    constraint fk_event_to_category foreign key (category_id) references category(id)
);

create table if not exists  compilations(
    id integer generated by default as identity primary key not null ,
    pinned boolean,
    title varchar(250)
);

create table if not exists  events_compilations (
    events_id integer REFERENCES events(id),
    compilations_id integer REFERENCES compilations(id)
);

create table if not exists  requests(
    id integer generated by default as identity primary key not null ,
    user_id integer,
    event_id integer,
    status varchar (10),
    created timestamp,
    constraint fk_request_to_users foreign key (user_id) references users(id),
    constraint fk_request_to_events foreign key (event_id) references events(id)
);

create table if not exists  comments(
    id integer generated by default as identity primary key not null ,
    author integer REFERENCES users(id),
    compilation_id integer REFERENCES compilations(id),
    header varchar (200),
    comment varchar,
    timestamp timestamp,
    modified varchar(20)
);