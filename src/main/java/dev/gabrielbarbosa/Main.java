package dev.gabrielbarbosa;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) throws MPException, MPApiException {

        MercadoPagoConfig.setAccessToken("ACCESS_TOKEN"); // ACCESS TOKEN MERCADO PAGO

        PreferenceItemRequest item = PreferenceItemRequest.builder()
                .title("TITULO DA VENDA") // TITULO DA VENDA
                .quantity(1)
                .unitPrice(new BigDecimal("49.90")) // VALOR
                .build();

        PreferencePayerRequest payer = PreferencePayerRequest.builder()
                .email("EMAIL@GMAIL.COM") // EMAIL DO CLIENTE
                .build();

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

        PreferenceClient preferenceClient = new PreferenceClient();
        Preference preference = preferenceClient.create(preferenceRequest);

        System.out.println("Link do Checkout Pro: " + preference.toString());

    }

}
