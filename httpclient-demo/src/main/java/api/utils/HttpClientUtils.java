package api.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author jiangxukun1
 * @date 2018年8月2日 上午12:17
 */
public class HttpClientUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtils.class);

    private volatile static CloseableHttpClient httpClient = null;
    private volatile static CloseableHttpClient httpsClient = null;

    public static CloseableHttpClient initHttpClient(){
        if(httpClient == null){
            synchronized (HttpClientUtils.class){
                if(httpClient == null){
                    httpClient = HttpClients.createDefault();
                    LOGGER.info("init httpclient success: {}", httpClient);
                }
            }
        }
        return httpClient;
    }

    public static CloseableHttpClient initHttpsClient(){
        if(httpsClient == null){
            synchronized (HttpClientUtils.class){
                if(httpsClient == null){
                    try {
                        SSLContextBuilder sslContextBuilder = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                            //信任所有
                            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                                return true;
                            }
                        });
                        SSLContext sslContext = sslContextBuilder.build();
                        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext);
                        httpsClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
                        LOGGER.info("init httpsclient success: {}", httpsClient);
                    } catch (Exception e) {
                        LOGGER.error("init HttpsClient fail, Exception: {}", e);
                    }
                }
            }
        }
        return httpsClient;
    }

    public static String httpGetRequest(String url, Map<String, String> params) throws Exception {
        initHttpClient();
        return getRequest(httpClient, url, params);
    }

    public static String httpsGetRequest(String url, Map<String, String> params)throws Exception{
        initHttpsClient();
        return getRequest(httpsClient, url, params);
    }

    private static String httpPostRequest(String url, Map<String, String> params) throws Exception{
        initHttpClient();
        return postRequest(httpClient, url, params);
    }

    private static String httpsPostRequest(String url, Map<String, String> params) throws Exception{
        initHttpsClient();
        return postRequest(httpsClient, url, params);
    }

    private static String getRequest(CloseableHttpClient httpClient, String url, Map<String, String> params) throws Exception{
        URIBuilder uriBuilder = new URIBuilder(url).setCharset(Charset.forName("UTF-8"));
        if(params != null && !params.isEmpty()){
            for(Entry<String, String> entry : params.entrySet()){
                uriBuilder.addParameter(entry.getKey(), entry.getValue());
            }
        }
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        HttpResponse response = httpClient.execute(httpGet);
        HttpEntity entity = response.getEntity();
        return EntityUtils.toString(entity);
    }

    private static String postRequest(CloseableHttpClient httpClient, String url, Map<String, String> params) throws Exception{
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> postPair = new ArrayList<>();
        for(String key : params.keySet()){
            postPair.add(new BasicNameValuePair(key, params.get(key)));
        }
        CloseableHttpResponse response = null;
        try{
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postPair, "UTF-8");
            httpPost.setEntity(formEntity);
            response = httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }finally {
            if(response != null){
                response.close();
            }
        }
    }
}
