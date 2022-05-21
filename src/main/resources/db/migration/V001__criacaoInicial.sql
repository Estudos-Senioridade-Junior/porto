create table cliente (id bigint not null auto_increment,
nome varchar(100), primary key (id)) engine=innoDB;

create table conteiner (id bigint not null auto_increment, categoria integer, identificacao varchar(11),
status integer, tipo integer, cliente_id bigint, primary key (id)) engine=innoDB;

create table movimentacao (id bigint not null auto_increment, hora_fim datetime(6),
hora_inicio datetime(6), tipo integer, conteiner_id bigint, primary key (id)) engine=innoDB;

alter table conteiner add constraint conteiner_clientefk
foreign key (cliente_id) references cliente (id);

alter table movimentacao add constraint movimentacao_conteinerfk
foreign key (conteiner_id) references conteiner (id);