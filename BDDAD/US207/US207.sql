-- Procedimento para que um utilizador poder listar os setores da sua exploração agrícola ordenados por ordem decrescente da quantidade de produção em uma determinada safra, medida em toneladas por hectare.
CREATE OR REPLACE PROCEDURE prcUS207ListarPeloMaxColheitas(pCodInstalacaoAgricola IN parcela.codInstalacaoAgricola%type) IS
    CURSOR curPlantacao IS
        SELECT p.codParcela, SUM(c.quantidade)
        FROM parcela p
        INNER JOIN plantacao pt ON pt.codParcela = p.codParcela
        INNER JOIN colheita c ON c.codPlantacao = pt.codPlantacao
        WHERE p.codInstalacaoAgricola = pCodInstalacaoAgricola
        GROUP BY p.codParcela
        ORDER BY SUM(c.quantidade) DESC;

    codigoParcela plantacao.codParcela%type;
    somaQuantidade INTEGER;
BEGIN
    OPEN curPlantacao;
    LOOP
        FETCH curPlantacao INTO codigoParcela, somaQuantidade;
        EXIT WHEN curPlantacao%notfound;
        dbms_output.put_line('Código da parcela: ' || codigoParcela || '. Quantidade: ' || somaQuantidade || ' t/ha.');
    END LOOP;
    CLOSE curPlantacao;
END;

-- Bloco anónimo para testar o procedimento que lista os setores da exploração agrícola ordenados pelas colheitas
DECLARE
    pCodInstalacaoAgricola parcela.codInstalacaoAgricola%type;
BEGIN
    pCodInstalacaoAgricola := 1;
    prcUS207ListarPeloMaxColheitas(pCodInstalacaoAgricola);
END;

-------------------------------------------------------------------------------------------------------------------------

-- Procedimento para um utilizador poder listar os setores da sua exploração agrícola ordenados por ordem decrescente do lucro por hectare em uma determinada safra, medido em K€ por hectare.
CREATE OR REPLACE PROCEDURE prcUS207ListarExploracaoPorRentabilidade(pCodInstalacaoAgricola IN parcela.codInstalacaoAgricola%type) IS
    CURSOR curPlantacao IS
        SELECT p.codParcela, SUM(c.quantidade) * pd.valorProduto
        FROM plantacao pt
        INNER JOIN parcela p ON p.codParcela = pt.codParcela
        INNER JOIN colheita c ON c.codPlantacao = pt.codPlantacao
        INNER JOIN cultura ct ON ct.codCultura = pt.codCultura
        INNER JOIN produto pd ON pd.codProduto = ct.codProduto
        WHERE p.codInstalacaoAgricola = pCodInstalacaoAgricola
        GROUP BY p.codParcela, pd.valorProduto
        ORDER BY SUM(c.quantidade) * pd.valorProduto DESC;

    codigoParcela plantacao.codParcela%type;
    lucro NUMBER;
BEGIN
    OPEN curPlantacao;
    LOOP
        FETCH curPlantacao INTO codigoParcela, lucro;
        EXIT WHEN curPlantacao%notfound;
        dbms_output.put_line('Código da parcela: ' || codigoParcela || '. Lucro: ' || lucro || ' €.');
    END LOOP;
    CLOSE curPlantacao;
END;

-- Bloco anónimo para testar o procedimento que lista os setores da exploração agrícola ordenados pelas colheitas
DECLARE
    pCodInstalacaoAgricola parcela.codInstalacaoAgricola%type;
BEGIN
    pCodInstalacaoAgricola := 1;
    prcUS207ListarExploracaoPorRentabilidade(pCodInstalacaoAgricola);
END;
