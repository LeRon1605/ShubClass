class HomeService {
    greeting(entity) {
        return {
            message: `Hello ${entity.username}`
        };
    }
}

export default new HomeService();