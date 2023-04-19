package org.example;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InjectPropertyConfigurator implements ObjectConfigurator {

    private final Map<String, String> properties;

    @SneakyThrows
    public InjectPropertyConfigurator() {
        final String path = ClassLoader.getSystemClassLoader().getResource( "application.properties" ).getPath();
        final Stream<String> lines = new BufferedReader( new FileReader( path ) ).lines();
        properties = lines.map( line -> line.split( "=" ) )
                          .collect( Collectors.toMap( e -> e[ 0 ],
                                                      e -> e[ 1 ] ) );
    }

    @Override
    @SneakyThrows
    public void configure( final Object object ) {
        final Class<?> implClass = object.getClass();
        for( Field field:implClass.getDeclaredFields()){
            InjectProperty annotation = field.getAnnotation( InjectProperty.class );

            String value;
            if ( annotation != null ){
                if( annotation.value().isEmpty() ){
                    value = properties.get( field.getName() );
                }
                else{
                    value = properties.get( annotation.value() );
                }
                field.setAccessible( true );
                field.set( object, value);
            }
        }

    }
}
