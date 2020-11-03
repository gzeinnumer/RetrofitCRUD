# Retrofit Insert Read

Please Enable [Retrofit](https://square.github.io/retrofit/) on your project, follow this step.

```gradle
implementation 'com.squareup.retrofit2:retrofit:2.6.0'
implementation 'com.google.code.gson:gson:2.8.5'
implementation 'com.squareup.retrofit2:converter-gson:2.5.0'
implementation 'com.squareup.okhttp3:okhttp:3.12.0'
```

- Make Class RetroServer
```java
public class RetroServer {
    //private static final String base_url = "http://10.0.2.2/retrofit/";
    private static final String base_url = "http://192.168.0.121/retrofit/";

    private static Retrofit setInit(){
        return new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiRequestBiodata getInstance(){
        return setInit().create(ApiRequestBiodata.class);
    }
}
```
