package automacao;

import java.io.IOException;
import java.util.List;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;

public class JsonUtils {

    private JsonUtils () {
    }

    /**
     * Convert String Json to java Object
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T convertStringToObject(String json, Class<T> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Convert String Json to java Object List
     */
    public static <T> List<T> convertStringToObjectList(String json, Class<T> clazz){
        try {
            ObjectMapper mapper = new ObjectMapper();
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return mapper.readValue(json, collectionType);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Convert java objects to String Json
     *
     * @param obj
     * @return
     */
    public static String convertObjectToStringJson(Object obj) {
        try {
            if (obj == null) {
                return "";
            }
            ObjectMapper mapper = new ObjectMapper();;
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.writeValueAsString(obj);
        } catch (IOException e) {
            return null;
        }
    }

}
