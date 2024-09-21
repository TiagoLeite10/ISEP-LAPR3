-- ************************************** RESTRIÇÕES DE DOMÍNIO ************************************** --
-- Tabela estadoEncomenda
-- Reprovado, pois a descrição não pode ser nula
INSERT INTO estadoEncomenda(codEstadoEncomenda) VALUES (200);
-- Aprovado
INSERT INTO estadoEncomenda(codEstadoEncomenda, descricao) VALUES (200, 'descricao');
-- Reprovado, pois a descrição não é unica
INSERT INTO estadoEncomenda(codEstadoEncomenda, descricao) VALUES (201, 'descricao');

-- Tabela unidade
-- Reprovado, pois a descrição não pode ser nula
INSERT INTO unidade(codUnidade) VALUES (200);
-- Aprovado
INSERT INTO unidade(codUnidade, descricao) VALUES (200, 'descricao');
-- Reprovado, pois a descrição não é unica
INSERT INTO unidade(codUnidade, descricao) VALUES (201, 'descricao');

-- Tabela fatorProducaoAplicacao
-- Reprovado, pois a descrição não pode ser nula
INSERT INTO fatorProducaoAplicacao(codFatorProducaoAplicacao) VALUES (200);
-- Aprovado
INSERT INTO fatorProducaoAplicacao(codFatorProducaoAplicacao, descricao) VALUES (200, 'descricao');
-- Reprovado, pois a descrição não é unica
INSERT INTO fatorProducaoAplicacao(codFatorProducaoAplicacao, descricao) VALUES (201, 'descricao');

-- Tabela fatorProducaoClassificacao
-- Reprovado, pois a descrição não pode ser nula
INSERT INTO fatorProducaoClassificacao(codFatorProducaoClassificacao) VALUES (200);
-- Aprovado
INSERT INTO fatorProducaoClassificacao(codFatorProducaoClassificacao, descricao) VALUES (200, 'descricao');
-- Reprovado, pois a descrição não é unica
INSERT INTO fatorProducaoClassificacao(codFatorProducaoClassificacao, descricao) VALUES (201, 'descricao');

-- Tabela formulacaoProduto
-- Reprovado, pois a descrição não pode ser nula
INSERT INTO formulacaoProduto(codFormulacaoProduto) VALUES (200);
-- Aprovado
INSERT INTO formulacaoProduto(codFormulacaoProduto, descricao) VALUES (200, 'descricao');
-- Reprovado, pois a descrição não é unica
INSERT INTO formulacaoProduto(codFormulacaoProduto, descricao) VALUES (201, 'descricao');

-- Tabela instalacaoAgricola
-- Reprovado, pois o nome não pode ser nulo
INSERT INTO instalacaoAgricola(codInstalacaoAgricola) VALUES (200);
-- Aprovado
INSERT INTO instalacaoAgricola(codInstalacaoAgricola, nome) VALUES (200, 'nome');
-- Reprovado, pois o nome não é unico
INSERT INTO instalacaoAgricola(codInstalacaoAgricola, nome) VALUES (201, 'nome');

-- Tabela objetivoCultura
-- Reprovado, pois a descrição não pode ser nula
INSERT INTO objetivoCultura(codObjetivoCultura) VALUES (200);
-- Aprovado
INSERT INTO objetivoCultura(codObjetivoCultura, descricao) VALUES (200, 'descricao');
-- Reprovado, pois a descrição não é unica
INSERT INTO objetivoCultura(codObjetivoCultura, descricao) VALUES (201, 'descricao');

-- Tabela tipoCultura
-- Reprovado, pois a descrição não pode ser nula
INSERT INTO tipoCultura(codTipoCultura) VALUES (200);
-- Aprovado
INSERT INTO tipoCultura(codTipoCultura, descricao) VALUES (200, 'descricao');
-- Reprovado, pois a descrição não é unica
INSERT INTO tipoCultura(codTipoCultura, descricao) VALUES (201, 'descricao');

-- Tabela tipoEdificio
-- Reprovado, pois o nome não pode ser nulo
INSERT INTO tipoEdificio(codTipoEdificio) VALUES (200);
-- Aprovado
INSERT INTO tipoEdificio(codTipoEdificio, nome) VALUES (200, 'nome');
-- Reprovado, pois o nome não é unico
INSERT INTO tipoEdificio(codTipoEdificio, nome) VALUES (201, 'nome');

-- Tabela Iva
-- Reprovado, pois a percentagemIva não pode ser nula
INSERT INTO iva(codIva) VALUES (200);
-- Aprovado
INSERT INTO iva(codIva, percentagemIva) VALUES (200, 70);
-- Reprovado, pois a percentagemIva não é unica
INSERT INTO iva(codIva, percentagemIva) VALUES (201, 70);

-- Tabela produto
-- Reprovado, pois o codIva não pode ser nulo
INSERT INTO produto(codProduto, nome, valorProduto) VALUES (200, 'nome', 50);
-- Reprovado, pois o nome não pode ser nulo
INSERT INTO produto(codProduto, codIva, valorProduto) VALUES (200, 1, 50);
-- Reprovado, pois o valorProduto não pode ser nulo
INSERT INTO produto(codProduto, codIva, nome) VALUES (200, 1, 'nome');
-- Aprovado
INSERT INTO produto(codProduto, codIva, nome, valorProduto) VALUES (200, 1, 'nome', 70);
-- Reprovado, pois o nome não é unico
INSERT INTO produto(codProduto, codIva, nome, valorProduto) VALUES (201, 1, 'nome', 70);

-- Tabela tipoComposicao
-- Reprovado, pois o codUnidade não pode ser nulo
INSERT INTO tipoComposicao(codTipoComposicao, nome) VALUES (200, 'nome');
-- Reprovado, pois o nome não pode ser nulo
INSERT INTO tipoComposicao(codTipoComposicao, codUnidade) VALUES (200, 1);
-- Aprovado
INSERT INTO tipoComposicao(codTipoComposicao, codUnidade, nome) VALUES (200, 1, 'nome');
-- Reprovado, pois a percentagemIva não é unica
INSERT INTO tipoComposicao(codTipoComposicao, codUnidade, nome) VALUES (201, 1, 'nome');

-- Tabela tipoCliente
-- Reprovado, pois a descricao não pode ser nula
INSERT INTO tipoCliente(codTipoCliente) VALUES (200);
-- Aprovado
INSERT INTO tipoCliente(codTipoCliente, descricao) VALUES (200, 'descricao');
-- Reprovado, pois a descricao não é unica
INSERT INTO tipoCliente(codTipoCliente, descricao) VALUES (201, 'descricao');

-- Tabela cliente
-- Reprovado, pois o nome não pode ser nulo
INSERT INTO cliente(codInternoCliente, numeroFiscal, email, plafond, codTipoCliente) VALUES (200, 123456789, 'email@gmail.com', 6, 1);
-- Reprovado, pois o numeroFiscal não pode ser nulo
INSERT INTO cliente(codInternoCliente, nome, email, plafond, codTipoCliente) VALUES (200, 'nome', 'email@gmail.com', 6, 1);
-- Reprovado, pois o email não pode ser nulo
INSERT INTO cliente(codInternoCliente, nome, numeroFiscal, plafond, codTipoCliente) VALUES (200, 'nome', 123456789, 6, 1);
-- Reprovado, pois o plafond não pode ser nulo
INSERT INTO cliente(codInternoCliente, nome, numeroFiscal, email, codTipoCliente) VALUES (200, 'nome', 123456789, 'email@gmail.com', 1);
-- Reprovado, pois o codTipoCliente não pode ser nulo
INSERT INTO cliente(codInternoCliente, nome, numeroFiscal, email, plafond) VALUES (200, 'nome', 123456789, 'email@gmail.com', 6);
-- Aprovado
INSERT INTO cliente(codInternoCliente, nome, numeroFiscal, email, plafond, codTipoCliente) VALUES (200, 'nome', 123456789, 'email@gmail.com', 6, 1);
-- Reprovado, pois o numeroFiscal não é unico
INSERT INTO cliente(codInternoCliente, nome, numeroFiscal, email, plafond, codTipoCliente) VALUES (201, 'nome', 123456789, 'laime@gmail.com', 6, 1);
-- Aprovado
INSERT INTO cliente(codInternoCliente, nome, numeroFiscal, email, plafond, codTipoCliente) VALUES (201, 'nome', 987654321, 'laime@gmail.com', 6, 1);
-- Reprovado, pois o email não é unico
INSERT INTO cliente(codInternoCliente, nome, numeroFiscal, email, plafond, codTipoCliente) VALUES (202, 'nome', 567432891, 'laime@gmail.com', 6, 1);

-- Tabela tipoHub
-- Reprovado, pois a descricao não pode ser nula
INSERT INTO tipoHub(codTipoHub) VALUES (200);
-- Aprovado
INSERT INTO tipoHub(codTipoHub, descricao) VALUES (200, 'descricao');
-- Reprovado, pois a descricao não é unica
INSERT INTO tipoHub(codTipoHub, descricao) VALUES (201, 'descricao');

-- Tabela hub
-- Reprovado, pois o codTipoHub não pode ser nulo
INSERT INTO hub(codHub, latitude, longitude) VALUES ('CT200', 9.4253, -3.9323);
-- Reprovado, pois a latitude não pode ser nula
INSERT INTO hub(codHub, codTipoHub, longitude) VALUES ('CT200', 1, -3.9323);
-- Reprovado, pois o longitude não pode ser nulo
INSERT INTO hub(codHub, codTipoHub, latitude) VALUES ('CT200', 1, 9.4253);
-- Aprovado
INSERT INTO hub(codHub, codTipoHub, latitude, longitude) VALUES ('CT200', 1, 9.4253, -3.9323);

-- Tabela encomenda
-- Reprovado, pois o codInternoCliente não pode ser nulo
INSERT INTO encomenda(codEncomenda, codEstadoEncomenda, codHub, dataEncomenda) VALUES (200, 1, 'CT1', TO_DATE('1/1/2023', 'dd/mm/yy'));
-- Reprovado, pois o codEstadoEncomenda não pode ser nulo
INSERT INTO encomenda(codEncomenda, codInternoCliente, codHub, dataEncomenda) VALUES (200, 1, 'CT1', TO_DATE('1/1/2023', 'dd/mm/yy'));
-- Reprovado, pois o codHub não pode ser nulo
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, dataEncomenda) VALUES (200, 1, 1, TO_DATE('1/1/2023', 'dd/mm/yy'));
-- Reprovado, pois a dataEncomenda não pode ser nula
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub) VALUES (200, 1, 1, 'CT1');
-- Aprovado
INSERT INTO encomenda(codEncomenda, codInternoCliente, codEstadoEncomenda, codHub, dataEncomenda) VALUES (200, 1, 1, 'CT1', TO_DATE('1/1/2023', 'dd/mm/yy'));

-- Tabela edificio
-- Reprovado, pois o nome não pode ser nulo
INSERT INTO edificio(codEdificio, codTipoEdificio, codInstalacaoAgricola) VALUES (200, 1, 1);
-- Reprovado, pois o codTipoEdificio não pode ser nulo
INSERT INTO edificio(codEdificio, nome, codInstalacaoAgricola) VALUES (200, 'nome', 1);
-- Reprovado, pois o codInstalacaoAgricola não pode ser nulo
INSERT INTO edificio(codEdificio, nome, codTipoEdificio) VALUES (200, 'nome', 1);
-- Aprovado
INSERT INTO edificio(codEdificio, nome, codTipoEdificio, codInstalacaoAgricola) VALUES (200, 'nome', 1, 1);
-- Reprovado, pois o nome não é unico
INSERT INTO edificio(codEdificio, nome, codTipoEdificio, codInstalacaoAgricola) VALUES (201, 'nome', 1, 1);

-- Tabela cultura
-- Reprovado, pois o codObjetivoCultura não pode ser nulo
INSERT INTO cultura(codCultura, codTipoCultura, nome) VALUES (200, 1, 'nome');
-- Reprovado, pois o codTipoCultura não pode ser nulo
INSERT INTO cultura(codCultura, codObjetivoCultura, nome) VALUES (200, 1, 1, 'nome');
-- Reprovado, pois o nome não pode ser nulo
INSERT INTO cultura(codCultura, codObjetivoCultura, codTipoCultura) VALUES (200, 1, 1);
-- Aprovado
INSERT INTO cultura(codCultura, codObjetivoCultura, codTipoCultura, nome) VALUES (200, 1, 1, 'nome');
-- Reprovado, pois o nome não é unico
INSERT INTO cultura(codCultura, codObjetivoCultura, codTipoCultura, nome) VALUES (201, 1, 1, 'nome');

-- Tabela fichaTecnicaTipoComposicao
-- Reprovado, pois o codUnidade não pode ser nulo
INSERT INTO fichaTecnicaTipoComposicao(codFichaTecnicaTipoComposicao, nome) VALUES (200, 'nome');
-- Reprovado, pois o nome não pode ser nulo
INSERT INTO fichaTecnicaTipoComposicao(codFichaTecnicaTipoComposicao, codUnidade) VALUES (200, 1);
-- Aprovado
INSERT INTO fichaTecnicaTipoComposicao(codFichaTecnicaTipoComposicao, codUnidade, nome) VALUES (200, 1, 'nome');
-- Reprovado, pois o nome não é unico
INSERT INTO fichaTecnicaTipoComposicao(codFichaTecnicaTipoComposicao, codUnidade, nome) VALUES (201, 1, 'nome');

-- Tabela fornecedor
-- Reprovado, pois o nome não pode ser nulo
INSERT INTO fornecedor(codFornecedor) VALUES (200);
-- Aprovado
INSERT INTO fornecedor(codFornecedor, nome) VALUES (200, 'nome');

-- Tabela fatorProducao
-- Reprovado, pois o nomeComercial não pode ser nulo
INSERT INTO fatorProducao(codFatorProducao, codFatorProducaoAplicacao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor) VALUES (200, 1, 1, 1, 1);
-- Reprovado, pois o codFatorProducaoAplicacao não pode ser nulo
INSERT INTO fatorProducao(codFatorProducao, nomeComercial, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor) VALUES (200, 'nome comercial', 1, 1, 1);
-- Reprovado, pois o codFatorProducaoClassificacao não pode ser nulo
INSERT INTO fatorProducao(codFatorProducao, nomeComercial, codFatorProducaoAplicacao, codFormulacaoProduto, codFornecedor) VALUES (200, 'nome comercial', 1, 1, 1);
-- Reprovado, pois o codFormulacaoProduto não pode ser nulo
INSERT INTO fatorProducao(codFatorProducao, nomeComercial, codFatorProducaoAplicacao, codFatorProducaoClassificacao, codFornecedor) VALUES (200, 'nome comercial', 1, 1, 1);
-- Reprovado, pois o codFornecedor não pode ser nulo
INSERT INTO fatorProducao(codFatorProducao, nomeComercial, codFatorProducaoAplicacao, codFatorProducaoClassificacao, codFormulacaoProduto) VALUES (200, 'nome comercial', 1, 1, 1);
-- Aprovado
INSERT INTO fatorProducao(codFatorProducao, nomeComercial, codFatorProducaoAplicacao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor) VALUES (200, 'nome comercial', 1, 1, 1, 1);
-- Reprovado, pois o nome não é unico
INSERT INTO fatorProducao(codFatorProducao, nomeComercial, codFatorProducaoAplicacao, codFatorProducaoClassificacao, codFormulacaoProduto, codFornecedor) VALUES (201, 'nome comercial', 1, 1, 1, 1);

-- Tabela fichaTecnica
-- Reprovado, pois o codFatorProducao não pode ser nulo
INSERT INTO linhaFichaTecnica(codFichaTecnicaTipoComposicao, quantidade) VALUES (200, 50);
-- Reprovado, pois o codFichaTecnicaTipoComposicao não pode ser nulo
INSERT INTO linhaFichaTecnica(codFatorProducao, quantidade) VALUES (1, 50);
-- Reprovado, pois o quantidade não pode ser nulo
INSERT INTO linhaFichaTecnica(codFatorProducao, codFichaTecnicaTipoComposicao) VALUES (1, 200);
-- Aprovado
INSERT INTO linhaFichaTecnica(codFatorProducao, codFichaTecnicaTipoComposicao, quantidade) VALUES (1, 200, 50);

-- Tabela registoEncomenda
-- Reprovado, pois o codProduto não pode ser nulo
INSERT INTO registoEncomenda(codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (200, 50, 30, 23);
-- Reprovado, pois o codEncomenda não pode ser nulo
INSERT INTO registoEncomenda(codProduto, quantidade, valorProduto, percentagemIva) VALUES (200, 50, 30, 23);
-- Reprovado, pois a quantidade não pode ser nula
INSERT INTO registoEncomenda(codProduto, codEncomenda, valorProduto, percentagemIva) VALUES (200, 200, 30, 23);
-- Reprovado, pois o valorProduto não pode ser nulo
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, percentagemIva) VALUES (200, 200, 50, 23);
-- Reprovado, pois a percentagemIva não pode ser nula
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto) VALUES (200, 200, 50, 30);
-- Aprovado
INSERT INTO registoEncomenda(codProduto, codEncomenda, quantidade, valorProduto, percentagemIva) VALUES (200, 200, 50, 30, 23);

-- Tabela liquidoRega
-- Reprovado, pois a descricao não pode ser nula
INSERT INTO liquidoRega(codLiquidoRega) VALUES (200);
-- Aprovado
INSERT INTO liquidoRega(codLiquidoRega, descricao) VALUES (200, 'descricao');
-- Reprovado, pois o descricao não é unica
INSERT INTO liquidoRega(codLiquidoRega, descricao) VALUES (201, 'descricao');

-- Tabela metodoRega
-- Reprovado, pois a descricao não pode ser nula
INSERT INTO metodoRega(codMetodoRega) VALUES (200);
-- Aprovado
INSERT INTO metodoRega(codMetodoRega, descricao) VALUES (200, 'descricao');
-- Reprovado, pois o descricao não é unica
INSERT INTO metodoRega(codMetodoRega, descricao) VALUES (201, 'descricao');

-- Tabela tipoRega
-- Reprovado, pois o codLiquidoRega não pode ser nulo
INSERT INTO tipoRega(codTipoRega, codMetodoRega) VALUES (200, 1);
-- Reprovado, pois o codMetodoRega não pode ser nulo
INSERT INTO tipoRega(codTipoRega, codLiquidoRega) VALUES (200, 1);
-- Aprovado
INSERT INTO tipoRega(codTipoRega, codLiquidoRega, codMetodoRega) VALUES (200, 1, 1);

-- Tabela rega
-- Reprovado, pois o codTipoRega não pode ser nulo
INSERT INTO rega(codRega) VALUES (200);
-- Aprovado
INSERT INTO rega(codRega, codTipoRega) VALUES (200, 1);

-- Tabela tipoSensor
-- Reprovado, pois a descricao não pode ser nula
INSERT INTO tipoSensor(codTipoSensor, acronimo) VALUES (200, 'ET');
-- Aprovado
INSERT INTO tipoSensor(codTipoSensor, acronimo, descricao) VALUES (200, 'ET', 'Estabilidade da terra');
-- Reprovado, pois a descricao não é unica
INSERT INTO tipoSensor(codTipoSensor, acronimo, descricao) VALUES (201, 'TE', 'Estabilidade da terra');

-- Tabela sensor
-- Reprovado, pois o codTipoSensor não pode ser nulo
INSERT INTO sensor(codSensor, valorReferencia) VALUES ('00200', 7838974322423);
-- Aprovado
INSERT INTO sensor(codSensor, codTipoSensor, valorReferencia) VALUES ('00200', 1, 7838974322423);

-- Tabela estacaoMeteorologica
-- Reprovado, pois o nome não pode ser nulo
INSERT INTO estacaoMeteorologica(codEstacaoMeteorologica) VALUES (200);
-- Aprovado
INSERT INTO estacaoMeteorologica(codEstacaoMeteorologica, nome) VALUES (200, 'nome');
-- Reprovado, pois o nome não é unico
INSERT INTO estacaoMeteorologica(codEstacaoMeteorologica, nome) VALUES (201, 'nome');

-- Tabela leituraSensor
-- Reprovado, pois o codEstacaoMeteorologica não pode ser nulo
INSERT INTO leituraSensor(codLeituraSensor, codSensor, resultado) VALUES (200, '00001', 35);
-- Reprovado, pois o codSensor não pode ser nulo
INSERT INTO leituraSensor(codLeituraSensor, codEstacaoMeteorologica, resultado) VALUES (200, 1, 35);
-- Reprovado, pois o resultado não pode ser nulo
INSERT INTO leituraSensor(codLeituraSensor, codEstacaoMeteorologica, codSensor) VALUES (200, 1, '00001');
-- Aprovado
INSERT INTO leituraSensor(codLeituraSensor, codEstacaoMeteorologica, codSensor, resultado) VALUES (200, 1, '00001', 35);

-- Tabela planoRega
-- Reprovado, pois o codRega não pode ser nulo
INSERT INTO planoRega(codPlanoRega, codEstacaoMeteorologica, periodicidade, tempoRega) VALUES (200, 1, 100, 10);
-- Reprovado, pois o codEstacaoMeteorologica não pode ser nulo
INSERT INTO planoRega(codPlanoRega, codRega, periodicidade, tempoRega) VALUES (200, 1, 100, 10);
-- Reprovado, pois a periodicidade não pode ser nula
INSERT INTO planoRega(codPlanoRega, codRega, codEstacaoMeteorologica, tempoRega) VALUES (200, 1, 1, 10);
-- Reprovado, pois o tempoRega não pode ser nulo
INSERT INTO planoRega(codPlanoRega, codRega, codEstacaoMeteorologica, periodicidade) VALUES (200, 1, 1, 100);
-- Aprovado
INSERT INTO planoRega(codPlanoRega, codRega, codEstacaoMeteorologica, periodicidade, tempoRega) VALUES (200, 1, 1, 100, 10);

-- Tabela parcela
-- Reprovado, pois o codInstalacaoAgricola não pode ser nulo
INSERT INTO parcela(codParcela, designacao, area) VALUES (200, 'designacao', 50);
-- Reprovado, pois a designacao não pode ser nula
INSERT INTO parcela(codParcela, codInstalacaoAgricola, area) VALUES (200, 1, 50);
-- Reprovado, pois a area não pode ser nula
INSERT INTO parcela(codParcela, codInstalacaoAgricola, designacao) VALUES (200, 1, 'designacao');
-- Aprovado
INSERT INTO parcela(codParcela, codInstalacaoAgricola, designacao, area) VALUES (200, 1, 'designacao', 50);

-- Tabela plantacao
-- Reprovado, pois o codParcela não pode ser nulo
INSERT INTO plantacao(codPlantacao, codCultura, dataInicio, dataFim, area) VALUES (200, 1, TO_DATE('1/1/2023', 'dd/mm/yy'), TO_DATE('1/4/2023', 'dd/mm/yy'), 50);
-- Reprovado, pois o codCultura não pode ser nulo
INSERT INTO plantacao(codPlantacao, codParcela, dataInicio, dataFim, area) VALUES (200, 1, TO_DATE('1/1/2023', 'dd/mm/yy'), TO_DATE('1/4/2023', 'dd/mm/yy'), 50);
-- Reprovado, pois a dataInicio não pode ser nula
INSERT INTO plantacao(codPlantacao, codParcela, codCultura, dataFim, area) VALUES (200, 1, 1, TO_DATE('1/4/2023', 'dd/mm/yy'), 50);
-- Reprovado, pois a dataFim não pode ser nula
INSERT INTO plantacao(codPlantacao, codParcela, codCultura, dataInicio, area) VALUES (200, 1, 1, TO_DATE('1/1/2023', 'dd/mm/yy'), 50);
-- Reprovado, pois a area não pode ser nula
INSERT INTO plantacao(codPlantacao, codParcela, codCultura, dataInicio, dataFim) VALUES (200, 1, 1, TO_DATE('1/1/2023', 'dd/mm/yy'), TO_DATE('1/4/2023', 'dd/mm/yy'));
-- Aprovado
INSERT INTO plantacao(codPlantacao, codParcela, codCultura, dataInicio, dataFim, area) VALUES (200, 1, 1, TO_DATE('1/1/2023', 'dd/mm/yy'), TO_DATE('1/1/2023', 'dd/mm/yy'), 50);

-- Tabela colheita
-- Reprovado, pois o codPlantacao não pode ser nulo
INSERT INTO colheita(codColheita, codProduto, dataColheita, quantidade) VALUES (200, 1, TO_DATE('1/1/2023', 'dd/mm/yy'), 50);
-- Reprovado, pois o codProduto não pode ser nulo
INSERT INTO colheita(codColheita, codPlantacao, dataColheita, quantidade) VALUES (200, 1, TO_DATE('1/1/2023', 'dd/mm/yy'), 50);
-- Reprovado, pois a dataColheita não pode ser nula
INSERT INTO colheita(codColheita, codPlantacao, codProduto, quantidade) VALUES (200, 1, 1, 50);
-- Reprovado, pois a quantidade não pode ser nula
INSERT INTO colheita(codColheita, codPlantacao, codProduto, dataColheita) VALUES (200, 1, 1, TO_DATE('1/1/2023', 'dd/mm/yy'));
-- Aprovado
INSERT INTO colheita(codColheita, codPlantacao, codProduto, dataColheita, quantidade) VALUES (200, 1, 1, TO_DATE('1/1/2023', 'dd/mm/yy'), 50);

-- Tabela acao
-- Reprovado, pois o codPlantacao não pode ser nulo
INSERT INTO acao(codAcao, codPlanoRega, codFatorProducao, dataAcao) VALUES (200, 1, 1, TO_DATE('21-2-2022', 'dd-mm-yyyy'));
-- Reprovado, pois a codPlanoRega não pode ser nula
INSERT INTO acao(codAcao, codPlantacao, codFatorProducao, dataAcao) VALUES (200, 1, 1, TO_DATE('21-2-2022', 'dd-mm-yyyy'));
-- Reprovado, pois a codFatorProducao não pode ser nula
INSERT INTO acao(codAcao, codPlantacao, codPlanoRega, dataAcao) VALUES (200, 1, 1, TO_DATE('21-2-2022', 'dd-mm-yyyy'));
-- Reprovado, pois a dataAcao não pode ser nula
INSERT INTO acao(codAcao, codPlantacao, codPlanoRega, codFatorProducao) VALUES (200, 1, 1, 1);
-- Aprovado
INSERT INTO acao(codAcao, codPlantacao, codPlanoRega, codFatorProducao, dataAcao) VALUES (200, 1, 1, 1, TO_DATE('21-2-2022', 'dd-mm-yyyy'));