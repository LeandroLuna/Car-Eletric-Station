package br.com.fiap.controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MapsController implements ItemListener {
    private String API_KEY = "AIzaSyC6IoKPG8jv0mVydkz6TUGNlPYs7Uw1WjY";
    JLabel mapsIcon = new JLabel();

    public JLabel getMapsIcon() {
        return mapsIcon;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED && e.getItem() != "Select an address:") {
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
        } // else { // A really bad implemented easter egg..
          // try {
          // mapsIcon.setIcon(new ImageIcon(ImageIO.read(new URL(
          // "https://cdn-icons-png.flaticon.com/512/1809/1809056.png"))));
          // } catch (IOException e1) {
          // System.out.printf("Error creating new icon from giving url:", e1);
          // }
          // System.out.println("*poker face*");
          // }
    }
}
