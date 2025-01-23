# Solução Cozinha

Este projeto integra a API do Mercado Pago para a criação de preferências de pagamento utilizando o SDK do Mercado Pago. Com isso, é possível criar um link para o Checkout Pro sem a necessidade de lidar diretamente com os dados do cliente para integrar o pagamento em sua aplicação. Essa solução também pode ser usada em aplicações Spring Boot.

## Como Utilizar

1. Configure o token de acesso do Mercado Pago:
    ```java
    MercadoPagoConfig.setAccessToken("ACCESS_TOKEN"); // Substitua "ACCESS_TOKEN" pelo seu token de acesso
    ```
2. Crie um item de preferência:
    ```java
    PreferenceItemRequest item = PreferenceItemRequest.builder()
            .title("TITULO DA VENDA") // Substitua "TITULO DA VENDA" pelo título do item
            .quantity(1)
            .unitPrice(new BigDecimal("49.90")) // Substitua "49.90" pelo valor do item
            .build();
    ```
3. Configure o pagador:
    ```java
    PreferencePayerRequest payer = PreferencePayerRequest.builder()
            .email("EMAIL@GMAIL.COM") // Substitua "EMAIL@GMAIL.COM" pelo email do cliente
            .build();
    ```
4. Crie a preferência de pagamento:
    ```java
    PreferenceRequest preferenceRequest = PreferenceRequest
            .builder()
            .items(List.of(item))
            .payer(payer)
            .backUrls(PreferenceBackUrlsRequest.builder()
                    .success("https://www.seusite.com/sucesso")
                    .failure("https://www.seusite.com/falha")
                    .pending("https://www.seusite.com/pendente")
                    .build())
            .build();
    ```
5. Gere o link de checkout:
    ```java
    PreferenceClient preferenceClient = new PreferenceClient();
    Preference preference = preferenceClient.create(preferenceRequest);

    System.out.println("Link do Checkout Pro: " + preference.toString());
    ```

## Licença

Este projeto está licenciado sob a [MIT License](https://opensource.org/licenses/MIT).