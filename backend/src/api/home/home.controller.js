class HomeController {
    greeting(req, res) {
        console.log('this');
        return res.send('Hello');
    }
}

export default new HomeController();