package de.codecrunch;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicInteger;

import static android.support.test.internal.util.Checks.checkNotNull;

/**
 * An simple counter implementation of that determines idleness by
 * maintaining an internal counter. When the counter is 0 - it is considered to be IDLE, when it is
 * non-zero it is not IDLE. This is very similar to the way a Semaphore
 * behaves.
 * This class can then be used to wrap up operations that while in progress should block tests from
 * accessing the UI.
 */
public final class SimpleCountingIdlingResource implements IdlingResource {

    private final String mResourceName;

    private final AtomicInteger counter = new AtomicInteger(0);

    private volatile ResourceCallback resourceCallback;

    /**
     * Creates a SimpleCountingIdlingResource
     *
     * @param resourceName the resource name this resource should report to Espresso.
     */
    public SimpleCountingIdlingResource(String resourceName){
        mResourceName = checkNotNull(resourceName);
    }

    @Override
    public String getName(){
        return mResourceName;
    }

    @Override
    public boolean isIdleNow(){
        return counter.get() == 0;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback){
        this.resourceCallback = resourceCallback;
    }

    /**
     * Increments the count of in-flight transactions to the resource being monitored.
     */
    public void increment(){
        counter.getAndIncrement();
    }

    /**
     * Decrements the count of in-flight transactions to the resource being monitored.
     *
     * If this operation results in the counter falling below 0 - an exception is raised.
     *
     * @throws IllegalStateException if the counter is below 0.
     */
    public void decrement(){
        int counterValue = counter.decrementAndGet();
        if (counterValue == 0){
            if(null != resourceCallback){
                resourceCallback.onTransitionToIdle();
            }
        }

        if(counterValue <0){
            throw new IllegalArgumentException("Counter has been corrupted");
        }
    }
}
