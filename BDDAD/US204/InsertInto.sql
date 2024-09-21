-- ## tabela TipoCliente ##
INSERT INTO tipoCliente(codTipoCliente, descricao) VALUES(1, 'Empresa');
INSERT INTO tipoCliente(codTipoCliente, descricao) VALUES(2, 'Particular');

-- ## tabela InstalacaoAgricola ##
INSERT INTO instalacaoAgricola(codInstalacaoAgricola, nome) VALUES (1, 'Quinta do Quim Barreiros');

-- ## tabela Unidade ##
INSERT INTO unidade(codUnidade, descricao) VALUES(1, 'Percentagem');
INSERT INTO unidade(codUnidade, descricao) VALUES(2, 'Unidade de pH');
INSERT INTO unidade(codUnidade, descricao) VALUES(3, 'kg/L');
INSERT INTO unidade(codUnidade, descricao) VALUES(4, 'mm');
INSERT INTO unidade(codUnidade, descricao) VALUES(5, 'L');
INSERT INTO unidade(codUnidade, descricao) VALUES(6, 'kg');

-- ## tabela TipoHub ##
INSERT INTO tipoHub(codTipoHub, acronimo, descricao) VALUES(1, 'E', 'Empresa');
INSERT INTO tipoHub(codTipoHub, acronimo, descricao) VALUES(2, 'P', 'Produtor agrícola');

-- ## tabela EstadoEncomenda ##
INSERT INTO estadoEncomenda(codEstadoEncomenda, descricao) VALUES(1, 'Registada');
INSERT INTO estadoEncomenda(codEstadoEncomenda, descricao) VALUES(2, 'Entregue');
INSERT INTO estadoEncomenda(codEstadoEncomenda, descricao) VALUES(3, 'Paga');

-- ## tabela Iva ##
INSERT INTO iva(codIva, percentagemIva) VALUES (1, 6);
INSERT INTO iva(codIva, percentagemIva) VALUES (2, 23);

-- ## tabela Produto ##
INSERT INTO produto(codProduto, codIva, nome, valorProduto) VALUES(1, 1, 'Flores', 49.99);
INSERT INTO produto(codProduto, codIva, nome, valorProduto) VALUES(2, 2, 'Frutos', 35.99);
INSERT INTO produto(codProduto, codIva, nome, valorProduto) VALUES(3, 1, 'Cereais', 20);
INSERT INTO produto(codProduto, codIva, nome, valorProduto) VALUES(4, 1, 'Hortícolas', 14.99);
INSERT INTO produto(codProduto, codIva, nome, valorProduto) VALUES(5, 2, 'Forragens', 9.99);

-- ## tabela formaAplicacao ##
INSERT INTO formaAplicacao(codFormaAplicacao, descricao) VALUES(1, 'Foliar');
INSERT INTO formaAplicacao(codFormaAplicacao, descricao) VALUES(2, 'Fertirrega');
INSERT INTO formaAplicacao(codFormaAplicacao, descricao) VALUES(3, 'Solo');

-- ## tabela FatorProducaoClassificacao ##
INSERT INTO fatorProducaoClassificacao(codFatorProducaoClassificacao, descricao) VALUES(1, 'Fertilizantes');
INSERT INTO fatorProducaoClassificacao(codFatorProducaoClassificacao, descricao) VALUES(2, 'Adubos');
INSERT INTO fatorProducaoClassificacao(codFatorProducaoClassificacao, descricao) VALUES(3, 'Corretivos');
INSERT INTO fatorProducaoClassificacao(codFatorProducaoClassificacao, descricao) VALUES(4, 'Produtos fitofármacos');

-- ## tabela FormulacaoProduto ##
INSERT INTO formulacaoProduto(codFormulacaoProduto, descricao) VALUES(1, 'Líquido');
INSERT INTO formulacaoProduto(codFormulacaoProduto, descricao) VALUES(2, 'Granulado');
INSERT INTO formulacaoProduto(codFormulacaoProduto, descricao) VALUES(3, 'Pó');

-- ## tabela TipoEdificio ##
INSERT INTO tipoEdificio(codTipoEdificio, nome) VALUES (1, 'Estábulos para animais');
INSERT INTO tipoEdificio(codTipoEdificio, nome) VALUES (2, 'Garagens para máquinas e alfaias');
INSERT INTO tipoEdificio(codTipoEdificio, nome) VALUES (3, 'Armazéns para colheitas');
INSERT INTO tipoEdificio(codTipoEdificio, nome) VALUES (4, 'Factores de produção e rações para animais');
INSERT INTO tipoEdificio(codTipoEdificio, nome) VALUES (5, 'Sistemas de rega incluindo tanques agrícolas');

-- ## tabela TipoCultura ##
INSERT INTO tipoCultura(codTipoCultura, descricao) VALUES (1, 'Permanente');
INSERT INTO tipoCultura(codTipoCultura, descricao) VALUES (2, 'Temporária');

-- ## tabela ObjetivoCultura ##
INSERT INTO objetivoCultura(codObjetivoCultura, descricao) VALUES (1, 'Ser consumida por humanos/animais');
INSERT INTO objetivoCultura(codObjetivoCultura, descricao) VALUES (2, 'Para produzir adubação verde');

-- ## tabela LiquidoRega ##
INSERT INTO liquidoRega(codLiquidoRega, descricao) VALUES (1, 'Solução aquosa');
INSERT INTO liquidoRega(codLiquidoRega, descricao) VALUES (2, 'Água');

-- ## tabela MetodoRega ##
INSERT INTO metodoRega(codMetodoRega, descricao) VALUES (1,'Gravidade');
INSERT INTO metodoRega(codMetodoRega, descricao) VALUES (2,'Bombeada');

-- ## tabela TipoSensor ##
INSERT INTO tipoSensor(codTipoSensor, acronimo, descricao) VALUES (1, 'PL', 'Pluviosidade');
INSERT INTO tipoSensor(codTipoSensor, acronimo, descricao) VALUES (2, 'TS', 'Temperatura do solo');
INSERT INTO tipoSensor(codTipoSensor, acronimo, descricao) VALUES (3, 'HS', 'Humidade do solo');
INSERT INTO tipoSensor(codTipoSensor, acronimo, descricao) VALUES (4, 'VV', 'Velocidade do vento');
INSERT INTO tipoSensor(codTipoSensor, acronimo, descricao) VALUES (5, 'TA', 'Temperatura atmosférica');
INSERT INTO tipoSensor(codTipoSensor, acronimo, descricao) VALUES (6, 'HA', 'Humidade do ar');
INSERT INTO tipoSensor(codTipoSensor, acronimo, descricao) VALUES (7, 'PA', 'Pressão atmosférica');

-- ## tabela EstacaoMeteorologica ##
INSERT INTO estacaoMeteorologica(codEstacaoMeteorologica, nome) VALUES (1, 'Estação meteorológica agrícola');

-- ## tabela Cliente ##
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond) VALUES (1, 1, 'Joaquim de Magalhães Fernandes Barreiros', 193427454, 'quimbarreiros@gmail.com', 15000);
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond) VALUES (2, 2, 'Fernando Jorge Alves Mendes', 214546092, 'fernadomendes@gmail.com', 5500);
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond) VALUES (3, 2, 'João Manuel Oliveira Rendeiro', 245342423, 'joaorendeiro@gmail.com', 7000);
INSERT INTO cliente(codInternoCliente, codTipoCliente, nome, numeroFiscal, email, plafond) VALUES (4, 2, 'Cristiano Ronaldo', 212372647, 'cr7@gmail.com', 12500);

-- ## tabela MoradaFiscal ##
INSERT INTO moradaFiscal(codMoradaFiscal, codInternoCliente, morada, codPostal, cidade, estado) VALUES (1, 1, 'Rua da Costa', '4850-010', 'Minho', 1);
INSERT INTO moradaFiscal(codMoradaFiscal, codInternoCliente, morada, codPostal, cidade, estado) VALUES (2, 2, 'R. Heróis de França 307', '4450-155', 'Porto', 1);
INSERT INTO moradaFiscal(codMoradaFiscal, codInternoCliente, morada, codPostal, cidade, estado) VALUES (3, 3, 'Avenida da republica', '1000-003', 'Lisboa', 1);
INSERT INTO moradaFiscal(codMoradaFiscal, codInternoCliente, morada, codPostal, cidade, estado) VALUES (4, 4, 'GOAT Land', '1234-123', 'Madeira', 1);

-- ## tabela Hub ##
INSERT INTO hub(codHub, codTipoHub, latitude, longitude) VALUES ('CT1', 1, 23.4389, -5.7343);
INSERT INTO hub(codHub, codTipoHub, latitude, longitude) VALUES ('CT2', 1, 11.4459, -2.6353);
INSERT INTO hub(codHub, codTipoHub, latitude, longitude) VALUES ('CT3', 1, 43.4889, -5.1123);
INSERT INTO hub(codHub, codTipoHub, latitude, longitude) VALUES ('CT4', 2, 77.4979, -8.6673);
INSERT INTO hub(codHub, codTipoHub, latitude, longitude) VALUES ('CT5', 2, 88.4549, -1.3783);

-- ## tabela Encomenda ##
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda, dataVencimento, dataPagamento, dataEntrega) VALUES (1, 1, 2, 'CT1', TO_DATE('1-1-2022', 'dd-mm-yyyy'), TO_DATE('15-1-2022', 'dd-mm-yyyy'), TO_DATE('18-1-2022', 'dd-mm-yyyy'), TO_DATE('26-1-2022', 'dd-mm-yyyy'));
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda, dataVencimento, dataPagamento, dataEntrega) VALUES (2, 2, 2, 'CT2', TO_DATE('13-5-2022', 'dd-mm-yyyy'), TO_DATE('20-5-2022', 'dd-mm-yyyy'), TO_DATE('20-5-2022', 'dd-mm-yyyy'), TO_DATE('28-5-2022', 'dd-mm-yyyy'));
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda, dataVencimento, dataPagamento, dataEntrega) VALUES (3, 1, 2, 'CT3', TO_DATE('4-2-2022', 'dd-mm-yyyy'), TO_DATE('18-2-2022', 'dd-mm-yyyy'), TO_DATE('15-2-2022', 'dd-mm-yyyy'), TO_DATE('28-2-2022', 'dd-mm-yyyy'));
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda, dataVencimento, dataPagamento, dataEntrega) VALUES (4, 2, 2, 'CT1', TO_DATE('5-8-2022', 'dd-mm-yyyy'), TO_DATE('13-8-2022', 'dd-mm-yyyy'), TO_DATE('20-8-2022', 'dd-mm-yyyy'), TO_DATE('28-8-2022', 'dd-mm-yyyy'));
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda, dataVencimento, dataPagamento) VALUES (5, 3, 3, 'CT3', TO_DATE('27-11-2022', 'dd-mm-yyyy'), TO_DATE('27-11-2022', 'dd-mm-yyyy'), TO_DATE('27-11-2022', 'dd-mm-yyyy'));
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda, dataVencimento) VALUES (6, 3, 1, 'CT2', TO_DATE('27-11-2022', 'dd-mm-yyyy'), TO_DATE('27-11-2022', 'dd-mm-yyyy'));
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda, dataVencimento) VALUES (7, 3, 1, 'CT4', TO_DATE('27-11-2022', 'dd-mm-yyyy'), TO_DATE('27-11-2022', 'dd-mm-yyyy'));
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda, dataVencimento, dataEntrega) VALUES (8, 2, 2, 'CT2', TO_DATE('5-8-2022', 'dd-mm-yyyy'), TO_DATE('13-8-2022', 'dd-mm-yyyy'), TO_DATE('28-8-2022', 'dd-mm-yyyy'));
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda, dataVencimento, dataPagamento, dataEntrega) VALUES (9, 4, 2, 'CT1', TO_DATE('5-8-2022', 'dd-mm-yyyy'), TO_DATE('26-8-2022', 'dd-mm-yyyy'), TO_DATE('20-8-2022', 'dd-mm-yyyy'), TO_DATE('18-8-2022', 'dd-mm-yyyy'));
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda, dataVencimento, dataPagamento) VALUES (10, 3, 3, 'CT3', TO_DATE('21/11/2022', 'dd/mm/yy'), TO_DATE('21/11/2022', 'dd/mm/yy'), TO_DATE('23/11/2022', 'dd/mm/yy'));
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda, dataVencimento) VALUES (11, 3, 1, 'CT4', TO_DATE('27/11/2022', 'dd/mm/yy'), TO_DATE('27/11/2022', 'dd/mm/yy'));
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda, dataVencimento) VALUES (12, 3, 1, 'CT4', TO_DATE('27/11/2022', 'dd/mm/yy'), TO_DATE('27/11/2022', 'dd/mm/yy'));

-- ## tabela RegistoEncomenda ##
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (1, 1, 300, 49.99, 6);
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (2, 2, 140, 35.99, 23);
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (3, 3, 200, 20, 6);
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (4, 4, 75, 14.99, 6);
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (1, 5, 99, 49.99, 6);
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (2, 5, 29, 35.99, 23);
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (3, 5, 1, 20, 6);
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (1, 6, 5, 49.99, 6);
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (2, 6, 8, 35.99, 23);
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (3, 6, 9, 20, 6);
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (1, 7, 1, 49.99, 6);
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (2, 7, 1, 35.99, 23);
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (3, 7, 1, 20, 6);
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (1, 9, 240, 49.99, 23);
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (1, 10, 3, 15, 6);
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (2, 10, 4, 20, 6);
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (3, 10, 1, 30, 6);
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (1, 11, 5, 15, 6);
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (2, 11, 8, 20, 6);
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (3, 11, 9, 30, 6);
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (1, 12, 1, 15, 6);
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (2, 12, 1, 20, 6);
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (3, 12, 1, 30, 6);

-- ## tabela FichaTecnicaTipoComposicao ##
INSERT INTO fichaTecnicaTipoComposicao(codFichaTecnicaTipoComposicao, codUnidade, nome) VALUES (1, 1, 'Azoto orgânico');
INSERT INTO fichaTecnicaTipoComposicao(codFichaTecnicaTipoComposicao, codUnidade, nome) VALUES (2, 2, 'pH');
INSERT INTO fichaTecnicaTipoComposicao(codFichaTecnicaTipoComposicao, codUnidade, nome) VALUES (3, 3, 'Peso específico');
INSERT INTO fichaTecnicaTipoComposicao(codFichaTecnicaTipoComposicao, codUnidade, nome) VALUES (4, 4, 'Formulção peletizada');

-- ## tabela fornecedor ##
INSERT INTO fornecedor (codFornecedor, nome) VALUES (1, 'Fornecedor número Um');
INSERT INTO fornecedor (codFornecedor, nome) VALUES (2, 'Fornecedor número Dois');
INSERT INTO fornecedor (codFornecedor, nome) VALUES (3, 'Fornecedor número Três');

-- ## tabela FatorProducao ##
INSERT INTO fatorProducao(codFatorProducao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor, codUnidade, nomeComercial) VALUES (1, 1, 1, 1, 6, 'Guanito');
INSERT INTO fatorProducao(codFatorProducao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor, codUnidade, nomeComercial) VALUES (2, 2, 2, 1, 6, 'Farinha de concha');
INSERT INTO fatorProducao(codFatorProducao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor, codUnidade, nomeComercial) VALUES (3, 3, 3, 2, 5, 'Esterco de curral');
INSERT INTO fatorProducao(codFatorProducao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor, codUnidade, nomeComercial) VALUES (4, 4, 1, 1, 5, 'Húmus de minhoca');
INSERT INTO fatorProducao(codFatorProducao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor, codUnidade, nomeComercial) VALUES (5, 1, 2, 3, 5, 'Farinha de ossos');
INSERT INTO fatorProducao(codFatorProducao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor, codUnidade, nomeComercial) VALUES (6, 2, 3, 2, 6, 'Vinhaça');
INSERT INTO fatorProducao(codFatorProducao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor, codUnidade, nomeComercial) VALUES (7, 3, 1, 3, 6, 'Resíduos oleaginosos');
INSERT INTO fatorProducao(codFatorProducao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor, codUnidade, nomeComercial) VALUES (8, 4, 2, 2, 6, 'Adubo verde');
INSERT INTO fatorProducao(codFatorProducao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor, codUnidade, nomeComercial) VALUES (9, 1, 3, 3, 5, 'Resíduo de filtro prensa');
INSERT INTO fatorProducao(codFatorProducao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor, codUnidade, nomeComercial) VALUES (10, 2, 1, 1, 6, 'Lodo de esgoto');

-- ## tabela LinhaFichaTecnica ##
INSERT INTO linhaFichaTecnica(codFatorProducao, codFichaTecnicaTipoComposicao, quantidade) VALUES (1, 1, 20);
INSERT INTO linhaFichaTecnica(codFatorProducao, codFichaTecnicaTipoComposicao, quantidade) VALUES (2, 2, 15);
INSERT INTO linhaFichaTecnica(codFatorProducao, codFichaTecnicaTipoComposicao, quantidade) VALUES (3, 3, 5);
INSERT INTO linhaFichaTecnica(codFatorProducao, codFichaTecnicaTipoComposicao, quantidade) VALUES (4, 4, 7);
INSERT INTO linhaFichaTecnica(codFatorProducao, codFichaTecnicaTipoComposicao, quantidade) VALUES (5, 1, 14);
INSERT INTO linhaFichaTecnica(codFatorProducao, codFichaTecnicaTipoComposicao, quantidade) VALUES (6, 2, 24);
INSERT INTO linhaFichaTecnica(codFatorProducao, codFichaTecnicaTipoComposicao, quantidade) VALUES (7, 3, 37);
INSERT INTO linhaFichaTecnica(codFatorProducao, codFichaTecnicaTipoComposicao, quantidade) VALUES (8, 4, 16);
INSERT INTO linhaFichaTecnica(codFatorProducao, codFichaTecnicaTipoComposicao, quantidade) VALUES (9, 1, 8);
INSERT INTO linhaFichaTecnica(codFatorProducao, codFichaTecnicaTipoComposicao, quantidade) VALUES (10, 2, 13);

-- ## tabela Cultura ##
INSERT INTO cultura(codCultura, codObjetivoCultura, codTipoCultura, nome) VALUES (1, 1, 1, 'Cultura A');
INSERT INTO cultura(codCultura, codObjetivoCultura, codTipoCultura, nome) VALUES (2, 1, 1, 'Cultura B');
INSERT INTO cultura(codCultura, codObjetivoCultura, codTipoCultura, nome) VALUES (3, 1, 2, 'Cultura C');
INSERT INTO cultura(codCultura, codObjetivoCultura, codTipoCultura, nome) VALUES (4, 2, 1, 'Cultura D');
INSERT INTO cultura(codCultura, codObjetivoCultura, codTipoCultura, nome) VALUES (5, 2, 1, 'Cultura E');
INSERT INTO cultura(codCultura, codObjetivoCultura, codTipoCultura, nome) VALUES (6, 2, 2, 'Cultura F');
INSERT INTO cultura(codCultura, codObjetivoCultura, codTipoCultura, nome) VALUES (7, 1, 2, 'Cultura G');
INSERT INTO cultura(codCultura, codObjetivoCultura, codTipoCultura, nome) VALUES (8, 2, 2, 'Cultura H');

-- ## tabela Parcela ##
INSERT INTO parcela(codParcela, codInstalacaoAgricola, designacao, area) VALUES (1, 1, 'Parcela da macieira', 5);
INSERT INTO parcela(codParcela, codInstalacaoAgricola, designacao, area) VALUES (2, 1, 'Parcela da pereira', 60);
INSERT INTO parcela(codParcela, codInstalacaoAgricola, designacao, area) VALUES (3, 1, 'Parcela do trigo', 195);
INSERT INTO parcela(codParcela, codInstalacaoAgricola, designacao, area) VALUES (4, 1, 'Parcela do feijão', 360);
INSERT INTO parcela(codParcela, codInstalacaoAgricola, designacao, area) VALUES (5, 1, 'Parcela do batatas', 500);

-- ## tabela Edificio ##
INSERT INTO edificio(codEdificio, codTipoEdificio, codInstalacaoAgricola, nome) VALUES (1, 1, 1, 'Edificio A');
INSERT INTO edificio(codEdificio, codTipoEdificio, codInstalacaoAgricola, nome) VALUES (2, 2, 1, 'Edificio B');
INSERT INTO edificio(codEdificio, codTipoEdificio, codInstalacaoAgricola, nome) VALUES (3, 3, 1, 'Edificio C');
INSERT INTO edificio(codEdificio, codTipoEdificio, codInstalacaoAgricola, nome) VALUES (4, 4, 1, 'Edificio D');
INSERT INTO edificio(codEdificio, codTipoEdificio, codInstalacaoAgricola, nome) VALUES (5, 5, 1, 'Edificio E');

-- ## tabela Plantacao ##
INSERT INTO plantacao(codPlantacao, codParcela, codCultura, dataInicio, dataFim, area) VALUES (1, 1, 1, TO_DATE('3-3-2021', 'dd-mm-yyyy'), TO_DATE('3-9-2025', 'dd-mm-yyyy'), 5);
INSERT INTO plantacao(codPlantacao, codParcela, codCultura, dataInicio, dataFim, area) VALUES (2, 2, 2, TO_DATE('1-2-2021', 'dd-mm-yyyy'), TO_DATE('23-5-2025', 'dd-mm-yyyy'), 60);
INSERT INTO plantacao(codPlantacao, codParcela, codCultura, dataInicio, dataFim, area) VALUES (3, 3, 3, TO_DATE('4-3-2021', 'dd-mm-yyyy'), TO_DATE('4-6-2025', 'dd-mm-yyyy'), 100);
INSERT INTO plantacao(codPlantacao, codParcela, codCultura, dataInicio, dataFim, area) VALUES (4, 3, 4, TO_DATE('4-3-2021', 'dd-mm-yyyy'), TO_DATE('4-9-2025', 'dd-mm-yyyy'), 90);
INSERT INTO plantacao(codPlantacao, codParcela, codCultura, dataInicio, dataFim, area) VALUES (5, 4, 5, TO_DATE('7-6-2021', 'dd-mm-yyyy'), TO_DATE('5-6-2025', 'dd-mm-yyyy'), 60);
INSERT INTO plantacao(codPlantacao, codParcela, codCultura, dataInicio, dataFim, area) VALUES (6, 4, 6, TO_DATE('7-6-2021', 'dd-mm-yyyy'), TO_DATE('11-5-2025', 'dd-mm-yyyy'), 150);
INSERT INTO plantacao(codPlantacao, codParcela, codCultura, dataInicio, dataFim, area) VALUES (7, 4, 7, TO_DATE('18-9-2021', 'dd-mm-yyyy'), TO_DATE('18-4-2025', 'dd-mm-yyyy'), 150);
INSERT INTO plantacao(codPlantacao, codParcela, codCultura, dataInicio, dataFim, area) VALUES (8, 5, 7, TO_DATE('18-9-2022', 'dd-mm-yyyy'), TO_DATE('18-4-2023', 'dd-mm-yyyy'), 500);

-- ## tabela TipoRega ##
INSERT INTO tipoRega(codTipoRega, codLiquidoRega, codMetodoRega, descricao) VALUES (1, 1, 1, 'Gravidade');
INSERT INTO tipoRega(codTipoRega, codLiquidoRega, codMetodoRega, descricao) VALUES (2, 2, 2, 'Bombeada');

-- ## tabela Restricao
INSERT INTO restricao(codRestricao, codParcela, codFatorProducao, dataInicio, dataFim) VALUES (1, 1, 1, TO_DATE('1-1-2023', 'dd-mm-yyyy'), TO_DATE('1-12-2023', 'dd-mm-yyyy'));
INSERT INTO restricao(codRestricao, codParcela, codFatorProducao, dataInicio, dataFim) VALUES (2, 2, 5, TO_DATE('1-1-2023', 'dd-mm-yyyy'), TO_DATE('1-8-2023', 'dd-mm-yyyy'));
INSERT INTO restricao(codRestricao, codParcela, codFatorProducao, dataInicio, dataFim) VALUES (3, 1, 2, TO_DATE('4-1-2023', 'dd-mm-yyyy'), TO_DATE('1-9-2023', 'dd-mm-yyyy'));
INSERT INTO restricao(codRestricao, codParcela, codFatorProducao, dataInicio, dataFim) VALUES (4, 1, 2, TO_DATE('1-1-2023', 'dd-mm-yyyy'), TO_DATE('25-2-2023', 'dd-mm-yyyy'));
INSERT INTO restricao(codRestricao, codParcela, codFatorProducao, dataInicio, dataFim) VALUES (5, 4, 1, TO_DATE('1-1-2023', 'dd-mm-yyyy'), TO_DATE('5-1-2023', 'dd-mm-yyyy'));
INSERT INTO restricao(codRestricao, codParcela, codFatorProducao, dataInicio, dataFim) VALUES (6, 3, 3, TO_DATE('15-12-2022', 'dd-mm-yyyy'), TO_DATE('31-12-2022', 'dd-mm-yyyy'));
INSERT INTO restricao(codRestricao, codParcela, codFatorProducao, dataInicio, dataFim) VALUES (7, 3, 3, TO_DATE('10-10-2023', 'dd-mm-yyyy'), TO_DATE('2-12-2023', 'dd-mm-yyyy'));
INSERT INTO restricao(codRestricao, codParcela, codFatorProducao, dataInicio, dataFim) VALUES (8, 5, 4, TO_DATE('15-10-2023', 'dd-mm-yyyy'), TO_DATE('31-12-2023', 'dd-mm-yyyy'));
INSERT INTO restricao(codRestricao, codParcela, codFatorProducao, dataInicio, dataFim) VALUES (9, 1, 1, TO_DATE('1-1-2019', 'dd-mm-yyyy'), TO_DATE('1-12-2019', 'dd-mm-yyyy'));
INSERT INTO restricao(codRestricao, codParcela, codFatorProducao, dataInicio, dataFim) VALUES (10, 1, 1, TO_DATE('1-1-2999', 'dd-mm-yyyy'), TO_DATE('31-1-3000', 'dd-mm-yyyy'));

-- ## tabela PlanoRega ##
INSERT INTO planoRega(codPlanoRega, codEstacaoMeteorologica, periodicidade, tempoRega, dataInicio, dataFim) VALUES (1, 1, 480, 720, TO_DATE('1-1-2021', 'dd-mm-yyyy'), TO_DATE('31-12-2021', 'dd-mm-yyyy'));
INSERT INTO planoRega(codPlanoRega, codEstacaoMeteorologica, periodicidade, tempoRega, dataInicio, dataFim) VALUES (2, 1, 450, 690, TO_DATE('1-9-2022', 'dd-mm-yyyy'), TO_DATE('31-8-2023', 'dd-mm-yyyy'));
INSERT INTO planoRega(codPlanoRega, codEstacaoMeteorologica, periodicidade, tempoRega, dataInicio, dataFim) VALUES (3, 1, 700, 720, TO_DATE('1-1-2020', 'dd-mm-yyyy'), TO_DATE('31-12-2020', 'dd-mm-yyyy'));
INSERT INTO planoRega(codPlanoRega, codEstacaoMeteorologica, periodicidade, tempoRega, dataInicio, dataFim) VALUES (4, 1, 123, 456, TO_DATE('1-9-2023', 'dd-mm-yyyy'), TO_DATE('31-8-2024', 'dd-mm-yyyy'));

-- ## tabela Rega ##
INSERT INTO rega(codRega, codTipoRega, codPlanoRega, codParcela, quantidade, dataRega) VALUES (1, 1, 1, 1, 5, TO_DATE('4-1-2021 12:00:00', 'dd-mm-yyyy hh24:mi:ss'));
INSERT INTO rega(codRega, codTipoRega, codPlanoRega, codParcela, quantidade, dataRega) VALUES (2, 2, 4, 2, 12.5, TO_DATE('28-12-2023 15:30:00', 'dd-mm-yyyy hh24:mi:ss'));
INSERT INTO rega(codRega, codTipoRega, codPlanoRega, codParcela, quantidade, dataRega) VALUES (3, 1, 2, 1, 10, TO_DATE('4-9-2022 20:00:00', 'dd-mm-yyyy hh24:mi:ss'));
INSERT INTO rega(codRega, codTipoRega, codPlanoRega, codParcela, quantidade, dataRega) VALUES (4, 2, 3, 3, 2, TO_DATE('18-12-2020 12:00:00', 'dd-mm-yyyy hh24:mi:ss'));
INSERT INTO rega(codRega, codTipoRega, codPlanoRega, codParcela, quantidade, dataRega) VALUES (5, 2, 3, 3, 20, TO_DATE('18-12-2020 23:35:00', 'dd-mm-yyyy hh24:mi:ss'));
INSERT INTO rega(codRega, codTipoRega, codPlanoRega, codParcela, quantidade, dataRega) VALUES (6, 2, 4, 2, 18.9, TO_DATE('28-12-2023 17:32:30', 'dd-mm-yyyy hh24:mi:ss'));

-- ## tabela Sensor ##
INSERT INTO sensor(codSensor, codTipoSensor, valorReferencia) VALUES ('00000', 1, 25);
INSERT INTO sensor(codSensor, codTipoSensor, valorReferencia) VALUES ('00001', 2, 30);
INSERT INTO sensor(codSensor, codTipoSensor, valorReferencia) VALUES ('00002', 3, 45);
INSERT INTO sensor(codSensor, codTipoSensor, valorReferencia) VALUES ('00003', 4, 90);
INSERT INTO sensor(codSensor, codTipoSensor, valorReferencia) VALUES ('00004', 5, 15);
INSERT INTO sensor(codSensor, codTipoSensor, valorReferencia) VALUES ('00005', 6, 20);
INSERT INTO sensor(codSensor, codTipoSensor, valorReferencia) VALUES ('00006', 7, 35);

-- ## tabela LeituraSensor ##
INSERT INTO leituraSensor(codLeituraSensor, codEstacaoMeteorologica, codSensor, resultado, dataLeitura) VALUES (1, 1, '00000', 50, TO_DATE('1-2-2022 21:02:00', 'dd-mm-yyyy hh24:mi:ss'));
INSERT INTO leituraSensor(codLeituraSensor, codEstacaoMeteorologica, codSensor, resultado, dataLeitura) VALUES (2, 1, '00001', 14, TO_DATE('15-4-2022 11:00:00', 'dd-mm-yyyy hh24:mi:ss'));
INSERT INTO leituraSensor(codLeituraSensor, codEstacaoMeteorologica, codSensor, resultado, dataLeitura) VALUES (3, 1, '00002', 50, TO_DATE('29-3-2022 12:23:00', 'dd-mm-yyyy hh24:mi:ss'));
INSERT INTO leituraSensor(codLeituraSensor, codEstacaoMeteorologica, codSensor, resultado, dataLeitura) VALUES (4, 1, '00003', 8, TO_DATE('3-3-2022 08:03:00', 'dd-mm-yyyy hh24:mi:ss'));
INSERT INTO leituraSensor(codLeituraSensor, codEstacaoMeteorologica, codSensor, resultado, dataLeitura) VALUES (5, 1, '00004', 14, TO_DATE('1-3-2022 23:43:00', 'dd-mm-yyyy hh24:mi:ss'));
INSERT INTO leituraSensor(codLeituraSensor, codEstacaoMeteorologica, codSensor, resultado, dataLeitura) VALUES (6, 1, '00005', 75, TO_DATE('1-4-2022 21:00:00', 'dd-mm-yyyy hh24:mi:ss'));
INSERT INTO leituraSensor(codLeituraSensor, codEstacaoMeteorologica, codSensor, resultado, dataLeitura) VALUES (7, 1, '00006', 100, TO_DATE('8-2-2022 22:00:00', 'dd-mm-yyyy hh24:mi:ss'));

-- ## tabela operacaoAgricola ##
INSERT INTO operacaoAgricola(codOperacaoAgricola, codPlantacao, codFatorProducao, codFormaAplicacao, quantidade, dataOperacaoAgricola, cancelada) VALUES (1, 1, 1, 1, 20.5, TO_DATE('17-2-2023 21:02:00', 'dd-mm-yyyy hh24:mi:ss'), 0);
INSERT INTO operacaoAgricola(codOperacaoAgricola, codPlantacao, codFatorProducao, codFormaAplicacao, quantidade, dataOperacaoAgricola, cancelada) VALUES (2, 2, 2, 2, 10.4, TO_DATE('5-1-2023 18:00:00', 'dd-mm-yyyy hh24:mi:ss'), 0);
INSERT INTO operacaoAgricola(codOperacaoAgricola, codPlantacao, codFatorProducao, codFormaAplicacao, quantidade, dataOperacaoAgricola, cancelada) VALUES (3, 3, 3, 3, 19.9, TO_DATE('13-2-2023 13:20:00', 'dd-mm-yyyy hh24:mi:ss'), 1);
INSERT INTO operacaoAgricola(codOperacaoAgricola, codPlantacao, codFatorProducao, codFormaAplicacao, quantidade, dataOperacaoAgricola, cancelada) VALUES (4, 4, 1, 3, 30, TO_DATE('9-3-2023 15:35:00', 'dd-mm-yyyy hh24:mi:ss'), 0);
INSERT INTO operacaoAgricola(codOperacaoAgricola, codPlantacao, codFatorProducao, codFormaAplicacao, quantidade, dataOperacaoAgricola, cancelada) VALUES (5, 5, 3, 3, 55, TO_DATE('1-2-2023 10:55:00', 'dd-mm-yyyy hh24:mi:ss'), 0);
INSERT INTO operacaoAgricola(codOperacaoAgricola, codPlantacao, codFatorProducao, codFormaAplicacao, quantidade, dataOperacaoAgricola, cancelada) VALUES (6, 2, 2, 3, 90, TO_DATE('5-1-2023 15:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1);
INSERT INTO operacaoAgricola(codOperacaoAgricola, codPlantacao, codFatorProducao, codFormaAplicacao, quantidade, dataOperacaoAgricola, cancelada) VALUES (7, 3, 1, 3, 11.2, TO_DATE('29-1-2023 16:15:00', 'dd-mm-yyyy hh24:mi:ss'), 0);
INSERT INTO operacaoAgricola(codOperacaoAgricola, codPlantacao, codFatorProducao, codFormaAplicacao, quantidade, dataOperacaoAgricola, cancelada) VALUES (8, 1, 1, 1, 21, TO_DATE('15-1-2019 15:30:00', 'dd-mm-yyyy hh24:mi:ss'), 0);
INSERT INTO operacaoAgricola(codOperacaoAgricola, codPlantacao, codFatorProducao, codFormaAplicacao, quantidade, dataOperacaoAgricola, cancelada) VALUES (9, 1, 2, 1, 21, TO_DATE('10-1-2019 17:55:01', 'dd-mm-yyyy hh24:mi:ss'), 0);
INSERT INTO operacaoAgricola(codOperacaoAgricola, codPlantacao, codFatorProducao, codFormaAplicacao, quantidade, dataOperacaoAgricola, cancelada) VALUES (10, 2, 5, 1, 21, TO_DATE('17-1-2019 17:55:00', 'dd-mm-yyyy hh24:mi:ss'), 0);
INSERT INTO operacaoAgricola(codOperacaoAgricola, codPlantacao, codFatorProducao, codFormaAplicacao, quantidade, dataOperacaoAgricola, cancelada) VALUES (99999998, 1, 1, 1, 20, TO_DATE('1-1-3000 21:00:00', 'dd-mm-yyyy hh24:mi:ss'), 1);
INSERT INTO operacaoAgricola(codOperacaoAgricola, codPlantacao, codFatorProducao, codFormaAplicacao, quantidade, dataOperacaoAgricola, cancelada) VALUES (99999999, 1, 1, 1, 20, TO_DATE('1-1-3000 21:00:00', 'dd-mm-yyyy hh24:mi:ss'), 0);

--## tabela Colheita ##
INSERT INTO colheita(codColheita, codPlantacao, codProduto, dataColheita, quantidade) VALUES (1, 1, 1, TO_DATE('17-2-2022', 'dd-mm-yyyy'), 50);
INSERT INTO colheita(codColheita, codPlantacao, codProduto, dataColheita, quantidade) VALUES (2, 2, 2, TO_DATE('1-5-2022', 'dd-mm-yyyy'), 35);
INSERT INTO colheita(codColheita, codPlantacao, codProduto, dataColheita, quantidade) VALUES (3, 3, 3, TO_DATE('13-4-2022', 'dd-mm-yyyy'), 130);
INSERT INTO colheita(codColheita, codPlantacao, codProduto, dataColheita, quantidade) VALUES (4, 4, 4, TO_DATE('16-9-2022', 'dd-mm-yyyy'), 85);
INSERT INTO colheita(codColheita, codPlantacao, codProduto, dataColheita, quantidade) VALUES (5, 5, 1, TO_DATE('31-12-2022', 'dd-mm-yyyy'), 15);
INSERT INTO colheita(codColheita, codPlantacao, codProduto, dataColheita, quantidade) VALUES (6, 6, 2, TO_DATE('1-1-2022', 'dd-mm-yyyy'), 65);
INSERT INTO colheita(codColheita, codPlantacao, codProduto, dataColheita, quantidade) VALUES (7, 7, 1, TO_DATE('15-7-2022', 'dd-mm-yyyy'), 105);

--## tabela tipoAlteracao ##
INSERT INTO tipoAlteracao(codTipoAlteracao, descricao) VALUES(1, 'INSERT');
INSERT INTO tipoAlteracao(codTipoAlteracao, descricao) VALUES(2, 'UPDATE');
INSERT INTO tipoAlteracao(codTipoAlteracao, descricao) VALUES(3, 'DELETE');

--## tabela pistaAuditoriaOperacao##
INSERT INTO pistaAuditoriaOperacao(codPistaAuditoriaOperacao, codOperacaoAgricola, codTipoAlteracao, codUtilizador, dataAlteracao) VALUES(1, 1, 1, 12345671, TO_DATE('29-12-2022 16:15:00', 'dd-mm-yyyy hh24:mi:ss'));
INSERT INTO pistaAuditoriaOperacao(codPistaAuditoriaOperacao, codOperacaoAgricola, codTipoAlteracao, codUtilizador, dataAlteracao) VALUES(2, 2, 2, 87654321, TO_DATE('21-10-2022 16:15:00', 'dd-mm-yyyy hh24:mi:ss'));
INSERT INTO pistaAuditoriaOperacao(codPistaAuditoriaOperacao, codOperacaoAgricola, codTipoAlteracao, codUtilizador, dataAlteracao) VALUES(3, 3, 3, 34541241, TO_DATE('1-1-2023 16:15:00', 'dd-mm-yyyy hh24:mi:ss'));

--## tabela inputSensor ##
-- Registos válidos
INSERT INTO inputSensor(inputString) VALUES('00000PL078025010120231403');
INSERT INTO inputSensor(inputString) VALUES('00001TS100030020120231133');
INSERT INTO inputSensor(inputString) VALUES('00002HS034045010120231243');
INSERT INTO inputSensor(inputString) VALUES('00003VV055090020120230913');
INSERT INTO inputSensor(inputString) VALUES('00004TA001015030120231153');
INSERT INTO inputSensor(inputString) VALUES('00005HA034020020120232133');
INSERT INTO inputSensor(inputString) VALUES('00006PA059035030120232343');
INSERT INTO inputSensor(inputString) VALUES('00007TA021019010120232003');
-- Registo inválidos
INSERT INTO inputSensor(inputString) VALUES('00005--034020020120232133');
INSERT INTO inputSensor(inputString) VALUES('00005HA933020020120232133');
INSERT INTO inputSensor(inputString) VALUES('00005HA!!!020020120232133');
INSERT INTO inputSensor(inputString) VALUES('00005HA0340200201202321||');
INSERT INTO inputSensor(inputString) VALUES('00005HA034020020120232599');
INSERT INTO inputSensor(inputString) VALUES('00005H|0-4020020120232?33');
INSERT INTO inputSensor(inputString) VALUES('00005H!034020020120!32133');
INSERT INTO inputSensor(inputString) VALUES('00005HA0340200-0120232133');
INSERT INTO inputSensor(inputString) VALUES('0!!05H-034%%%020120?3--33');
INSERT INTO inputSensor(inputString) VALUES('000');
INSERT INTO inputSensor(inputString) VALUES('                         ');

--## tabela InputHub ##
INSERT INTO inputHub(inputString) VALUES('CT1;40.6389;-8.6553;C1');
INSERT INTO inputHub(inputString) VALUES('CT2;38.0333;-7.8833;C2');
INSERT INTO inputHub(inputString) VALUES('CT14;38.5243;-8.8926;E1');
INSERT INTO inputHub(inputString) VALUES('CT11;39.3167;-7.4167;E2');
INSERT INTO inputHub(inputString) VALUES('CT10;39.7444;-8.8072;P3');