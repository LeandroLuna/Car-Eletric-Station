package br.com.fiap.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MapsController implements ItemListener {
    private String API_KEY = "AIzaSyC6IoKPG8jv0mVydkz6TUGNlPYs7Uw1WjY";
    private String[] databaseStations = { "Nothing to show here yet! Register some stations in system." }; // Initial
                                                                                                           // value
    JLabel mapsIcon = new JLabel();

    public void setDatabaseStations(ArrayList<String> mapsAddress) {
        databaseStations = null; // Clean array.
        this.databaseStations = mapsAddress.toArray(new String[mapsAddress.size()]); // Creates an array from a string
                                                                                     // list.
    }

    public JLabel getMapsIcon() {
        return mapsIcon;
    }

    public String[] getDatabaseStations() {
        return databaseStations;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED && e.getItem() != databaseStations[0]) {
            String selectedStationAddress = e.getItem().toString().replaceAll(" ", "+");
            String rawUrl = String.format(
                    "https://maps.googleapis.com/maps/api/staticmap?center=%s&zoom=16&scale=false&size=300x200&maptype=roadmap&key=%s&format=png&visual_refresh=true",
                    selectedStationAddress, API_KEY);
            try {
                URL url = new URL(rawUrl);
                BufferedImage image = ImageIO.read(url);
                mapsIcon.setIcon(new ImageIcon(image));
            } catch (IOException error) {
                System.out.printf("Error creating new icon from giving url:", error);
            }
        }
    }
}