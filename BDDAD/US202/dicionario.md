# DICIONÁRIO DE DADOS #
| Entidade                      | Descrição |
| ----------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Tipo de Edifício**          | Os edifícios podem ser: <br />- **estábulo** - estábulos para animais; <br />- **garagem** - garagens para máquinas e alfaias; <br />- **armazém** - armazéns para colheitas, factores de produção (e.g., fertilizantes) e rações para animais; <br />- **sistema de rega** - sistemas de rega incluindo tanques agrícolas. |
| **Tipo de Sensor**            | Os sensores só podem ser de um dos seguintes tipos: <br />- **HS** - Sensor de humidade do solo; <br />- **PL** - Sensor de pluviosidade; <br />- **TS** - Sensor de temperatura do solo; <br />- **VV** - Sensor de velocidade do vento; <br />- **TA** - Sensor de temperatura atmosférica; <br />- **HA** - Sensor de humidade do ar; <br />- **PA** - Sensor de pressão atmosférica. |
| **Tipo de Utilizador**        | Os tipos de utilizadores na aplicação envolvem: <br />- **gestor de distribuição** - pessoa que gere processo de recolha e transporte dos produtos agrícolas entre explorações, Hubs e posterior recolha pelos clientes; <br />- **gestor agrícola** - pessoa que gere culturas nas parcelas, realiza as diferentes acções culturais e as regista no Caderno de Campo; <br />- **condutor** - pessoa que recolhe os cabazes na exploração agrícola e os deposita nos Hubs de distribuição; <br />- **cliente** - pessoa que encomenda e consume os produtos agrícolas, distribuídos sob a forma de cabazes. |
| **Tipo de Cliente**           | Clientes podem ser divididos em: <br />- **empresa** ou **particular** - empresas ou particulares que compram os bens produzidos na exploração agrícola. |
| **Tipo de Cultura**           | As culturas podem ser: <br />- **temporária** - temporária, culturas de pouca ou longa duração, com ciclos vegetativos abaixo de um ano; <br />- **permanente** - culturas de longa duração, com longos ciclos vegetativos. |
| **Estado da Encomenda**       | Os estados das encomendas realizadas pelo cliente podem ser: <br />- **pago** - a encomenda foi paga pelo cliente; <br />- **entregue** - a encomenda foi entregue ao cliente; <br />- **registado** - a encomenda foi registada na aplicação. |
| **Tipo de Rega**              | Os tipos de rega incluem: <br />-  por **gravidade** - quando existe um desnível entre a fonte e as culturas; <br />- **bombeada** - através de bombas hidráulicas. |
| **Tipo de Sistema de Rega**   | Os tipos de sistema de rega incluem por: <br />- **aspersão** - processo pelo qual a água é distribuída às plantas por meio de tubagem e sob pressão; <br />- **gotejamento** - método baseado em aplicar gotas de água diretamente na raiz da planta; <br />- **pulverização** - processo muito semelhante à aspersão mas são usados pulverizadores em vez de aspersores. |
| **Tipo de Produto**           | Os produtos podem ser do tipo: <br />- **consumível** - produtos prontos para serem consumidos e comprados pelo cliente; <br />- **fator de produção** - são todos os produtos que são aplicados no solo ou nas plantas, por forma a melhorar e nutrir o solo e as plantas, prevenir doenças, corrigir desequilíbrios, e combater pragas e doenças. |
| **Tipo de Operação**          | O tipo de operações que existem são: <br />- **fertilização** - via foliar, fertirrega ou aplicação directa ao solo; <br />- **sensor** - recolha de dados do Sensor; <br />- **colheita** - recolha de colheitas; <br />- **rega** - execução das regas. |
| **Tipo de Fertilização**      | A fertilização pode ser do tipo: <br />- **foliar** - aplicação de nutrientes nas folhas para que os absorvidos possam ser transportados para outras partes do vegetal; <br />- **fertirrega** - aplicação de fertilizantes, nutrientes para as plantas, junto com a água de irrigação; <br />- **aplicação directa ao solo** - aplicação de fertilizantes diretamente no solo. |
| **Tipo de Fator de Produção** | Os fatores de produção podem ser: <br />- **fertilizantes** - fertilizantes sintéticos; <br />- **adubos** - fertilizantes orgânicos; <br />- **correctivos** - utilizados para garantir um excelente rendimento e qualidade da produção, estes produtos reduzem a acidez e estimulam diversos tipos de nutrientes; <br />- **produtos fitofármacos** - substâncias ou misturas utilizadas para proteger as plantas de doenças e pragas, ou para prevenir as suas ações. |
| **Tipo de Formulação de Produto**        | A formulação pode ser do tipo: <br />- **líquido** - o produto está no estado líquido; <br />- **granulado** - o produto está no estado sólido, forma em grão; <br />- **pó** - o produto está no estado sólido seco composto por uma grande quantidade de partículas muito finas. |

# Tabela de restrições #
| Nome de restrição      | Chave de restrição |
| -----------------------| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Primary key**        | **PK**: Deve ser o mais simples possível, usando letras maiúsculas e minúsculas, com o primeiro caractere sem maiúsculas e, de preferência, uma palavra. |
| **Foreign Key**        | **FK**: Deve seguir as mesmas regras da Chave Primária e o nome deve estar relacionado com a relação que resulta na Chave Estrangeira. |
| **Not Null**           | **NN**: Garante que uma coluna não admite valores NULL. Isto significa que será abortada uma operação de INSERT ou UPDATE que coloque um valor NULL nessa coluna. |
| **Unique**             | **UQ**: Garante que o conteúdo da coluna (ou combinação de colunas) assume um valor diferente para cada linha da tabela. |
| **Default**            | **DF**: A restrição DEFAULT é usada para inserir um valor padrão especificado em uma coluna; O valor padrão será adicionado a todos os novos registros caso nenhum outro valor seja especificado na hora de inserir dados. |

# Tecnologia #
Uma base de dados é um repositório de informação relacionada com determinado assunto ou origem, isto é, é uma coleção de dados ou itens informação controlada de determinada maneira que permite a sua consulta, atualização e outros tipos de operação processada por meios informáticos.

Esta serve para gerir vastos conjuntos de informação de modo a facilitar a organização, manutenção e pesquisa de dados.

Regra geral, as bases de dados relacionais utilizam tabelas com dados organizados em linhas (que contêm entidades) e colunas (que contêm atributos das entidades). Este processo é conhecido como normalização. Cada linha contém um identificador ou chave exclusiva que une tabelas para estabelecer uma relação. Quando uma base de dados relacional é consultada, a chave é utilizada para encontrar dados relacionais entre conjuntos de dados.

Os dados mais comuns de uma base de dados em operação atualmente são normalmente modelados em linhas e colunas em uma série de tabelas para tornar o processo e a consulta de dados eficientes. Os dados podem então ser facilmente acedidos, geridos, modificados, atualizados, controlados e organizados. A maioria das bases de dados usa linguagem de consulta estruturada (SQL) para escrever e consultar dados. Temos como exemplos:

-**Oracle XE**

-**My SQL**

-**SQL Server**

-**PostgreSQL**



As bases de dados relacionais trabalham com dados estruturados. Elas oferecem suporte à consistência transacional ACID e fornecem uma maneira flexível de estruturar dados que não são possíveis com outras tecnologias de base de dados. Os principais recursos das bases de dados relacionais incluem a capacidade de fazer duas tabelas parecerem uma, unir várias tabelas em campos-chave, criar índices complexos com bom desempenho e fáceis de gerir e manter a integridade dos dados para máxima precisão dos mesmos.

A base de dados relacional é um sistema de armazenamento e recuperação de dados no qual o conteúdo de dados é armazenado em tabelas, linhas, colunas ou campos.

Quando temos várias informações que precisam de ser relacionadas entre si, é importante armazená-las nesse tipo de formato.

A utilização de uma base de dados relacional tem bastantes benefícios, visto que permitem que os dados sejam claros, organizados e permite gerirmos a base de dados face às nossas necessidades dos dados. Temos então uma breve análise aos benefícios da mesma:



**1. Fácil utilização**

Os utilizadores conseguem aceder ou recuperar facilmente as informações necessárias em segundos, não tendo problemas com a complexidade da base de dados. O SQL é usado para executar consultas complexas.

**2. Integridade de Dados**

As bases de dados RDBMS também são amplamente usadas ​​para integridade de dados, pois fornecem consistência em todas as tabelas. As integridades dos dados garantem recursos como precisão e utilização fácil.

**3. Precisão**

Uma característica fundamental das bases de dados relacionais é que elas são estritamente definidas e bem organizadas, para que os dados não sejam duplicados. Bancos de dados relacionais têm precisão por causa de sua estrutura sem duplicação de dados.

**4. Simplicidade**

Em contraste com outros tipos de modelos de base de dados, o modelo de base de dados relacional é muito mais simples.

**5. Normalização**

À medida que os dados se tornam cada vez mais complexos, aumenta a necessidade de formas eficientes de armazená-los, por isso a normalização é um método que divide as informações em partes gerenciáveis ​​para reduzir o tamanho do armazenamento. Os dados podem ser divididos em diferentes níveis, com qualquer nível exigindo preparação antes de passar para outro nível de normalização dos seus dados.

**6. Desenho físico**

Na fase de desenho físico, o desenho lógico, é mapeado ou convertido para sistemas de software que serão utilizados na implementação da aplicação e na base de dados.

**7. Segurança**

Relativamente à segurança podemos concluir que os dados estão seguros, visto que o sistema da base de dados relacional permite que apenas os utilizadores autorizados acedam diretamente aos dados.

**8. Armazenamento Físico**

Uma base de dados relacional é composta por linhas e colunas, o que requer muita memória física, pois cada operação executada depende de armazenamento separado.

**9. Complexidade na Estrutura**

Uma base de dados relacional só pode armazenar dados em formato tabular, o que dificulta a representação de relacionamentos complexos entre objetos.

**10. Melhoria de tomada de decisões**

Devido aos dados serem mais bem geridos e haver um melhor acesso a esses mesmos dados tornam possível gerar informação de melhor qualidade. A qualidade da informação gerada depende da qualidade dos dados subjacentes. A qualidade é uma abordagem abrangente para promover a exatidão, validade e atualidade dos dados.

**11. Redução de erros e aumento da consistência**

Uma base de dados oferece ao utilizador consistência no seu funcionamento, seja na análise dos dados ou na sua atualização.

Além disso, há poucas incidências de erros de dados dentro de uma base de dados, o que origina consistência.

**12. Maior independência e integridade**

Os sistemas de gestão de base de dados (SGBD) são frequentemente independentes de quaisquer outros programas informáticos e podem ser acedidos por todas as outras aplicações.

**13. Reduz duplicação**

Uma base de dados desempenha um papel vital na garantia da integridade dos dados e na redução da redundância que elimina os casos de duplicação de dados.

Após esta análise, podemos verificar que a base de dados relacional é bastante vantajosa devido à facilidade que fornece ao utilizador, a segurança, a simplicidade e a coesão.