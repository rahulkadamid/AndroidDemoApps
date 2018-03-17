<?php
require_once './db_handler.php';
require_once './vendor/autoload.php';

$app = new \Slim\App();

/**
 * Get (no arguments / patterns / segments )
 */
$app->get('/', function () {
    echo '<h1> Android Programming </h1><br><br>';
    echo '<form action ="http://rahul-kadam.com/post" method ="post">';
    echo '<label> First Name : </label>';
    echo '<input name ="first" type ="text"/> <br>';
    echo '<label> Last Name : </label>';
    echo '<input name ="last" type ="text"/> <br>';
    echo '<input type ="submit" value ="Submit"/> ';
    echo '</form>';
});


/**
 * Get all Movies
 */
$app->get('/movies/', function () {
    $response = array();
    $db = new DbHandler();
    $result = $db->getAllMovies();

    if ($result != NULL) {
        $response['error'] = false;
        $response['movies'] = array();
        while ($movie = $result->fetch_assoc()) {
            $tmp = array();
            $tmp['ID'] = $movie['ID'];
            $tmp['MovieId'] = $movie['MovieId'];
            $tmp['Title'] = $movie['Title'];
            $tmp['VoteCnt'] = $movie['VoteCnt'];
            $tmp['VoteAvg'] = $movie['VoteAvg'];
            $tmp['Popularity'] = $movie['Popularity'];
            $tmp['Poster'] = $movie['Poster'];
            $tmp['Backdrop'] = $movie['Backdrop'];
            $tmp['Overview'] = $movie['Overview'];
            $tmp['ReleaseDay'] = $movie['ReleaseDay'];
            array_push($response['movies'], $tmp);
        }
        echo json_encode($response);
    } else {
        $response['error'] = true;
        $response['message'] = "There is no movie in database";
        echo json_encode($response);
    }
    $db->Close();
});

/**
 * Get Single Movie
 */
$app->get('/movies/id/{mid}', function ($request, $response, $args) {
    $response = array();
    $db = new DbHandler();
    $result = $db->getMovieById($args['mid']);
    if ($result != NULL) {
        $response['error'] = false;
        $response['ID'] = $result['ID'];
        $response['MovieId'] = $result['MovieId'];
        $response['Title'] = $result['Title'];
        $response['VoteCnt'] = $result['VoteCnt'];
        $response['VoteAvg'] = $result['VoteAvg'];
        $response['Popularity'] = $result['Popularity'];
        $response['Poster'] = $result['Poster'];
        $response['Backdrop'] = $result['Backdrop'];
        $response['Overview'] = $result['Overview'];
        $response['ReleaseDay'] = $result['ReleaseDay'];
        echo json_encode($response);
    } else {
        $response['error'] = true;
        $response['message'] = "There is no requested movie in database";
        echo json_encode($response);
    }
    $db->Close();
});

/**
 * Get Movies rating above value 'ratingsabove'
 */
$app->get('/movies/rating/{ratingsabove}',
    function( $request, $response, $args) {

        $response = array();
        $db = new DbHandler();
        $result = $db->getMoviesByRating($args['ratingsabove']);

        if ($result != NULL) {
            $response['error'] = false;
            $response['movies'] = array();
            while ($movie = $result->fetch_assoc()) {
                $tmp = array();
                $tmp['ID'] = $movie['ID'];
                $tmp['MovieId'] = $movie['MovieId'];
                $tmp['Title'] = $movie['Title'];
                $tmp['VoteCnt'] = $movie['VoteCnt'];
                $tmp['VoteAvg'] = $movie['VoteAvg'];
                $tmp['Popularity'] = $movie['Popularity'];
                $tmp['Poster'] = $movie['Poster'];
                $tmp['Backdrop'] = $movie['Backdrop'];
                $tmp['Overview'] = $movie['Overview'];
                $tmp['ReleaseDay'] = $movie['ReleaseDay'];
                array_push($response['movies'], $tmp);
            }
            echo json_encode($response);
        } else {
            $response['error'] = true;
            $response['message'] = "There is no movie in database";
            echo json_encode($response);
        }
        $db->Close();
    });

/**
 * POST
 */
$app->post('/post', function ($request, $response, $args) {
    echo " This is a POST route . <br >";
    $data = $request->getParsedBody();
    echo $data['first']."<br >";
    echo $data['last'];
});

/**
 *  Get route to Delete
 */
$app->get(
    '/delete/id/{mid}',
    function(){
        echo '<h1> Android Programming </h1><br>';
        echo '<h2> Please specify Movie Id to be deleted from database</h2>';
        echo '<form action ="http://rahul-kadam.com/delete" method ="post">';
        echo '<label> Movie Id : </label>';
        echo '<input name ="movieid" type ="text"/> <br><br>';
        echo '<input type ="submit" value ="Delete"/> ';
        echo '</form>';
    });

/**
 *  Post route to Delete
 */
$app->post(
    '/delete',
    function($request, $response){
        $movie = $request->getParsedBody();
        $db = new DbHandler();
        $db->deleteMovieById($movie['movieid']);
    });


/**
 *  Get route to Add
 */
$app->get(
    '/add',
    function(){
        echo '<h1> Android Programming </h1><br>';
        echo '<h2> Please specify following details to add a movie to database</h2>';
        echo '<form action ="http://rahul-kadam.com/add" method ="post">';
        echo '<label> Movie Id : </label>';
        echo '<input name ="MovieId" type ="text"/> <br>';
        echo '<label> Title : </label>';
        echo '<input name ="Title" type ="text"/> <br>';
        echo '<label> VoteCnt : </label>';
        echo '<input name ="VoteCnt" type ="text"/> <br>';
        echo '<label> VoteAvg : </label>';
        echo '<input name ="VoteAvg" type ="text"/> <br>';
        echo '<label> Popularity : </label>';
        echo '<input name ="Popularity" type ="text"/> <br>';
        echo '<label> Poster : </label>';
        echo '<input name ="Poster" type ="text"/> <br>';
        echo '<label> Backdrop : </label>';
        echo '<input name ="Backdrop" type ="text"/> <br>';
        echo '<label> Overview : </label>';
        echo '<input name ="Overview" type ="text"/> <br>';
        echo '<label> ReleaseDay : </label>';
        echo '<input name ="ReleaseDay" type ="text"/> <br>';
        echo '<input type ="submit" value ="Add"/> ';
        echo '</form>';
    });

/**
 *  Post route to Add
 */
$app->post(
    '/add',
    function($request, $response){
        $movie = $request->getParsedBody();
        $db = new DbHandler();
        $db->addNewMovie($movie);
    });

// Run the Slim Application
$app->run();

?>