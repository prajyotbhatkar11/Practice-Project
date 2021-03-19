package com.company;

public class Worker {
    private String name;
    private boolean active;

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public Worker(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public synchronized void worke(SharedResource sharedResource, Worker otherWorker){
        while (active){
            if (sharedResource.getOwner() != this){
                try {
                    wait(10);
                }catch (InterruptedException e){

                }
            }
            continue;
        }
        if (otherWorker.isActive()) {
            System.out.println(getName() + "give the resource to the worker" + otherWorker.getName());
            sharedResource.setOwner(otherWorker);
            
        }
        System.out.println(getName()+": Working on the common resource");
        active=false;
        sharedResource.setOwner(otherWorker);
    }
}
