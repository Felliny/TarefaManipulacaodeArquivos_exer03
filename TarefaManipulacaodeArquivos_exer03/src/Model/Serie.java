package Model;

public class Serie {

    String major_genre;
    String title;
    String subgenre;
    int premiere_year;
    String episodes;
    String status;
    int imdb_rating;

    public Serie(String major_genre, String title, String subgenre, int premiere_year, String episodes, String status, int imdb_rating){
        this.major_genre= major_genre;
        this.title= title;
        this.subgenre= subgenre;
        this.premiere_year= premiere_year;
        this.episodes= episodes;
        this.status= status;
        this.imdb_rating= imdb_rating;
    }

    public String getMajor_genre() {
        return major_genre;
    }

    public String getTitle() {
        return title;
    }

    public String getSubgenre() {
        return subgenre;
    }

    public int getPremiere_year() {
        return premiere_year;
    }

    public String getEpisodes() {
        return episodes;
    }

    public String getStatus() {
        return status;
    }

    public int getImdb_rating() {
        return imdb_rating;
    }

    @Override
    public String toString() {
        return "Serie{" +
                "major_genre='" + major_genre + '\'' +
                ", title='" + title + '\'' +
                ", subgenre='" + subgenre + '\'' +
                ", premiere_year=" + premiere_year +
                ", episodes='" + episodes + '\'' +
                ", status='" + status + '\'' +
                ", imdb_rating=" + imdb_rating +
                '}';
    }
}
