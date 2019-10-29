package com.example.buscabbva.deserializers;

import com.example.buscabbva.modelos.BBVASucursal;
import com.example.buscabbva.modelos.ListaSucursales;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MyDeserializer implements JsonDeserializer<ListaSucursales> {
    @Override
    public ListaSucursales deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<BBVASucursal> sucursals = new ArrayList<>();

        JsonArray data = json.getAsJsonObject().get("data").getAsJsonArray();
        JsonArray brand = data.getAsJsonObject().get("Brand").getAsJsonArray();
        JsonArray branch = brand.getAsJsonObject().get("Branch").getAsJsonArray();
        for (int i = 0; i < branch.size(); i++) {
            sucursals.add(new BBVASucursal(
                    branch.get(i).getAsJsonObject().get("Name").getAsString(),
                    branch.get(i).getAsJsonObject().get("PostalAddress").getAsJsonObject().get("AddressLine").getAsJsonArray().get(0).getAsString(),
                    branch.get(i).getAsJsonObject().get("ContactInfo").getAsJsonObject().get("ContactType").getAsJsonArray().get(0).getAsJsonObject().get("ContactContent").getAsString(),
                    branch.get(i).getAsJsonObject().get("PostalAddress").getAsJsonObject().get("GeoLocation").getAsJsonObject().get("GraphicCoordinates").getAsJsonObject().get("Latitude").getAsString() +
                            branch.get(i).getAsJsonObject().get("PostalAddress").getAsJsonObject().get("GeoLocation").getAsJsonObject().get("GraphicCoordinates").getAsJsonObject().get("Latitude").getAsString()
            ));
        }
        return new ListaSucursales(sucursals);
    }
}
