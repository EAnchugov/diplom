DROP TABLE IF EXISTS users cascade ;
DROP TABLE IF EXISTS category cascade ;
DROP TABLE IF EXISTS events cascade ;
drop table if exists compilations cascade ;

create table users(
    id integer GENERATED BY DEFAULT AS IDENTITY primary key NOT NULL,
    email varchar(50) NOT NULL ,
    name varchar(50) not null
);

create table category(
    id integer generated by default as identity primary key not null ,
    name varchar(255) not null unique
);

create table events(
    id integer generated by default as identity not null,
    annotation varchar(255) not null,
    category integer references category(id),
    createdOn TIMESTAMP,
    eventDate TIMESTAMP,
    description varchar(1000),
    initiator integer,
    paid boolean default false,
    participantLimit integer default (0),
    publishedOn TIMESTAMP,
    requestModeration boolean default true,
    title varchar (100),
    lat float,
    lon real,
    state varchar (10),
    constraint fk_events_to_users foreign key (initiator) references users (id),
    constraint fk_event_to_category foreign key (category) references category(id)
);
create table compilations(
    id integer generated by default as identity not null,
    pinned boolean,
    title varchar(250)
);