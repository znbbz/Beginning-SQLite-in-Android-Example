package com.examplesqlproject.app;

/**
 * Created by apple on 19/07/2014.
 */
public class GettingVegetableData
{
    private int id;
    private String vegetableName;

    public GettingVegetableData()
    {

    }

    public GettingVegetableData(int id, String groupName)
    {
        this.id = id;
        this.vegetableName = groupName;
    }

    public GettingVegetableData(String groupName)
    {
        this.vegetableName = groupName;
    }

    public void setId(int id)
    {
        this.id = id;
    }


    public int getId()
    {
        return this.id;
    }

    public void setVegetableName(String groupName)
    {
        this.vegetableName = groupName;
    }


    public String getVegetableName()
    {
        return this.vegetableName;
    }
}

