import axios from 'axios';

// const http = axios.create({
//     baseURL: "http://localhost:8080"
// });

export default {
    getAllCourses() {
        return axios.get('/courses');
    },

    getCourseById(id) {
        return axios.get(`/courses/${id}`);
    },

    // getAllScores() {
    //     return axios.get('/scoreboard')
    // },

    getScoresById(id) {
        return axios.get(`/scoreboard/${id}`);
    },

    getAllLeagues() {
        return axios.get('/leagues');
    },

    getLeagueById(id) {
        return axios.get(`/leagues/${id}`);
    },

    getLeaderboard() {
        return axios.get('/leaderboard');
    },

    getLeaderboardById(user_id) {
        return axios.get(`/leaderboard/${user_id}`);
    },

    addNewRound(round,user_id) {
        return axios.post(`/schedule-round/${user_id}`,round);
    },

    addNewCourse(course) {
        return axios.post('/courses', course);
    },

    createNewLeague(newLeague) {
        return axios.post('/leagues', newLeague);
    },

    addPersonalRound(id, round) {
        return axios.post(`/round/${id}`,round);
    },

    getAllUsers(){
        return axios.get('/all-users');
    },

    addUserToLeague(user_id,league_id){
        return axios.post(`/user-league/${user_id}/${league_id}`);
    },

    upcomingRounds(user_id){
        return axios.get(`/upcoming/${user_id}`);
    }
}
