package org.miage.placesearcher.service;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by Simon on 15/01/2018.
 */

public class EventBusManager {

    public static Bus BUS = new Bus(ThreadEnforcer.ANY);
}
