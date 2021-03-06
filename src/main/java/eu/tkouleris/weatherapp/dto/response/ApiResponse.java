package eu.tkouleris.weatherapp.dto.response;

import org.springframework.stereotype.Component;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ApiResponse {
    private Map<String, Object> body = new LinkedHashMap<>();

    public ApiResponse(){
        body.put("message", null);
        body.put("data", null);
    }

    public void setMessage(String message){
        body.put("message", message);
    }

    public void setData(Object data)
    {
        body.put("data", data);
    }


    public Map<String,Object> getBodyResponse(){
        return body;
    }
}
