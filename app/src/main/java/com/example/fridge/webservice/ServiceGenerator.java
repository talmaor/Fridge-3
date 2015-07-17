package com.example.fridge.webservice;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class ServiceGenerator {

    private ServiceGenerator() {

    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl, final String apiKey, final String apiHeader, RestAdapter.LogLevel logLevel) {


        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(baseUrl)
                .setLogLevel(logLevel);

        if (apiKey != null && !apiKey.isEmpty()) {

            RequestInterceptor requestInterceptor = new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader(apiHeader, apiKey);
                }
            };
            builder.setRequestInterceptor(requestInterceptor);
        }


        RestAdapter adapter = builder.build();

        return adapter.create(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl, final String apiKey, final String apiHeader) {
        return createService(serviceClass, baseUrl, apiKey, apiHeader, RestAdapter.LogLevel.FULL);
    }

    public static RESTfulService createRESTfulService(final String apiKey) {
        return createService(RESTfulService.class, RESTfulService.ENDPOINT, apiKey, RESTfulService.AuthorizationHeader);
    }

    public static RESTfulService createRESTfulService() {
        return createService(RESTfulService.class, RESTfulService.ENDPOINT, "", "");
    }
}
