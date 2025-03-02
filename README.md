## **Sumário**
1. [Introdução](#introdu%C3%A7%C3%A3o)
2. [Tech Stack](#tech-stack)
3. [Instalação e Execução](#instala%C3%A7%C3%A3o-e-execu%C3%A7%C3%A3o)
4. [Estrutura do Projeto](#estrutura-do-projeto)
5. [Funcionalidades](#funcionalidades)
6. [Contribuindo](#contribuindo)
7. [Licença](#licen%C3%A7a)

## **Introdução**
A `DemoApplication` é uma aplicação Spring Boot projetada para simplificar o processamento e envio automatizado de dados. Ela é capaz de:
- Obter dados de produtos a partir de uma estrutura em JSON externa configurável;
- Processar os dados recebidos e agrupá-los conforme uma chave única;
- Gerar um arquivo CSV dos dados processados;
- Enviar o arquivo gerado via e-mail como anexo para um destinatário configurado.

Esta aplicação é especialmente útil para automação de tarefas relacionadas à gestão e reporte de dados em formato estruturado, como em uso corporativo para equipes de marketing e análise.
## **Tech Stack**
A aplicação foi construída utilizando as seguintes tecnologias e ferramentas:
- **Java 22** – Linguagem base do projeto.
- **Spring Boot** – Framework principal para desenvolvimento de aplicações.
    - Módulos usados: `Spring Mail` para o envio de e-mails.

- **Apache Maven** – Gerenciador de dependências e build da aplicação.
- **HTML integrado ao e-mail** – Para mensagens de e-mail estilizadas e dinâmicas.

## **Instalação e Execução**
### **Pré-requisitos**
- Java 22 instalado.
- Maven configurado no ambiente.
- Conexão com a internet para enviar e-mails.
- Configurar as propriedades de e-mail e API no arquivo `application.properties`.

### **Instalando o Projeto**
1. Clone o repositório:
``` bash
   git clone https://github.com/Neosolar
   cd neosolar
```
1. Compile o projeto:
``` bash
   mvn clean install
```
### **Executando o Projeto**
1. Certifique-se de configurar o arquivo `application.properties` com os valores adequados (veja [application.properties](#configura%C3%A7%C3%A3o)).
2. Inicie a aplicação:
``` bash
   mvn spring-boot:run
```
1. A aplicação será inicializada no terminal e processará os dados automaticamente.

### **Configuração**
Certifique-se de alterar as propriedades de configuração (exemplo `application.properties`):
``` properties
# Configuração do endpoint JSON
app.json.url=https://sua-api-externa.com/
app.json.idGenerator=1000
app.json.nameCsv=dados.csv
app.json.bodyMailHtml=estrutura do email

# Configuração do email
spring.mail.from=seuamail@dominio.com
spring.mail.to=destinatario@dominio.com
spring.mail.subject=Relatório de Produtos
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=seuamail@dominio.com
spring.mail.password=suasenha
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.nameCsv=dados.csv

# Configuração do timeout para requisições
spring.web.client.connect-timeout=5000
spring.web.client.read-timeout=5000
```
## **Estrutura do Projeto**
O projeto segue uma estrutura bem organizada para manter a manutenção simples e código limpo:
``` plaintext
src/main/java/com/example/demo/
├── config/
│   ├── Config.java                     # Configurações personalizadas do app.
│   └── EmailConfig.java                # Configurações de e-mail baseadas no application.properties.
├── entity/
│   └── Product.java                    # Entidade que define a estrutura de um produto.
├── service/
│   └── EmailService.java               # Serviço responsável pelo envio de e-mails com anexos.
├── util/
│   ├── CsvGenerator.java               # Utilitário para geração de arquivos CSV.
│   └── IdGeneratorController.java      # Controlador para gerar IDs únicos.
├── DemoApplication.java                # Classe principal do projeto.
└── resources/
    └── application.properties          # Arquivo de configurações.
```

## **Funcionalidades**
1. **Consumo de API Externa**:
    - Realiza requisição `GET` para uma URL configurada.
    - Mapeia os dados JSON de produtos para a classe Java `Product`.

2. **Agrupamento e Processamento de Produtos**:
    - Processa produtos recebidos.
    - Agrupa dados com base em ID gerado dinamicamente.

3. **Geração de Relatório CSV**:
    - Gera tabelas de dados com cabeçalhos pré-definidos em um arquivo CSV.
    - Salva o arquivo com o nome configurado no diretório local.

4. **Envio de E-mail Automatizado**:
    - Envia o arquivo CSV gerado como anexo.
    - Configura e envia e-mails com suporte a autenticação SMTP.

