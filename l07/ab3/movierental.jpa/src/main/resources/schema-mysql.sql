create table movies (
    movie_id bigint not null auto_increment,
    movie_releasedate date,
    movie_rented bit,
    movie_title varchar(255),
    pricecategory_fk bigint,
    primary key (movie_id)
);

create table pricecategories (
    pricecategory_type varchar(31) not null,
    pricecategory_id bigint not null auto_increment,
    primary key (pricecategory_id)
);

create table rentals (
    rental_id bigint not null auto_increment,
    rental_rentaldate date,
    rental_rentaldays integer,
    movie_id bigint,
    user_id bigint,
    primary key (rental_id)
);

create table users (
    user_id bigint not null auto_increment,
    user_email varchar(255),
    user_firstname varchar(255),
    user_name varchar(255),
    primary key (user_id)
);