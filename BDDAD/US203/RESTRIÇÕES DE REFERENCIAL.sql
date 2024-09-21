-- ************************************** RESTRIÇÕES DE REFERENCIAL ************************************** --
-- Tabela Produto
-- Reprovado, pois o código do iva não existe na base de dados
INSERT INTO produto(codProduto, codIva, nome, valorProduto) VALUES(408, 5, 'Sumos', 33.99);
-- Aprovado
INSERT INTO produto(codProduto, codIva, nome, valorProduto) VALUES(406, 2, 'Sumosa', 35.99);

-- Tabela Produto
-- Reprovado, pois o código Unidade não existe na base de dados
INSERT INTO fichaTecnicaTipoComposicao(codFichaTecnicaTipoComposicao, codUnidade, nome) VALUES (406, 10, 'Formulção  peletizada');
-- Aprovado
INSERT INTO fichaTecnicaTipoComposicao(codFichaTecnicaTipoComposicao, codUnidade, nome) VALUES (405, 4, 'Formulção  peletizada');

-- Tabela cliente
-- Reprovado, pois o código tipo cliente não existe na base de dados
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond) VALUES (406, 9, 'João  Oliveira Rendeiro', 245434223, 'joaoreiro@gmail.com', 700);
-- Aprovado
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond) VALUES (404, 2, 'João  Rendeiro', 245342523, 'joaor@gmail.com', 6000);

-- Tabela Hub
-- Reprovado, pois o código tipo Hub não existe na base de dados
INSERT INTO hub(codHub, codTipoHub, latitude, longitude) VALUES ('CT400', 10, 12.4453, -3.7443);
-- Aprovado
INSERT INTO hub(codHub, codTipoHub, latitude, longitude) VALUES ('CT400', 1, 12.4453, -3.7443);

-- Tabela encomenda
-- Reprovado, pois o código Interno Cliente não existe na base de dados
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda, dataVencimento) VALUES (408, 412, 1, 'CT1', TO_DATE('27/11/2022', 'dd/mm/yy'), TO_DATE('27/11/2022', 'dd/mm/yy'));
-- Reprovado, pois o código Estado Encomenda não existe na base de dados
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda, dataVencimento) VALUES (408, 3, 55, 'CT1', TO_DATE('27/11/2022', 'dd/mm/yy'), TO_DATE('27/11/2022', 'dd/mm/yy'));
-- Reprovado, pois o código Hub não existe na base de dados
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda, dataVencimento) VALUES (408, 3, 1, 'CT999', TO_DATE('27/11/2022', 'dd/mm/yy'), TO_DATE('27/11/2022', 'dd/mm/yy'));
-- Aprovado
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda, dataVencimento) VALUES (408, 3, 1, 'CT1', TO_DATE('24/11/2022', 'dd/mm/yy'), TO_DATE('24/11/2022', 'dd/mm/yy'));

-- Tabela edificio
-- Reprovado, pois o código Tipo Edificio não existe na base de dados
INSERT INTO edificio(codEdificio, codTipoEdificio, codInstalacaoAgricola, nome) VALUES (406, 41, 1, 'Edificio W');
-- Reprovado, pois o código InstalacaoAgricola não existe na base de dados
INSERT INTO edificio(codEdificio, codTipoEdificio, codInstalacaoAgricola, nome) VALUES (406, 5, 42, 'Edificio W');
-- Aprovado
INSERT INTO edificio(codEdificio, codTipoEdificio, codInstalacaoAgricola, nome) VALUES (406, 2, 1, 'Edificio FA');

-- Tabela cultura
-- Reprovado, pois o código Objetivo Cultura não existe na base de dados
INSERT INTO cultura(codCultura, codObjetivoCultura, codTipoCultura, nome) VALUES (409, 5, 2, 'Cultura W');
-- Reprovado, pois o código Tipo Cultura não existe na base de dados
INSERT INTO cultura(codCultura, codObjetivoCultura, codTipoCultura, nome) VALUES (409, 2, 5, 'Cultura W');
-- Aprovado
INSERT INTO cultura(codCultura, codObjetivoCultura, codTipoCultura, nome) VALUES (409, 1, 2, 'Cultura ZA');

-- Tabela fichaTecnicaTipoComposicao
-- Reprovado, pois o código unidade não existe na base de dados
INSERT INTO fichaTecnicaTipoComposicao(codFichaTecnicaTipoComposicao, codUnidade, nome) VALUES (406, 9, 'Formolçao  peletizada');
-- Aprovado
INSERT INTO fichaTecnicaTipoComposicao(codFichaTecnicaTipoComposicao, codUnidade, nome) VALUES (409, 3, 'PhY');

-- Tabela fatorProducao
-- Reprovado, pois o código fator Producao Aplicacao não existe na base de dados
INSERT INTO fatorProducao(codFatorProducao, codFatorProducaoAplicacao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor, nomeComercial) VALUES (411, 5, 2, 1, 1, 'Facção  J');
-- Reprovado, pois o código fator Producao Classificacao não existe na base de dados
INSERT INTO fatorProducao(codFatorProducao, codFatorProducaoAplicacao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor, nomeComercial) VALUES (411, 2, 6, 1, 1, 'Facção  J');
-- Reprovado, pois o código formulacao Produto não existe na base de dados
INSERT INTO fatorProducao(codFatorProducao, codFatorProducaoAplicacao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor, nomeComercial) VALUES (411, 2, 2, 9, 1, 'Facção  J');
-- Reprovado, pois o código fornecedor não existe na base de dados
INSERT INTO fatorProducao(codFatorProducao, codFatorProducaoAplicacao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor, nomeComercial) VALUES (411, 2, 2, 9, 75, 'Facção  J');
-- Aprovado
INSERT INTO fatorProducao(codFatorProducao, codFatorProducaoAplicacao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor, nomeComercial) VALUES (411, 2, 2, 1, 1, 'Facção LA');

-- Tabela fichaTecnica
-- Reprovado, pois o código fator Producao não existe na base de dados
INSERT INTO linhaFichaTecnica(codFatorProducao, codFichaTecnicaTipoComposicao, quantidade) VALUES (441, 42, 13);
-- Reprovado, pois o código ficha Tecnica Tipo Composicao não existe na base de dados
INSERT INTO linhaFichaTecnica(codFatorProducao, codFichaTecnicaTipoComposicao, quantidade) VALUES (414, 2, 100);
-- Aprovado
INSERT INTO linhaFichaTecnica(codFatorProducao, codFichaTecnicaTipoComposicao, quantidade) VALUES (41, 4, 1);

-- Tabela registoEncomenda
-- Reprovado, pois o código produto não existe na base de dados
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (410, 7, 1, 20, 6);
-- Reprovado, pois o código encomenda não existe na base de dados
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (403, 100, 1, 20, 6);
-- Aprovado
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (404, 3, 1, 20, 6);

-- Tabela tipoRega
-- Reprovado, pois o código liquido Rega não existe na base de dados
INSERT INTO tipoRega(codTipoRega, codLiquidoRega, codMetodoRega) VALUES (406, 41, 1);
-- Reprovado, pois o código metodo Rega não existe na base de dados
INSERT INTO tipoRega(codTipoRega, codLiquidoRega, codMetodoRega) VALUES (407, 2, 4);
-- Aprovado
INSERT INTO tipoRega(codTipoRega, codLiquidoRega, codMetodoRega) VALUES (407, 2, 1);

-- Tabela rega
-- Reprovado, pois o código tipo Rega Rega não existe na base de dados
INSERT INTO rega(codRega, codTipoRega) VALUES (405, 61);
-- Aprovado
INSERT INTO rega(codRega, codTipoRega) VALUES (405, 4);

-- Tabela sensor
-- Reprovado, pois o código tipo sensor não existe na base de dados
INSERT INTO sensor(codSensor, codTipoSensor, valorReferencia) VALUES ('00408', 100, 7811145522423);
-- Aprovado
INSERT INTO sensor(codSensor, codTipoSensor, valorReferencia) VALUES ('00408', 3, 7811145522423);

-- Tabela leitura Sensor
-- Reprovado, pois o código estacao Meteorologica não existe na base de dados
INSERT INTO leituraSensor(codLeituraSensor, codEstacaoMeteorologica, codSensor, resultado) VALUES (408, 51, '00001', 100);
-- Reprovado, pois o código sensor não existe na base de dados
INSERT INTO leituraSensor(codLeituraSensor, codEstacaoMeteorologica, codSensor, resultado) VALUES (408, 1, '00051', 100);
-- Aprovado
INSERT INTO leituraSensor(codLeituraSensor, codEstacaoMeteorologica, codSensor, resultado) VALUES (408, 1, '00001', 13);

-- Tabela plano Rega
-- Reprovado, pois o código rega não existe na base de dados
INSERT INTO planoRega(codPlanoRega, codRega, codEstacaoMeteorologica, periodicidade, tempoRega) VALUES (405, 41, 1, 123, 456);
-- Reprovado, pois o código estacao Meteorologica não existe na base de dados
INSERT INTO planoRega(codPlanoRega, codRega, codEstacaoMeteorologica, periodicidade, tempoRega) VALUES (405, 4, 14, 123, 456);
-- Aprovado
INSERT INTO planoRega(codPlanoRega, codRega, codEstacaoMeteorologica, periodicidade, tempoRega) VALUES (405, 2, 1, 143, 446);

-- Tabela parcela
-- Reprovado, pois o código Instalacao Agricola não existe na base de dados
INSERT INTO parcela(codParcela, codInstalacaoAgricola, designacao, area) VALUES (405, 15, 'Parcela do feijão', 360);
-- Aprovado
INSERT INTO parcela(codParcela, codInstalacaoAgricola, designacao, area) VALUES (405, 1, 'Parcela do arroz', 350);

-- Tabela plantacao
-- Reprovado, pois o código Parcela não existe na base de dados
INSERT INTO plantacao(codPlantacao, codParcela, codCultura, dataInicio, dataFim, area) VALUES (408, 41, 7, TO_DATE('18/9/2023', 'dd/mm/yy'), TO_DATE('18/4/2024', 'dd/mm/yy'), 150);
-- Reprovado, pois o código Cultura não existe na base de dados
INSERT INTO plantacao(codPlantacao, codParcela, codCultura, dataInicio, dataFim, area) VALUES (408, 4, 10, TO_DATE('18/9/2023', 'dd/mm/yy'), TO_DATE('18/4/2024', 'dd/mm/yy'), 150);
-- Aprovado
INSERT INTO plantacao(codPlantacao, codParcela, codCultura, dataInicio, dataFim, area) VALUES (408, 4, 4, TO_DATE('08/9/2023', 'dd/mm/yy'), TO_DATE('18/4/2024', 'dd/mm/yy'), 150);

-- Tabela acao
-- Reprovado, pois o código plantacao não existe na base de dados
INSERT INTO acao(codAcao, codPlantacao, codPlanoRega, codFatorProducao, dataAcao) VALUES (408, 10, 3, 3, TO_DATE('27-5-2022', 'dd-mm-yyyy'));
-- Reprovado, pois o código plano Rega não existe na base de dados
INSERT INTO acao(codAcao, codPlantacao, codPlanoRega, codFatorProducao, dataAcao) VALUES (408, 7, 10, 3, TO_DATE('27-5-2022', 'dd-mm-yyyy'));
-- Reprovado, pois o código fator Producao não existe na base de dados
INSERT INTO acao(codAcao, codPlantacao, codPlanoRega, codFatorProducao, dataAcao) VALUES (409, 7, 3, 22, TO_DATE('27-5-2022', 'dd-mm-yyyy'));
-- Aprovado
INSERT INTO acao(codAcao, codPlantacao, codPlanoRega, codFatorProducao, dataAcao) VALUES (408, 4, 3, 3, TO_DATE('27-5-2022', 'dd-mm-yyyy'));