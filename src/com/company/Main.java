package com.company;

import com.company.controllers.Menu;
import java.io.IOException;

/**
 * This is the main Class, it contains the main method
 * @author Mosaab Abbas
 * @since 1.0.0
 **/

public class Main {

    public static void main(String[] args) {
        try {
            new Menu().run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
