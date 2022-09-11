const movieSearchBox = document.getElementById('movie-search-box');
const searchList = document.getElementById('search-list');
const resultGrid = document.getElementById('result-grid');

async function loadMovies(){
    var searchTerm = document.getElementById('movie-search-box').value;
    const URL = `http://localhost:8080/movie/byName?name=${searchTerm}&page=1&apikey=3fe95daa`;
    const res = await fetch(`${URL}`);
    const data = await res.json();
    console.log(data.Search);
    if(data.Response == "True") displayMovieList(data.Search);
}

function displayMovieList(movies){
    resultGrid.innerHTML ='';
    searchList.innerHTML = "";
    for(let idx = 0; idx < movies.length; idx++){
        let movieListItem = document.createElement('div');
        movieListItem.dataset.id = movies[idx].imdbID;
        movieListItem.classList.add('search-list-item');
        if(movies[idx].Poster != "N/A")
            moviePoster = movies[idx].Poster;
        else
            moviePoster = "image_not_found.png";

        movieListItem.innerHTML = `
        <div class = "search-item-thumbnail">
            <img src = "${moviePoster}">
        </div>
        <div class = "search-item-info">
            <h3>${movies[idx].Title}</h3>
            <p>${movies[idx].Year}</p>
        </div>
        `;
        searchList.appendChild(movieListItem);
    }
    loadMovieDetails();
}

function loadMovieDetails(){
    const searchListMovies = searchList.querySelectorAll('.search-list-item');
    searchListMovies.forEach(movie => {
        movie.addEventListener('click', async () => {
            searchList.innerHTML = '';
            movieSearchBox.value = "";
            const result = await fetch(`http://localhost:8080/movie/byId?id=${movie.dataset.id}`);
            const movieDetails = await result.json();
            console.log(movieDetails);
            displayMovieDetails(movieDetails);
        });
    });

}

function displayMovieDetails(details){
    resultGrid.innerHTML = `
    <div class = "movie-poster">
        <img src = "${(details.Poster != "N/A") ? details.Poster : "image_not_found.png"}" alt = "movie poster">
    </div>
    <div class = "movie-info">
        <h3 class = "movie-title">${details.Title}</h3>
        <ul class = "movie-misc-info">
            <li class = "year">Year: ${details.Year}</li>
            <li class = "rated">Rated: ${details.Rated}</li>
            <li class = "released">Released: ${details.Released}</li>
        </ul>
        <p class = "type"><b>Type:</b> ${details.Type}</p>
        <p class = "genre"><b>Genre:</b> ${details.Genre}</p>
        <p class = "writer"><b>Writer:</b> ${details.Writer}</p>
        <p class = "actors"><b>Actors: </b>${details.Actors}</p>
        <p class = "plot"><b>Plot:</b> ${details.Plot}</p>
        <p class = "language"><b>Language:</b> ${details.Language}</p>
        <p class = "boxOffice"><b>Box Office:</b> ${details.BoxOffice}</p>
        <p class = "country"><b>Country:</b> ${details.Country}</p>
        <p class = "metascore"><b>Metascore:</b> ${details.Metascore}</p>
        <p class = "runtime"><b>Runtime:</b> ${details.Runtime}</p>
        <p class = "imdbId"><b>Imdb Id:</b> ${details.imdbID}</p>
        <p class = "imdbVotes"><b>Imdb Votes:</b> ${details.imdbVotes}</p>
        <p class = "awards"><b>Awards: </b> ${details.Awards}</p>
    </div>
    `;
}
