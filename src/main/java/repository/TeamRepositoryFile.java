package repository;

import model.FotbalPlayer;
import model.Team;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TeamRepositoryFile extends TeamRepositoryInMemory {
    private String FileName;
    private FotbalPlayerRepo aRepo;
    private static int idGenerator=1;

    public TeamRepositoryFile(String s, FotbalPlayerRepo aRepo) {
        this.FileName = s;
        this.aRepo=aRepo;
        ReadFromFile();
    }

    private static int getNextId(){
        return idGenerator++;
    }

    private void ReadFromFile() {
        try(BufferedReader br = new BufferedReader(new FileReader(FileName))) {
            String line=br.readLine();
            try{
                idGenerator=Integer.parseInt(line);
            }catch (NumberFormatException ex){
                System.err.println("Invalid Value for idGenerator, starting from 0");
            }
            while((line = br.readLine())!=null) {
                String[] el = line.split(";");
                if (el.length != 5) {
                    System.err.println("Line is not valid." + line);
                    continue;
                }
                try {
                    int id = Integer.parseInt(el[0]);
                    int fId = Integer.parseInt(el[4]);
                    FotbalPlayer f = aRepo.findById(fId);
                    Team o = new Team(id, el[1], el[2], el[3], f);
                    super.add(o);
                }
                catch(NumberFormatException nr) {
                    System.err.println("Id not valid." + el[0]+ nr); }
            }
        }
        catch(IOException ex) {
            throw new RepositoryException("Error" + ex); }
    }

    private void writeToFile()
    {
        try(PrintWriter pw = new PrintWriter(FileName))
        {
            pw.println(idGenerator);
            for(Team obj: findAll())
            {
                String str = obj.getId() + ";" + obj.getTname() + ";" + obj.getCountry() +";" + obj.getGame()+ ";" + obj.getF();
                pw.println(str);
            }
        }catch(IOException ex) { throw new RepositoryException("error" + ex);
        }
    }

    @Override
    public Team add(Team m){
        m.setId(getNextId());
        super.add(m);
        writeToFile();
        return m;
    }

    @Override
    public void delete(Team m)
    {
        try  {
            super.delete(m);
            writeToFile();
        }
        catch(RuntimeException ex){throw new RepositoryException(ex);}
    }

    @Override
    public void update(Integer id, Team m)
    {
        try {
            super.update(id,m);
            writeToFile();
        }
        catch(RuntimeException ex) {throw new RepositoryException(ex);}
    }
}