-- ************************************** RESTRIÇÕES DE APLICAÇÃO ************************************** --

-- ** Tabela iva **
-- Reprovado, pois o iva não pode ser menor do que 0
INSERT INTO iva(codIva, percentagemIva) 
VALUES (100, -1);
-- Reprovado, pois o iva não pode ser maior do que 100
INSERT INTO iva(codIva, percentagemIva) 
VALUES (100, 101);
-- Aprovado
INSERT INTO iva(codIva, percentagemIva) 
VALUES (100, 100);
-- Aprovado
INSERT INTO iva(codIva, percentagemIva) 
VALUES (101, 1);

-- ** Tabela produto **
-- Reprovado, pois o valor do produto não pode ser menor do que 0
INSERT INTO produto(codProduto, codIva, nome, valorProduto) 
VALUES(100, 100, 'Flores Teste 0', -1);
-- Aprovado
INSERT INTO produto(codProduto, codIva, nome, valorProduto) 
VALUES(100, 100, 'Flores Teste 1', 0);
-- Aprovado
INSERT INTO produto(codProduto, codIva, nome, valorProduto) 
VALUES(101, 100, 'Flores Teste 2', 49.99);

-- ** Tabela cliente **
-- Reprovado, pois o valor do numeroFiscal não pode ser menor do que 100000000
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond) 
VALUES (100, 1, 'Teste', 10000000, 'teste@gmail.com', 15000);
-- Reprovado, pois o valor do numeroFiscal não pode ser maior do que 999999999
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond) 
VALUES (100, 1, 'Teste', 1000000000, 'teste@gmail.com', 15000);
-- Aprovado
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond) 
VALUES (100, 1, 'Teste', 100000000, 'teste@gmail.com', 15000);

-- Reprovado, pois o e-mail tem um formato inválido (falta indicar o '@algumacoisa' e o '.algumacoisa', por esta ordem)
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond) 
VALUES (101, 1, 'Teste 2', 100000001, 'teste_email', 15000);
-- Reprovado, pois o e-mail tem um formato inválido (falta indicar o '.algumacoisa' após o '@hotmail')
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond) 
VALUES (101, 1, 'Teste 2', 100000001, 'testemail@hotmail', 15000);
-- Reprovado, pois o e-mail tem um formato inválido (falta indicar o '@algumacoisa' antes do '.com')
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond) 
VALUES (101, 1, 'Teste 2', 100000001, 'testemail.com', 15000);
-- Aprovado
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond) 
VALUES (101, 1, 'Teste 2', 100000001, 'testemail@hotmail.com', 15000);

-- Reprovado, pois o plafond não pode ser menor ou igual a 0
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond) 
VALUES (102, 1, 'Teste 3', 100000002, 'novoteste@hotmail.com', -1);
-- Reprovado, pois o plafond não pode ser menor ou igual a 0
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond) 
VALUES (102, 1, 'Teste 3', 100000002, 'novoteste@hotmail.com', 0);
-- Aprovado
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond) 
VALUES (102, 1, 'Teste 3', 100000002, 'novoteste@hotmail.com', 1);

-- Reprovado, pois o valor do campo numeroEncomendas tem de ser maior ou igual a 0
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond, numeroEncomendas) 
VALUES (103, 1, 'Teste 4', 1000000003, 'testedois@gmail.com', 15000, -1);
-- Reprovado, pois o valor do campo valorTotalEncomendas tem de ser maior ou igual a 0
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond, valorTotalEncomendas) 
VALUES (103, 1, 'Teste 4', 1000000003, 'testedois@gmail.com', 15000, -1);
-- Aprovado
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond, numeroEncomendas) 
VALUES (103, 1, 'Teste 4', 100000003, 'testedois@gmail.com', 15000, 0);
-- Aprovado
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond, valorTotalEncomendas) 
VALUES (104, 1, 'Teste 4_2', 100000004, 'testetres@gmail.com', 15000, 0);
-- Aprovado
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond, numeroEncomendas, valorTotalEncomendas) 
VALUES (105, 1, 'Teste 4_3', 100000005, 'testequatro@gmail.com', 15000, 0, 0);

-- ** Tabela hub **
-- Reprovado, pois o código do hub tem de corresponder ao seguinte formato 'CTnnn' m que n é um número de 0 a 9, sendo apenas o primeiro número obrigatório
INSERT INTO hub(codHub, codTipoHub, latitude, longitude) VALUES ('CT', 2, 38.4549, -3.3783);
-- Reprovado, pois o código do hub tem de corresponder ao seguinte formato 'CTnnn' m que n é um número de 0 a 9, sendo apenas o primeiro número obrigatório
INSERT INTO hub(codHub, codTipoHub, latitude, longitude) VALUES ('AT100', 2, 38.4549, -3.3783);
-- Aprovado
INSERT INTO hub(codHub, codTipoHub, latitude, longitude) VALUES ('CT100', 2, 38.4549, -3.3783);

-- ** Tabela encomenda **
-- Reprovado, pois a data de encomenda não pode ser maior do que a data de vencimento
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda, dataVencimento, dataPagamento, dataEntrega)
VALUES (100, 1, 1, 'CT1', TO_DATE('2/1/2023', 'dd/mm/yy'), TO_DATE('1/1/2023', 'dd/mm/yy'), TO_DATE('18/1/2023', 'dd/mm/yy'), TO_DATE('26/1/2023', 'dd/mm/yy'));
-- Reprovado, pois a data de encomenda não pode ser maior do que a data de pagamento
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda, dataVencimento, dataPagamento, dataEntrega)
VALUES (100, 1, 1, 'CT1', TO_DATE('2/1/2023', 'dd/mm/yy'), TO_DATE('15/1/2023', 'dd/mm/yy'), TO_DATE('1/1/2023', 'dd/mm/yy'), TO_DATE('26/1/2023', 'dd/mm/yy'));
-- Reprovado, pois a data de encomenda não pode ser maior do que a data de entrega
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda, dataVencimento, dataPagamento, dataEntrega)
VALUES (100, 1, 1, 'CT1', TO_DATE('2/1/2023', 'dd/mm/yy'), TO_DATE('15/1/2023', 'dd/mm/yy'), TO_DATE('18/1/2023', 'dd/mm/yy'), TO_DATE('1/1/2023', 'dd/mm/yy'));
-- Aprovado
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda, dataVencimento, dataPagamento, dataEntrega)
VALUES (100, 1, 1, 'CT1', TO_DATE('1/1/2023', 'dd/mm/yy'), TO_DATE('15/1/2023', 'dd/mm/yy'), TO_DATE('18/1/2023', 'dd/mm/yy'), TO_DATE('26/1/2023', 'dd/mm/yy'));

-- ** Tabela fichaTecnica **
-- Reprovado, pois a quantidade não pode ser menor do que 0
INSERT INTO linhaFichaTecnica(codFatorProducao, codFichaTecnicaTipoComposicao, quantidade) 
VALUES (1, 2, -1);
-- Aprovado
INSERT INTO linhaFichaTecnica(codFatorProducao, codFichaTecnicaTipoComposicao, quantidade) 
VALUES (1, 2, 1);

-- ** Tabela registoEncomenda **
-- Reprovado, pois a quantidade tem de ser maior do que 0
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) 
VALUES (2, 1, 0, 49.99, 6);
-- Reprovado, pois o valor do produto tem de ser maior ou igual a 0
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) 
VALUES (2, 1, 5, -0.1, 6);
-- Reprovado, pois o valor do iva tem de estar entre 0 e 100
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) 
VALUES (2, 1, 5, 49.99, -1);
-- Reprovado, pois o valor do iva tem de estar entre 0 e 100
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) 
VALUES (2, 1, 5, 49.99, 101);
-- Aprovado
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) 
VALUES (2, 1, 5, 49.99, 6);

-- ** tabela planoRega ** 
-- Reprovado, pois o valor da periodicidade não pode ser menor ou igual a 0
INSERT INTO planoRega(codPlanoRega, codRega, codEstacaoMeteorologica, periodicidade, tempoRega) 
VALUES (100, 1, 1, 0, 720);
-- Reprovado, pois o valor do tempoRega não pode ser menor ou igual a 0
INSERT INTO planoRega(codPlanoRega, codRega, codEstacaoMeteorologica, periodicidade, tempoRega) 
VALUES (100, 1, 1, 480, 0);
-- Aprovado
INSERT INTO planoRega(codPlanoRega, codRega, codEstacaoMeteorologica, periodicidade, tempoRega) 
VALUES (100, 1, 1, 480, 720);

-- ** tabela parcela **
-- Reprovado, pois o valor do campo area não pode ser menor ou igual a 0
INSERT INTO parcela(codParcela, codInstalacaoAgricola, designacao, area) 
VALUES (100, 1, 'Parcela da pereira', 0);
-- Arpovado
INSERT INTO parcela(codParcela, codInstalacaoAgricola, designacao, area) 
VALUES (100, 1, 'Parcela da pereira', 10);

-- ** tabela plantacao **
-- Reprovado, pois o valor do campo area não pode ser menor ou igual a 0
INSERT INTO plantacao(codPlantacao, codParcela, codCultura, dataInicio, dataFim, area) 
VALUES (100, 1, 1, TO_DATE('3/3/2023', 'dd/mm/yy'), TO_DATE('3/9/2023', 'dd/mm/yy'), 0);
-- Reprovado, pois a data de fim não pode ser antes da data de início
INSERT INTO plantacao(codPlantacao, codParcela, codCultura, dataInicio, dataFim, area) 
VALUES (100, 1, 1, TO_DATE('3/3/2023', 'dd/mm/yy'), TO_DATE('2/3/2023', 'dd/mm/yy'), 15);
-- Reprovado, pois a data de fim não pode ser antes da data de início
INSERT INTO plantacao(codPlantacao, codParcela, codCultura, dataInicio, dataFim, area) 
VALUES (100, 1, 1, TO_DATE('3/3/2023', 'dd/mm/yy'), TO_DATE('10/2/2023', 'dd/mm/yy'), 15);
-- Aprovado
INSERT INTO plantacao(codPlantacao, codParcela, codCultura, dataInicio, dataFim, area) 
VALUES (100, 1, 1, TO_DATE('3/3/2023', 'dd/mm/yy'), TO_DATE('3/9/2023', 'dd/mm/yy'), 15);

-- ** tabela colheita **
-- Reprovado, pois a quantidade da colheita não pode ser menor ou igual a 0
INSERT INTO colheita(codColheita, codPlantacao, codProduto, dataColheita, quantidade)
VALUES (100, 1, 1, TO_DATE('2/12/2022', 'dd/mm/yy'), 0);
-- Aprovado
INSERT INTO colheita(codColheita, codPlantacao, codProduto, dataColheita, quantidade)
VALUES (100, 1, 1, TO_DATE('2/12/2022', 'dd/mm/yy'), 5);