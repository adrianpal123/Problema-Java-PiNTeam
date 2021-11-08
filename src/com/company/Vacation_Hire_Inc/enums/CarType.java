package com.company.Vacation_Hire_Inc.enums;


public enum CarType {
    TRUCK,
    SEDAN,
    MINIVAN;

    public String toString()
    {
        switch (this)
        {
            case TRUCK:
                return "TRUCK";
            case SEDAN:
                return "SEDAN";
            case MINIVAN:
                return "MINIVAN";
        }
        return null;
    }

}
