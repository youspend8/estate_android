package kr.co.estate.client;

public class RetrofitSpecificationFactory {
    public static RetrofitApiSpecification getApiSpecification() {
        return RetrofitClient.getInstance().create(RetrofitApiSpecification.class);
    }
}
