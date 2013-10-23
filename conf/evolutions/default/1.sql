# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table cv (
  id                        bigint auto_increment not null,
  filename                  varchar(255),
  person_username           varchar(255),
  constraint pk_cv primary key (id))
;

create table city (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  country_id                integer,
  constraint pk_city primary key (id))
;

create table country (
  id                        integer auto_increment not null,
  name                      varchar(255),
  nationality               varchar(255),
  constraint pk_country primary key (id))
;

create table options (
  id                        bigint auto_increment not null,
  birthday_confidential     tinyint(1) default 0,
  constraint pk_options primary key (id))
;

create table person (
  username                  varchar(255) not null,
  surname                   varchar(255),
  firstname                 varchar(255),
  birthday                  datetime,
  mail                      varchar(255),
  phone                     VARCHAR(20),
  skype                     varchar(255),
  description               TEXT,
  account_activated         tinyint(1) default 0,
  passw_id                  bigint,
  school_of_origin_id       bigint,
  current_school_id         bigint,
  nationality_id            integer,
  city_id                   bigint,
  photo_id                  bigint,
  options_id                bigint,
  secured_id                bigint,
  constraint pk_person primary key (username))
;

create table photo (
  id                        bigint auto_increment not null,
  filename                  varchar(255),
  constraint pk_photo primary key (id))
;

create table school (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  city_id                   bigint,
  constraint pk_school primary key (id))
;

create table secured (
  id                        bigint auto_increment not null,
  salt                      varchar(255),
  pass_hash                 VARCHAR(128),
  centrale_uid              VARCHAR(8),
  centrale_pass_hash        VARCHAR(128),
  constraint pk_secured primary key (id))
;

alter table cv add constraint fk_cv_person_1 foreign key (person_username) references person (username) on delete restrict on update restrict;
create index ix_cv_person_1 on cv (person_username);
alter table city add constraint fk_city_country_2 foreign key (country_id) references country (id) on delete restrict on update restrict;
create index ix_city_country_2 on city (country_id);
alter table person add constraint fk_person_passw_3 foreign key (passw_id) references secured (id) on delete restrict on update restrict;
create index ix_person_passw_3 on person (passw_id);
alter table person add constraint fk_person_schoolOfOrigin_4 foreign key (school_of_origin_id) references school (id) on delete restrict on update restrict;
create index ix_person_schoolOfOrigin_4 on person (school_of_origin_id);
alter table person add constraint fk_person_currentSchool_5 foreign key (current_school_id) references school (id) on delete restrict on update restrict;
create index ix_person_currentSchool_5 on person (current_school_id);
alter table person add constraint fk_person_nationality_6 foreign key (nationality_id) references country (id) on delete restrict on update restrict;
create index ix_person_nationality_6 on person (nationality_id);
alter table person add constraint fk_person_city_7 foreign key (city_id) references city (id) on delete restrict on update restrict;
create index ix_person_city_7 on person (city_id);
alter table person add constraint fk_person_photo_8 foreign key (photo_id) references photo (id) on delete restrict on update restrict;
create index ix_person_photo_8 on person (photo_id);
alter table person add constraint fk_person_options_9 foreign key (options_id) references options (id) on delete restrict on update restrict;
create index ix_person_options_9 on person (options_id);
alter table person add constraint fk_person_secured_10 foreign key (secured_id) references secured (id) on delete restrict on update restrict;
create index ix_person_secured_10 on person (secured_id);
alter table school add constraint fk_school_city_11 foreign key (city_id) references city (id) on delete restrict on update restrict;
create index ix_school_city_11 on school (city_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table cv;

drop table city;

drop table country;

drop table options;

drop table person;

drop table photo;

drop table school;

drop table secured;

SET FOREIGN_KEY_CHECKS=1;

