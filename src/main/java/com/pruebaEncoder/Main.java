package com.pruebaEncoder;

import net.java.games.input.*;
import net.java.games.input.Component;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        while(true) {
            Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
            if(controllers.length==0) {
                System.out.println("Found no controllers.");
                break;
            }

            for(int i=0;i<controllers.length;i++) {
                controllers[i].poll();

                EventQueue queue = controllers[i].getEventQueue();
                Event event = new Event();

                while(queue.getNextEvent(event)) {
                    StringBuffer buffer = new StringBuffer(controllers[i].getName());
                    buffer.append(" at ");
                    buffer.append(event.getNanos()).append(", ");
                    Component comp = event.getComponent();
                    System.out.println("Tecla presionada: " +comp.getName());
                    buffer.append(comp.getName()).append(" changed to ");
                    float value = event.getValue();
                    if(comp.isAnalog()) {
                        buffer.append(value);
                    } else {
                        if(value==1.0f) {
                            buffer.append("On");
                        } else {
                            buffer.append("Off");
                        }
                    }
                    System.out.println(buffer.toString());
                }
            }

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
