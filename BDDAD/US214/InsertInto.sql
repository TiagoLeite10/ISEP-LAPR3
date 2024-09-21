DECLARE
    vCodTempo tempo.codTempo%TYPE:= 1;
BEGIN
    -- ## tabela Tempo ##
    FOR contadorAno IN 2018..2023
        LOOP
            FOR contadorMes IN 1..12
                LOOP
                    INSERT INTO tempo(codTempo, ano, mes) VALUES (vCodTempo, contadorAno, contadorMes);
                    vCodTempo := vCodTempo + 1;
                END LOOP;
        END LOOP;
    
    -- ## tabela Produto ##
    INSERT INTO produto(codProduto, nome, tipoCultura, cultura) VALUES ( 1, 'Produto 1', 'Permanente', 'Tomate' );
    INSERT INTO produto(codProduto, nome, tipoCultura, cultura) VALUES ( 2, 'Produto 2', 'Permanente', 'Cebola' );
    INSERT INTO produto(codProduto, nome, tipoCultura, cultura) VALUES ( 3, 'Produto 3', 'Temporaria', 'Batata' );
    INSERT INTO produto(codProduto, nome, tipoCultura, cultura) VALUES ( 4, 'Produto 4', 'Permanente', 'Cenoura' );
    INSERT INTO produto(codProduto, nome, tipoCultura, cultura) VALUES ( 5, 'Produto 5', 'Permanente', 'Alface' );
    INSERT INTO produto(codProduto, nome, tipoCultura, cultura) VALUES ( 6, 'Produto 6', 'Permanente', 'Alho' );
    INSERT INTO produto(codProduto, nome, tipoCultura, cultura) VALUES ( 7, 'Produto 7', 'Permanente', 'Cereja' );
    INSERT INTO produto(codProduto, nome, tipoCultura, cultura) VALUES ( 8, 'Produto 8', 'Permanente', 'Maça' );

    -- ## tabela Cliente ##
    INSERT INTO cliente(codCliente, numeroFiscal) VALUES ( 1, 123456789 );
    INSERT INTO cliente(codCliente, numeroFiscal) VALUES ( 2, 987654321 );
    INSERT INTO cliente(codCliente, numeroFiscal) VALUES ( 3, 567483920 );
    INSERT INTO cliente(codCliente, numeroFiscal) VALUES ( 4, 123456589 );
    INSERT INTO cliente(codCliente, numeroFiscal) VALUES ( 5, 423456789 );
    INSERT INTO cliente(codCliente, numeroFiscal) VALUES ( 6, 787654321 );

    -- ## tabela Parcela ##
    INSERT INTO parcela(codParcela, designacao, area) VALUES ( 1, 'Parcela 1', 100 );
    INSERT INTO parcela(codParcela, designacao, area) VALUES ( 2, 'Parcela 2', 200 );
    INSERT INTO parcela(codParcela, designacao, area) VALUES ( 3, 'Parcela 3', 150 );
    INSERT INTO parcela(codParcela, designacao, area) VALUES ( 4, 'Parcela 4', 250 );
    INSERT INTO parcela(codParcela, designacao, area) VALUES ( 5, 'Parcela 5', 100 );
    INSERT INTO parcela(codParcela, designacao, area) VALUES ( 6, 'Parcela 6', 200 );
    INSERT INTO parcela(codParcela, designacao, area) VALUES ( 7, 'Parcela 7', 150 );
    INSERT INTO parcela(codParcela, designacao, area) VALUES ( 8, 'Parcela 8', 250 );
    INSERT INTO parcela(codParcela, designacao, area) VALUES ( 9, 'Parcela 9', 100 );
    INSERT INTO parcela(codParcela, designacao, area) VALUES ( 10, 'Parcela 10', 200 );
    INSERT INTO parcela(codParcela, designacao, area) VALUES ( 11, 'Parcela 11', 150 );
    INSERT INTO parcela(codParcela, designacao, area) VALUES ( 12, 'Parcela 12', 250 );
    INSERT INTO parcela(codParcela, designacao, area) VALUES ( 13, 'Parcela 13', 100 );
    INSERT INTO parcela(codParcela, designacao, area) VALUES ( 14, 'Parcela 14', 200 );
    INSERT INTO parcela(codParcela, designacao, area) VALUES ( 15, 'Parcela 15', 150 );

    -- ## tabela Venda ##
    FOR contadorCliente IN 1..6
        LOOP
            FOR contadorTempo IN 1..72
                LOOP
                    FOR contadorProduto IN 1..8
                        LOOP
                             INSERT INTO venda(codCliente, codProduto, codTempo, vendasLiquidas, quantidade) 
                             VALUES (contadorCliente, contadorProduto, contadorTempo, ROUND(DBMS_RANDOM.VALUE(1, 50)), ROUND(DBMS_RANDOM.VALUE(1, 9999)));
                        END LOOP;
                END LOOP;
        END LOOP;

    -- ## tabela Colheita ##
    FOR contadorParcela IN 1..15
        LOOP
            FOR contadorTempo IN 1..72
                LOOP
                    FOR contadorProduto IN 1..8
                        LOOP
                            INSERT INTO colheita(codParcela, codProduto, codTempo, quantidade) 
                            VALUES (contadorParcela, contadorProduto, contadorTempo, ROUND(DBMS_RANDOM.VALUE(1, 9999)));        
                        END LOOP;
                END LOOP;
        END LOOP;
END;