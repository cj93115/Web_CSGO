package com.Web_CSGO.common.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lianggs
 * @version 2017年06月23日 23时21分
 */
public final class HttpClientUtil {
    private HttpClientUtil() {
    }


    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 7000;

    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        //        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        // 在提交请求之前 测试连接是否可用
        //configBuilder.setStaleConnectionCheckEnabled(true);
        requestConfig = configBuilder.build();
    }


    /**
     * Http get 请求
     *
     * @param url
     * @param param
     * @return
     */
    public static String doGet(String url, Map<String, Object> param, Map<String, Object> header) {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            //创建URI
            URIBuilder builder = new URIBuilder(url);
            if (param != null && !param.isEmpty()) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key).toString());
                }
            }
            URI uri = builder.build();
            //创建HTTP GET请求
            HttpGet httpGet = new HttpGet(uri);

            if (header != null && !header.isEmpty()) {
                for (String key : header.keySet()) {
                    httpGet.addHeader(key, header.get(key).toString());
                }
            }
            //执行请求
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(httpClient, response);
        }
        return resultString;
    }

    /**
     * http get 请求，不带参的
     *
     * @param url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, null, null);
    }

    /**
     * http post 请求，参数带header
     *
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static String doPost(String url, Map<String, String> params, Map<String, String> headers) {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        //1.创建 HttpClient 的实例
        try {
            HttpPost httpPost = new HttpPost(url);
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : params.keySet()) {
                    paramList.add(new BasicNameValuePair(key, params.get(key)));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
                entity.setContentType("application/json;charset=utf-8");
                httpPost.setEntity(entity);
            }

            if (headers != null && !headers.isEmpty()) {
                Header[] _headers = new Header[headers.size()];
                int i = 0;
                for (String key : headers.keySet()) {
                    _headers[i] = new BasicHeader(key, headers.get(key));
                    i++;
                }
                httpPost.setHeaders(_headers);
            }
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            return resultString;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String doPostByHeader(String url, Map<String, String> params) {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        //1.创建 HttpClient 的实例
        try {
            HttpPost httpPost = new HttpPost(url);
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : params.keySet()) {
                    paramList.add(new BasicNameValuePair(key, params.get(key)));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
                httpPost.setEntity(entity);
            }
            httpPost.setHeader("Version", "1");
            //使用base64进行加密
            byte[] tokenByte = Base64.encodeBase64(("testclient:password").getBytes());
            //将加密的信息转换为string
            String token = new String(tokenByte, 0, tokenByte.length, "utf-8");
            System.out.println(token);
            //把认证信息发到header中
            httpPost.setHeader("Authorization", "Basic " + token);
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            return resultString;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 带参数的post请求
     *
     * @param url
     * @param param
     * @return
     */
    public static String doPost(String url, Map<String, String> param) {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            if (param != null && !param.isEmpty()) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
                entity.setContentType("application/json;charset=utf-8");
                httpPost.setEntity(entity);
            }
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            //保护cookie来保存
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(httpClient, response);
        }
        return resultString;
    }


    /**
     * http post 请求，不带参的
     *
     * @param url
     * @return
     */
    public static String doPost(String url) {
        return doPost(url, new HashMap<>());
    }

    public static String doPost(String url, String json, ContentType contentType) {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;

        if (null == contentType) {
            contentType = ContentType.APPLICATION_JSON;
        }
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(json, contentType);
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(httpClient, response);
        }
        return resultString;
    }

    public static String doPost(String url, String json,String header, ContentType contentType) {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;

        if (null == contentType) {
            contentType = ContentType.APPLICATION_JSON;
        }
        try {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(json, contentType);
            httpPost.setEntity(entity);
            httpPost.setHeader("Authorization",header);
            response = httpClient.execute(httpPost);
            if(null == response.getEntity()){
                return null;
            }
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(httpClient, response);
        }
        return resultString;
    }

    public static String doPut(String url, String json,String header, ContentType contentType) {
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";
        CloseableHttpResponse response = null;

        if (null == contentType) {
            contentType = ContentType.APPLICATION_JSON;
        }
        try {
            HttpPut httpPut = new HttpPut(url);
            StringEntity entity = new StringEntity(json, contentType);
            httpPut.setEntity(entity);
            httpPut.setHeader("Authorization",header);
            response = httpClient.execute(httpPut);
            if(null == response.getEntity()){
                return null;
            }
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(httpClient, response);
        }
        return resultString;
    }

    private static void close(CloseableHttpClient httpClient, CloseableHttpResponse response) {
        try {
            if (httpClient != null) {
                httpClient.close();
            }

            if (response != null) {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 发送 SSL POST 请求（HTTPS），JSON形式
     * @param apiUrl API接口URL
     * @param json JSON对象
     * @return
     */
    public static String doPostSSL(String apiUrl, Object json) {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).
                setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        String httpStr = null;

        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json.toString(),"UTF-8");//解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 创建SSL安全连接
     *
     * @return
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                @Override
                public void verify(String host, SSLSocket ssl) throws IOException {
                }

                @Override
                public void verify(String host, X509Certificate cert) throws SSLException {
                }

                @Override
                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
                }
            });
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }
}
