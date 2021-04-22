package ru.dsoccer.aktor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.beans.factory.annotation.Qualifier;

@Retention(RetentionPolicy.RUNTIME)
@Qualifier
@Thriller @Comedy @Adventure
public @interface AnyGenre {

}
