/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Until;

import java.io.BufferedReader;
import javax.servlet.http.HttpServletRequest;

/**
 * @author cleju
 */
public class GetJson {
    public static String getJson(HttpServletRequest request) {
        StringBuffer json = new StringBuffer();
        String line = "";
        BufferedReader read;
        try {
            read = request.getReader();
            if ((line = read.readLine()) != null) {
                json.append(line);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json.toString();
    }
}
