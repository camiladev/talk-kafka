# kafka-example
Este projeto foi desenvolvido para servir de exemplo no Quick Talk realizado na Redspark, com o objetivo de apresentar o Apache Kafka.

## Como executar o projeto
Para rodar esse projeto na parte de consumer e producer é necessário executar o arquivo docker-compose.yml

Após subir todos os contâiner é necessário acessar a página do Kafdrop para criar o tópico que deseja enviar mensagens via producer.
URL: http://localhost:9000/

O arquivo docker-compose-2.yml tem os serviços necessários para criar um conector.


### Endpoints
- Criar um novo conector: 
```
POST http://localhost:8083/connectors/
```
- Lista conectores:
```
GET http://localhost:8083/connectors/
```
- Verifica status conector:
```
GET http://localhost:8083/connectors/{nome-conector}/status
```
- Verifica tópico vinculado ao conector:
```
GET http://localhost:8083/connectors/{nome-conector}/topics
```


### O que é Kafka:

O Apache Kafka é uma plataforma de streaming de dados distribuída, desenvolvida para lidar com grandes volumes de dados em tempo real. Ele foi inicialmente desenvolvido pela LinkedIn e, posteriormente, tornado um projeto de código aberto mantido pela Apache Software Foundation.

### Caracteristas:

- **Arquitetura**
    - Sua arquitetura distribuída permite a escalabilidade horizontal, ou seja, a capacidade de lidar com grandes volumes de dados distribuídos em diversos servidores.
- **Garantias de Entrega**
    - O Kafka oferece garantias de entrega de mensagens, permitindo que os dados sejam replicados e persistidos de maneira confiável. Isso evita a perda de dados em caso de falhas.
- **Persistência**
    - Os eventos no Kafka são persistentemente armazenados em log, garantindo durabilidade mesmo em casos de falha do sistema.
- **Integração com Ecossistemas:**
    - O Kafka é projetado para se integrar facilmente com outros componentes e ferramentas, tornando-se um elemento central em arquiteturas de dados distribuídas.


## Arquitetura:

- **Cluster:**
  - O Apache ZooKeeper é um serviço de coordenação utilizado para gerenciar o cluster, porem foi descontinuado na versão 3.3 do Kafka, pois o Apache Kafka desenvolveu o KRaft que já vem integrado ao Kafka.
    - Fonte: [KRaft](https://developer.confluent.io/learn/kraft/)
  - Apache Kafka Raft (KRaft): É o protocolo de consenso introduzido no KIP-500 para remover a dependência do Apache Kafka do ZooKeeper para gerenciamento de metadados. Isso simplifica muito a arquitetura do Kafka, consolidando a responsabilidade pelos metadados no próprio Kafka, em vez de dividi-la entre dois sistemas diferentes: ZooKeeper e Kafka. O modo KRaft faz uso de um novo serviço de controlador de quorum no Kafka que substitui o controlador anterior e faz uso de uma variante baseada em eventos do protocolo de consenso Raft.
  - **Controladores (Controllers):** Cada cluster Kafka tem um controlador, que é um broker especial responsável por gerenciar a distribuição das partições e as informações sobre os líderes das partições.
- **Brokers**
    - **Líderes de Partição (Partition Leaders):** Em cada partição, um broker é designado como líder, e os demais brokers replicam os dados dessa partição.
    - **Replicação de Dados:** O Kafka replica dados entre os brokers para garantir a durabilidade e a tolerância a falhas.
- **Tópicos**
  - Permitem a categorização dos dados e são a unidade fundamental de organização dos eventos.
- **Partições**
  - Permite a distribuição de carga e o processamento paralelo

### Consumer

- **Descrição:** Consumidores recebem eventos dos tópicos e os processam conforme necessário.
- **Função:** São os destinatários finais dos dados, realizando operações como armazenamento em banco de dados, análise em tempo real ou qualquer outra lógica de negócios.

### Producer

- **Descrição:** Produtores são responsáveis por enviar eventos ou mensagens para os tópicos no Kafka.
- **Função:** Eles geram os dados que serão processados pelo sistema.

### Schema:

- Um shema define a estrutura dos dados em termos de tipos de dados, campos e relacionamentos. Em tópicos que usam serialização com esquemas, as mensagens são produzidas e consumidas de acordo com a estrutura especificada no esquema.
    - **Serialização de Dados:**
        - No Kafka, os dados são frequentemente serializados antes de serem publicados em um tópico e deserializados ao serem consumidos. A serialização com esquemas é útil porque permite que os dados sejam interpretados corretamente, mesmo quando o produtor e o consumidor estão em diferentes linguagens de programação.
    - **Schema Registry:**
        - O Schema Registry é um componente frequentemente usado em conjunto com o Kafka para gerenciar e armazenar esquemas. Ele fornece um repositório centralizado para armazenar e recuperar esquemas, permitindo que os produtores e consumidores obtenham e validem esquemas dinamicamente. O Confluent Schema Registry é uma implementação popular para esse propósito.

## Kafka Connect

Os conectores Kafka, também conhecidos como Kafka Connect, são uma parte essencial do ecossistema Apache Kafka. Eles são projetados para simplificar e facilitar a integração entre o Kafka e outros sistemas de dados, permitindo a transferência de dados de forma eficiente e escalável.

1. **Definição:**
    - Os conectores Kafka são plugins modulares que facilitam a ingestão e ejeção de dados entre o Kafka e diferentes sistemas de armazenamento ou fontes de dados.
2. **Funcionamento:**
    - Eles conectam o Kafka a sistemas externos, movendo dados de ou para o Kafka de maneira eficiente e confiável.
3. **Ingestão de Dados:**
    - Os conectores de origem (source connectors) são responsáveis por ingestar dados de fontes externas para os tópicos do Kafka. Por exemplo, um conector de origem pode capturar dados de um banco de dados relacional e enviá-los para um tópico no Kafka.
4. **Ejeção de Dados:**
    - Os conectores de destino (sink connectors) movem dados do Kafka para sistemas externos. Por exemplo, um conector de destino pode pegar dados de um tópico no Kafka e gravá-los em um banco de dados NoSQL.
5. **Configuração Declarativa:**
    - Os conectores Kafka são configurados de maneira declarativa, o que significa que você especifica a configuração desejada e o conector gerencia a execução.
6. **Facilidade de Escala:**
    - Eles são projetados para facilitar a escalabilidade horizontal. Você pode aumentar a capacidade de ingestão ou ejeção simplesmente adicionando mais instâncias dos conectores.
7. **Conectores Padrão e Personalizados:**
    - O Kafka fornece conectores padrão para integração com sistemas comuns, como bancos de dados SQL, Hadoop, Amazon S3, Elasticsearch, entre outros. Além disso, você pode desenvolver conectores personalizados para se integrar a sistemas específicos.
8. **Conectores De Terceiros:**
    - A comunidade do Kafka desenvolve e compartilha conectores de terceiros para uma ampla variedade de sistemas, o que aumenta a flexibilidade e a variedade de integrações possíveis.
9. **Confluent Hub:**
    - O [Confluent Hub](https://www.confluent.io/hub/) é um repositório online onde você pode encontrar e baixar conectores Kafka desenvolvidos pela comunidade e pela Confluent (a empresa que fornece uma distribuição do Kafka).

## Referências:

Para obter informações detalhadas e precisas sobre o Apache Kafka, recomendo consultar as seguintes fontes diretamente:

1. **Documentação Oficial do Apache Kafka:**
    - [Site oficial do Apache Kafka](https://kafka.apache.org/)
2. **Blogs e Tutoriais Online:**
    - Blogs e tutoriais de sites respeitáveis, como Confluent, Medium, e Stack Overflow, podem fornecer insights práticos e casos de uso específicos.
3. **Confluent Platform:**
    - [Confluent Platform Documentation](https://docs.confluent.io/)
4. **Apache Kafka Raft (KRaft):**
   - [KRaft](https://developer.confluent.io/learn/kraft/)
