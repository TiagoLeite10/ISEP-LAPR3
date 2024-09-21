/*
    Função que retorna o fator de risco de um cliente. O fator de risco de um cliente é dado pelo rácio entre o valor total
    dos incidentes observados nos últimos 12 meses e o número de encomendas colocadas depois do último incidente e ainda
    pendentes de pagamento.
    Por exemplo, um cliente que tenha um total de incidentes de 2400€ e tenha feito 3 encomendas depois do último incidente
    que ainda não pagou tem um fator de risco de 800€ (2400/3).
*/
CREATE OR REPLACE FUNCTION fncUS205FatorRiscoCliente(pCodInternoCliente IN cliente.codInternoCliente%TYPE,
                                                    pDataAtual IN encomenda.dataEncomenda%TYPE) RETURN NUMBER IS
    --Encomendas com incidente nos últimos 12 meses
    CURSOR curEncomenda IS
        SELECT codEncomenda
        FROM encomenda
        WHERE codInternoCliente = pCodInternoCliente AND dataEncomenda >= ADD_MONTHS(pDataAtual, - 12)
                                                     AND dataEncomenda <= pDataAtual
                                                     AND dataPagamento IS NOT NULL
                                                     AND dataPagamento > dataVencimento;

    resultado NUMBER;
    temporario NUMBER;
    codigoEncomenda encomenda.codEncomenda%TYPE;
    numeroIncidentes INTEGER;
    numClientes INTEGER;
    clienteInexistente EXCEPTION;
BEGIN
    SELECT COUNT(*) INTO numClientes FROM cliente WHERE codInternoCliente = pCodInternoCliente;

    IF numClientes = 0 THEN
        RAISE clienteInexistente;
    ELSE
        resultado := 0;

        OPEN curEncomenda;
        LOOP
            FETCH curEncomenda INTO codigoEncomenda;
            EXIT WHEN curEncomenda%notfound;
            SELECT SUM(valorProduto * quantidade) INTO temporario FROM registoEncomenda WHERE codEncomenda = codigoEncomenda;
            resultado := resultado + temporario;
        END LOOP;
        CLOSE curEncomenda;

        --Número de encomendas realizadas e não pagas após a data do último incidente
        SELECT COUNT(*) INTO numeroIncidentes FROM encomenda
                WHERE codInternoCliente = pCodInternoCliente AND dataEncomenda >= (SELECT MAX(dataVencimento)
                                                                   FROM encomenda
                                                                   WHERE codInternoCliente = pCodInternoCliente
                                                                   AND dataEncomenda >= ADD_MONTHS(pDataAtual, - 12)
                                                                   AND dataEncomenda <= pDataAtual
                                                                   AND dataPagamento IS NOT NULL
                                                                   AND dataPagamento > dataVencimento) AND dataPagamento IS NULL;
        -- Verificar se existem incidentes
        IF (numeroIncidentes = 0) THEN
            resultado := 0;
        ELSE
            resultado := resultado / numeroIncidentes;
        END IF;

        RETURN resultado;
    END IF;

    EXCEPTION
        WHEN clienteInexistente THEN
           dbms_output.put_line('Não existe nenhum cliente com o código interno ' || pCodInternoCliente || '.');
           RETURN NULL;
END;

-- Bloco anónimo para testar a função que retorna o fator de risco de um cliente
DECLARE
    codInternoCliente cliente.codInternoCliente%TYPE;
    dataAtual encomenda.dataEncomenda%TYPE;
    resultado NUMBER;
BEGIN
    codInternoCliente := 3;
    dataAtual := TO_DATE('04/12/2022', 'dd/mm/yy');

    resultado := fncUS205FatorRiscoCliente(codInternoCliente, dataAtual);

    IF resultado IS NOT NULL THEN
        dbms_output.put_line('Fator de risco do cliente com o id ' || codInternoCliente || ': ' || ROUND(resultado, 2) || '€');
    END IF;
END;

------------------------------------------------------------------------------------------------------------------------

/*
    Função para inserir um novo cliente. Se a inserção for bem-sucedida, o utilizador
    é informado sobre o valor da chave primária do novo cliente.
*/
CREATE OR REPLACE FUNCTION fncUS205CriacaoCliente(
                                            pCodTipoCliente IN cliente.codTipoCliente%TYPE,
                                            pNome IN cliente.nome%TYPE,
                                            pNumeroFiscal IN cliente.numeroFiscal%TYPE,
                                            pEmail IN cliente.email%TYPE,
                                            pPlafond IN cliente.plafond%TYPE) RETURN cliente.codInternoCliente%TYPE IS

    contarEmail NUMBER;
    contarNumeroFiscalCliente NUMBER;
    cod INTEGER;
    emailExistente EXCEPTION;
    nifExistente EXCEPTION;
    plafondNegativo EXCEPTION;
BEGIN
    SELECT COUNT(*) INTO contarEmail FROM cliente WHERE email = pEmail;
            IF contarEmail = 1 THEN
                RAISE emailExistente;
            ELSE
                SELECT COUNT(*) INTO contarNumeroFiscalCliente FROM cliente WHERE numeroFiscal = pNumeroFiscal;
                    IF contarNumeroFiscalCliente = 1 THEN
                        RAISE nifExistente;
                    ELSIF pPlafond <= 0 THEN
                            RAISE plafondNegativo;
                        ELSE
                            SELECT MAX(codInternoCliente)
                            INTO cod
                            FROM cliente;
                            cod := cod + 1;

                            INSERT INTO cliente(codInternoCliente, codTipoCliente, nome,  numeroFiscal, email, plafond)
                            VALUES (cod, pCodTipoCliente, pNome, pNumeroFiscal, pEmail, pPlafond);

                            RETURN cod;
                    END IF;
            END IF;
    EXCEPTION
        WHEN emailExistente THEN
        dbms_output.put_line('Já existe um cliente com o email indicado!!');
        RETURN NULL;

        WHEN nifExistente THEN
        dbms_output.put_line('Já existe um cliente com o nif indicado!');
	  RETURN NULL;

        WHEN plafondNegativo THEN
        dbms_output.put_line('O plafond não pode ser negativo!');
	  RETURN NULL;

        WHEN DUP_VAL_ON_INDEX THEN
	  dbms_output.put_line('Não foi possível inserir o cliente com os dados indicados!');
        RETURN NULL;
END;

-- Bloco anónimo para testar a função de inserir um novo cliente.
DECLARE
    codTipoCliente  cliente.codTipoCliente%TYPE;
    nomeCliente  cliente.nome%TYPE;
    numeroFiscalCliente  cliente.numeroFiscal%TYPE;
    emailCliente  cliente.email%TYPE;
    plafondCliente  cliente.plafond%TYPE;
    resultado  cliente.codInternoCliente%TYPE;
BEGIN
    codTipoCliente := 1;
    nomeCliente := 'Joaquim Bastos';
    numeroFiscalCliente := 212344678;
    emailCliente := 'joa1bastos@gmail.com';
    plafondCliente := 500;

    resultado := fncUS205CriacaoCliente(codTipoCliente, nomeCliente, numeroFiscalCliente, emailCliente, plafondCliente);

    IF resultado IS NOT NULL THEN
        dbms_output.put_line('O código do cliente inserido é ' || resultado || '.');
    END IF;
END;

------------------------------------------------------------------------------------------------------------------------
-- Procedimento para atualizar o número e o valor total das encomendas colocadas no último ano por cada cliente
CREATE OR REPLACE PROCEDURE prcUS205AtualizaEncomendas(pDataAtual IN encomenda.dataEncomenda%TYPE) IS
    CURSOR curCliente IS
        SELECT codInternoCliente FROM cliente;

    codCliente cliente.codInternoCliente%TYPE;
    numeroEncomendasCliente INTEGER;
    valorTotal NUMBER;
BEGIN
    OPEN curCliente;
    LOOP
        FETCH curCliente INTO codCliente;

        EXIT WHEN curCliente%notfound;

        SELECT SUM(r.valorProduto * r.quantidade)
        INTO valorTotal
        FROM encomenda e
        INNER JOIN registoEncomenda r ON r.codEncomenda = e.codEncomenda
        WHERE e.codInternoCliente = codCliente AND dataEncomenda >= ADD_MONTHS(pDataAtual, - 12)
                                               AND dataEncomenda <= pDataAtual;

        SELECT COUNT(codEncomenda) INTO numeroEncomendasCliente FROM encomenda
        WHERE codInternoCliente = codCliente AND dataEncomenda >= ADD_MONTHS(pDataAtual, - 12)
                                             AND dataEncomenda <= pDataAtual;

        UPDATE cliente SET numeroEncomendas = numeroEncomendasCliente, valorTotalEncomendas = valorTotal
        WHERE codInternoCliente = codCliente;

        dbms_output.put_line('O número e o valor total das encomendas do cliente com codigo ' || codCliente || ' foi atualizado com sucesso.');
    END LOOP;
    CLOSE curCliente;
END;

-- Bloco anónimo para testar o procedimento que atualiza o número e o valor total das encomendas colocadas no último ano por cada cliente
DECLARE
    dataAtual encomenda.dataEncomenda%TYPE;
BEGIN
    dataAtual := TO_DATE('4/12/2022', 'dd/mm/yy');

    prcUS205AtualizaEncomendas(dataAtual);
END;


/* 4. View */
/* 
    Função para somar o valor total de encomendas pagas nos últimos 12 meses de um dado cliente.
    Recebe como parâmetro o código interno do cliente a analisar e a data da qual será verificada a partir dai
    até 12 meses atrás.
    Posteriormente, devolve a soma total dos pagamentos de encomendas realizados nesses mesmo período de 12 meses.
*/
CREATE OR REPLACE FUNCTION fncUS205VolumeTotalDeVendasEncomendasPagas(
                                            pCodInternoCliente IN cliente.codInternoCliente%TYPE,
                                            pDataAtual IN encomenda.dataPagamento%TYPE
                                            ) RETURN registoEncomenda.valorProduto%TYPE IS
    
    volumeTotalPago registoEncomenda.valorProduto%TYPE;

BEGIN

    SELECT SUM(ROUND((valorProduto * quantidade) * (1 + percentagemIva / 100), 2)) INTO volumeTotalPago
    FROM encomenda e
    INNER JOIN cliente c ON c.codInternoCliente = e.codInternoCliente
    INNER JOIN registoEncomenda rE on rE.codEncomenda = e.codEncomenda
    WHERE 
        pCodInternoCliente = c.codInternoCliente 
        AND e.dataPagamento IS NOT NULL 
        AND TO_DATE(e.dataPagamento, 'dd-mm-yyyy') <= TO_DATE(pDataAtual, 'dd-mm-yyyy') 
        AND TO_DATE(e.dataPagamento, 'dd-mm-yyyy') >= (TO_DATE(ADD_MONTHS(pDataAtual, - 12), 'dd-mm-yyyy'));

    RETURN volumeTotalPago;

END;

/*
    Função para atribuír um nível (A, B, C) ao cliente em função aos últimos 12 meses.
    Clientes que não tenham incidentes reportados nos últimos 12 meses e que
    tenham um volume total de vendas (encomendas pagas) no mesmo período superior a 10000€ são
    do nível A; clientes sem incidentes reportados nos últimos 12 meses e que tenham um volume total
    de vendas (encomendas pagas) no mesmo período superior a 5000€ são do nível B; clientes que
    tenham incidentes reportados nos últimos 12 meses são do nível C independentemente do volume
    de vendas."
    Recebe como parâmetro o código interno do cliente a analisar e a data da qual será verificada a partir dai
    até 12 meses atrás.
    Posteriormente, devolve o nível correspondente a este cliente.
*/
CREATE OR REPLACE FUNCTION fncUS205NivelCliente(
                                            pCodInternoCliente IN cliente.codInternoCliente%TYPE,
                                            pDataAtual IN encomenda.dataPagamento%TYPE
                                            ) RETURN CHAR IS

    numIncidentes INTEGER;
    volumeVendas registoEncomenda.valorProduto%TYPE;
    nivelCliente CHAR;

BEGIN
    SELECT COUNT(*) INTO numIncidentes
    FROM encomenda e
    INNER JOIN cliente c ON c.codInternoCliente = e.codInternoCliente
    WHERE 
        pCodInternoCliente = c.codInternoCliente 
        AND TO_DATE(e.dataVencimento, 'dd-mm-yyyy') < TO_DATE(e.dataPagamento, 'dd-mm-yyyy') 
        AND TO_DATE(e.dataPagamento, 'dd-mm-yyyy') <= TO_DATE(pDataAtual, 'dd-mm-yyyy') 
        AND TO_DATE(e.dataPagamento, 'dd-mm-yyyy') >= (TO_DATE(ADD_MONTHS(pDataAtual, - 12), 'dd-mm-yyyy'));

    volumeVendas := fncUS205VolumeTotalDeVendasEncomendasPagas(pCodInternoCliente, pDataAtual);
    
    IF (numIncidentes = 0 AND volumeVendas > 10000) THEN
        nivelCliente := 'A';
    ELSE 
        IF (numIncidentes = 0 AND volumeVendas > 5000) THEN
            nivelCliente := 'B';
        ELSE
            nivelCliente := 'C';
        END IF;
    END IF;

    RETURN nivelCliente;

END;

/*
    Função para descorbir em que data foi o último incidente de um dado cliente em que é verificado a partir da data fornecida
    até 12 meses para trás.
    A função recebe como parâmetro o código interno do cliente, e a data a partir do qual será verificado 12 meses para
    trás se exite algum incidente.
    A função devolve a data do último incidente em texto, ou então devolve 'Sem incidentes à data' caso o cliente
    não tenha nenhum incidente nesse intervalo de datas.
*/
CREATE OR REPLACE FUNCTION fncUS205DataUltimoIncidente(
                                            pCodInternoCliente IN cliente.codInternoCliente%TYPE,
                                            pDataAtual IN encomenda.dataPagamento%TYPE
                                            ) RETURN NVARCHAR2 IS
    
    ultimoIncidente NVARCHAR2(21);

BEGIN
    SELECT CASE WHEN MAX(e.dataPagamento) IS NULL THEN 'Sem incidentes à data' ELSE TO_CHAR(MAX(e.dataPagamento), 'dd/mm/yyyy') end into ultimoIncidente
    FROM encomenda e
    INNER JOIN cliente c ON c.codInternoCliente = e.codInternoCliente
    WHERE 
        pCodInternoCliente = c.codInternoCliente 
        AND TO_DATE(e.dataVencimento, 'dd-mm-yyyy') < TO_DATE(e.dataPagamento, 'dd-mm-yyyy')  
        AND TO_DATE(e.dataPagamento, 'dd-mm-yyyy') <= TO_DATE(pDataAtual, 'dd-mm-yyyy') 
        AND TO_DATE(e.dataPagamento, 'dd-mm-yyyy') >= (TO_DATE(ADD_MONTHS(pDataAtual, - 12), 'dd-mm-yyyy'));

    RETURN ultimoIncidente;

END;

/*
    Função para contar o número de encomendas que o cliente recebeu mas ainda não pagou.
    A função recebe como parâmetro o código interno do cliente, e a data a partir do qual será verificado 12 meses para
    trás se exite alguma encomenda recebida e ainda que não tenha sido paga.
    A função devolve a contagem de encomendas que o cliente recebeu mas ainda não pagou nesse intervalo de datas.
*/
CREATE OR REPLACE FUNCTION fncUS205TotalEncomendasEntreguesPendentesPagamento(
                                            pCodInternoCliente IN cliente.codInternoCliente%TYPE,
                                            pDataAtual IN encomenda.dataPagamento%TYPE
                                            ) RETURN NVARCHAR2 IS
    
    pagamentosPendentes INTEGER;

BEGIN
    SELECT COUNT(*) INTO pagamentosPendentes
    FROM encomenda e
    INNER JOIN cliente c ON c.codInternoCliente = e.codInternoCliente
    WHERE 
        pCodInternoCliente = c.codInternoCliente 
        AND e.dataPagamento IS NULL
        AND e.dataEntrega IS NOT NULL
        AND TO_DATE(e.dataEntrega, 'dd-mm-yyyy') <= TO_DATE(pDataAtual, 'dd-mm-yyyy') 
        AND TO_DATE(e.dataEntrega, 'dd-mm-yyyy') >= (TO_DATE(ADD_MONTHS(pDataAtual, - 12), 'dd-mm-yyyy'));

    RETURN pagamentosPendentes;

END;

/* 
    View que agregua para cada cliente:
    a) o seu nível (A, B, C),
    b) a data do último incidente – ou a menção “Sem incidentes à data” caso não tenha incidentes reportados;
    c) o volume total de vendas (encomendas pagas) nos últimos 12 meses;
    d) o volume total das encomendas já entregues mas ainda pendentes de pagamento.
*/
CREATE OR REPLACE VIEW viewUS205CaracterizacaoCliente AS
SELECT
c.codInternoCliente,
c.nome,
fncUS205NivelCliente(c.codInternoCliente, SYSDATE) "Nível do cliente",
fncUS205DataUltimoIncidente(c.codInternoCliente, SYSDATE) "Data do último incidente nos passados 12 meses",
fncUS205VolumeTotalDeVendasEncomendasPagas(c.codInternoCliente, SYSDATE) "Volume total de encomendas pagas nos últimos 12 meses",
fncUS205TotalEncomendasEntreguesPendentesPagamento(c.codInternoCliente, SYSDATE) "Volume total das encomendas já entregues mas ainda pendentes de pagamento"
FROM cliente c
WITH READ ONLY;

/*
Mostrar os dados existentes na view
*/
SELECT * FROM viewUS205CaracterizacaoCliente;