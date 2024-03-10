package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class    Movie {
    private String title;
    private String description;
    public enum genreEnum {
        ACTION, ADVENTURE, ANIMATION, BIOGRAPHY, COMEDY,
        CRIME, DRAMA, DOCUMENTARY, FAMILY, FANTASY, HISTORY, HORROR,
        MUSICAL, MYSTERY, ROMANCE, SCIENCE_FICTION, SPORT, THRILLER, WAR,
        WESTERN, GOAT
    }
    private List<genreEnum> genres;

    // TODO add more properties here

    public Movie(String title, String description, List<genreEnum> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }



    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getGenresAsString() {
        return genres.stream().map(Enum::name).reduce((a, b) -> a + ", " + b).orElse("No Genre");
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Star Wars: Episode I – The Phantom Menace", "Two Jedi escape a hostile blockade to find allies and come across a young boy who may bring balance to the Force, but the long dormant Sith resurface to claim their original glory.",
                   new ArrayList<>(Arrays.asList(genreEnum.ACTION, genreEnum.ADVENTURE, genreEnum.FANTASY))));
        movies.add(new Movie("Star Wars: Episode II – Attack of the Clones", "Ten years after initially meeting, Anakin Skywalker shares a forbidden romance with Padmé Amidala, while Obi-Wan Kenobi discovers a secret clone army crafted for the Jedi.",
                   new ArrayList<>(Arrays.asList(genreEnum.ACTION, genreEnum.ADVENTURE, genreEnum.FANTASY))));
        movies.add(new Movie("Star Wars: Episode III – Revenge of the Sith", "Three years into the Clone Wars, Obi-Wan pursues a new threat, while Anakin is lured by Chancellor Palpatine into a sinister plot to rule the galaxy.",
                   new ArrayList<>(Arrays.asList(genreEnum.ACTION, genreEnum.ADVENTURE, genreEnum.FANTASY))));
        movies.add(new Movie("Star Wars: Episode IV – A New Hope", "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee and two droids to save the galaxy from the Empire's world-destroying battle station, while also attempting to rescue Princess Leia from the mysterious Darth Vader.",
                   new ArrayList<>(Arrays.asList(genreEnum.ACTION, genreEnum.ADVENTURE, genreEnum.FANTASY))));
        movies.add(new Movie("Star Wars: Episode V – The Empire Strikes Back", "After the Rebels are overpowered by the Empire, Luke Skywalker begins his Jedi training with Yoda, while his friends are pursued across the galaxy by Darth Vader and bounty hunter Boba Fett.",
                   new ArrayList<>(Arrays.asList(genreEnum.ACTION, genreEnum.ADVENTURE, genreEnum.FANTASY))));
        movies.add(new Movie("Star Wars: Episode VI – Return of the Jedi", "After rescuing Han Solo from Jabba the Hutt, the Rebels attempt to destroy the second Death Star, while Luke struggles to help Darth Vader back from the dark side.",
                   new ArrayList<>(Arrays.asList(genreEnum.ACTION, genreEnum.ADVENTURE, genreEnum.FANTASY))));
        movies.add(new Movie("Star Wars: Episode VII – The Force Awakens", "As a new threat to the galaxy rises, Rey, a desert scavenger, and Finn, an ex-stormtrooper, must join Han Solo and Chewbacca to search for the one hope of restoring peace.",
                   new ArrayList<>(Arrays.asList(genreEnum.ACTION, genreEnum.ADVENTURE, genreEnum.FANTASY))));
        movies.add(new Movie("Star Wars: Episode VIII – The Last Jedi", "The Star Wars saga continues as new heroes and galactic legends go on an epic adventure, unlocking mysteries of the Force and shocking revelations of the past.",
                   new ArrayList<>(Arrays.asList(genreEnum.ACTION, genreEnum.ADVENTURE, genreEnum.FANTASY))));
        movies.add(new Movie("Star Wars: Episode IX – The Rise of Skywalker", "In the riveting conclusion of the landmark Skywalker saga, new legends will be born-and the final battle for freedom is yet to come.",
                   new ArrayList<>(Arrays.asList(genreEnum.ACTION, genreEnum.ADVENTURE, genreEnum.FANTASY))));
        movies.add(new Movie("The Godfather", "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
                   new ArrayList<>(Arrays.asList(genreEnum.CRIME, genreEnum.DRAMA))));
        movies.add(new Movie("The Dark Knight", "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
                   new ArrayList<>(Arrays.asList(genreEnum.CRIME, genreEnum.DRAMA, genreEnum.ACTION))));
        movies.add(new Movie("Schindler's List", "In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.",
                   new ArrayList<>(Arrays.asList(genreEnum.BIOGRAPHY, genreEnum.DRAMA, genreEnum.HISTORY))));
        movies.add(new Movie("The Lord of the Rings: The Return of the King", "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.",
                   new ArrayList<>(Arrays.asList(genreEnum.ADVENTURE, genreEnum.DRAMA, genreEnum.ACTION))));
        movies.add(new Movie("The Good, the Bad and the Ugly", "A bounty hunting scam joins two men in an uneasy alliance against a third in a race to find a fortune in gold buried in a remote cemetery.",
                   new ArrayList<>(Arrays.asList(genreEnum.ADVENTURE, genreEnum.WESTERN))));
        movies.add(new Movie("Forrest Gump", "The history of the United States from the 1950s to the '70s unfolds from the perspective of an Alabama man with an IQ of 75, who yearns to be reunited with his childhood sweetheart.",
                   new ArrayList<>(Arrays.asList(genreEnum.DRAMA, genreEnum.ROMANCE))));
        movies.add(new Movie("Se7en", "Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his motives.",
                   new ArrayList<>(Arrays.asList(genreEnum.DRAMA, genreEnum.CRIME, genreEnum.MYSTERY))));
        movies.add(new Movie("The Silence of the Lambs", "A young F.B.I. cadet must receive the help of an incarcerated and manipulative cannibal killer to help catch another serial killer, a madman who skins his victims.",
                   new ArrayList<>(Arrays.asList(genreEnum.CRIME, genreEnum.DRAMA, genreEnum.THRILLER))));
        movies.add(new Movie("Saving Private Ryan", "Following the Normandy Landings, a group of U.S. soldiers go behind enemy lines to retrieve a paratrooper whose brothers have been killed in action.",
                   new ArrayList<>(Arrays.asList(genreEnum.WAR, genreEnum.DRAMA))));
        movies.add(new Movie("Back to the Future", "Marty McFly, a 17-year-old high school student, is accidentally sent 30 years into the past in a time-traveling DeLorean invented by his close friend, the maverick scientist Doc Brown.",
                   new ArrayList<>(Arrays.asList(genreEnum.ADVENTURE, genreEnum.COMEDY, genreEnum.SCIENCE_FICTION))));
        movies.add(new Movie("Spirited Away", "During her family's move to the suburbs, a sullen 10-year-old girl wanders into a world ruled by gods, witches and spirits, a world where humans are changed into beasts.",
                   new ArrayList<>(Arrays.asList(genreEnum.ANIMATION, genreEnum.ADVENTURE, genreEnum.FAMILY))));
        movies.add(new Movie("Psycho", "A Phoenix secretary embezzles $40,000 from her employer's client, goes on the run and checks into a remote motel run by a young man under the domination of his mother.",
                   new ArrayList<>(Arrays.asList(genreEnum.HORROR, genreEnum.MYSTERY, genreEnum.THRILLER))));
        movies.add(new Movie("Mean Girls", "Cady Heron is a hit with the Plastics, an A-list girl clique at her new school. But everything changes when she makes the mistake of falling for Aaron Samuels, the ex-boyfriend of alpha Plastic Regina George.",
                   new ArrayList<>(Arrays.asList(genreEnum.MUSICAL, genreEnum.COMEDY))));
        movies.add(new Movie("Einstein and the Bomb", "What happened after Einstein fled Nazi Germany? Using archival footage and his own words, this docudrama dives into the mind of a tortured genius.",
                   new ArrayList<>(Arrays.asList(genreEnum.DOCUMENTARY, genreEnum.HISTORY))));
        movies.add(new Movie("Einstein and the Bomb", "What happened after Einstein fled Nazi Germany? Using archival footage and his own words, this docudrama dives into the mind of a tortured genius.",
                   new ArrayList<>(Arrays.asList(genreEnum.DOCUMENTARY, genreEnum.HISTORY))));
        movies.add(new Movie("Formula 1: Drive to Survive", "Docuseries following the FIA Formula One World Championship across multiple seasons.",
                   new ArrayList<>(Arrays.asList(genreEnum.DOCUMENTARY, genreEnum.SPORT))));
        movies.add(new Movie("Lionel Messi: Destiny", "The G.O.A.T.",
                   new ArrayList<>(Arrays.asList(genreEnum.DOCUMENTARY, genreEnum.SPORT, genreEnum.GOAT))));
        return movies;
    }
}
