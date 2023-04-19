package org.example;

import lombok.SneakyThrows;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;

public class FileAnnouncer implements Announcer {


    @Override
    @SneakyThrows
    public void announce( final String message ) {
      try ( final var writer = new BufferedWriter( new FileWriter( "output.txt" + LocalDateTime.now() ) ) ) {
            writer.append( message );
        }
    }
}
