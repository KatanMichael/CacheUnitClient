package com.hit.controller;

import com.hit.model.Model;
import com.hit.util.ObserMessage;
import com.hit.view.View;

import java.util.Observable;

public class CacheUnitController implements Controller
{

    private Model model;
    private View view;

    public CacheUnitController(Model model, View view)
    {
        this.model = model;
        this.view = view;
    }

    @Override
    public void update(Observable o, Object arg)
    {
        ObserMessage update = (ObserMessage) arg;

        if(update.getSentIdentifier ().equals ("view"))
        {
            if(update.getMessege ().equals ("load"))
            {
                model.updateModelData ("load");
            }
        }

        if(update.getSentIdentifier ().equals ("model"))
        {
            view.updateUIData (update.getMessege ());

        }



    }

}
