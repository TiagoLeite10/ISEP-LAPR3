-- ************************************** RESTRIÇÕES DE IDENTIDADE ************************************** --
-- Tabela TipoCliente
-- Reprovado, pois o código do tipo cliente já existe na base de dados
INSERT INTO tipoCliente(codTipoCliente, descricao) VALUES(1, 'Empresa de Restauração');
-- Aprovado
INSERT INTO tipoCliente(codTipoCliente, descricao) VALUES(300, 'Empresa de Restauração');

-- Tabela instalacaoAgricola
-- Reprovado, pois o código da instalação agrícola já existe na base de dados
INSERT INTO instalacaoAgricola(codInstalacaoAgricola, nome) VALUES (1, 'Quinta do Manuel António');
-- Aprovado
INSERT INTO instalacaoAgricola(codInstalacaoAgricola, nome) VALUES (300, 'Quinta do Manuel António');

-- Tabela unidade
-- Reprovado, pois o código da unidade já existe na base de dados
INSERT INTO unidade(codUnidade, descricao) VALUES(1, 'Cm');
-- Aprovado
INSERT INTO unidade(codUnidade, descricao) VALUES(300, 'Cm');

-- Tabela TipoHub
-- Reprovado, pois o código do tipo de hub já existe na base de dados
INSERT INTO tipoHub(codTipoHub, descricao) VALUES(1, 'Industrial');
-- Aprovado
INSERT INTO tipoHub(codTipoHub, descricao) VALUES(300, 'Industrial');

-- Tabela EstadoEncomenda
-- Reprovado, pois o código do tipo estado de encomenda já existe na base de dados
INSERT INTO estadoEncomenda(codEstadoEncomenda, descricao) VALUES(1, 'Recusado');
-- Aprovado
INSERT INTO estadoEncomenda(codEstadoEncomenda, descricao) VALUES(300, 'Recusado');

-- Tabela Iva
-- Reprovado, pois o código do tipo de iva já existe na base de dados
INSERT INTO iva(codIva, percentagemIva) VALUES (1, 13);
-- Aprovado
INSERT INTO iva(codIva, percentagemIva) VALUES (300, 13);

-- Tabela Produto
-- Reprovado, pois o código do produto já existe na base de dados
INSERT INTO produto(codProduto, codIva, nome, valorProduto) VALUES(1, 1, 'Rosas', 1.95);
-- Aprovado
INSERT INTO produto(codProduto, codIva, nome, valorProduto) VALUES(300, 1, 'Rosas', 1.95);

-- Tabela FatorProducaoAplicacao
-- Reprovado, pois o código do tipo de aplicação do fator de produção já existe na base de dados
INSERT INTO fatorProducaoAplicacao(codFatorProducaoAplicacao, descricao) VALUES(1, 'Árvores');
-- Aprovado
INSERT INTO fatorProducaoAplicacao(codFatorProducaoAplicacao, descricao) VALUES(300, 'Árvores');

-- Tabela FatorProducaoClassificacao
-- Reprovado, pois o código do tipo de classificação do fator de produção já existe na base de dados
INSERT INTO fatorProducaoClassificacao(codFatorProducaoClassificacao, descricao) VALUES(1, 'Biofertilizante');
-- Aprovado
INSERT INTO fatorProducaoClassificacao(codFatorProducaoClassificacao, descricao) VALUES(300, 'Biofertilizante');

-- Tabela FormulacaoProduto
-- Reprovado, pois o código do tipo de formulação do produto já existe na base de dados
INSERT INTO formulacaoProduto(codFormulacaoProduto, descricao) VALUES(1, 'Gasoso');
-- Aprovado
INSERT INTO formulacaoProduto(codFormulacaoProduto, descricao) VALUES(300, 'Gasoso');

-- Tabela TipoEdificio
-- Reprovado, pois o código do tipo de edifício já existe na base de dados
INSERT INTO tipoEdificio(codTipoEdificio, nome) VALUES (1, 'Apicultor');
-- Aprovado
INSERT INTO tipoEdificio(codTipoEdificio, nome) VALUES (300, 'Apicultor');

-- Tabela TipoCultura
-- Reprovado, pois o código do tipo de cultura já existe na base de dados
INSERT INTO tipoCultura(codTipoCultura, descricao) VALUES (1, 'Indefinida');
-- Aprovado
INSERT INTO tipoCultura(codTipoCultura, descricao) VALUES (300, 'Indefinida');

-- Tabela ObjetivoCultura
-- Reprovado, pois o código do objetivo de cultura já existe na base de dados
INSERT INTO objetivoCultura(codObjetivoCultura, descricao) VALUES (1, 'Para estrume');
-- Aprovado
INSERT INTO objetivoCultura(codObjetivoCultura, descricao) VALUES (300, 'Para estrume');

-- Tabela LiquidoRega
-- Reprovado, pois o código do líquido de rega já existe na base de dados
INSERT INTO liquidoRega(codLiquidoRega, descricao) VALUES (1, 'Desinfetante');
-- Aprovado
INSERT INTO liquidoRega(codLiquidoRega, descricao) VALUES (300, 'Desinfetante');

-- Tabela MetodoRega
-- Reprovado, pois o código do método de rega já existe na base de dados
INSERT INTO metodoRega(codMetodoRega, descricao) VALUES (1, 'Passada');
-- Aprovado
INSERT INTO metodoRega(codMetodoRega, descricao) VALUES (300, 'Passada');

-- Tabela TipoSensor
-- Reprovado, pois o código do tipo de sensor já existe na base de dados
INSERT INTO tipoSensor(codTipoSensor, acronimo, descricao) VALUES (1, 'ES', 'Estabilidade do solo');
-- Aprovado
INSERT INTO tipoSensor(codTipoSensor, acronimo, descricao) VALUES (300, 'ES', 'Estabilidade do solo');


-- Tabela EstacaoMeteorologica
-- Reprovado, pois o código da estação meteorológica já existe na base de dados
INSERT INTO estacaoMeteorologica(codEstacaoMeteorologica, nome) VALUES (1, 'Estação meteorológica ambiental');
-- Aprovado
INSERT INTO estacaoMeteorologica(codEstacaoMeteorologica, nome) VALUES (300, 'Estação meteorológica ambiental');

-- Tabela Cliente
-- Reprovado, pois o código do cliente já existe na base de dados
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond) VALUES (1, 1, 'Manuel Ferro', 193421234, 'manuelferro@gmail.com', 100);
-- Aprovado
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond) VALUES (300, 1, 'Manuel Ferro', 193421234, 'manuelferro@gmail.com', 100);

-- Tabela Hub
-- Reprovado, pois o código do hub já existe na base de dados
INSERT INTO hub(codHub, codTipoHub, latitude, longitude) VALUES ('CT1', 1, 12.4453, -3.7443);
-- Aprovado
INSERT INTO hub(codHub, codTipoHub, latitude, longitude) VALUES ('CT300', 1, 12.4453, -3.7443);

-- Tabela Encomenda
-- Reprovado, pois o código da encomenda já existe na base de dados
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda, dataVencimento) VALUES (1, 1, 1, 'CT1', TO_DATE('1/12/2022', 'dd/mm/yy'), TO_DATE('3/12/2022', 'dd/mm/yy'));
-- Aprovado
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda, dataVencimento) VALUES (300, 1, 1, 'CT1', TO_DATE('1/12/2022', 'dd/mm/yy'), TO_DATE('3/12/2022', 'dd/mm/yy'));

-- Tabela RegistoEncomenda
-- Reprovado, pois já existe na base de dados um registo com o código do produto na mesma encomenda
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (1, 1, 300, 49.99, 6);
-- Aprovado
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (300, 1, 300, 49.99, 6);

-- Tabela FichaTecnicaTipoComposicao
-- Reprovado, pois o código do tipo de composição da ficha técnica já existe na base de dados
INSERT INTO fichaTecnicaTipoComposicao(codFichaTecnicaTipoComposicao, codUnidade, nome) VALUES (1, 1, 'Dióxido de carbono');
-- Aprovado
INSERT INTO fichaTecnicaTipoComposicao(codFichaTecnicaTipoComposicao, codUnidade, nome) VALUES (300, 1, 'Dióxido de carbono');

-- Tabela FatorProducao
-- Reprovado, pois o código do fator de produção já existe na base de dados
INSERT INTO fatorProducao(codFatorProducao, codFatorProducaoAplicacao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor, nomeComercial) VALUES (1, 1, 3, 3, 1, 'Facção Z');
-- Aprovado
INSERT INTO fatorProducao(codFatorProducao, codFatorProducaoAplicacao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor, nomeComercial) VALUES (300, 1, 3, 3, 1, 'Facção Z');

-- Tabela LinhaFichaTecnica
-- Reprovado, pois já existe na base de dados um registo com o código de fator de produção na mesma ficha técnica
INSERT INTO linhaFichaTecnica(codFatorProducao, codFichaTecnicaTipoComposicao, quantidade) VALUES (1, 1, 35);
-- Aprovado
INSERT INTO linhaFichaTecnica(codFatorProducao, codFichaTecnicaTipoComposicao, quantidade) VALUES (300, 1, 35);

-- Tabela Cultura
-- Reprovado, pois o código da cultura já existe na base de dados
INSERT INTO cultura(codCultura, codObjetivoCultura, codTipoCultura, nome) VALUES (1, 1, 1, 'Cultura Z');
-- Aprovado
INSERT INTO cultura(codCultura, codObjetivoCultura, codTipoCultura, nome) VALUES (300, 1, 1, 'Cultura Z');

-- Tabela Parcela
-- Reprovado, pois o código da parcela já existe na base de dados
INSERT INTO parcela(codParcela, codInstalacaoAgricola, designacao, area) VALUES (1, 1, 'Parcela da laranjeira', 50);
-- Aprovado
INSERT INTO parcela(codParcela, codInstalacaoAgricola, designacao, area) VALUES (300, 1, 'Parcela da laranjeira', 50);

-- Tabela Edificio
-- Reprovado, pois o código do edifício já existe na base de dados
INSERT INTO edificio(codEdificio, codTipoEdificio, codInstalacaoAgricola, nome) VALUES (1, 1, 1, 'Edificio Z');
-- Aprovado
INSERT INTO edificio(codEdificio, codTipoEdificio, codInstalacaoAgricola, nome) VALUES (300, 1, 1, 'Edificio Z');

-- Tabela Plantacao
-- Reprovado, pois o código da plantação já existe na base de dados
INSERT INTO plantacao(codPlantacao, codParcela, codCultura, dataInicio, dataFim, area) VALUES (1, 3, 6, TO_DATE('1/12/2022', 'dd/mm/yy'), TO_DATE('12/12/2022', 'dd/mm/yy'), 50);
-- Aprovado
INSERT INTO plantacao(codPlantacao, codParcela, codCultura, dataInicio, dataFim, area) VALUES (300, 3, 6, TO_DATE('1/12/2022', 'dd/mm/yy'), TO_DATE('12/12/2022', 'dd/mm/yy'), 50);

-- Tabela TipoRega
-- Reprovado, pois o código do tipo de rega já existe na base de dados
INSERT INTO tipoRega(codTipoRega, codLiquidoRega, codMetodoRega) VALUES (1, 2, 1);
-- Aprovado
INSERT INTO tipoRega(codTipoRega, codLiquidoRega, codMetodoRega) VALUES (300, 2, 1);

-- Tabela Rega
-- Reprovado, pois o código da rega já existe na base de dados
INSERT INTO rega(codRega, codTipoRega) VALUES (1, 4);
-- Aprovado
INSERT INTO rega(codRega, codTipoRega) VALUES (300, 4);

-- Tabela PlanoRega
-- Reprovado, pois o código do plano de rega já existe na base de dados
INSERT INTO planoRega(codPlanoRega, codRega, codEstacaoMeteorologica, periodicidade, tempoRega) VALUES (1, 2, 1, 900, 640);
-- Aprovado
INSERT INTO planoRega(codPlanoRega, codRega, codEstacaoMeteorologica, periodicidade, tempoRega) VALUES (300, 2, 1, 900, 640);

-- Tabela Sensor
-- Reprovado, pois o código do sensor já existe na base de dados
INSERT INTO sensor(codSensor, codTipoSensor, valorReferencia) VALUES ('00001', 7, 7811174322423);
-- Aprovado
INSERT INTO sensor(codSensor, codTipoSensor, valorReferencia) VALUES ('00300', 7, 7811174322423);

-- Tabela LeituraSensor
-- Reprovado, pois o código da leitura do sensor já existe na base de dados
INSERT INTO leituraSensor(codLeituraSensor, codEstacaoMeteorologica, codSensor, resultado) VALUES (1, 1, '00001', 25);
-- Aprovado
INSERT INTO leituraSensor(codLeituraSensor, codEstacaoMeteorologica, codSensor, resultado) VALUES (300, 1, '00001', 25);

-- Tabela Acao
-- Reprovado, pois o código da ação já existe na base de dados
INSERT INTO acao(codAcao, codPlantacao, codPlanoRega, codFatorProducao, dataAcao) VALUES (1, 4, 3, 2, TO_DATE('27-2-2022', 'dd-mm-yyyy'));
-- Aprovado
INSERT INTO acao(codAcao, codPlantacao, codPlanoRega, codFatorProducao, dataAcao) VALUES (300, 4, 3, 2, TO_DATE('27-2-2022', 'dd-mm-yyyy'));

-- Tabela Colheita
-- Reprovado, pois o código da colheita já existe na base de dados
INSERT INTO colheita(codColheita, codPlantacao, codProduto, dataColheita, quantidade) VALUES (1, 1, 1, TO_DATE('03/2/2022', 'dd/mm/yy'), 500);
-- Aprovado
INSERT INTO colheita(codColheita, codPlantacao, codProduto, dataColheita, quantidade) VALUES (300, 1, 1, TO_DATE('03/2/2022', 'dd/mm/yy'), 500);

-- Tabela Fornecedor
-- Reprovado, pois o código do fornecedor já existe na base de dados
INSERT INTO fornecedor (codFornecedor, nome) VALUES (1, 'Batatoide');
-- Aprovado
INSERT INTO fornecedor (codFornecedor, nome) VALUES (300, 'Batatoide');