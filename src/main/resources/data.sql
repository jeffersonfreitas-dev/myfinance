INSERT INTO bank (code, name) VALUES ('001', 'BANCO DO BRASIL S/A'); 
INSERT INTO bank (code, name) VALUES ('104', 'CAIXA ECONOMICA FEDERAL'); 

INSERT INTO bank_agence(agence, id_bank) VALUES('0122', 1);
INSERT INTO bank_agence(agence, id_bank) VALUES('0613', 2);

INSERT INTO company(name, document) VALUES ('UNIMED', '0000000000000');
INSERT INTO company(name, document) VALUES ('UNILAB', '1111111111111');

INSERT INTO company_account(code, account, id_company, id_bank_agence) VALUES ('13199', '129-5', 1, 1);
INSERT INTO company_account(code, account, id_company, id_bank_agence) VALUES ('13195', '4211-0', 2, 1);

INSERT INTO account_plan(name, credit) VALUES ('SALARIOS', 1);
INSERT INTO account_plan(name, credit) VALUES ('AGUA/LUZ/TELEFONE', 0);

INSERT INTO account_subplan(name, id_accountplan) VALUES ('SALARIOS UNIMED', 1);
INSERT INTO account_subplan(name, id_accountplan) VALUES ('AGUA/LUZ/TELEFONE MESSEJANA', 2);

INSERT INTO person(name, phone, document) VALUES ('UNIMED', '35827700', '11685526000179');
INSERT INTO person(name, phone, document) VALUES ('UNILAB', '35827700', '11685526000250');
