package com.hit.view;

import com.hit.util.ObserMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Observable;


public class CacheUnitView extends Observable implements View
{
    JLabel textLabel;

    public CacheUnitView()
    {
        javax.swing.SwingUtilities.invokeLater (new Runnable ()
        {
            @Override
            public void run()
            {
                start ();
            }
        });

    }

    @Override
    public void start()
    {
        JFrame frame = new JFrame ("CachUnitUI");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel ();
        panel1.setOpaque (true);

        JPanel panel2 = new JPanel ();
        panel2.setOpaque (true);

        textLabel = new JLabel ("Waiting For Input");



        panel2.add (textLabel);

        JButton loadReq_Btn = new JButton ("Load a Request");

        loadReq_Btn.addActionListener (new AbstractAction ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setChanged ();
                notifyObservers (new ObserMessage ("view","load"));
            }
        });

        JButton statistics_Btn = new JButton ("Show Statistics");
        statistics_Btn.addActionListener (new AbstractAction ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setChanged ();
                notifyObservers (new ObserMessage ("view","stats"));
            }
        });


        panel1.add (loadReq_Btn);
        panel1.add (statistics_Btn);




        frame.add (panel1, BorderLayout.NORTH);
        frame.add (panel2, BorderLayout.SOUTH);
        frame.pack ();
        frame.setVisible (true);

    }

    @Override
    public <T> void updateUIData(T t)
    {
        String string = (String) t;

        String currentText = textLabel.getText ()+"\n";

        textLabel.setText (currentText+string);

    }


}
