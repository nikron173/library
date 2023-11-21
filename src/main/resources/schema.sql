create table author (
    id uuid not null default random_uuid(),
    name varchar(30) not null unique,
    primary key (id)
);
create table book (
    id uuid not null default random_uuid(),
    price numeric(38,2),
    author_id uuid not null,
    genre_id uuid not null,
    student_id uuid,
    title varchar(50) not null unique,
    primary key (id)
);
create table genre (
    id uuid not null default random_uuid(),
    name varchar(30) not null unique,
    primary key (id)
);
create table student (
    id uuid not null default random_uuid(),
    birth_day date not null,
    name varchar(255) not null,
    email varchar(255) not null unique,
    primary key (id)
);
alter table if exists book add constraint fk_author_id foreign key (author_id) references author;
alter table if exists book add constraint fk_genre_id foreign key (genre_id) references genre;
alter table if exists book add constraint fk_student_id foreign key (student_id) references student;