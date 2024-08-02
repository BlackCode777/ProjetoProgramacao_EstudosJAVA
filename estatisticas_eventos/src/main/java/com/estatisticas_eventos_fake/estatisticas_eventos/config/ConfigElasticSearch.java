package com.estatisticas_eventos_fake.estatisticas_eventos.config;

/*

Principais funções para criar um cliente elasticsearch

1) - CredentialsProvider / USU_EVENTO / SENHA_EVENTO
2) - SSLContext / HTTPS / ENDERECO_DESTINO / 9200
3) - RestHighLevelClientBuilder / elasticsearchClient() / setApiCompatibilityMode(true)
4) - createSimpleElasticClient()

O código apresentado é uma configuração para criar um cliente Elasticsearch na linguagem Java usando a biblioteca Elasticsearch High-Level REST Client.
A classe ConfigElasticSearch possui dois métodos para criar o cliente Elasticsearch: elasticsearchClient() e createSimpleElasticClient(). Ambos os métodos 
têm o mesmo objetivo, que é criar um cliente Elasticsearch para interagir com o cluster Elasticsearch.

No método elasticsearchClient(), o cliente é criado usando o RestHighLevelClientBuilder. Ele configura um cliente que faz uso do Elasticsearch High-Level 
REST Client e é criado para suportar versões do Elasticsearch de 7.14 ou superiores (devido à chamada setApiCompatibilityMode(true)).

A configuração inclui a definição de um CredentialsProvider, que é usado para fornecer as credenciais de autenticação necessárias para se conectar ao cluster 
Elasticsearch. Nesse caso, as credenciais são definidas no início da classe (variáveis USU_EVENTO e SENHA_EVENTO) e são usadas para autenticação no cluster.

Também é configurado um SSLContext, que é usado para estabelecer uma conexão segura usando HTTPS com o cluster Elasticsearch.

O cliente é configurado para se conectar ao endereço de destino especificado (variável ENDERECO_DESTINO) e porta 9200 (porta padrão do Elasticsearch).

O método createSimpleElasticClient() é uma alternativa ao primeiro método, mas faz essencialmente a mesma coisa. Ele também cria um cliente Elasticsearch usando 
o Elasticsearch High-Level REST Client e estabelece uma conexão segura usando HTTPS com o cluster Elasticsearch.

Ambos os métodos retornam um objeto RestHighLevelClient, que é a instância do cliente Elasticsearch que pode ser usada para interagir com o cluster Elasticsearch, 
como executar consultas, inserir, atualizar ou excluir documentos, entre outras operações.

É importante notar que o código parece usar credenciais hardcoded (hardcoded credentials), o que não é uma prática recomendada em produção, pois as credenciais 
geralmente devem ser armazenadas de forma segura e gerenciadas adequadamente. Em um ambiente de produção, você deve considerar o uso de configurações externas 
(por exemplo, arquivos de propriedades ou variáveis de ambiente) para fornecer as informações sensíveis, como credenciais de acesso.

Além disso, o código também trata exceções usando try-catch, mas apenas loga as exceções sem realizar nenhum tratamento adicional. Em um ambiente real, você deve 
lidar com exceções de forma adequada, seja tratando-as de forma apropriada, ou lançando-as para serem tratadas em um nível superior.

*/

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.RestHighLevelClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.estatisticas_eventos_fake.estatisticas_eventos.migration.Projeto;

@SuppressWarnings("deprecation")
public class ConfigElasticSearch {

    private static final Logger LOG = LoggerFactory.getLogger(ConfigElasticSearch.class);

    private static String ENDERECO_DESTINO = "https://localhost:9200";
    private static String USU_EVENTO = "DESKTOP-D2G5F9V";
    private static String SENHA_EVENTO = "etQwdR-WR0OuPs8UmztZlw";

    public RestHighLevelClient elasticsearchClient() {
        Projeto site = new Projeto();

        site.setEnderecoDestino(ENDERECO_DESTINO);
        site.setUsuario(USU_EVENTO);
        site.setSenha(SENHA_EVENTO);

        try {

            /*
             * Provedor de credenciais abstratas que mantém uma coleção de credenciais de
             * usuário.
             * 
             * As implementações dessa interface devem ser thread-safe. O acesso aos dados
             * compartilhados deve ser sincronizado, pois os métodos dessa interface podem
             * ser executados a partir de vários threads.
             */
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials(site.getUsuario(), site.getSenha()));

            /*
             * Observe: a implementação Oracle JSSE padrão de SSLContext.init(KeyManager [],
             * TrustManager [], SecureRandom) aceita várias chaves e gerenciadores de
             * confiança, no entanto, apenas o primeiro tipo correspondente é usado.
             * Consulte o exemplo: SSLContext.html#init
             */
            SSLContextBuilder sslContextBuilder = SSLContexts.custom().loadTrustMaterial(null,
                    (x509Certificates, s) -> true);
            final SSLContext sslContext = sslContextBuilder.build();

            return new RestHighLevelClientBuilder(
                    RestClient.builder(new HttpHost(site.getEnderecoDestino(), 9200, "https"))
                            .setHttpClientConfigCallback(

                                    new HttpClientConfigCallback() {
                                        @Override
                                        public HttpAsyncClientBuilder customizeHttpClient(
                                                HttpAsyncClientBuilder httpClientBuilder) {
                                            return httpClientBuilder.setSSLContext(sslContext)
                                                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                                                    .setDefaultCredentialsProvider(credentialsProvider);
                                        }

                                        @SuppressWarnings("unused")
                                        public RequestConfig.Builder customizeResquestConfig(
                                                RequestConfig.Builder requestConfigBuilder) {
                                            return requestConfigBuilder.setConnectTimeout(5000).setSocketTimeout(60000);
                                        }
                                    })
                            .build())
                    .setApiCompatibilityMode(true).build();
        } catch (Exception e) {
            LOG.error("Erro ao criar o cliente elasticsearch", e);
        }
        return null;
    }

    public RestHighLevelClient createSimpleElasticClient() throws Exception {

        Projeto site = new Projeto();
        site.setEnderecoDestino(ENDERECO_DESTINO);
        site.setNome(USU_EVENTO);
        site.setSenha(SENHA_EVENTO);

        try {
            // Destino (Elastic ~7.14)
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials(USU_EVENTO, SENHA_EVENTO));

            SSLContextBuilder sslBuilder = SSLContexts.custom().loadTrustMaterial(null, (x509Certificates, s) -> true);
            final SSLContext sslContext = sslBuilder.build();

            RestHighLevelClient restHighLevelClientDestino = new RestHighLevelClient(RestClient
                    .builder(new HttpHost(site.getEnderecoDestino(), 9200, "https")).setHttpClientConfigCallback(

                            new HttpClientConfigCallback() {
                                @Override
                                public HttpAsyncClientBuilder customizeHttpClient(
                                        HttpAsyncClientBuilder httpClientBuilder) {
                                    return httpClientBuilder.setSSLContext(sslContext)
                                            .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                                            .setDefaultCredentialsProvider(credentialsProvider);
                                }

                                @SuppressWarnings("unused")
                                public RequestConfig.Builder customizeRequestConfig(
                                        RequestConfig.Builder requestConfigBuilder) {
                                    return requestConfigBuilder
                                            .setConnectTimeout(5000)
                                            .setSocketTimeout(60000);
                                }
                            }

                    // new RestClientBuilder.HttpClientConfigCallback() {
                    // @Override
                    // public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder
                    // httpClientBuilder) {
                    // return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    // }
                    // }
                    ));
            return restHighLevelClientDestino;

        } catch (Exception e) {
            // TODO: handle exception
            throw e;
        }
    }

}
