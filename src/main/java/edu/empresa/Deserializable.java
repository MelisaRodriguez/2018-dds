package edu.empresa;

import java.lang.reflect.Type;

import com.google.gson.*;

import edu.empresa.EstadoDispositivo;

public class Deserializable<T> implements JsonDeserializer<T> {

    private static final String CLASSNAME = "className";

    @Override
    public T deserialize(final JsonElement jsonElement, final Type type,
                         final JsonDeserializationContext deserializationContext
                        ) throws JsonParseException {

        final JsonObject jsonObject = jsonElement.getAsJsonObject();
        final JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
        final String className = prim.getAsString();
        final Class<T> clazz = getClassInstance(className);
        return deserializationContext.deserialize(jsonObject, clazz);
    }

    @SuppressWarnings("unchecked")
    public Class<T> getClassInstance(String className) {
        try {
            return (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException cnfe) {
            throw new JsonParseException(cnfe.getMessage());
        }
    }

}