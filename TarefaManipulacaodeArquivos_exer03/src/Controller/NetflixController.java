package Controller;


import Biblioteca.FilaObject.Fila;
import Biblioteca.ListaObject.Lista;
import Model.Serie;

import java.io.*;
import java.util.List;

public class NetflixController implements INetflixController {

    Lista[] vetSeries;
    public NetflixController(){
        vetSeries= new Lista[7];
        for (int i=0; i<7; i++){
            vetSeries[i]= new Lista();
        }
    }

    @Override
    public void arquivoMajor_genre() throws IOException {
        Fila fila= (Fila) geraFilaMajor_genre();
        File dir= new File("D:\\TEMP");
        File file= new File("D:\\TEMP", "Major_genre.csv");
        if (dir.exists() && dir.isDirectory()){
            String series= "";
            StringBuffer buffer= new StringBuffer(series);
            boolean existe= false;
            if (file.exists()){
                existe= true;
            }
        series= String.valueOf(buffer.append("Title;Major_genre;Subgenre;Premiere_year;Imdb_rating;Episodes;Status \n"));
            while (!fila.isEmpty()){
                try {
                    Serie serie= (Serie) fila.remove();
                    series= String.valueOf((buffer.append(serie.getTitle() +";"+ serie.getMajor_genre() +";"+ serie.getSubgenre() +";"+ serie.getPremiere_year() +";"+ serie.getImdb_rating() +";"+ serie.getEpisodes() +";"+ serie.getStatus() +"\n")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            FileWriter fileWriter= new FileWriter(file, existe);
            PrintWriter print= new PrintWriter(fileWriter);
            print.write(series);
            print.flush();
            print.close();
            fileWriter.close();
        }
        else {
            throw new IOException("Diretório inválido!");
        }

    }

    private Object geraFilaMajor_genre() throws IOException {
        File file= new File("D:\\TEMP", "netflix_originals_series_2.csv");
        Fila fila= new Fila();

        if (file.exists() && file.isFile()){
            FileInputStream fluxo= new FileInputStream(file);
            InputStreamReader leitor= new InputStreamReader(fluxo);
            BufferedReader buffer= new BufferedReader(leitor);
            String linha = buffer.readLine();
            linha = buffer.readLine();
            while (linha != null){
                String[] vet= linha.split(";");
                fila.insert(new Serie(vet[0], vet[1], vet[2], Integer.parseInt(vet[4]), vet[5], vet[6], Integer.parseInt(vet[11])));
                linha= buffer.readLine();
            }
        }
        else {
            throw new IOException("Arquivo inválido!");
        }
        return fila;
    }

    @Override
    public void arquivoPremiere_year() throws IOException {
        Lista lista= (Lista) geraListaPremierie_year();
        File dir= new File("D:\\TEMP");
        File file= new File("D:\\TEMP", "premiere_year.csv");
        if (dir.exists() && dir.isDirectory()){
            String series= "";
            StringBuffer buffer= new StringBuffer(series);
            boolean existe= false;
            if (file.exists()){
                existe= true;
            }
            series= String.valueOf(buffer.append("Title;Major_genre;Subgenre;Premiere_year;Imdb_rating;Episodes;Status \n"));
            while (!lista.isEmpty()){
                try {
                    Serie serie= (Serie) lista.get(0);
                    series= String.valueOf((buffer.append(serie.getTitle() +";"+ serie.getMajor_genre() +";"+ serie.getSubgenre() +";"+ serie.getPremiere_year() +";"+ serie.getImdb_rating() +";"+ serie.getEpisodes() +";"+ serie.getStatus() +"\n")));
                    lista.removeFirst();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            FileWriter fileWriter= new FileWriter(file, existe);
            PrintWriter print= new PrintWriter(fileWriter);
            print.write(series);
            print.flush();
            print.close();
            fileWriter.close();
        }
        else {
            throw new IOException("Diretório inválido!");
        }
    }

    private Object geraListaPremierie_year() throws IOException {
        File file= new File("D:\\TEMP", "netflix_originals_series_2.csv");
        Lista lista= new Lista();

        if (file.exists() && file.isFile()){
            FileInputStream fluxo= new FileInputStream(file);
            InputStreamReader leitor= new InputStreamReader(fluxo);
            BufferedReader buffer= new BufferedReader(leitor);
            String linha = buffer.readLine();
            linha = buffer.readLine();
            while (linha != null){
                if (linha.contains("Renewed")){
                    String[] vet= linha.split(";");
                    lista.addFirst(new Serie(vet[0], vet[1], vet[2], Integer.parseInt(vet[4]), vet[5], vet[6], Integer.parseInt(vet[11])));
                }
                linha= buffer.readLine();
            }
            fluxo.close();
            leitor.close();
            buffer.close();
        }
        else {
            throw new IOException("Arquivo inválido!");
        }
        return lista;
    }




    private int hashcode(int avaliacao){
        int posicao= 0;
        if (avaliacao >= 0 && avaliacao <= 15){
            posicao= 0;
        }
        if (avaliacao >= 16 && avaliacao <= 30){
            posicao= 1;
        }
        if (avaliacao >= 31 && avaliacao<= 45){
            posicao= 2;
        }
        if (avaliacao >= 46 && avaliacao <= 60){
            posicao= 3;
        }
        if (avaliacao >= 61 && avaliacao <= 75){
            posicao= 4;
        }
        if (avaliacao >= 76 && avaliacao <= 90){
            posicao= 5;
        }
        if (avaliacao >= 91 && avaliacao <= 100){
            posicao= 6;
        }
        return posicao;
    }

    public void geraTabela() throws IOException{
        Fila fila= (Fila) geraFilaMajor_genre();

        while (!fila.isEmpty()){
            try {
                Serie serie= (Serie) fila.remove();
                int hash= hashcode(serie.getImdb_rating());
                Lista l= vetSeries[hash];
                if (l.isEmpty()){
                    l.addFirst(serie);
                }
                else {
                    l.addLast(serie);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


    public void buscaSerie(int estrelas){
        Lista lista= vetSeries[estrelas];
        while (!lista.isEmpty()){
            try {
                Serie serie= (Serie) lista.get(0);
                System.out.println(serie);
                lista.removeFirst();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
