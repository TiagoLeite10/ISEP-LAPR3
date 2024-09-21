-- View para analisar a evolução das vendas mensais por tipo de cultura e hub
CREATE OR REPLACE VIEW viewUS216EvolucaoVendasMensaisPorTipoCulturaEHub AS
SELECT
p.tipoCultura,
hub.hub,
t.ano,
t.mes,
SUM(v.vendasLiquidas) "Valor das vendas do mês",
SUM(v.quantidade) "Quantidades vendidas no mês"
FROM venda v
INNER JOIN tempo t ON t.codTempo = v.codTempo
INNER JOIN hub ON hub.codHub = v.codHub
INNER JOIN produto p ON p.codProduto = v.codProduto
GROUP BY p.tipoCultura, hub.hub, t.ano, t.mes
ORDER BY p.tipoCultura, hub.hub, t.ano, t.mes ASC
WITH READ ONLY;

/*
Mostrar os dados existentes na view
*/
SELECT * FROM viewUS216EvolucaoVendasMensaisPorTipoCulturaEHub;