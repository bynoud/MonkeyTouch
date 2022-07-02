package me.ductran.monkeytouch.output.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import me.ductran.monkeytouch.output.touch.CircleMapping;
import me.ductran.monkeytouch.output.touch.FPSJoystick;
import me.ductran.monkeytouch.output.touch.SlideMapping;
import me.ductran.monkeytouch.output.touch.TapMapping;
import me.ductran.monkeytouch.output.touch.TouchMapping;
import me.ductran.monkeytouch.output.touch.TriggeredJoystickMapping;

public class ConfigParser {
    Gson gson;

    public ConfigParser() {
        GsonBuilder newGson = new GsonBuilder();
        newGson.registerTypeAdapterFactory(buildMappingAdapter());

        gson = newGson.create();
    }

    public TouchConfig parseConfig(File file) throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(file));
        return gson.fromJson(reader, TouchConfig.class);
    }

    public static RuntimeTypeAdapterFactory<TouchMapping> buildMappingAdapter() {
        final RuntimeTypeAdapterFactory<TouchMapping> typeFactory =
                RuntimeTypeAdapterFactory.of(TouchMapping.class, TouchMapping.PAYMENT_TYPE_KEY)
                                         .registerSubtype(TapMapping.class, TapMapping.KEY_TYPE)
                                         .registerSubtype(CircleMapping.class, CircleMapping.KEY_TYPE)
                                         .registerSubtype(FPSJoystick.class, FPSJoystick.KEY_TYPE)
                                         .registerSubtype(SlideMapping.class, SlideMapping.KEY_TYPE)
                                         .registerSubtype(TriggeredJoystickMapping.class, TriggeredJoystickMapping.KEY_TYPE);

        return typeFactory;
    }

}
