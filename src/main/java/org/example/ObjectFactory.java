package org.example;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectFactory {

    private static ObjectFactory ourInstance = new ObjectFactory();
    private List<ObjectConfigurator> configurators = new ArrayList<>();
    private Config config;


    public static ObjectFactory getInstance(){
        return ourInstance;
    }

    @SneakyThrows
    private ObjectFactory(){
        this.config = new JavaConfig( "org.example",
                                      new HashMap<>( Map.of( Policeman.class,AngryPoliceman.class,
                                                             Announcer.class, ConsoleAnnouncer.class ) ) );
        for ( final Class<? extends ObjectConfigurator> aClass : config.getScanner().getSubTypesOf( ObjectConfigurator.class ) ) {
            configurators.add( aClass.getDeclaredConstructor().newInstance() );
        }

    }

    @SneakyThrows
    public <T> T createObject( Class<T> type ){
        Class<? extends T> implClass = type;
        if( type.isInterface() ) {
            implClass = config.getImplClass( type );
        }
        T t =  implClass.getDeclaredConstructor().newInstance();
        configurators.forEach( objectConfigurator -> objectConfigurator.configure( t ) );

        return t;
    }
}

