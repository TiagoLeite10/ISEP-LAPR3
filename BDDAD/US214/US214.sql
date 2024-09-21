-- Função para obter a evolução da produção de uma determinada cultura num determinado setor.
CREATE OR REPLACE FUNCTION fncUS214ObterEvolucaoProducao(
    pCultura IN produto.cultura%TYPE,
    pCodParcela IN parcela.codParcela%TYPE,
    pAno IN tempo.ano%TYPE,
    pMes IN tempo.mes%TYPE
) RETURN NUMBER AS

    quantidadeAtual colheita.quantidade%TYPE;
    quantidadePassada colheita.quantidade%TYPE;
    tmpAno tempo.ano%TYPE;
    tmpMes tempo.mes%TYPE;
BEGIN
    SELECT quantidade
    INTO quantidadeAtual
    FROM colheita c
             INNER JOIN produto p on p.codProduto = c.codProduto
             INNER JOIN tempo t on t.codTempo = c.codTempo
    WHERE p.cultura = pCultura AND codParcela = pCodParcela AND t.ano = pAno AND t.mes = pMes;

    tmpAno := pAno;
    tmpMes := pMes - 1;
    if (tmpMes <= 0) THEN
        tmpMes := 12;
        tmpAno := tmpAno - 1;
    END IF;

    SELECT quantidade
    INTO quantidadePassada
    FROM colheita c
             INNER JOIN produto p on p.codProduto = c.codProduto
             INNER JOIN tempo t on t.codTempo = c.codTempo
    WHERE p.cultura = pCultura AND codParcela = pCodParcela AND t.ano = tmpAno AND t.mes = tmpMes;

    RETURN quantidadeAtual - quantidadePassada;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN NULL;
END;

-- View para mostrar a evolução da produção de uma determinada cultura num determinado setor ao longo dos últimos cinco anos.
CREATE OR REPLACE VIEW viewUS214EvoluçãoUltimos5Anos AS
SELECT t.ano,
       t.mes,
       p.cultura,
       c.codParcela,
       c.quantidade,
       COALESCE(TO_CHAR(fncUS214ObterEvolucaoProducao(p.cultura, c.codParcela, t.ano, t.mes)),
                'Não é possível fazer uma comparação com o mês passado!') as EVOLUCAO
FROM colheita c
         INNER JOIN tempo t on t.codTempo = c.codTempo
         INNER JOIN produto p on p.codProduto = c.codProduto
WHERE t.ano >= TO_NUMBER(TO_CHAR(SYSDATE, 'YYYY'), '9999') - 5;

-- Mostrar os dados existentes na view que serve para mostrar a evolução da produção de uma determinada cultura num determinado setor ao longo dos últimos cinco anos.
SELECT * FROM viewUS214EvoluçãoUltimos5Anos;

------------------------------------------------------------------------------------------------------------------------
-- View para comparar as vendas de um ano com outro.
CREATE OR REPLACE VIEW viewUS214CompararVendas AS
SELECT t1.mes,
       p.nome AS nomeProduto,
       t1.ano AS primeiroAno,
       v1.quantidade AS primeiroAnoVendas,
       t2.ano AS segundoAno,
       v2.quantidade AS segundoAnoVendas,
       v1.quantidade - v2.quantidade AS comparacaoAnos
FROM venda v1
        INNER JOIN tempo t1 ON v1.codTempo = t1.codTempo
        INNER JOIN produto p ON p.codProduto = v1.codProduto,
     venda v2
        INNER JOIN tempo t2 ON t2.codTempo = v2.codTempo
WHERE t1.mes = t2.mes
        AND v1.codCliente = v2.codProduto
        AND v1.codCliente = v2.codCliente;

-- Mostrar os dados existentes na view que serve para comparar as vendas de um ano com outro.
SELECT * FROM viewUS214CompararVendas;

------------------------------------------------------------------------------------------------------------------------
-- View para analisar a evolução das vendas mensais por tipo de cultura.
CREATE OR REPLACE VIEW viewUS214AnalisarEvolucaoVendas AS
SELECT DISTINCT t.ano,
                t.mes,
                tipoCultura,
                SUM(quantidade) AS QUANTIDADE,
                COALESCE(TO_CHAR(SUM(quantidade) - (SELECT DISTINCT SUM(quantidade)
                                                    FROM produto filho
                                                             INNER JOIN venda v3 ON filho.codProduto = v3.codProduto
                                                             INNER JOIN tempo t2 ON t2.codTempo = v3.codTempo
                                                    WHERE filho.tipoCultura = pai.tipoCultura
                                                      AND t2.codTempo = (SELECT codTempo
                                                                         FROM tempo t3
                                                                         WHERE (t3.mes = t.mes - 1 AND t3.ano = t.ano)
                                                                            OR (t3.ano = t.ano - 1 AND t3.mes = 12)
                                                                         ORDER BY ano DESC, mes FETCH FIRST ROW ONLY))),'Não há valores para comparar!') AS COMPARACAO
FROM produto pai
         INNER JOIN venda v2 ON pai.codProduto = v2.codProduto
         INNER JOIN tempo t ON t.codTempo = v2.codTempo
GROUP BY t.ano, t.mes, tipoCultura
ORDER BY t.ano, t.mes;

-- Mostrar os dados existentes na view que serve para analisar a evolução das vendas mensais por tipo de cultura.
SELECT * FROM viewUS214AnalisarEvolucaoVendas;