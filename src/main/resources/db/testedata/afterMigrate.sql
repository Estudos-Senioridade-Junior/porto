set foreign_key_checks = 0;

delete from cliente;
delete from movimentacao;
delete from conteiner;

set foreign_key_checks=1;

alter table cliente auto_increment=1;
alter table conteiner auto_increment=1;
alter table movimentacao auto_increment=1;

insert into cliente (nome) values ('Ana');
insert into cliente (nome) values ('Pedro');
insert into cliente (nome) values ('João');
insert into cliente (nome) values ('Maria');
insert into cliente (nome) values ('Patrick');
insert into cliente (nome) values ('Márcia');

insert into conteiner (categoria, identificacao, status, tipo, cliente_id) values
(0, "ABCD1234567",1,1,1);

insert into conteiner (categoria, identificacao, status, tipo, cliente_id) values
(1, "GDJG5478598",0,0,2);

insert into conteiner (categoria, identificacao, status, tipo, cliente_id) values
(1, "QUTR7843658",1,1,2);

insert into conteiner (categoria, identificacao, status, tipo, cliente_id) values
(0, "DGQR1756352",1,0,3);

insert into conteiner (categoria, identificacao, status, tipo, cliente_id) values
(1, "LLLL5789454",0,0,4);

