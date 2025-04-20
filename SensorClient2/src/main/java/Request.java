import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

public class Request {
    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            request("Sensor1");
        }
        for (int i = 0; i < 50; i++) {
            request("Sensor2");
        }

        // Получение данных
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/measurements";
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);

    }
    // отправка данных
    private static void request(String sensorName){
        RestTemplate restTemplate = new RestTemplate();
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setName(sensorName);
        MeasurementDTO measDTO = new MeasurementDTO();
        measDTO.setOwner(sensorDTO);
        measDTO.setRaining(Math.random() < 0.5);
        measDTO.setValue((199*Math.random()-100));
        // Для того, чтобы отправлять объект по сети, необходимо упаковать его в httpEntity.
        HttpEntity<MeasurementDTO> request = new HttpEntity<>(measDTO);
        // Адрес, куда отправлять данные.
        String url = "http://localhost:8080/measurements/add";
        // в отправке указывем адрес, объект для отправки, в виде какого класса принимать ответ
        String response = restTemplate.postForObject(url, request, String.class);
        System.out.println(response);
    }
}
