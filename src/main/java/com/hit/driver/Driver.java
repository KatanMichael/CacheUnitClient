package com.hit.driver;

import com.hit.controller.CacheUnitController;
import com.hit.controller.Controller;
import com.hit.model.CacheUnitModel;
import com.hit.model.Model;
import com.hit.view.CacheUnitView;
import com.hit.view.View;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Driver
{

    public static void main(String[] args)
    {
        fun ();

        Model model = new CacheUnitModel ();
        View view = new CacheUnitView ();
        Controller controller = new CacheUnitController (model, view);
        ((CacheUnitModel)model).addObserver(controller);
        ((CacheUnitView)view).addObserver(controller);
        view.start();

    }


    public static void fun()
    {
        ArrayList<String> req = new ArrayList<> ();

        String s1 = ("{\"headers\":{\"action\":\"GET\"},\"body\":[{\"id\":100,\"content\":100}]}");
        String s2 = ("{\"headers\":{\"action\":\"DELETE\"},\"body\":[{\"id\":101,\"content\":101}]}");
        String s3 = ("{\"headers\":{\"action\":\"GET\"},\"body\":[{\"id\":102,\"content\":100}]}");

        req.add (s1);
        //req.add (s2);
        //req.add (s3);

        ObjectOutputStream stream = null;
        try
        {

            stream = new ObjectOutputStream (new FileOutputStream ("data.txt",false));
        } catch (IOException e)
        {
            e.printStackTrace ();
        }
        String[] strings = new String[req.size ()];

        for(int i = 0; i < req.size (); i++)
        {
            strings[i] = req.get (i);
        }

        try
        {
            stream.writeObject (strings);
        } catch (IOException e)
        {
            e.printStackTrace ();
        }
    }

}
