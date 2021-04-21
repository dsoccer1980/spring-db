package ru.dsoccer.corona.configuration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import ru.dsoccer.corona.treatment.Лечение;

@Retention(RetentionPolicy.RUNTIME)
public @interface InjectList {

  Class<? extends Лечение>[] value();

}
