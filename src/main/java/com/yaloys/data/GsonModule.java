package com.yaloys.data;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import java.time.LocalDate;

@Module
public class GsonModule
{
    @Provides
    @Singleton
    public Gson provideGson()
    {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting().create();
    }
}
