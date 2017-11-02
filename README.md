# producerConsumer

Solução do problema dos Produtores e Consumidores usando monitor em Java

## Ambiente usado para implementação e testes

* Sistema Operacional Windows 10
* Eclipse IDE Oxygen Release (4.7.0)
* Eclipse IDE Neon.1a Release (4.6.1)

## Como executar o programa

Abra o Eclipse e adicione o projeto.

1. New
2. Java Project
3. Desmaque o checkbox `Use default location`
4. Browse
5. Selecione a pasta do projeto
6. Finish

Abra o arquivo `Main.java`.

Dentro do arquivo poderão ser feitas algumas mudanças de configuração nos `Producers` e nos `Consumers`. O construtor de cada um deles é composto pelo `buffer` e a `printer` compartilhados além de outros dados, como mostrador abaixo:

```java
/*
* Parâmetros do construtor do Producer:
* (printer, buffer, numero_identificador, numero_de_producoes, possui_tempo_de_espera_para_produzir)
*/
Producer p1 = new Producer(p, b, 1, 8, true);

/*
* Parâmetros do construtor do Consumer:
* (printer, buffer, numero_identificador, numero_de_consumos, possui_tempo_de_espera_para_consumir)
Consumer c1 = new Consumer(p, b, 1, 20, false);
*/
```

> O `possui_tempo_de_espera_para_consumir` é um boolean que diz se você quer que um `Producer` espere um tempo randômico para então tentar colocar outra produção no `buffer`.

### Exemplo de uso com um ou mais produtores:

```java
Buffer b = new Buffer();
Printer p = new Printer();

Producer p1 = new Producer(p, b, 1, 8, true);
Producer p2 = new Producer(p, b, 2, 12, true);
Consumer c1 = new Consumer(p, b, 1, 20, false);

p1.start(); 
p2.start();
c1.start();
```

> Não altere o `buffer` e `printer` compartilhados. Precisam ser iguais para que os `Producers` e `Consumers` interajam entre si.

## Implementação

### Buffer

O `buffer` possui a finalidade de solucionar o problema de exclusão mútua para a produção e consumo dos conteúdos gerados.

### Printer

A `printer` possui a finalidade de sincronizar as mensagens mostradas ao usuário. Somente as de valor relevante e não as de informação (`INFO>`).

## Exemplo do formato de saída:

### Configuração utilizada

```java
Producer p1 = new Producer(p, b, 1, 2, true);
Producer p2 = new Producer(p, b, 2, 3, true);
Consumer c1 = new Consumer(p, b, 1, 5, false);
```

### Saída

```javascript
Producer #2 put: 1. Wait time to put: 0. 
Consumer #1 got: 1 from Producer #2. Wait time to get: 0
Consumer #1 got: 1 from Producer #1. Wait time to get: 0
Producer #1 put: 1. Wait time to put: 1. 
INFO> Producer #2 produce again in 35 miliseconds
INFO> Producer #1 produce again in 38 miliseconds
Producer #2 put: 2. Wait time to put: 0. 
Consumer #1 got: 2 from Producer #2. Wait time to get: 37
INFO> Producer #2 produce again in 20 miliseconds
Producer #1 put: 2. Wait time to put: 0. 
INFO> Producer #1 produce again in 63 miliseconds
Consumer #1 got: 2 from Producer #1. Wait time to get: 3
Producer #2 put: 3. Wait time to put: 0. 
Consumer #1 got: 3 from Producer #2. Wait time to get: 17
INFO> Producer #2 produce again in 52 miliseconds
```
