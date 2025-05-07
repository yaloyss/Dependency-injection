package com.yaloys.data;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {GsonModule.class})
public interface AppComponent
{
    RemindersRepository getRemindersRepository();
}
