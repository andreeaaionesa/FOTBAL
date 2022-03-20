package model;

public class Team implements Identifiable <Integer>
{ private int Id;
  private String tname, country, game;
  private FotbalPlayer f;

  public Team()
  {
      this.Id=0;
      this.tname=" ";
      this.country=" ";
      this.game=" ";
      this.f=new FotbalPlayer();
  }

  public Team(int Id, String tname, String country, String game, FotbalPlayer f)
  {
      this.Id=Id;
      this.tname=tname;
      this.country=country;
      this.game=game;
      this.f=f;
  }

    public Team(String tname, String country, String game, FotbalPlayer f)
    {

        this.tname=tname;
        this.country=country;
        this.game=game;
        this.f=f;
    }

    public Integer getId()
    { return Id; }

    public String getTname()
    { return tname; }

    public String getCountry()
    { return country;}

    public String getGame()
    {return game;}

    public FotbalPlayer getF()
    {return f;}

    public void setId(Integer Id)
    {this.Id=Id;}

    public void setTname(String tname)
    {this.tname=tname;}

    public void setCountry(String country)
    {this.country=country;}

    public void setGame(String game)
    {this.game=game;}

    public void setF(FotbalPlayer f)
    {this.f=f;}

    public String toString(){
        String str = Id + "," + tname +","+ country + "," + game+ ','+ f;
        return str;

    }







}
