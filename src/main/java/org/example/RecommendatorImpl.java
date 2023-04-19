package org.example;

public class RecommendatorImpl implements Recommendator {
    @InjectProperty("#alcohol")
    private String alcohol;

    @Override
    public void recommend() {
        System.out.println(" to protect from covid drink " + alcohol );
    }
}
