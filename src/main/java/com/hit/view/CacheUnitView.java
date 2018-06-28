package com.hit.view;

import com.hit.util.ObserMessage;

import javax.swing.*;
import javax.swing.plaf.LabelUI;
import javax.swing.plaf.basic.BasicLabelUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Observable;


public class CacheUnitView extends Observable implements View
{
    private JLabel textLabel;
    private LabelUI labelUI;
    private JPanel panel2;
    private JFrame frame;


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
        frame = new JFrame ("CachUnitUI");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        frame.setLayout (new BorderLayout ());

        JPanel panel1 = new JPanel ();
        panel1.setOpaque (true);

        panel2 = new JPanel ();
        panel2.setOpaque (true);

        textLabel = new JLabel ("Waiting For Input");


        JButton loadReq_Btn = new JButton ("Load a Request");

        loadReq_Btn.addActionListener (new AbstractAction ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setChanged ();
                notifyObservers (new ObserMessage ("view", "load"));
            }
        });

        JButton statistics_Btn = new JButton ("Show Statistics");
        statistics_Btn.addActionListener (new AbstractAction ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                setChanged ();
                notifyObservers (new ObserMessage ("view", "stats"));
            }
        });


        panel1.add (loadReq_Btn);
        panel1.add (statistics_Btn);


        frame.add (panel1, BorderLayout.NORTH);
        frame.add (panel2, BorderLayout.CENTER);
        frame.pack ();
        frame.setVisible (true);

    }

    @Override
    public <T> void updateUIData(T t)
    {

        panel2.removeAll ();

        ObserMessage tMsg = (ObserMessage) t;
        String labelString = null;


        if (tMsg.getSentIdentifier ().equals ("load"))
        {
            String inputString = tMsg.getMessege ();
            labelString = "Failed";

            if (inputString.equals ("true"))
            {
                labelString = "Succeeded";
            }

        }else if(tMsg.getSentIdentifier ().equals ("stats"))
        {
            labelString = tMsg.getMessege ();
        }

        panel2.add (new JLabel (labelString));
        panel2.validate ();


    }
}
