import HomeService from './home.service.js';
import { HomeCreateDto } from './dtos/home-create.dto.js';
class HomeController {
    greeting(req, res) {
        const homeEntity = HomeCreateDto.toEntity(req.body);
        const result = HomeService.greeting(homeEntity);
        return res.status(200).json(result);
    }
}

export default new HomeController();
