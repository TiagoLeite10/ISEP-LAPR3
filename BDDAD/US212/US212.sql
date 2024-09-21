-- Função que devolve o n-ésimo elemento de cada tuplo de input_sensor.
CREATE OR REPLACE FUNCTION fncUS212NesimoElementoDeCadaTuplo(n NUMBER) RETURN inputSensor.inputString%TYPE IS
    tuplo inputSensor.inputString%TYPE;
BEGIN
    SELECT inputString INTO tuplo
    FROM (SELECT inputString, ROWNUM AS num FROM inputSensor)
    WHERE num = n;

    RETURN tuplo;
END;

-- Bloco anonimo para testar a função que devolve o n-ésimo elemento de cada tuplo de input_sensor.
DECLARE
    n NUMBER;
    tuplo inputSensor.inputString%TYPE;
BEGIN
    n := 6;
    tuplo := fncUS212NesimoElementoDeCadaTuplo(n);
    dbms_output.put_line('Tuplo: ' || tuplo);
END;

------------------------------------------------------------------------------------------------------------------------
-- Função que separa o tuplo
CREATE OR REPLACE FUNCTION fncUS212SepararTuplo(n NUMBER, tuplo IN inputSensor.inputString%TYPE) RETURN inputSensor.inputString%TYPE IS
    elemento inputSensor.inputString%TYPE;

BEGIN
    CASE n
        WHEN 1 THEN elemento := SUBSTR(tuplo, 1, 5);
        WHEN 2 THEN elemento := SUBSTR(tuplo, 6, 2);
        WHEN 3 THEN elemento := SUBSTR(tuplo, 8, 3);
        WHEN 4 THEN elemento := SUBSTR(tuplo, 11, 3);
        WHEN 5 THEN elemento := SUBSTR(tuplo, 14, 12);
        ELSE elemento := '';
    END CASE;

    RETURN elemento;

END;

------------------------------------------------------------------------------------------------------------------------
-- Função para validar o identificador do sensor.
CREATE OR REPLACE FUNCTION fncUS212ValidarIdentificadorSensor(pIdentificadorSensor IN inputSensor.inputString%TYPE)
                                                                    RETURN sensor.codSensor%TYPE IS
BEGIN
    IF (LENGTH(pIdentificadorSensor) = 5) THEN
            RETURN pIdentificadorSensor;
        ELSE
            RETURN NULL;
    END IF;
END;

-- Função para validar o tipo de sensor.
CREATE OR REPLACE FUNCTION fncUS212ValidarTipoSensor(pTipoSensor IN inputSensor.inputString%TYPE)
                                                            RETURN tipoSensor.acronimo%TYPE IS
BEGIN
    IF (LENGTH(pTipoSensor) = 2) AND (pTipoSensor = 'HS' OR pTipoSensor = 'PL' OR pTipoSensor = 'TS' OR
                                    pTipoSensor = 'VV' OR pTipoSensor = 'TA' OR pTipoSensor = 'HA' OR
                                    pTipoSensor = 'PA') THEN
        RETURN pTipoSensor;
    ELSE
        RETURN NULL;
    END IF;
END;

-- Função para validar o valor lido.
CREATE OR REPLACE FUNCTION fncUS212ValidarValorLido(pLeituraSensor IN inputSensor.inputString%TYPE)
                                                        RETURN leituraSensor.resultado%TYPE IS
    vLeituraSensor leituraSensor.resultado%TYPE;
BEGIN
    vLeituraSensor := TO_NUMBER(pLeituraSensor);

    IF (vLeituraSensor BETWEEN 0 AND 100) THEN
        RETURN vLeituraSensor;
    ELSE
        RETURN NULL;
    END IF;

    EXCEPTION
        WHEN OTHERS THEN
            RETURN NULL;
END;

-- Função para validar o valor de referência.
CREATE OR REPLACE FUNCTION fncUS212ValidarValorReferencia(pValorReferencia IN inputSensor.inputString%TYPE)
                                                        RETURN sensor.valorReferencia%TYPE IS
    vValorReferencia leituraSensor.resultado%TYPE;
BEGIN
    vValorReferencia := TO_NUMBER(pValorReferencia);

    IF (vValorReferencia BETWEEN 0 AND 100) THEN
        RETURN vValorReferencia;
    ELSE
        RETURN NULL;
    END IF;

    EXCEPTION
        WHEN OTHERS THEN
            RETURN NULL;
END;

-- Função para validar o instante da leitura.
CREATE OR REPLACE FUNCTION fncUS212ValidarLeituraSensor(pLeituraSensor IN inputSensor.inputString%TYPE)
                                                            RETURN leituraSensor.dataLeitura%TYPE IS
    vLeituraSensor leituraSensor.dataLeitura%TYPE;
BEGIN
    vLeituraSensor := TO_DATE(pLeituraSensor, 'ddmmyyyyhh24mi');
    RETURN vLeituraSensor;

    EXCEPTION
        WHEN OTHERS THEN
            RETURN NULL;
END;

-- Função para contar o número de erros identificados.
CREATE OR REPLACE FUNCTION fncUS212ContarNumeroErrosIdentificados(pIdentificadorSensor IN sensor.codSensor%TYPE,
                                                                    pTipoSensor IN tipoSensor.acronimo%TYPE,
                                                                    pValorLido IN leituraSensor.resultado%TYPE,
                                                                    pValorReferencia IN sensor.valorReferencia%TYPE,
                                                                    pInstanteLeitura IN leituraSensor.dataLeitura%TYPE)
                                                            RETURN INTEGER IS
    vNumErrosIdentificados INTEGER := 0;
BEGIN
    IF (pIdentificadorSensor IS NULL) THEN
        vNumErrosIdentificados := vNumErrosIdentificados + 1;
    END IF;

    IF (pTipoSensor IS NULL) THEN
        vNumErrosIdentificados := vNumErrosIdentificados + 1;
    END IF;

    IF (pValorLido IS NULL) THEN
        vNumErrosIdentificados := vNumErrosIdentificados + 1;
    END IF;

    IF (pValorReferencia IS NULL) THEN
        vNumErrosIdentificados := vNumErrosIdentificados + 1;
    END IF;

    IF (pInstanteLeitura IS NULL) THEN
        vNumErrosIdentificados := vNumErrosIdentificados + 1;
    END IF;

    RETURN vNumErrosIdentificados;
END;

-- Procedimento para transferir as leituras dos sensores para um conjunto de tabelas.
CREATE OR REPLACE PROCEDURE prcUS212TransferirLeiturasSensores IS
    CURSOR curInputSensor IS
        SELECT * FROM inputSensor;

    indexIdentificadorSensor CONSTANT NUMBER := 1;
    indexTipoSensor CONSTANT NUMBER := 2;
    indexValorLido CONSTANT NUMBER := 3;
    indexValorReferencia CONSTANT NUMBER := 4;
    indexInstanteLeitura CONSTANT NUMBER := 5;

    vTuplo inputSensor.inputString%TYPE;
    vIdentificadorSensor sensor.codSensor%TYPE;
    vTipoSensor tipoSensor.acronimo%TYPE;
    vValorLido leituraSensor.resultado%TYPE;
    vValorReferencia sensor.valorReferencia%TYPE;
    vInstanteLeitura leituraSensor.dataLeitura%TYPE;

    vNumSensores INTEGER;
    vCodTipoSensor tipoSensor.codTipoSensor%TYPE;
    vProximoCodLeituraSensor leituraSensor.codLeituraSensor%TYPE;
    vProximoCodProcessoLeituraSensor processoLeituraSensor.codProcessoLeituraSensor%TYPE;
    vProximoCodRegistoProcessoLeituraSensor registoProcessoLeituraSensor.codRegistoProcessoLeituraSensor%TYPE;

    vNumTotalRegistosLidos INTEGER := 0;
    vNumRegistosTransferidos INTEGER := 0;
    vNumRegistosNãoTransferidos INTEGER := 0;
    vNumErrosIdentificados INTEGER := 0;
BEGIN
    SELECT MAX(codProcessoLeituraSensor) INTO vProximoCodProcessoLeituraSensor FROM processoLeituraSensor;
    IF (vProximoCodProcessoLeituraSensor IS NULL ) THEN
        vProximoCodProcessoLeituraSensor := 1;
    ELSE
        vProximoCodProcessoLeituraSensor := vProximoCodProcessoLeituraSensor + 1;
    END IF;

    -- Regista o processo de leitura de dados dos sensores
    INSERT INTO processoLeituraSensor(codProcessoLeituraSensor)
    VALUES (vProximoCodProcessoLeituraSensor);

    OPEN curInputSensor;
    LOOP
        BEGIN
            FETCH curInputSensor INTO vTuplo;
            EXIT WHEN curInputSensor%NOTFOUND;

            vNumTotalRegistosLidos := vNumTotalRegistosLidos + 1;

            vIdentificadorSensor := fncUS212ValidarIdentificadorSensor(fncUS212SepararTuplo(indexIdentificadorSensor, vTuplo));
            vTipoSensor := fncUS212ValidarTipoSensor(fncUS212SepararTuplo(indexTipoSensor, vTuplo));
            vValorLido := fncUS212ValidarValorLido(fncUS212SepararTuplo(indexValorLido, vTuplo));
            vValorReferencia := fncUS212ValidarValorReferencia(fncUS212SepararTuplo(indexValorReferencia, vTuplo));
            vInstanteLeitura := fncUS212ValidarLeituraSensor(fncUS212SepararTuplo(indexInstanteLeitura, vTuplo));

            vNumErrosIdentificados := fncUS212ContarNumeroErrosIdentificados(vIdentificadorSensor, vTipoSensor,vValorLido,
                                                                                vValorReferencia, vInstanteLeitura);

            -- Verifica o número de sensores existentes com o identificador fornecido
            SELECT COUNT(*) INTO vNumSensores FROM sensor WHERE codSensor LIKE vIdentificadorSensor;

            IF (vNumErrosIdentificados = 0) THEN

                -- Se o sensor não existir vai criá-lo
                IF (vNumSensores = 0) THEN
                    -- Procura o código do tipo de sensor
                    SELECT codTipoSensor INTO vCodTipoSensor FROM tipoSensor WHERE acronimo LIKE vTipoSensor;

                    -- Cria o sensor
                    INSERT INTO sensor(codSensor, codTipoSensor, valorReferencia)
                    VALUES (vIdentificadorSensor, vCodTipoSensor, vValorReferencia);
                END IF;

                SELECT MAX(codLeituraSensor) INTO vProximoCodLeituraSensor FROM leituraSensor;
                IF (vProximoCodLeituraSensor IS NULL ) THEN
                    vProximoCodLeituraSensor := 1;
                ELSE
                    vProximoCodLeituraSensor := vProximoCodLeituraSensor + 1;
                END IF;

                -- Regista a leitur do sensor
                INSERT INTO leituraSensor(codLeituraSensor, codSensor, resultado, dataLeitura)
                VALUES (vProximoCodLeituraSensor, vIdentificadorSensor, vValorLido, vInstanteLeitura);

                -- Eliminar registo da tabela inputSensor
                DELETE FROM inputSensor WHERE inputString LIKE vTuplo;

                vNumRegistosTransferidos := vNumRegistosTransferidos + 1;
            ELSE
                -- Se o sensor existir vai registar o identificador do sensor e o número de erros identificados no registo da leitura
                IF (vNumSensores = 1) THEN
                    SELECT MAX(codRegistoProcessoLeituraSensor) INTO vProximoCodRegistoProcessoLeituraSensor FROM registoProcessoLeituraSensor;
                    IF (vProximoCodRegistoProcessoLeituraSensor IS NULL ) THEN
                        vProximoCodRegistoProcessoLeituraSensor := 1;
                    ELSE
                        vProximoCodRegistoProcessoLeituraSensor := vProximoCodRegistoProcessoLeituraSensor + 1;
                    END IF;

                    -- Regista para um registo com erros de formatação o identificador do sensor e o número de erros identificados
                    INSERT INTO registoProcessoLeituraSensor(codRegistoProcessoLeituraSensor, codProcessoLeituraSensor, codSensor, numErrosIdentificados)
                    VALUES (vProximoCodRegistoProcessoLeituraSensor, vProximoCodProcessoLeituraSensor, vIdentificadorSensor, vNumErrosIdentificados);
                END IF;

                vNumRegistosNãoTransferidos := vNumRegistosNãoTransferidos + 1;
            END IF;
        END;
    END LOOP;
    CLOSE curInputSensor;

    -- Atualiza o registo do processo de leitura de dados dos sensores
    UPDATE processoLeituraSensor
    SET numTotalRegistosLidos = vNumTotalRegistosLidos, numRegistosTransferidos = vNumRegistosTransferidos, numRegistosNãoTransferidos = vNumRegistosNãoTransferidos
    WHERE codProcessoLeituraSensor = vProximoCodProcessoLeituraSensor;

    dbms_output.put_line('***** Resumo do Processo *****');
    dbms_output.put_line('Número total de registos lidos: ' || vNumTotalRegistosLidos);
    dbms_output.put_line('Número de registos transferidos: ' || vNumRegistosTransferidos);
    dbms_output.put_line('Número de registos não transferidos: ' || vNumRegistosNãoTransferidos);
END;

-- Chamada para testar o procedimento que transfere as leituras dos sensores para um conjunto de tabelas.
CALL prcUS212TransferirLeiturasSensores();