/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package company.connectRecaptcha;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;


/**
 *
 * @author Admin
 */
public class VerifyUtils {
    public static final String SITE_VERIFY_URL = //
            "https://www.google.com/recaptcha/api/siteverify";
    
    public static boolean verify(String gRecaptchaResponse){
        if(gRecaptchaResponse == null || gRecaptchaResponse.length() == 0){
            return false;
        }
        try {
            URL verifyURL = new URL(SITE_VERIFY_URL);
            
            //Mo mot ket noi(Connection) toi URL tren;
            HttpsURLConnection conn = (HttpsURLConnection)verifyURL.openConnection();
            
            //Them cacs thong tiin header vao Request chuan bi gui toi sever
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            
            //Du lieu se gui toi sever
            String postParams = "secret=" + MyConstants.SECRET_KEY//
                    + "&response=" + gRecaptchaResponse;
            
            //Send Request
            conn.setDoOutput(true);
            
            //Lay Output Steram (Luong dau ra) cua ket noi toi Sever
            //Ghi du lieu vao Output Stream, co nghia la gui thong tin den Servr
            
            OutputStream outStream = conn.getOutputStream();
            outStream.write(postParams.getBytes());
            
            outStream.flush();
            outStream.close();
            
            //Ma tra loi duoc tra ve tu sever
//            int responseCode = conn.getResponseCode();
            
            //Lay Input Stram (Luong dau vao) cua Connection
            //de doc du lieu gui den tu Sever
            InputStream is = conn.getInputStream();
            
            JsonReader jsonReader = Json.createReader(is);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();
            
            //==> {"Success":true}
            
            boolean success = jsonObject.getBoolean("success");
            return success;
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
