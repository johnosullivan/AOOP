package edu.comp373.model.observer;

import edu.comp373.model.users.UserInterface;

public abstract class Observer implements UserInterface  {
    protected Request subject;
    public abstract void update();
}
