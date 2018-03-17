<?php
/**
 * Class to handle all db operations
 */

class DbHandler
{
    private $conn;

    function __construct()
    {
        require_once dirname(__FILE__) . '/db_connect.php';
        // opening db connection
        $db = new DbConnect();
        $this->conn = $db->connect();
    }

    public function Close()
    {
        $this->conn->close();
    }

    /**
     * Fetching all movie
     */
    public function getAllMovies()
    {
        $stmt = $this->conn->prepare("SELECT * FROM Movies WHERE 1");
        $stmt->execute();
        $movies = $stmt->get_result();
        $stmt->close();
        return $movies;
    }

    /**
     * Fetching movie by id
     * @param String $mid -- movie id
     */
    public function getMovieById($mid)
    {
        $stmt = $this->conn->prepare("SELECT * FROM Movies WHERE id = ?");
        $stmt->bind_param("s", $mid);
        if ($stmt->execute()) {
            $movie = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $movie;
        } else {
            return NULL;
        }

    }

    public function getMoviesByRating($rating)
    {
        $stmt = $this->conn->prepare("SELECT * FROM Movies WHERE VoteAvg > ? ORDER BY VoteAvg ASC");
        $stmt->bind_param("s", $rating);
        if ($stmt->execute()) {
        $movies = $stmt->get_result();
        $stmt->close();
        return $movies;
        } else {
            return NULL;
        }
    }

    public function addNewMovie($movie)
    {
        $stmt = $this->conn->prepare("INSERT INTO Movies (MovieId, Title, VoteCnt, VoteAvg, Popularity, Poster, Backdrop, Overview, ReleaseDay)
					VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        $movieTitle = $movie['Title'];
        $stmt->bind_param("isiddssss", $movie['MovieId'],$movie['Title'],$movie['VoteCnt'],$movie['VoteAvg'],$movie['Popularity'],$movie['Poster'],$movie['Backdrop'],$movie['Overview'],$movie['ReleaseDay']);
        if ($stmt->execute()) {
            $movies = $stmt->get_result();
            echo "Movie with MovieId: $movieTitle has been added to the Database";
            $stmt->close();
        } else {
            echo "Error in adding Movie: $movieTitle to database";
        }
    }

    public function deleteMovieById($mid)
    {
        $stmt = $this->conn->prepare("DELETE FROM Movies WHERE MovieId =  ? ");
        $stmt->bind_param("i", $mid);
        if ($stmt->execute()) {
            $movies = $stmt->get_result();
            echo "Movie with MovieId: $mid has been deleted from the Database";
            $stmt->close();
        } else {
            echo "No Movie with MovieId: $mid in the Database";
        }
    }

}

?>