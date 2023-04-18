package org.example;

public class CoronaDesinfector {

    private Announcer announcer = ObjectFactory.getInstance().createObject( Announcer.class );
    private Policeman policeman = ObjectFactory.getInstance().createObject( Policeman.class );

    public void start( Room room ){
        announcer.announce( "Начинаем дезинфекцию, все вон!");
        policeman.makePeopleLeave();
        desinfect( room );

        announcer.announce( "Можете вернуться!");

    }

    private void desinfect( Room room ){
        System.out.println("молитва: корона изыди - вирус низвергнут в ад" );
    }
}
