package edu.sjsu.android.swipingsammies;

public class Match
{
    String myEmail;
    String myBio;
    String myAge;
    String myName;

    public Match(String me, String mn, String mb, String ma)
    {
        myEmail = me;
        myName = mn;
        myBio = mb;
        myAge = ma;
    }

    public String getAge()
    {
        return myAge;
    }

    public String getEmail()
    {
        return myEmail;
    }

    public String getBio()
    {
        return myBio;
    }

    public String getName() { return myName; }
}
