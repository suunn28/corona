package org.example;

public class ConsoleAnnouncer implements Announcer {
    @Override
    public void announce( final String message ) {
        System.out.println(message);
    }
}
