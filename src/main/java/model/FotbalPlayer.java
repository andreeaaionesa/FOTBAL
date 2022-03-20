package model;

public class FotbalPlayer implements Identifiable <Integer>
{ private int Id,age;
  private String name, height, experience;
  public FotbalPlayer(){
      Id=0;
      this.name =" ";
      this.age =0;
      this.height =" ";
      this.experience =" ";
  }
    public FotbalPlayer(int Id, String name, int age, String height, String experience )
    {
        this.Id=Id;
        this.name=name;
        this.age=age;
        this.height=height;
        this.experience=experience;
    }
    public FotbalPlayer(String name, int age, String height, String experience )
    {
        this.name=name;
        this.age=age;
        this.height=height;
        this.experience=experience;
    }
    public Integer getId()
    {return Id;}

    public String getName()
    { return name; }

    public Integer getAge()
    {return age;}

    public String getHeight()
    {return height;}

    public String getExperience()
    {return experience;}


    public void setId(Integer Id)
    { this.Id=Id; }

    public void setName(String name)
    { this.name=name; }

    public void setAge(Integer age)
    { this.age=age; }

    public void setHeight(String height)
    {this.height=height;}

    public void setExperience(String experience)
    {this.experience=experience;}

    public String toString()
    {String str = Id + "," + name + "," + age + "," + height + "," + experience;
    return  str;}
}
