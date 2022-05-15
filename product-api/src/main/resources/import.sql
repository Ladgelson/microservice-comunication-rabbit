insert into CATEGORY (id, description) values (1000, 'Comic Books');
insert into CATEGORY (id, description) values (2000, 'Movies');
insert into CATEGORY (id, description) values (3000, 'Books');

insert into SUPPLIER (id, description) values (1000, 'Panini Comics');
insert into SUPPLIER (id, description) values (2000, 'Amazon');

insert into PRODUCT (id, name, fk_category, fk_supplier, quantity_available, created_date, modifield_date) values (1000, 'Crise nas Infinitas Terras', 1000, 1000, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into PRODUCT (id, name, fk_category, fk_supplier, quantity_available, created_date, modifield_date) values (2000, 'Interestelar', 2000, 2000, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);